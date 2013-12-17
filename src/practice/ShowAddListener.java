package practice;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShowAddListener extends JFrame{
	private JTextField name = new JTextField(20);
	private JTextArea result = new JTextArea();
	private static Pattern addListener = Pattern.compile("(add\\w+?Listener\\(.*?\\))");
	//∆•≈‰πÊ‘Ú: ∆•≈‰
	private static Pattern qualifier = Pattern .compile("\\w+\\.");
	
	private class NameL implements ActionListener{
		public void actionPerformed(ActionEvent e){
			String nm = name.getText().trim();
			if(nm.length() == 0){
				result.setText("Please input right Component");
				return ;
			}
			Class<?> kind;
			try{
				kind = Class.forName( ("javax.swing." + nm));
			}catch(ClassNotFoundException ee){
				result.setText("class not found, no match!");
				return ;
			}
			Method[] methods = kind.getMethods();
			result.setText("");
			for(Method m: methods){
				Matcher matcher = addListener.matcher(m.toString());
				if(matcher.find()){
					//result.append(matcher.group(1)+ "\n");
					result.append(qualifier.matcher(matcher.group(1)).replaceAll("") + "\n");
				}
				System.out.println(matcher.toString());
				System.out.println(result.getText());
			}
		}
	}
	
	public ShowAddListener(){
		name.addActionListener( new NameL());
		JPanel jp = new JPanel();
		jp.add( new JLabel("Swing class name(Press Enter): "));
		jp.add(name);
		add(BorderLayout.NORTH, jp);
		add( new JScrollPane(result));
		setBounds(100, 50, 500, 600);
		setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ShowAddListener();
	}

}
