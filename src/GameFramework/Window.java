package GameFramework;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JPanel implements GameObserver,KeyListener,MouseListener{
	/**
	 * @invariant height > 0 && width > 0
	 */
	private int height;
	private int width;
	private Image im;
	protected Game game;
	
	/**
	 * constructor
	 * @param height: height of the board
	 * @param width: width of the board
	 * @param game: the gameboard
	 */
	public Window(int height, int width, Game game) {
		this.height = height;
		this.width = width;
		this.setPreferredSize(new Dimension(width, height));
		this.game = game;
		addKeyListener(this);
		setFocusable(true);
	}
	
	/**
	 * handle key inputs
	 */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();
		
		switch(key) {
			case KeyEvent.VK_LEFT:
				game.left1Pressed();
				break;
			case KeyEvent.VK_RIGHT:
				game.right1Pressed();
				break;
			case KeyEvent.VK_UP:
				game.up1Pressed();
				break;
			case KeyEvent.VK_DOWN:
				game.down1Pressed();
				break;
			case KeyEvent.VK_CONTROL:
				game.action1Pressed();
				break;
			case KeyEvent.VK_A:
				game.left2Pressed();
				break;
			case KeyEvent.VK_D:
				game.right2Pressed();
				break;
			case KeyEvent.VK_W:
				game.up2Pressed();
				break;
			case KeyEvent.VK_S:
				game.down2Pressed();
				break;
			case KeyEvent.VK_SHIFT:
				game.action2Pressed();
				break;
			default:
					break;

		}

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * handle mouse input
	 */
	public void mousePressed(MouseEvent e) {
		Point p = e.getPoint();
		int xTile = p.x/game.getBoard().getWidth();
		int yTile = p.y/game.getBoard().getHeight();
		game.mousePressed(xTile, yTile);
	}
	
	/**
	 * paint the board 
	 */
	public void paintComponent(Graphics g) {
		
		super.paintComponents(g);
		Graphics2D g2 = (Graphics2D)g;
		int x = game.getBoard().getWidth();
		int y = game.getBoard().getHeight();
		int xSpace = width/x;
		int ySpace = height/y;
		for(int i = 0; i < width; i=i+xSpace) {
			for(int j = 0; j < height; j=j+ySpace) {
				g.drawImage(game.getBoard().getImage(), i, j, xSpace, ySpace, null);
			}
		}
		
		for(int i = 0; i < game.getBoard().getWidth(); i++)
		{
			for(int j = 0; j < game.getBoard().getHeight(); j++)
			{
				Actor a = game.getBoard().getBoard(i, j);
				if(a != null)
				{
					g.drawImage(a.getImage(), (i*xSpace), (j*ySpace), xSpace, ySpace, null);
				}	
			}
		}
		
		ArrayList<Actor> list = game.getBoard().getActor();
		for(int i = 0; i < list.size(); i++)
		{
			Actor a = list.get(i);
			g.drawImage(a.getImage(), xSpace*a.getX(), ySpace*a.getY(), xSpace, ySpace, null);
		}
	}

	/**
	 * call on repaint()
	 */
	public void updateSignal() {
		repaint();
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
