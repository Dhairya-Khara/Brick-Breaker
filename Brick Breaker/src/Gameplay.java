import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
	private boolean play = false;
	private int score = 0;

	private int totalBricks = 21;

	private Timer time;
	private int delay = 8;

	private int playerX = 310;
	private int playerY = 550;

	private int ballposX = 310;
	private int ballposY = 200;
	public int ballposXB = 250;
	public int ballposYB = 260;
	public int ballposXC = 200;
	public int ballposYC = 200;

	public int ballXdir1 = 2;
	public int ballYdir1 = 4;
	public int ballXdir2 = 3;
	public int ballYdir2 = 10;
	public int ballXdir3 = 1;
	public int ballYdir3 = 2;
	public int ballXdir3B = 1;
	public int ballYdir3B = 2;
	public int ballXdir3C = 1;
	public int ballYdir3C = 2;

	
	private MapGenerator map;
	private int var;

	public Gameplay() {
		map = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		time = new Timer(delay, this);
		time.start();

	}

	public void paint(Graphics g) {

		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);

		g.setColor(Color.red);
		g.setFont(new Font("serif", Font.BOLD, 50));
		g.drawString("Welcome to Brick Breaker", 50, 200);
		g.setFont(new Font("serif", Font.BOLD, 45));
		g.drawString("A) EASY", 50, 300);
		g.drawString("B) MEDIUM", 50, 350);
		g.drawString("C) HARD", 50, 400);

		g.setFont(new Font("serif", Font.BOLD, 15));
		g.drawString("(Press A for Easy, B for Medium or C for Hard)", 375, 550);

		// background
		if (var == 1 || var == 2 || var == 3) {

			g.setColor(Color.black);
			g.fillRect(1, 1, 692, 592);

			// border
			g.setColor(Color.yellow);
			g.fillRect(0, 0, 3, 592);
			g.fillRect(0, 0, 692, 3);
			g.fillRect(691, 0, 3, 592);

			// the paddle
			g.setColor(Color.green);
			g.fillRect(playerX, playerY, 100, 8);

			// ball
			g.setColor(Color.yellow);
			g.fillOval(ballposX, ballposY, 20, 20);
			if (var == 2) {
				g.fillOval(ballposXB, ballposYB, 20, 20);
			}
			else if (var == 3) {
				g.fillOval(ballposXB, ballposYB, 20, 20);
				g.fillOval(ballposXC, ballposYC, 20, 20);
			}
			// score
			g.setColor(Color.yellow);
			g.setFont(new Font("serif", Font.BOLD, 25));
			g.drawString("" + score, 590, 30);
			map.draw((Graphics2D) g);
			if (score == 42) {
				g.setColor(Color.GREEN);
				g.setFont(new Font("serif", Font.BOLD, 75));
				g.drawString("You Won", 310, 225);
				g.setFont(new Font("serif", Font.ITALIC, 25));
				g.drawString("Press Z to Restart", 210, 530);
				g.setColor(Color.black);
				g.fillOval(ballposX, ballposY, 20, 20);
				g.fillOval(ballposXB, ballposYB, 20, 20);
				g.fillOval(ballposXC, ballposYC, 20, 20);

			} else if (score != 42 && ballposY > 592 || ballposYB > 592 || ballposYC > 592) {

				g.setColor(Color.RED);
				g.setFont(new Font("serif", Font.BOLD, 75));
				g.drawString("You Lose", 200, 500);
				g.setFont(new Font("serif", Font.ITALIC, 25));
				g.drawString("Press Z to Restart", 210, 530);

				if (ballposY > 592) {
					ballposYB = 595;
					ballposYC = 595;

				}
				if (ballposYB > 592) {
					ballposY = 595;
					ballposYC = 595;
				}
				if(ballposYC > 592) {
					ballposYB = 595;
					ballposY = 595;	
				}
			}

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time.start();
		if (var == 1) {

			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir1 = -1 * ballYdir1;
			}

			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);

						if (ballRect.intersects(rect)) {
							map.setBrickValue(0, i, j);
							totalBricks -= 1;
							score += 2;
							ballYdir1 = -1 * ballYdir1;

							if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
								ballXdir1 = -1 * ballXdir1;
							}
							break A;
						}

					}
				}
			}

			ballposX += ballXdir1;
			ballposY += ballYdir1;
			if (ballposX < 0) {
				ballXdir1 = -ballXdir1;
			}
			if (ballposY < 0) {
				ballYdir1 = -1 * ballYdir1;
			}
			if (ballposX > 670) {
				ballXdir1 = -1 * ballXdir1;
			}

		}

		repaint();

		if (var == 2) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir3 = -1 * ballYdir3;
			}
			if (new Rectangle(ballposXB, ballposYB, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir3B = -ballYdir3B;
			}

			A: for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle ballRect2 = new Rectangle(ballposXB, ballposYB, 20, 20);
						if (ballRect.intersects(rect)) {
							map.setBrickValue(0, i, j);
							totalBricks -= 1;
							score += 2;
							ballYdir3 = -1 * ballYdir3;

							if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
								ballXdir3 = -1 * ballXdir3;
							}
							break A;
						}
						if (ballRect2.intersects(rect)) {
							map.setBrickValue(0, i, j);
							totalBricks -= 1;
							score += 2;
							ballYdir3B = -1 * ballYdir3B;

							if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
								ballXdir3B = -1 * ballXdir3B;
							}
						}
					}
				}
			}

			ballposX += ballXdir3;
			ballposY += ballYdir3;
			ballposXB += ballXdir3B;
			ballposYB += ballYdir3B;

			if (ballposX < 0) {
				ballXdir3 = -ballXdir3;
			}
			if (ballposY < 0) {
				ballYdir3 = -1 * ballYdir3;
			}
			if (ballposX > 670) {
				ballXdir3 = -1 * ballXdir3;
			}

			if (ballposXB < 0) {
				ballXdir3B = -ballXdir3B;

			}
			if (ballposYB < 0) {
				ballYdir3B = -1 * ballYdir3B;
			}
			if (ballposXB > 670) {
				ballXdir3B = -1 * ballXdir3B;
			}
		}
		if (var == 3) {
			if (new Rectangle(ballposX, ballposY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir3 = -1 * ballYdir3;
			}
			if (new Rectangle(ballposXB, ballposYB, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir3B = -ballYdir3B;
			}
			if (new Rectangle(ballposXC, ballposYC, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				ballYdir3C = -ballYdir3C;
			}
			for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if (map.map[i][j] > 0) {
						int brickX = j * map.brickWidth + 80;
						int brickY = i * map.brickHeight + 50;
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;

						Rectangle rect = new Rectangle(brickX, brickY, brickWidth, brickHeight);
						Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
						Rectangle ballRect2 = new Rectangle(ballposXB, ballposYB, 20, 20);
						Rectangle ballRect3 = new Rectangle(ballposXC, ballposYC, 20, 20);
						if (ballRect.intersects(rect)) {
							map.setBrickValue(0, i, j);
							totalBricks -= 1;
							score += 2;
							ballYdir3 = -1 * ballYdir3;

							if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
								ballXdir3 = -1 * ballXdir3;
							}

						}
						if (ballRect2.intersects(rect)) {
							map.setBrickValue(0, i, j);
							totalBricks -= 1;
							score += 2;
							ballYdir3B = -1 * ballYdir3B;

							if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
								ballXdir3B = -1 * ballXdir3B;
							}
						}
						if (ballRect3.intersects(rect)) {
							map.setBrickValue(0, i, j);
							totalBricks -= 1;
							score += 2;
							ballYdir3C = -1 * ballYdir3C;
							ballXdir3C = -ballXdir3C;

							if (ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width) {
								ballXdir3C = -1 * ballXdir3C;
								
							}
						}
					}
				}
			}

			ballposX += ballXdir3;
			ballposY += ballYdir3;
			ballposXB += ballXdir3B;
			ballposYB += ballYdir3B;
			ballposXC += ballXdir3C;
			ballposYC += ballYdir3C;
			

			if (ballposX < 0) {
				ballXdir3 = -ballXdir3;
			}
			if (ballposY < 0) {
				ballYdir3 = -1 * ballYdir3;
			}
			if (ballposX > 670) {
				ballXdir3 = -1 * ballXdir3;
			}

			if (ballposXB < 0) {
				ballXdir3B = -ballXdir3B;

			}
			if (ballposYB < 0) {
				ballYdir3B = -1 * ballYdir3B;
			}
			if (ballposXB > 670) {
				ballXdir3B = -1 * ballXdir3B;
			}
			if (ballposXC < 0) {
				ballXdir3C = -ballXdir3C;

			}
			if (ballposYC < 0) {
				ballYdir3C = -1 * ballYdir3C;
			}
			if (ballposXC > 670) {
				ballXdir3C = -1 * ballXdir3C;
			}
			
		}
		repaint();

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (playerX >= 600) {
				playerX = 600;

			} else {
				moveRight();
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (playerX < 10) {
				playerX = 10;
			} else {
				moveLeft();
			}
		}

		if (e.getKeyCode() == KeyEvent.VK_Z) {
			map = new MapGenerator(3, 7);
			score = 0;
			playerX = 310;
			playerY = 550;
			ballposX = 310;
			ballposY = 200;
			ballposXB = 250;
			ballposYB = 260;
			 ballposXC = 200;
			 ballposYC = 270;
			ballXdir1 = 2;
			ballYdir1 = 4;
			ballXdir2 = 3;
			ballYdir2 = 10;
			ballXdir3 = 1;
			ballYdir3 = 2;
			ballXdir3B = 1;
			ballYdir3B = 2;
			ballXdir3C = 1;
			ballYdir3C = 2;
			

		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			var = 1;
		}
		if (e.getKeyCode() == KeyEvent.VK_B) {
			var = 2;
		}
		if (e.getKeyCode() == KeyEvent.VK_C) {
			var = 3;
		}

	}

	public void moveRight() {
		play = true;
		playerX += 60;
	}

	public void moveLeft() {

		play = true;
		playerX -= 60;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
