package GameApp;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import GameFramework.Game;
import GameFramework.GameBoard;
import GameFramework.Window;

public class BomberWindow extends Window{

	/**
	 * @invariant state == 1 || state == 0
	 */
	static final int PLAYER1WIN = 1;
	static final int PLAYER2WIN = 2;
	int state;
	JLabel label = new JLabel();
	
	/**
	 * constructor
	 * @param height: height of the board
	 * @param width: width of the board
	 * @param game: the board
	 */
	public BomberWindow(int height, int width, Game game) {
		super(height, width, game);
		state = 0;
		label.setFont(new Font(label.getName(),Font.BOLD, 40));
		//label.setPreferredSize(new Dimension(width/2, height));
		this.setLayout(new GridBagLayout());
        this.add(label);
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	}
	
	/**
	 * paint the wining screen
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if(state == PLAYER1WIN) {
			
			
			label.setText("<html><h1><center>PLAYER 1 WINS <center/></h1></html>");
		}
		else if(state == PLAYER2WIN)
		{
			label.setText("<html><h1><center>PLAYER 2 WINS <center/></h1></html>");
		}
		else
		{
			label.setText("");
		}
		
		
	}
	
	/**
	 * update the observer
	 */
	@Override
	public void updateSignal()
	{
		BomberMan b = (BomberMan)game;
		state = b.getState();
		repaint();
	}
	

	
	
}
