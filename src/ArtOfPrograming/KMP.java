package ArtOfPrograming;

public class KMP {

	public static void kmp(char[] des, char[] T){
		int[] next = new int[T.length];
		getNext(T, next);
		int i=0, j=1;
		while(des.length-i > T.length-j && j<T.length){
			if(des[i] == T[j]){
				j++;
				i++;
			}
			else{
				j = next[j];
			}
		}
		if(j == T.length) i = i-j+1;
		System.out.println("index is " + i);
		
	}
	
	public static void getNext(char[] T, int[] next){
		next[1] = 0;
		int j = 1, k = 0 ;
		while( j < T.length-1 ){
			if(k==0 || T[j] == T[k]){
				j++;
				k++;
				next[j] = k;
			}
			else 
				k = next[k] ;
		}
	}
	
	public static void main(String[] args) {
		String a = "ababcabcacbab";
		String b = "0abcac";
		kmp(a.toCharArray(), b.toCharArray());
	}

}
