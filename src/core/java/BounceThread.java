package core.java;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class BounceThread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BounceThread bounce = new BounceThread();
		JFrame frame = bounce.new BounceFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	class Ball{
		private static final int XSIZE = 15;
		private static final int YSIZE = 15;
		private double x =0;
		private double y =0;
		private double dx = 1;
		private double dy = 1;
		
		public void move(Rectangle2D bounds){
			x += dx;
			y += dy;
			if( x < bounds.getMinX()){
				x = bounds.getMinX();
				dx = -dy;
			}
			if( x + XSIZE >= bounds.getMaxX()){
				x = bounds.getMaxX() - XSIZE;
				dx = -dx;
			}
			if( y < bounds.getMinY()){
				y = bounds.getMinY();
				dy = -dy;
			}
			if( y + YSIZE >= bounds.getMaxY()){
				y = bounds.getMaxY() - YSIZE;
				dy = -dy;
			}
		}
		
		public Ellipse2D getShape(){
			return new Ellipse2D.Double(x,y, XSIZE, YSIZE);
		}
	}
	
	class BallPanel extends JPanel{
		private ArrayList<Ball> balls = new ArrayList<Ball>();

		public void add(Ball b){
			balls.add(b);
		}
		public void paintComponent(Graphics g){
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D) g;
			for(Ball b : balls){
				g2.fill(b.getShape());
			}
		}
	}
	
	class BounceFrame extends JFrame{
		private BallPanel panel;
		public static final int DEFAULT_WIDTH = 450;
		public static final int DEFAULT_HEIGHT = 350;
		
		public BounceFrame(){
			setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
			setTitle("BounceThread");
			panel = new BallPanel();
			add(panel, BorderLayout.CENTER);
			JPanel buttonPanel = new JPanel();
			addButton(buttonPanel, "Start", 
					new ActionListener(){
						public void actionPerformed(ActionEvent event){
							addBall();
						}
					});
			addButton(buttonPanel, "Close", 
					new ActionListener(){
						public void actionPerformed(ActionEvent event){
							System.exit(0);
						}
					});
			add(buttonPanel, BorderLayout.SOUTH);
		}
		
		public void addButton(Container c, String title, ActionListener listener){
			JButton button = new JButton(title);
			c.add(button);
			button.addActionListener(listener);
		}
		
		public void addBall(){
			Ball ball = new Ball();
			panel.add(ball);
			BallRunnable br = new BallRunnable(ball, panel);
//			br.run();
			Thread t = new Thread(br);
			t.start();
			System.out.println(Thread.currentThread());
		}
	}
	
	class BallRunnable implements Runnable{
		
		public static final int STEPS = 1000;
		public static final int DELAY = 5;
		private Ball ball = null;
		private JPanel panel = null;
		
		public BallRunnable(Ball b, JPanel jp){
			this.ball = b;
			this.panel = jp;
		}
		
		public void run(){
			try{
				for(int i=1; i <= STEPS; i++){
					ball.move(panel.getBounds());
					//panel.paint(panel.getGraphics());
					panel.repaint();
					Thread.sleep(DELAY);
				}
			}catch(InterruptedException e){
				Thread.currentThread().interrupt();
			}
		}
	}

}
