package com.ildefonso.game.graphics;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.JPanel;

import com.ildefonso.game.Player;
import com.ildefonso.game.World;
import com.ildefonso.game.input.Keyboard;
import com.ildefonso.game.util.Vector3D;

/**
 * This class is used for drawing.
 * @author IldefonsoNB
 *
 */
public class Screen extends JPanel {

	public int width;
	public int height;
	private Player player;
	private World world;

	BufferedImage img;
	private int[] pixels;

	/**
	 * Clears the screen to a given color
	 * @param color Color to be used.
	 */
	public void clear(int color) {
		for (int i = 0; i < pixels.length; ++i) {
			pixels[i] = color;
		}
	}
	
	/**
	 * Draws a pixel with a given color at a given position
	 * 
	 * @param x X coordinate
	 * @param y Y coordinate
	 * @param color Color of the pixel
	 */
	public void drawPixel(int x, int y, int color) {
		int index = y * width + x;
		if (x < 0 || x >= width || y < 0 || y >= height)
			return;

		pixels[index] = color;
	}
	
	/**
	 * Draws a line of a given color from an initial point to a final point
	 * 
	 * @param v0 Initial coordinates
	 * @param v1 Final coordinates
	 * @param color Color of the line
	 */
	public void drawLine(Vector3D v0, Vector3D v1, int color) {
		drawLine((int)v0.x,(int)v0.y,(int)v1.x,(int)v1.y,color);
	}
	
	/**
	 * Draws a line of a given color from an initial point to a final point.
	 * @param x0 Initial X coordinate.
	 * @param y0 Initial Y coordinate.
	 * @param x1 Final X coordinate.
	 * @param y1 Final Y coordinate.
	 * @param color Color of the line.
	 */
	public void drawLine(int x0, int y0, int x1, int y1, int color) {
		
		int initialY = y0 > y1 ? y1 : y0;
		int finalY = y0 > y1 ? y0 : y1;
		int initialX = x0 > x1 ? x1 : x0;
		int finalX = x0 > x1 ? x0 : x1;
		
		if(initialX < 0)initialX = 0;
		if(initialY < 0)initialY = 0;
		if(initialX >= width) initialX = width;
		if(initialY >= height) initialY = height;
		
		// Vertical lines
		if (x0 == x1) {
			for (int i = initialY; i < finalY; ++i)
				drawPixel(x0, i, color);
			return;
		}

		// Horizontal lines
		if (y1 == y0) {
			for (int i = initialX; i < finalX; ++i)
				drawPixel(i, y0, color);
			return;
		}
		
		//General case
			//deltas are always possitive
			double deltaX = x1 - x0;
			double deltaY = y1 - y0;
			
			//we step through X
			if(Math.abs(deltaX) >= Math.abs(deltaY)) {
				if(initialX == x0)initialY = y0;
				else initialY = y1;
				
				//No division by zero will occur
				double m = deltaY/deltaX;
				
				for(int x = initialX; x <= finalX; ++x) {
					drawPixel(x,(int)(initialY + m*(x-initialX)), color);
				}
			}
			//we step through Y
			else {
				if(initialY == y0)initialX = x0;
				else initialX = x1;
				
				//No division by zero will occur
				double m = deltaX/deltaY;
				for(int y = initialY; y <= finalY; ++y) {
					drawPixel((int)(initialX + m*(y-initialY)),y, color);
				}
			}
	}

	/**
	 * Called every frame update.
	 */
	public void render() {
		clear(0x000000);
		player.render(this);
		this.repaint();
	}
	
	/**
	 * Called every frame update.
	 */
	public void update(){
		player.update();
	}

	/**
	 * Initializing the Screen to a given width and height.
	 * @param width Screen width.
	 * @param height Screen height.
	 */
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setDoubleBuffered(true);
		world = new World(World.world01File);
		player = new Player(new Vector3D(5,5,0),new Vector3D(1,2,0),world);
		img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) (img.getRaster().getDataBuffer())).getData();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		g.drawImage(img, 0, 0, img.getWidth(), img.getHeight(), null);
	}
}