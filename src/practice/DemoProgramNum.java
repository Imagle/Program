
 // 统计某个目录下所有java文件的代码行、空白行、注释行
 // 使用正则表达式^.*\\.java$匹配java文件
 // 使用正则表示^\\s*$匹配空白行
 // 使用正则表达式^\\s*/\\*.*$匹配注释/*
 // 使用正则表达式^\\s*.*\\*/$匹配注释*/
 // 使用正则表达式^\\s*//.*$匹配注释//
 // 使用递归遍历整个目录
 
package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoProgramNum {
	private static boolean comment=false;
	private static int commentLine = 0;
	private static int whiteLine = 0;
	private static int codeLine = 0;
	
	public static void main(String[] args) {
		BufferedReader in = null;
		File files= null;
		DemoProgramNum dpn = null;
		String arg = null;
		try{
			in = new BufferedReader(new InputStreamReader(System.in));
			if( (arg = in.readLine())!=null){
				files = new File(arg);
				dpn = new DemoProgramNum();
				dpn.getIn(files);
			}
			dpn.printResult();
		}catch(IOException e){
				e.printStackTrace();
		}finally{
			try{
				if(in!=null){
					in.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
	
	public void getIn(File files){
		if(files.isDirectory()){
			/*
			String[] currentDirectoryFilelists = files.list();
			String buildpath = files.getAbsolutePath();
			for(String filepath: currentDirectoryFilelists){
				System.out.println(buildpath + "\\" + filepath);
				filepath = buildpath + "\\" + filepath;
				File file = new File(filepath);
				getIn(file);
			}			
			*/
			File[] filelists = files.listFiles();
			for(File file: filelists){
				getIn(file);
			}
		}else{
			//System.out.println(files.getAbsolutePath());
			if(files.getAbsolutePath().matches("^.*\\.java$")){
				parse(files);
			}
		}
	}
	
	public static void parse(File file){
		System.out.println(file);
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(file));
			String line = "";			
			Pattern p1 = Pattern.compile("^\\s*/\\*.*$");
			Pattern p2 = Pattern.compile("^\\s*.*\\*/$");
			while((line=br.readLine())!=null){
				Matcher m1 = p1.matcher(line);
				Matcher m2 = p2.matcher(line);
				if(m1.find()){
					comment = true;
				}
				if(m2.find()){
					comment = false;
				}
				if(comment){
					if(!line.matches("^\\s*$")){
						commentLine++;
					}else{
						whiteLine++;
					}					
 				}else if(line.matches("^\\s*.*\\*/$|^\\s*//.*$")){
					commentLine++;
				}else if(line.matches("^\\s*$")){
					whiteLine++;
				}else{
					codeLine++;
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			try{
				if(br!=null){
					br.close();
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}	
	
	public void printResult(){
		System.out.println("commentLine: " + commentLine);
		System.out.println("whiteLine: " + whiteLine);
		System.out.println("codeLine: " + codeLine);
	}
}
