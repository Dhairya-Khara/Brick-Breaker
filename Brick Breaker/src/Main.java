import java.awt.Graphics;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		
		JFrame frame = new JFrame();
		Gameplay Gameplay = new Gameplay();
		frame.setBounds(10,10,700,600);
		frame.setTitle("BRICK BREAKER");
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(Gameplay);
	}


}
