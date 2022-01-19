package GameFramework;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Actor{
	/**
	 * @invariant x > 0 && y > 0
	 */
	protected int x;
	protected int y;
	private Image im;
	
	/**
	 * constructor
	 * @param x: x position
	 * @param y: y position
	 */
	public Actor(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * get x position
	 * @return x position
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * get y position
	 * @return y position
	 */
	public int getY() {
		return y;
	}
	/**
	 * get image
	 * @return image
	 */
	public Image getImage() {
		return im;
	}
	
	/**
	 * set image from file
	 * @param path: file name 
	 */
	public void setImage(String path) {
		try {
			im = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
