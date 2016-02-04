import java.awt.Color;
import java.awt.Graphics;
import java.security.SecureRandom;

import javax.swing.*;

/**
 * This class creates the planets and implements runnable
 * 
 * @author rbanda
 *
 */
public class Planet implements Runnable {
	/**
	 * x-coordinate of first endpoint
	 */
	private int x; 
	/**
	 * y-coordinate of first endpoints
	 */
	private int y; 
	/**
	 * color of this line
	 */
	private Color color;
	/**
	 * variable for planet panel
	 */
	private PlanetPanel planet;
	/**
	 * holds radius
	 */
	private int radius;
	/**
	 * holds pi
	 */
	private double theta;
	/**
	 * holds the stars size
	 */
	private int starSize;
	/**
	 * This constructor creates the planet
	 * @param radius
	 * 			takes in the size of the radius and sets it
	 * @param starSize
	 * 			takes in the size of the star
	 * @param theta
	 * 			takes in pi
	 * @param color
	 * 			takes in the color
	 * @param planet
	 * 			takes in planet
	 */
	public Planet(int radius, int starSize, double theta, Color color, PlanetPanel planet) {
		// TODO Auto-generated constructor stub

		this.x = (int) (starSize + radius * Math.cos(theta));
		this.y = (int) (starSize + radius * Math.sin(theta));
		this.color = color;
		this.theta = theta;
		this.planet = planet;
		this.radius = radius;
		this.starSize = starSize;
	}
	/**
	 * This method calculates the path of motion
	 * @param theta
	 * 			takes in pi
	 */
	public void motion(double theta) {
		this.theta = theta;
		x = (int) (starSize + radius * Math.cos(theta) - 5);
		y = (int) (starSize + radius * Math.sin(theta) - 5);
	}
	/**
	 * This method creates the graphic and sets the color of it
	 * @param g
	 * 		creates planet
	 */
	public void create(Graphics g) {
		g.setColor(color);
		g.fillOval((int) x, (int) y, 15, 15);
	}
	/**
	 * this method tells what each thread should do
	 */
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {

				motion(theta + 0.0174533);
				planet.repaint();
				Thread.sleep(20);

			} catch (InterruptedException e) {
				e.printStackTrace();
				Thread.currentThread().interrupt(); // re-interrupt the thread
			}
		}

	}

}
