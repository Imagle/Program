/*
 * 提取一个网页中的email地址，并将其输出到txt文件中
 * 正则表达式\\w[\\w[-.]]*\\w@\\w[\\w[-.]]*\\w\\.\\w+匹配电子邮件
 */
package practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoGetMailAddress {

	public static void main(String[] args){
		BufferedReader br=null;
		BufferedWriter bw=null;
		int counter = 0;
		try {
			br = new BufferedReader(new FileReader("E:\\regex\\address.html"));
			bw = new BufferedWriter(new FileWriter("E:\\regex\\MailAddress.txt"));
			String line = "";
			Pattern p = Pattern.compile("\\w[\\w[-.]]*\\w@\\w[\\w[-.]]*\\w\\.\\w+");
			//Pattern p = Pattern.compile("[\\w[-.]]+@[\\w[-.]]+\\.\\w+");
			while((line=br.readLine())!=null){
				Matcher m = p.matcher(line);
				while(m.find()){
					counter++;
					System.out.println(m.group());
					bw.write(m.group().toString());
					bw.newLine();
				}
			}
			System.out.println("count: " + counter);
			br.close();
			bw.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br!=null){
				try{
					br.close();
				}catch(IOException e){
					
				}
			}
			
			if(bw!=null){
				try{
					bw.close();
				}catch(IOException e){
					
				}
			}
		}
	}

}
