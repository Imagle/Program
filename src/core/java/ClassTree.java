package core.java;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;
import java.lang.reflect.*;
import java.util.Enumeration;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

public class ClassTree {
	public static void main(String[] args) {
		ClassTreeFrame frame = new ClassTreeFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

class ClassTreeFrame extends JFrame{
	
	private JTree tree = null;
	private DefaultTreeModel treeModel = null;
	private JTextField textField = null;
	private DefaultMutableTreeNode rootNode = null;
	private JTextArea textArea = null;
	
	public ClassTreeFrame(){
		
		setTitle("ClassTree");
		setSize(400, 300);
		
		//init tree
		rootNode = new DefaultMutableTreeNode(java.lang.Object.class);
		treeModel = new DefaultTreeModel(rootNode);
		tree = new JTree(treeModel);
		
		addClass(getClass());
		
		tree.addTreeSelectionListener(new TreeSelectionListener(){
			public void valueChanged(TreeSelectionEvent event){
				TreePath path = tree.getSelectionPath();
				if(path == null) return;
				DefaultMutableTreeNode node = (DefaultMutableTreeNode)path.getLastPathComponent();
				Class c = (Class)node.getUserObject();
				String description = getFieldDescription(c);
				textArea.setText(description);
			}
		});
		textArea = new JTextArea();
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(1,2));
		panel.add(new JScrollPane(tree));
		panel.add(new JScrollPane(textArea));
		
		//add tree cell renderer
		ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
		renderer.setLeafIcon(new ImageIcon("src/res/trayIcon.png"));
		tree.setCellRenderer(renderer);
		
		add(panel, BorderLayout.CENTER);
		//add(new JScrollPane(tree), BorderLayout.CENTER);
		addTextField();
	}
	
	public static String getFieldDescription(Class c){
		StringBuilder r = new StringBuilder();
		Field[] fields = c.getDeclaredFields();
		for(int i=0; i<fields.length; i++){
			Field f = fields[i];
			if((f.getModifiers() & Modifier.STATIC) != 0)
				r.append("static ");
			r.append(f.getType().getName());
			r.append(" ");
			r.append(f.getName());
			r.append("\n");
		}
		return r.toString();
	}
	
	public void addTextField(){
		JPanel panel = new JPanel();
		ActionListener addListener = new ActionListener(){
			public void actionPerformed(ActionEvent event){
				try{
					String name = textField.getText();
					addClass(Class.forName(name));
					textField.setText("");
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}
			}

		};
		textField = new JTextField(20);
		textField.addActionListener(addListener);
		panel.add(textField);
		
		JButton button= new JButton("add");
		button.addActionListener(addListener);
		panel.add(button);
		
		add(panel, BorderLayout.SOUTH);
		
	}
	
	public DefaultMutableTreeNode addClass(Class c){
		if(c.isInterface() || c.isPrimitive()) return null;
		Class s = c.getSuperclass();
		DefaultMutableTreeNode parent = null;
		if( s == null){
			parent = rootNode;
		}
		else{
			parent = addClass(s);
		}
		DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
		treeModel.insertNodeInto(newNode, parent, parent.getChildCount());
		
		TreePath path = new TreePath(treeModel.getPathToRoot(newNode));
		//tree.makeVisible(path);
		tree.scrollPathToVisible(path);
		
		return newNode;
	}

	public DefaultMutableTreeNode findUserObject(Object obj){
		Enumeration e = rootNode.breadthFirstEnumeration();
		while(e.hasMoreElements()){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
			if( node.getUserObject().equals(obj)){
				return node;
			}
		}
		return null;
	}
}

class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer{
	private Font plainFont = null;
	private Font italicFont = null;
	
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected,
			boolean expanded, boolean leaf, int row, boolean hasFocus){
		super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)value;
		Class c = (Class)node.getUserObject();
		if( plainFont == null){
			plainFont = getFont();
			if(plainFont != null)
				italicFont = plainFont.deriveFont(Font.ITALIC);
		}
		if( (c.getModifiers() & Modifier.ABSTRACT) == 0)
			setFont(plainFont);
		else
			setFont(italicFont);
		return this;
	}
	
}