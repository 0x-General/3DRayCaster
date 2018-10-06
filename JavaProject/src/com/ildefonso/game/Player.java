package com.ildefonso.game;

import com.ildefonso.game.graphics.Screen;
import com.ildefonso.game.input.Keyboard;
import com.ildefonso.game.util.Vector3D;

/**
 *
 * Stores information about the player; its position, and where the player is looking at.
 * @author IldefonsoNB
 *
 *
 */
public class Player {


	public Vector3D position;
	public Vector3D glance;
	
	private World world;
	private double velocity = 0.025;
	private double rotationAngle = 0.01;
	
	/**
	 * This method draws vertical lines, one for every column of pixels in the window.
	 * @param Used for drawing.
	 */
	private void castRays(Screen screen) {
		//Every ray is represented by a vector
		Vector3D ray;
		//This vector is perpendicular to the players glance
		//and represents the sccreen
		Vector3D screenVector = this.glance.ortho();
		
		////////////////////////////////////////
		//Debugging
		Vector3D v0 = new Vector3D(position.x + glance.x*100,position.y + glance.y*100,position.z);
		Vector3D v1 = new Vector3D(v0.x + screenVector.x*100, v0.y + screenVector.y*100, v0.z);
		
		//screen.drawLine(v0, v1, 0x00FFFF);
		////////////////////////////////////////
		
		for(int i= 0; i < screen.width; ++i) {
			//casting rays
			double c = (double)i/(double)screen.width;
			ray = new Vector3D(c*screenVector.x + glance.x- screenVector.x/2,
					c*screenVector.y + glance.y- screenVector.y/2
					,0);
			double currentX = position.x;
			double currentY = position.y;
			double m = ray.y/ray.x;
			
			double dX;
			double dY;
			
			boolean collision = false;
			
			int xIndex = (int) position.x;
			int yIndex = (int) position.y;
			int color = 0x000000;
			
			double xTextCoord = 0;
			
			while(!collision) {
				//First cuadrant
				if (ray.x > 0 && ray.y > 0) {
					dX = xIndex + 1 - currentX;
					dY = yIndex + 1 - currentY;

					if (m*dX > dY) {
						//collided above
						yIndex++;

						if (world.solidAt(xIndex, yIndex)) {
							color = world.tileColor(xIndex, yIndex);
							collision = true;
						}
						currentX = currentX + dY / m;
						currentY = yIndex;
//						xTextCoord = w.wood.width - (currentX - xIndex) * w.wood.width;
					}
					else {
						//collided right
						xIndex++;
						if (world.solidAt(xIndex, yIndex)) {
							collision = true;
							color = world.tileColor(xIndex, yIndex);
						}

						currentX = xIndex;
						currentY = currentY + dX*m;
//						xTextCoord = (currentY - yIndex) * w.wood.width;
					}
				}
				//Second cuadrant
				else if (ray.x <= 0 && ray.y > 0) {
					dX = xIndex - currentX;
					dY = yIndex + 1 - currentY;
					if (m*dX > dY) {
						//Collided above
						yIndex++;
						if (world.solidAt(xIndex, yIndex)) {
							collision = true;
							color = world.tileColor(xIndex, yIndex);
						}
						currentX = currentX + dY / m;
						currentY = yIndex;
//						xTextCoord = w.wood.width - (currentX - xIndex) * w.wood.width;
					}
					else {
						//Collided left
						currentX = xIndex;
						xIndex--;
						if (world.solidAt(xIndex, yIndex)) {
							collision = true;
							color = world.tileColor(xIndex, yIndex);
						}
						currentY = currentY + dX*m;
//						xTextCoord = w.wood.width - (currentY - yIndex) * w.wood.width;
					}
				}
				
				
				//Third cuadrant
				else if (ray.x <= 0 && ray.y <= 0) {
					dX = xIndex - currentX;
					dY = yIndex - currentY;
					if (m*dX <= dY) {
						//Collided below
						currentY = yIndex;
						yIndex--;
						if (world.solidAt(xIndex, yIndex)) {
							collision = true;
							color = world.tileColor(xIndex, yIndex);
						}
						currentX = currentX + dY / m;
//						xTextCoord = (currentX - xIndex) * w.wood.width;
					}
					else {
						//Collided left
						currentX = xIndex;
						xIndex--;
						if (world.solidAt(xIndex, yIndex)) {
							collision = true;
							color = world.tileColor(xIndex, yIndex);
						}
						currentY = currentY + dX*m;
//						xTextCoord = w.wood.width - (currentY - yIndex) * w.wood.width;
					}
				}

				//Fourth cuadrant
				else {
					dX = xIndex + 1 - currentX;
					dY = yIndex - currentY;
					if (m*dX <= dY) {
						//Collided below
						currentY = yIndex;
						yIndex--;
						if (world.solidAt(xIndex, yIndex)) {
							collision = true;
							color = world.tileColor(xIndex, yIndex);
						}
						currentX = currentX + dY / m;
//						xTextCoord = (currentX - xIndex) * w.wood.width;
					}
					else {
						//Collided right
						xIndex++;
						currentX = xIndex;
						if (world.solidAt(xIndex, yIndex)) {
							collision = true;
							color = world.tileColor(xIndex, yIndex);
						}
						currentY = currentY + dX*m;
//						xTextCoord = (currentY - yIndex) * w.wood.width;
					}
				}
				if (color != 0x000000) {
					double z = new Vector3D(currentX - position.x, currentY - position.y,0).length();
					double cosAngle = glance.cosAngle(ray);
					z *= cosAngle;
					screen.drawLine(i,(int)(screen.height/2 - World.blockSize/z),i, (int)(screen.height/2 + World.blockSize/z), color);
//					drawStripe(i, xTextCoord, height / (2 * pixelSize) - w.block_size / (z*pixelSize), height / (2 * pixelSize) + w.block_size / (z*pixelSize), w.wood);
				}
			}
			
		}
		
	}
	
	/**
	 * Initializes the players position and glance.
	 * @param p Initial position
	 * @param g Initial glance
	 * @param world	World instance
	 */
	public Player(Vector3D p, Vector3D g, World world) {
		position = new Vector3D(p.x, p.y, p.z);
		glance = g.normalize();
		this.world = world;
	}
	
	/**
	 * Called every frame update.
	 * @param screen Used for drawing.
	 */
	public void render(Screen screen) {
		castRays(screen);
	}
	
	/**
	 * Called every frame update. Responsible for moving the player around according to user input.
	 */
	public void update() {
		if(Keyboard.up){
			moveForward();
		}
		if(Keyboard.down){
			moveBackwards();
		}
		if(Keyboard.left){
			moveLeft();
		}
		if(Keyboard.right) {
			moveRight();
		}
		if(Keyboard.rotateLeft) {
			glance.rotate(-rotationAngle);
			glance = glance.normalize();
		}
		if(Keyboard.rotateRight) {
			glance.rotate(rotationAngle);
			glance = glance.normalize();
		}
	}
	
	private void moveForward() {
		
		double deltaX = glance.x*velocity;
		double deltaY = glance.y*velocity;
		
		//Collision detection
		if(world.solidAt((int)(position.x +deltaX*2), (int)(position.y)))deltaX = 0;
		if(world.solidAt((int)(position.x), (int)(position.y+deltaY*2)))deltaY = 0;
		
		position.x += deltaX;
		position.y += deltaY;
	}
	
	private void moveBackwards() {
		
		double deltaX = glance.x*velocity;
		double deltaY = glance.y*velocity;
		
		//Collision detection
		if(world.solidAt((int)(position.x -deltaX*2), (int)(position.y)))deltaX = 0;
		if(world.solidAt((int)(position.x), (int)(position.y-deltaY*2)))deltaY = 0;
		
		position.x -= deltaX;
		position.y -= deltaY;
	}
	
	private void moveRight() {
		
		double deltaX = glance.x*velocity;
		double deltaY = glance.y*velocity;
		
		//Collision detection
		if(world.solidAt((int)(position.x -deltaY*2), (int)(position.y)))deltaY = 0;
		if(world.solidAt((int)(position.x), (int)(position.y + deltaX*2)))deltaX = 0;
		
		position.x -= deltaY;
		position.y += deltaX;
	}
	
	private void moveLeft() {
		
		double deltaX = glance.x*velocity;
		double deltaY = glance.y*velocity;
		
		//Collision detection
		if(world.solidAt((int)(position.x + deltaY*2), (int)(position.y)))deltaY = 0;
		if(world.solidAt((int)(position.x), (int)(position.y - deltaX*2)))deltaX = 0;
		
		position.x += deltaY;
		position.y -= deltaX;
	}
	
	/**
	 * Used for respawning the player at a given position.
	 * @param v New players position
	 */
	public void respawn(Vector3D v) {
		position = new Vector3D(v.x,v.y,v.z);
	}	
}
