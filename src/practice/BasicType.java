package practice;

import java.io.File;
import java.io.FileInputStream;

public class BasicType {
	int a =0;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=1,j=0;
		float f1 = 0.1f, f2 = 125;
		long l1 = 123456, l2 = 8888888888l;
		double d1 = 2e20, d2 = 124;
		byte b1 = 1, b2 = 2, b3 = 127;
		i = j + 1;
		i = i/10;
		i = (int)(i * 0.1);
		char c1 = 'a', c2 =127;
		byte b = (byte)(b1 - b2);
		float f3 = f1 + f2;
		float f4 = (float)(f1 + f2*0.1);
		double d = d1 * i + j;	
		String ss = new String("lkjhgttrcx\\????\\sdfs");
		int lk = ss.indexOf("dd");
		System.out.println("length:" + ss.length());
		//String sp =ss.substring(2,6);
		//String s12 = "jhgt";
		if(ss.matches("\\w+\\\\[?]{4}\\\\\\w+")){
			System.out.println("[?]");
		}
		int p =5;
		//int p = 1<<3;
		System.out.println(1<<p);
		System.out.println(p);
		
		try{
			T t = new T();
			FileInputStream file = new FileInputStream("the name");
			float f = (float)(d1*5 + d2);
			t.run();
			
		}catch(Exception e){
			
		}finally{
			String sbn = "C:\\d\\r\\t\\y\\uiu\\pop\\s.java ";
			int index = sbn.lastIndexOf(File.separator);
			sbn = sbn.substring(index+1);
			System.out.println(sbn);
		}
	}

}

interface Runner{
	int id = 0;
	void run();
}

class T implements Runner{
	public void run(){
		System.out.println("TTTTTTT");
	}
}
