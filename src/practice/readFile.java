package practice;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class readFile {

	
	public static void func1(String filepath){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(filepath))));
			String str = null;
			while( (str=br.readLine()) != null){
				//...
			}
		} catch(FileNotFoundException e){
		} catch(IOException e){
		}finally{
			if( br != null ){
				try{
					br.close();
					br = null;
				} catch(IOException e){
				}
			}
		}
	}
	
	public static void func2(String filepath){
		BufferedReader br = null;
		try{
			br = new BufferedReader(new FileReader(new File(filepath)));
			String str = null;
			while( (str=br.readLine()) != null ){
				//...
			}
		} catch(FileNotFoundException e){
		} catch(IOException e){
		} finally{
			if( br != null ){
				try{
					br.close();
					br = null;
				} catch(IOException e){
				}
			}
		}
	}
	
	public static void main(String[] args) {
	}

}
