import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.security.SecureRandom;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JPanel;
/**
 * This class creates the GUI extends JPanels
 * @author rohit
 *
 */
public class PlanetPanel extends JPanel{
	/**
	 * sets the number of planets
	 */
	private int size = 8;
	/**
	 * creates array of plantes of size
	 */
	private Planet planetArray[] = new Planet[size];
	/**
	 * counter
	 */
	private int count = 0;
	/**
	 * coordinate of the star
	 */
	private int starCoord = 380;
	/**
	 * instantiates the ExecutorService
	 */
	private ExecutorService xecutorService = Executors.newCachedThreadPool();
	/**
	 * hold the value of pi
	 */
	private double theta = Math.PI;
	/**
	 * sets the radius to 80
	 */
	private int radius = 80;
	/**
	 * this method adds each planet to the planet array and has a mouse listener added to the panel
	 */
	public PlanetPanel() {
		// TODO Auto-generated constructor stub
		Random randomNumbers = new Random();
		setBackground(Color.BLACK);
		for (int i = 0; i < size; i++) {
			Color color = new Color(randomNumbers.nextInt(230), randomNumbers.nextInt(240), randomNumbers.nextInt(220));
			planetArray[i] = new Planet(radius, starCoord, theta, color, this);
			radius += 40;
		}
		addMouseListener(new MouseClickHandler());

	}
	/**
	 * This class handles the mouseclick and add planet to panel by launching each of the threads
	 * @author rohit
	 *
	 */
	private class MouseClickHandler extends MouseAdapter {
		public void mouseClicked(MouseEvent event) {
			if (count < size) {
				xecutorService.execute(planetArray[count]);
				count++;

			}
		}
	}
	/**
	 * This method takes in the planet and paints it to the panel
	 * @param g
	 * 			takes in planet
	 */
	public synchronized void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.YELLOW);
		g.fillOval(starCoord, starCoord, 40, 40);
		for (int i = 0; i < count; i++) {
			planetArray[i].create(g);
		}

	}

}
