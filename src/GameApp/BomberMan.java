package GameApp;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import GameFramework.Actor;
import GameFramework.Game;
import GameFramework.GameBoard;
import GameFramework.GameObserver;



public class BomberMan extends Game
{
	static final int NEUTRAL = 0;
	static final int LEFT = 1;
	static final int RIGHT = 2;
	static final int UP = 3;
	static final int DOWN = 4;
	static final int ACTION = 5;
	static final int PLAYER1WIN = 1;
	static final int PLAYER2WIN = 2;
	static final int RUNNING = 0;
	
	private Player player1,player2;
	//private GameBoard board;
	private int move,move2;
	//private GameObserver obs;
	private int state = RUNNING;
	private boolean reset = false;
	
	/**
	 * contructor for BomberMan
	 * call on superclass and initGame()
	 */
	public BomberMan()
	{
		super(15, 15);
		initGame();
	}
	

	/**
	 * set up a new game
	 */
	public void initGame()
	{
		board = new GameBoard(15,15);
		player1 = new Player(13,13);
		player2 = new Player(1,1);
		player2.setImage("imgSrc/Player2.png");
		board.addActor(player1);
		board.addActor(player2);
		placeWalls();
		setBoxes();
	}
	
	/**
	 * handle collision and user input
	 * update the game
	 */
	public void refresh() {
		//board.setActor(null, player1.getX(), player1.getY());
		//board.setActor(null, player2.getX(), player2.getY());
		if(state == RUNNING) 
		{
			//System.out.println("running");
			if(move == LEFT) {
				int x = player1.getX();
				int y = player1.getY();
				Actor a = board.getBoard(x-1, y);
				if(a == null || a.getClass() == Fire.class)
				{
					player1.move(LEFT);
					move = NEUTRAL;
				}
				
			}
			else if(move == RIGHT) {
				int x = player1.getX();
				int y = player1.getY();
				Actor a = board.getBoard(x+1, y);
				if(a == null || a.getClass() == Fire.class)
				{
					player1.move(RIGHT);
					move = NEUTRAL;
				}
			}
			else if(move == UP)
			{
				int x = player1.getX();
				int y = player1.getY();
				Actor a = board.getBoard(x, y-1);
				if(a == null || a.getClass() == Fire.class)
				{
				player1.move(UP);
				move = NEUTRAL;
				}
			}
			else if(move == DOWN)
			{
				int x = player1.getX();
				int y = player1.getY();
				Actor a = board.getBoard(x, y+1);
				if(a == null || a.getClass() == Fire.class)
				{
				player1.move(DOWN);
				move = NEUTRAL;
				}
			}
			else if(move == ACTION)
			{
				board.setBoard(new Dynamite(player1.getX(), player1.getY()), player1.getX(), player1.getY());
				move = NEUTRAL;
			}
			
			if(move2 == LEFT) {
				int x = player2.getX();
				int y = player2.getY();
				Actor a = board.getBoard(x-1, y);
				if(a == null || a.getClass() == Fire.class)
				{
					player2.move(LEFT);
					move2 = NEUTRAL;
				}
				
			}
			else if(move2 == RIGHT) {
				int x = player2.getX();
				int y = player2.getY();
				Actor a = board.getBoard(x+1, y);
				if(a == null || a.getClass() == Fire.class)
				{
					player2.move(RIGHT);
					move2 = NEUTRAL;
				}
			}
			else if(move2 == UP)
			{
				int x = player2.getX();
				int y = player2.getY();
				Actor a = board.getBoard(x, y-1);
				if(a == null || a.getClass() == Fire.class)
				{
					player2.move(UP);
					move2 = NEUTRAL;
				}
			}
			else if(move2 == DOWN)
			{
				int x = player2.getX();
				int y = player2.getY();
				Actor a = board.getBoard(x, y+1);
				if(a == null || a.getClass() == Fire.class)
				{
					player2.move(DOWN);
					move2 = NEUTRAL;
				}
			}
			else if(move2 == ACTION)
			{
				board.setBoard(new Dynamite(player2.getX(), player2.getY()), player2.getX(), player2.getY());
				move2 = NEUTRAL;
			}
			
			dynamiteCheck();
			fireCheck();
			
			
			
			//board.setActor(player1, player1.getX(), player1.getY());
			//board.setActor(player2, player2.getX(), player2.getY());
		}
		else if(state == PLAYER1WIN || state == PLAYER2WIN)
		{
			if(reset)
			{
				
				reset = false;
				state = RUNNING;
				initGame();
				
			}
			
		}
		//obs.updateSignal();
		move = NEUTRAL;
		move2 = NEUTRAL;
		
	}
	
	/**
	 * get game state
	 * @return state
	 */
	public int getState()
	{
		return state;
	}
	
	/**
	 * update all fire duration in the board 
	 */
	private void fireCheck()
	{
		for(int i = 0; i<board.getWidth();i++)
		{
			for(int n = 0; n < board.getHeight();n++)
			{
				Actor a = board.getBoard(i, n);
				if(a != null && a.getClass() == Fire.class)
				{
					Fire f  = (Fire)a;
					f.decDuration();
					if(player1.getX() == i && player1.getY() == n)
					{
						state = PLAYER2WIN;
					}
					if(player2.getX() == i && player2.getY() == n)
					{
						state = PLAYER1WIN;
					}
					if(f.getDuration() == 0)
					{
						board.setBoard(null, i, n);
					}
				
				}
			}
		}
	
	}
	
	/**
	 * update all dynamite duration and trigger dynamite if duration == 0
	 */
	private void dynamiteCheck()
	{
		for(int x = 0; x < board.getWidth(); x++)
		{
			for(int y = 0; y < board.getHeight(); y++)
			{
				Actor a = board.getBoard(x, y);
				if(a != null && a.getClass() == Dynamite.class)
				{
					Dynamite d = (Dynamite)a;
					d.decDuration();
					if(d.getDuration() <= 0)
					{
						triggerDynamite(d);
					}
				}
			}
		}
	}
	
	/**
	 * make the dynamite explode and check for possible chain explosion
	 * @param d: dynamite that duration == 0
	 */
	private void triggerDynamite(Dynamite d)
	{
		board.setBoard(null, d.getX(), d.getY());
		Actor[] neightbours = getNeighbours(d.getX(), d.getY());
		for(Actor a: neightbours)
		{
			if(a.getClass() == Box.class)
			{
				board.setBoard(null, a.getX(), a.getY());
			}
			else if(a.getClass() == Dynamite.class)
			{
				Dynamite dy = (Dynamite)a;
				triggerDynamite(dy);
			}
		}
		addFire(d.getX(), d.getY());
	}
	
	/**
	 * paint fire in all four direction
	 * @param x: x position
	 * @param y: y position
	 */
	private void addFire(int x, int y)
	{
		board.setBoard(new Fire(x,y), x, y);
		if(board.getBoard(x-1, y) == null)
		{
			board.setBoard(new Fire(x-1,y), x-1, y);
		}
		if(board.getBoard(x+1, y) == null)
		{
			board.setBoard(new Fire(x+1,y), x+1, y);
		}
		if(board.getBoard(x, y-1) == null)
		{
			board.setBoard(new Fire(x,y-1), x, y-1);
		}
		if(board.getBoard(x, y+1) == null)
		{
			board.setBoard(new Fire(x,y+1), x, y+1);
		}
		
	}
	
	/**
	 * get the neighbouring items
	 * @param x: x position
	 * @param y: y position
	 * @return a list of actor
	 */
	private Actor[] getNeighbours(int x, int y)
	{
		ArrayList<Actor> list = new ArrayList<Actor>();
		Actor a = board.getBoard(x-1, y);
		if(a != null && a.getClass() != Wall.class)
		{
			list.add(a);
		}
		
		a = board.getBoard(x+1, y);
		if(a != null && a.getClass() != Wall.class)
		{
			list.add(a);
		}
		a = board.getBoard(x, y-1);
		if(a != null && a.getClass() != Wall.class)
		{
			list.add(a);
		}
		a = board.getBoard(x, y+1);
		if(a != null && a.getClass() != Wall.class)
		{
			list.add(a);
		}
		
		
		return list.toArray(new Actor[list.size()]);
	}
	

	/**
	 * paint walls in a preset location
	 */
	private void placeWalls()
	{
		for(int i = 0; i < 15;i++)
		{
			for(int j = 0; j <15;j++)
			{
				if(i == 0 || i == 14 || j == 0 || j == 14) {
					board.setBoard(new Wall(i, j), i, j);
				}
				if(i %2 == 0 && j % 2 == 0) {
					board.setBoard(new Wall(i, j), i, j);
				}
				
			}
		}
	}
	
	/**
	 * set player1s movement to left
	 */
	@Override
	public void left1Pressed() {
		// TODO Auto-generated method stub
		move = LEFT;
		
	}
	
	/**
	 * set player1s movement to right 
	 */
	@Override
	public void right1Pressed() {
		// TODO Auto-generated method stub
		move = RIGHT;
		

	}
	
	/**
	 * set player1s movement to up
	 */
	@Override
	public void up1Pressed() {
		// TODO Auto-generated method stub
		move = UP;
	}

	/**
	 * set player1s movement to down
	 */
	@Override
	public void down1Pressed() {
		// TODO Auto-generated method stub
		move = DOWN;
	}
	
	/**
	 * set player2s movement to left
	 */
	@Override
	public void left2Pressed() {
		// TODO Auto-generated method stub
		move2 = LEFT;
	}
	
	/**
	 * set player2s movement to right
	 */
	@Override
	public void right2Pressed() {
		// TODO Auto-generated method stub
		move2 = RIGHT;
		
	}
	
	/**
	 * set player2s movement to up
	 */
	@Override
	public void up2Pressed() {
		// TODO Auto-generated method stub
		move2 = UP;
		
	}

	/**
	 * set player2s movement to down
	 */
	@Override
	public void down2Pressed() {
		// TODO Auto-generated method stub
		move2 = DOWN;
		
	}

	/**
	 * set player2s movement to action
	 * reset the game
	 */
	@Override
	public void action2Pressed() {
		// TODO Auto-generated method stub
		move2 = ACTION;
		if(state != RUNNING)reset = true;
	}

	/**
	 * set player1s movement to action
	 * reset the game
	 */
	@Override
	public void action1Pressed() {
		// TODO Auto-generated method stub
		move = ACTION;
		if(state != RUNNING)reset = true;
		
		
	}

	/**
	 * unimplemented
	 */
	@Override
	public void mousePressed(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return board
	 */
	public GameBoard getBoard() 
	{
		return board;
	}
	
	/**
	 * paint create on preset locations
	 */
	private void setBoxes()
	{
		for(int i = 0; i<board.getWidth();i++)
		{
			for(int j = 0;j<board.getHeight();j++ )
			{
				if(!(i %2 == 0 && j % 2 == 0) && i != 0 && j != 0 && i != board.getWidth()-1 && j != board.getHeight()-1)
				{
					board.setBoard(new Box(i,j), i, j);
				}
			}
		}
		board.setBoard(null, 1, 1);
		board.setBoard(null, 1, 2);
		board.setBoard(null, 1, 3);
		board.setBoard(null, 2, 1);
		board.setBoard(null, 3, 1);
		
		board.setBoard(null, 13, 11);
		board.setBoard(null, 13, 12);
		board.setBoard(null, 13, 13);
		board.setBoard(null, 12, 13);
		board.setBoard(null, 11, 13);
		
	
	}
	

}
