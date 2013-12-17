package practice;

import java.util.Random;

public class LCS {

	/*
	 * 计算方法一：
	 * f(m-1, n-1) = same(x[m-1], y[n-1]);
	 * f(i,j) = Max( f(i+1,j+1)+same(x[i],x[j]), f(i+1,j), f(i,j+1) );
	 */
	public static String longestSequence(String x, String y){
		String s = null;
		int m = x.length();
		int n = y.length();
		int[][] c = new int[m+1][n+1];
		int[][]  cc= new int[m+1][n+1];

		for(int i=m-1; i>=0; i--)
			for(int j=n-1; j>=0; j--){
				if(x.charAt(i) == y.charAt(j)){
					c[i][j] = c[i+1][j+1] + 1;
				}
				else
					c[i][j] = Math.max(c[i+1][j], c[i][j+1]);
			}

		System.out.println(x);
		System.out.println(y);
		//输出最大子序列
		int i=0, j=0;
		while(i<m && j<n){
			if(x.charAt(i) == y.charAt(j)){
				System.out.print(x.charAt(i));
				i++;
				j++;
			}else if(c[i+1][j] >= c[i][j+1])
				i++;
			else
				j++;
		}
		System.out.println("==from begin===");
		
		return s;
	}
	
	/*
	 * 方法二: 按照正常的思路进行计算，然后输出最大子序列
	 * f(1, 1) = same(x[1], y[1]);
	 * f(i,j) = Max( f(i-1,j-1)+same(x[i],x[j]), f(i-1,j), f(i,j-1) );
	 * 此时，x，y的第0位都为无效字符 
	 */
	//计算二维数组c
	public static int[][] calArray(String x, String y){
		int m = x.length();
		int n = y.length();
		int[][]  cc= new int[m][n];
		
		for(int i=1; i<m; i++)
			for(int j=1; j<n; j++){
				if(x.charAt(i) == y.charAt(j))
					cc[i][j] = cc[i-1][j-1] + 1;
				else if(cc[i-1][j] >= cc[i][j-1])
					cc[i][j] = cc[i-1][j];
				else
					cc[i][j] = cc[i][j-1];
			}
		return cc;
	}
	
	/*
	 *依据calArray计算出来的二维数组，输出最大子序列
	 *递归输出的最大子序列为倒序的，需要在逆序输出 
	 */
	public static void printLCS(int[][] c, int i, int j, String x, String y){
		if(i==0||j==0) return ;
		if(x.charAt(i) == y.charAt(j)){
			System.out.print(x.charAt(i));
			printLCS(c,i-1, j-1, x, y);
		}else if(c[i-1][j] >= c[i][j-1])
			printLCS(c,i-1, j, x, y);
		else
			printLCS(c,i, j-1, x, y);
	}

	
	public static void main(String[] args) {
		int lengthx = 10;
		int lengthy = 10;
		//String x = generateString(lengthx);
		//String y = generateString(lengthy);
		//longestSequence(x,y);
		String x = "0abcbdab";
		String y = "0bdcaba";
		//longestSequence(x,y);
		int[][] cc = calArray(x, y);
		int i = x.length()-1;
		int j = y.length()-1;
		printLCS(cc, i, j, x, y);
	}
	
	public static String generateString(int length){
		StringBuffer buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		int range = buffer.length();
		for(int i=0; i<length; i++){
			sb.append(buffer.charAt(r.nextInt(range)));
		}
		return sb.toString();
	}
}
