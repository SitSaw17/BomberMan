package GameApp;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import GameFramework.Window;

public class Main {
	public static void main(String[] args) {
		JFrame f = new JFrame();
		BomberMan game = new BomberMan();
		
		BomberWindow window = new BomberWindow(750, 750, game);
	
		f.add(window);
		//f.repaint();
		f.setVisible(true);
		f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		f.pack();
		game.setObserver(window);
		game.startGame();
	}
}
