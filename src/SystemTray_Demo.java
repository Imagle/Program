import java.awt.AWTException;
import java.awt.BorderLayout; 
import java.awt.Color;
import java.awt.Container; 
import java.awt.FlowLayout; 
import java.awt.MenuItem; 
import java.awt.PopupMenu; 
import java.awt.SystemTray; 
import java.awt.TrayIcon; 
import java.awt.event.ActionEvent; 
import java.awt.event.ActionListener; 
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import java.io.RandomAccessFile; 
import java.net.URL; 
import java.nio.channels.FileChannel; 
import java.nio.channels.FileLock;
import java.awt.EventQueue;

import javax.imageio.ImageIO; 
import javax.swing.ImageIcon;
import javax.swing.JButton; 
import javax.swing.JCheckBox; 
import javax.swing.JDialog; 
import javax.swing.JFrame; 
import javax.swing.JLabel; 
import javax.swing.JOptionPane; 
import javax.swing.JPanel; 
import javax.swing.SwingConstants; 
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class SystemTray_Demo extends JFrame{
	
	//private static SystemTray_Demo frame;
	//private TrayIcon trayIcon = null;
	
	private SystemTray systemTray;
	ImageIcon icon =new ImageIcon(getClass().getResource("/res/book.png"));
	private SystemTray_Demo(){
		super();
		//getContentPane().add(new JLabel("This is one simple test!",SwingConstants.CENTER));
		//initUI();
		initSystemTray();
	}
	
	private void initUI(){
		setIconImage(icon.getImage());
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(Color.green, 1, false));
		panel.setLayout(new BorderLayout());
		JLabel label = new JLabel("This is one simple test!",SwingConstants.CENTER);
		panel.add(label);
		getContentPane().add(panel);
	}
	
	private void initSystemTray(){
		setTitle("SystemTray");
		if(SystemTray.isSupported()){
			systemTray = SystemTray.getSystemTray();		
			//ImageIcon icon= new ImageIcon(getClass().getResource("/res/book.png"));
			TrayIcon trayIcon = new TrayIcon(icon.getImage());
			trayIcon.setImageAutoSize(true);
				
			
			PopupMenu popupMenu = new PopupMenu();
			MenuItem item_1 = new MenuItem("open");
			item_1.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					SystemTray_Demo.this.setVisible(true);
				}
			});
			popupMenu.add(item_1);
			popupMenu.addSeparator();
			
			MenuItem item_2 = new MenuItem("exit");
			item_2.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e){
					System.exit(0);
				}
			});
			popupMenu.add(item_2);
			trayIcon.setPopupMenu(popupMenu);
			try{
				systemTray.add(trayIcon);
			}catch(AWTException e){
				e.printStackTrace();
			}		
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					SystemTray_Demo frame = new SystemTray_Demo();
					frame.setVisible(true);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		});		
	}
}
