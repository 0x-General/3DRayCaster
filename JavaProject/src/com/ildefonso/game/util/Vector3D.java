package com.ildefonso.game.util;

/**
 * Provides useful vector operations.
 * @author IldefonsoNB
 *
 */
public class Vector3D {

	public double x;
	public double y;
	public double z;
	
	/**
	 * Initialization of the vector.
	 * @param x initial X coordinate.
	 * @param y initial Y coordinate.
	 * @param z initial Z coordinate.
	 */
	public Vector3D(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Returns the length of the vector.
	 * @return length of the vector.
	 */
	public double length(){
		return Math.sqrt(x*x+ y*y + z*z);
	}
	
	/**
	 * Returns a vector in the same direction, but with size 1.
	 * @return Vector with the same direction, but with length 1.
	 */
	public Vector3D normalize(){
		double l = length();
		return new Vector3D(x/l, y/l, z/l);
	}
	
	/**
	 * Returns a vector perpendicular to this vector.
	 * @return Vector perpendicular (in the two dimensional scene)
	 */
	public Vector3D ortho() {
		return new Vector3D(-this.y,this.x,this.z);
	}
	
	/**
	 * Returns the scalar product.
	 * @param v input vector.
	 * @return Scalar product.
	 */
	public double dot(Vector3D v){
		return x * v.x + y*v.y + z * v.z;
	}
	
	/**
	 * Returns the cosine of the angle.
	 * @param v Input vector.
	 * @return Cosine of the angle between the vectors.
	 */
	public double cosAngle(Vector3D v) {
		return this.dot(v)/(this.length()*v.length());
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(" + x + "," + y + "," + z + ")";
	}
	
	/**
	 * Rotates the vector a given amount of degrees.
	 * @param angle Angle of rotation
	 */
	public void rotate(double angle) {
		double oldX = x;
		double oldY = y;
		
		x = oldX*Math.cos(angle) - oldY * Math.sin(angle);
		y = oldY*Math.cos(angle) + oldX * Math.sin(angle);
	}
}