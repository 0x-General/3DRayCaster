package com.ildefonso.game.graphics;

import javax.swing.JFrame;

import com.ildefonso.game.input.Keyboard;

/**
 * This class is a wrapper over {@link javax.swing.JFrame}, it also contains a {@link com.ildefonso.game.graphics.Screen} object which is used for drawing.
 * @author IldefonsoNB
 *
 */
public class Window extends JFrame{

	private int width;
	private int height;
	public Screen screen;
	public Keyboard keyboard;
	private final String TITLE;
		
	/**
	 * Creating the {@link com.ildefonso.game.graphics.Screen} and 
	 * {@link com.ildefonso.game.input.Keyboard} objects.
	 * @param width Initial window width.
	 * @param height Initial window height.
	 * @param title Window title.
	 */
	public Window(int width, int height, String title){
		this.width = width;
		this.height = height;
		this.TITLE = title;
		screen = new Screen(width, height);
		keyboard = new Keyboard();
		init();
	}
	
	/**
	 * Called every frame update. Calls render method of the screen object.
	 */
	public void render(){
		screen.render();
	}
	/**
	 * Called every frame update. Calls update method of the screen object.
	 */
	public void update(){
		screen.update();
	}
	
	private void init(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(width, height);
		this.setTitle(TITLE);
		this.add(screen);
		this.addKeyListener(keyboard);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}