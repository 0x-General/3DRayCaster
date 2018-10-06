
/**
 * com.ildefonso.game Contains the main functional classes for the game
 */
package com.ildefonso.game;

import com.ildefonso.game.graphics.Window;

/**
 * This is the entry point of the application.
 * It contains a static {@link com.ildefonso.game.graphics.Window} object.
 */
public class Main {

	private static Window w;
	
	/**
	 * Repeatedly calls {@link com.ildefosno.game.graphics.Window.update()}} and
	 * {@link com.ildefonso.game.graphics.Window.render()}
	 */
	private static void gameLoop(){
		while (true) {
				w.update();
				w.render();
				
			try {
				Thread.sleep(10);
			} catch (Exception e) {
			}
		}
	}
	
	/**
	 * Initialization of the game, and starting game loop.
	 * @param args Not used.
	 */
	public static void main(String[] args) {
		w = new Window(600, 400, "3D ray caster");
		gameLoop();
	}
}
