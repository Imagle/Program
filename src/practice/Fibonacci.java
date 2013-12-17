package practice;

public class Fibonacci {

	/**
	 * use recursion to solve Fibonacci
	 * @param n
	 * n is the parameter that want to calculate 
	 */
	public static int recursionMethod(int n){
		if(n==1 || n==2)
			return 1;
		else
			return recursionMethod(n-1)+recursionMethod(n-2);
	}
	
	/**
	 * 
	 * @param n
	 */
	public static int nonRecursionMethod(int n){
		int f1=1, f2=1, f3=0;
		for(int i=3; i<=n;i++){
			f3 = f1 + f2;
			int temp = f1;
			f1 = f2;
			f2 = f3;
		}
		return f3;
	}
	/**
	 * calculate the sum of all the numerical Fibonacci,
	 * such as f(1)+f(2)+...f(n).
	 */
	public static int sumOfFibonacci(int n){
		int result = 2, p = 1, q = 1;
			for(int i=3; i<=n; i++){		
				p += q;
				result += p;
				q = p-q;
			}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("recursionMethod: " + recursionMethod(40));
		System.out.println();
		System.out.println("nonRecursionMethod: " + nonRecursionMethod(40));
		System.out.println();
		System.out.println("SumOfFibonacci: " + sumOfFibonacci(7));
		String s = args[0];
		System.out.println(s.length());
		String s1 = "aaaa";
		String s2 = "aaaa";
		System.out.println( (s1==s2));
		System.out.println( s2.hashCode());
		System.out.println(s1.hashCode());
		StringBuffer  sp = new StringBuffer();
		
	}
}