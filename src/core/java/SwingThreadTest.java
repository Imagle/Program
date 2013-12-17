package core.java;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SwingThreadTest {

	public static void main(String[] args) {
		SwingThreadFrame frame = new SwingThreadFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}

class SwingThreadFrame extends JFrame{
	public SwingThreadFrame(){
		setTitle("SwingThreadTest");
		final JComboBox combo = new JComboBox();
		combo.insertItemAt(new Integer(Integer.MAX_VALUE), 0);
		combo.setPrototypeDisplayValue(combo.getItemAt(0));
		combo.setSelectedIndex(0);
		
		JPanel panel = new JPanel();
		JButton goodButton = new JButton("Good");
		goodButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new Thread(new GoodWorkerRunnable(combo)).start();
			}
		});
		panel.add(goodButton);
		JButton badButton = new JButton("Bad");
		badButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent event){
				new Thread(new BadWorkerRunnable(combo)).start();
			}
		});
		panel.add(badButton);
		panel.add(combo);
		add(panel);
		pack();
	}
}

class BadWorkerRunnable implements Runnable{
	private JComboBox combo = null;
	private Random generator;
	
	public BadWorkerRunnable(JComboBox aCombo){
		this.combo = aCombo;
		generator = new Random();
	}

	@Override
	public void run() {
		try{
			while(true){
				combo.showPopup();
				int i = Math.abs(generator.nextInt());
				if(i%2 == 0){
					combo.insertItemAt(new Integer(i), 0);
				}
				else if(combo.getItemCount() > 0){
					combo.removeItemAt(i % combo.getItemCount());
				}
				Thread.sleep(1);
			}
		}catch(InterruptedException e){}
	}
}

class GoodWorkerRunnable implements Runnable{
	private JComboBox combo = null;
	private Random generator;
	
	public GoodWorkerRunnable(JComboBox aCombo){
		this.combo = aCombo;
		generator = new Random();
	}

	@Override
	public void run() {
		try{
			while(true){
				EventQueue.invokeLater(new Runnable(){
					public void run(){
						combo.showPopup();
						int i = Math.abs(generator.nextInt());
						if(i%2 == 0){
							combo.insertItemAt(new Integer(i), 0);
						}
						else if(combo.getItemCount() > 0){
							combo.removeItemAt(i % combo.getItemCount());
						}
					}
				});
				Thread.sleep(1);
			}
		}catch(InterruptedException e){}
	}
}