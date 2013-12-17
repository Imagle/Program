/*
 * Question: 1. how to set the vertical separator on the menuitem
 */

package practice;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JMenuItem;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class DemoNotepad extends JFrame implements ActionListener{
	
	private  DemoFileAction dfc;
	
	private JMenuBar menuBar = null;
	private JMenu file = null;
	private JMenu edit = null;
	private JMenu format = null;
	private JMenu view = null;
	private JMenu help = null;
	
	private JMenuItem newFile = null;
	private JMenuItem open = null;
	private JMenuItem save = null;
	private JMenuItem exit = null;
	
	private JMenuItem undo = null;
	private JMenuItem cut = null;
	private JMenuItem copy = null;
	private JMenuItem paste = null;
	
	private JScrollPane jsp = null;
	private JTextPane jtp= null;
	
	public DemoNotepad(){
		init();
		dfc = new DemoFileAction();
	}
	
	private void init(){
		file = new JMenu("File");
		edit = new JMenu("Edit");
		format = new JMenu("Format");
		view = new JMenu("View");
		help = new JMenu("Help");
		jtp = new JTextPane();		
		menuBar = new JMenuBar();
		Dimension dms = new Dimension(100, 20);
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
		jsp = new JScrollPane(jtp, v, h);
		
		//create and fill file menu
		newFile = new JMenuItem("New");
		open = new JMenuItem("Open");
		save = new JMenuItem("Save", new ImageIcon(getClass().getResource("/res/trayIcon.png")));
		exit = new JMenuItem("Exit");
		file.add(newFile);
		file.addSeparator();
		file.add(open);
		file.add(save);
		file.add(new JSeparator());
		file.add(exit);		
		newFile.setPreferredSize(dms);
		open.setPreferredSize(dms);
		save.setPreferredSize(dms);
		exit.setPreferredSize(dms);
		
		//create and fill edit menu
		undo = new JMenuItem("Undo");
		cut = new JMenuItem("Cut");
		copy = new JMenuItem("Copy");
		paste = new JMenuItem("Paste");
		edit.add(undo);
		edit.add(new JSeparator());
		edit.add(cut);
		edit.add(copy);
		edit.add(new JSeparator());
		edit.add(paste);
		undo.setPreferredSize(dms);
		cut.setPreferredSize(dms);
		copy.setPreferredSize(dms);
		paste.setPreferredSize(dms);
		
		jtp.setEnabled(true);
		jtp.setBounds(250, 100, 490, 340);
		jtp.setSize(490, 600);
		jsp.setBackground(Color.WHITE);

		menuBar.add(file);
		menuBar.add(edit);
		menuBar.add(format);
		menuBar.add(help);
		
		newFile.setActionCommand("NEWFILE_COMMAND");
		newFile.addActionListener(this);
		open.setActionCommand("OPEN_COMMAND");
		open.addActionListener(this);
		save.setActionCommand("SAVE_COMMAND");
		save.addActionListener(this);
		exit.setActionCommand("EXIT_COMMAND");
		exit.addActionListener(this);
		
		jsp.setBounds(250, 100, 500, 350);		
		getContentPane().add(jsp);
		setJMenuBar(menuBar);
		setTitle("notepad");
		setBounds(250, 100, 500, 350);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/*
	 * response the menu action
	 */
	public void actionPerformed(ActionEvent event){
		String command = event.getActionCommand();
		if(command.equals("NEWFILE_COMMAND")){
			System.out.println("create new file");
		}else if(command.equals("OPEN_COMMAND")){
			System.out.println("open a file");
		}else if(command.equals("SAVE_COMMAND")){
			String content = jtp.getText();
			System.out.println(dfc.getFileName());
			System.out.println("�ļ����ݣ� " + content);
			dfc.setFileContent(content);
			try{
				dfc.saveFile();
			}catch(IOException e){
				e.printStackTrace();
			}
			System.out.println("save.");
		}else if(command.equals("EXIT_COMMAND")){
			System.out.println("exit!!");
		}
	}
	public static void main(String args[]){
		DemoNotepad dn = new DemoNotepad();
		System.out.println("OK");
	}
}
