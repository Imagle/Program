package ArtOfPrograming;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

public class Strstr {

	private static Logger logger = LogManager.getLogger(Strstr.class.getClass());
	
	/*
	 * 给定一个字符串A, 要求在A中查找一个子串B;
	 * 如A="ABCDF",要你在A中查找子串B="CD"
	 */
	public static int strstr(){
		char[] a = "abcdefcdq".toCharArray();
		int n=a.length;
		char[] b = "cdq".toCharArray();
		int m = b.length;
		int i=0;
		while( i<= n-m){
			int j=0;
			while(i<=n-m && a[i]!=b[0]){
				i++;
			}
			if(i>n-m) return -1;
			int begin1 = i+1;
			int begin2 = j+1;
			while(begin2<m && a[begin1]==b[begin2]){
				begin1++;
				begin2++;
			}
			if(begin2 >= m) {
				logger.info("i is " + i);
				return i;
			}
			i++;
		}
		return -1;
	}
	
	/*
	 * 寻找第一个只出现一次的数
	 */
	public static int findOnceChar(String s){
		int i=0;
		return s.charAt(i);
	}

	public static void main(String[] args) {
		int i = strstr();
		logger.info("strstr is " + i);
	}
	
}
