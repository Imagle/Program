package ArtOfPrograming;

public class AnTh {

	/*
	 * 利用减治法求解a的n次方
	 * 若a为偶数，则计算a^n/2次方
	 * 若a为奇数，则计算a^(n-1)次方
	 */
	public static long power(int a, int n){
		if(n==1) return a;
		long temp;
		if(n%2==0){
			temp = power(a, n/2);
			return temp * temp;
		}
		else{
			temp = power(a, n-1);
			return temp*a;
		}
	}
	
	public static void main(String[] args) {
		long result = power(5, 10);
		System.out.println(result);
	}

}
