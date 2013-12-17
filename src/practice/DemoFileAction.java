package practice;

import java.awt.geom.GeneralPath;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;

public class DemoFileAction {
	private File file;
	private String fileName;
	private String fileContent;
	//create new file under the current directory
	public boolean createFile() throws IOException{
		/*when fileName is null, let use to set fileName and directory
		 * need to do later
		 */ 
		if(fileName == null){
			FileSave fs = new FileSave();
			fs.selectLocation();
			fileName = fs.getPathName();
		}
		file  = new File(fileName);
		if(file!= null && file.exists() && file.isFile()){
			//file has content, first clear its content and write again
			if(file.length() > 0){
				System.out.println(file.length());
				file.delete();
			}
		}
		if(file.createNewFile()){
			return true;
		}else
			return false;
	}
	/*
	 * save file in 2 conditions:
	 * 1. one new file 
	 * 2. one exsiting file, made some modifications
	 */
	public boolean saveFile() throws IOException{
		//
		if(createFile()){
			FileWriter out = new FileWriter(file);
			out.write(fileContent);
			out.close();
			System.out.println("Okay");
			return true;
		}else{
			System.out.println("create file fail!");
			return false;
		}
	}
	public void setFileContent(String content){
		this.fileContent = content;
	}
	public String getFileContent(){
		return this.fileContent;
	}
	public String getFileName(){
		return this.fileName;
	}
	public void setFileName(String filename){
		this.fileName = filename;
	}
	public static void main(String[] args){
		DemoFileAction dfc = new DemoFileAction();
		dfc.fileContent = "���ܿ����� ���������Ǳ�ģ�this is just test";
		//dfc.fileName = "test.txt";
		try{
			dfc.saveFile();
		}catch(Exception e){
			
		}
	}
}
class FileSave{
	private String pathname;
	private JFileChooser saveLocation;
	public void selectLocation(){
		saveLocation = new JFileChooser();
		if( saveLocation.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			pathname = saveLocation.getSelectedFile().getAbsolutePath();
			System.out.println(pathname);
			//String name = saveLocation.getSelectedFile().getName();
		}
	}
	public String getPathName(){
		return this.pathname;
	}
}