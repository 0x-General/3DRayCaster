package com.ildefonso.game;

import java.io.BufferedReader;
import java.io.FileReader;

import com.ildefonso.game.graphics.Screen;

/**
 * Stores information about the world map.
 * @author IldefonsoNB
 *
 */
public class World {

	public static double blockSize = 100;
	public static String world01File = "res/world01.txt";
	
	
	/**
	 * The type of "blocks" that are possible in the world map.
	 * @author IldefonsoNB
	 *
	 */
	public enum Tile{
		VOID, WOOD, ROCK;
		public boolean isSolid() {
			if(this.equals(VOID))return false;
			else return true;
		}
	}
	
	public int width;
	public int height;
	private Tile [][] tiles;
	
	/**
	 * Reading data from input file, and storing it into an array.
	 * @param world Input file.
	 */
	private void readWorld(String world) {
		try (BufferedReader br = new BufferedReader(new FileReader(world))){
		    StringBuilder sb = new StringBuilder();

		    String line = null;
		    int i = 0;
		    
		    this.width = Integer.parseInt(br.readLine());
		    this.height = Integer.parseInt(br.readLine());
		    
		    this.tiles = new Tile[width][height];
		    
		    while ((line = br.readLine()) != null) {
		        
		    	for(int j = 0; j < line.length(); ++j) {
		    		
		    		switch(line.charAt(j)) {
		    		case '0':
		    			tiles[i][j] = Tile.VOID;
		    			break;
		    		case '1':
		    			tiles[i][j] = Tile.WOOD;
		    			break;
		    		case '2':
		    			tiles[i][j] = Tile.ROCK;
		    			break;
		    			default:
		    			tiles[i][j] = Tile.VOID;
		    		}
		    		
		    	}
		    	
		        i++;
		    }
		    String everything = sb.toString();
		}catch(Exception e) {
			System.out.println("Could not read world file!");
			System.exit(0);
		}
	}
	
	/**
	 * Returns the type of the tile located at a given position.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return type of a tile at a given position.
	 */
	public Tile getTileAt(int x, int y) {
		if(x < 0 || x >= width || y < 0 || y >= height)return Tile.WOOD;
		return tiles[x][y];
	}
	
	/**
	 * Returns true if the tile at a given position is solid, false otherwise.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return returns true if the tile corresponds to a solid tile, false otherwise.
	 */
	public boolean solidAt(int x, int y) {
		if(getTileAt(x, y) == Tile.VOID)return false;
		else return true;
	}
	
	/**
	 * Returns the color of the tile at a given position.
	 * @param x X coordinate.
	 * @param y Y coordinate.
	 * @return color of a given tile.
	 */
	public int tileColor(int x, int y) {
		switch(getTileAt(x, y)) {
		case WOOD:
			return 0xF4A460;
		case ROCK:
			return 0x909090;
			default:
			return 0x000000;
		}
	}
	
	/**
	 * Initializing the world according to the input file.
	 * @param world Input file.
	 */
	public World(String world) {
		
		readWorld(world);
	}
	
	private void debugPrint() {
		for(int y = 0; y < height; ++y) {
			for(int x = 0; x < width; ++x) {
				if(tiles[x][y] == Tile.WOOD)System.out.print(1);
				else if(tiles[x][y] == Tile.VOID)System.out.print(0);
			}
			System.out.println();
		}
	}
}
