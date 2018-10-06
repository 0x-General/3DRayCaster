package com.ildefonso.game.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Simple class for getting keyboard input.
 * @author IldefonsoNB
 *
 */
public class Keyboard implements KeyListener{

	public static boolean up;
	public static boolean down;
	public static boolean right;
	public static boolean left;
	public static boolean rotateRight;
	public static boolean rotateLeft;
	
	@Override
	public void keyPressed(KeyEvent k) {
		// TODO Auto-generated method stub
		if (k.getKeyCode() == KeyEvent.VK_UP || k.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_DOWN || k.getKeyCode() == KeyEvent.VK_S) {
			down = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_RIGHT || k.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_LEFT || k.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_Q) {
			rotateLeft = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_E) {
			rotateRight = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent k) {
		// TODO Auto-generated method stub
		if (k.getKeyCode() == KeyEvent.VK_UP || k.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_DOWN || k.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_RIGHT || k.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_LEFT || k.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_Q) {
			rotateLeft = false;
		}
		if (k.getKeyCode() == KeyEvent.VK_E) {
			rotateRight = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}