package GameFramework;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class GameBoard {
	/**
	 * @variant height > 0 && width > 0
	 */
	int height;
	int width;
	Image im;
	ArrayList<Actor> actors ;
	Actor[][] board;
	
	/**
	 * constructor
	 * @param x: width of the board
	 * @param y: height of the board
	 */
	public GameBoard(int x, int y) {
		height = y;
		width = x;
		board = new Actor[x][y];
		setImage("imgSrc/blank.png");
		actors = new ArrayList<Actor>();
	}
	/**
	 * add player actor in a list
	 * @param a: players
	 */
	public void addActor(Actor a)
	{
		actors.add(a);
	}
	
	/**
	 * get actors
	 * @return return a list of actor
	 */
	public ArrayList<Actor> getActor()
	{
		return actors;
	}
	
	/**
	 * set an actor in the board
	 * @param actor: actor to set
	 * @param x: x position
	 * @param y: y position
	 */
	public void setBoard(Actor actor, int x, int y)
	{
		board[x][y] = actor;
	}
	/**
	 * 
	 * @param x: x position
	 * @param y: y position
	 * @return actor of that position
	 */
	public Actor getBoard(int x, int y)
	{
		return board[x][y];
	}
	/**
	 * get height of board
	 * @return height
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * get width of the board
	 * @return width
	 */
	public int getWidth() {
		return width;
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
