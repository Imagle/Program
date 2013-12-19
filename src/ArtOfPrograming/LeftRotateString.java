package ArtOfPrograming;

public class LeftRotateString {

	/*
	 * 逆序字符串
	 */
	public static char[] nixu(char[] s, int begin, int end){
		if(begin > end) return null;
		char temp;
		for(; begin<=end; begin++, end--){
			temp = s[begin];
			s[begin] = s[end];
			s[end] = temp;
		}
		return s;
	}

	/*
	 * 左旋转n位字符串s，
	 * 使用ba = (br)^T(ar)^T = (rarb)^T
	 * 三次逆序字符串
	 */
	public static char[] leftRotateString(char[] s, int n){
		int length = s.length;
		s = nixu(s, 0, n-1);
		s = nixu(s, n, length-1);
		s = nixu(s, 0, length-1);
		return s;
	}
	
	/*
	 * 利用最大公因数的特点，就字符串分成gcd(n,m)个组, 每个组内有n/gcd(n,m)个元素;
	 * 每个组内每个元素固定移动1位，位间距m位,每个组内元素移动结束后，该位置就是最终的位置。
	 * 该算法的思想类似希尔排序的思想，现将元素分组，找到组内元素移动规律，进行分组移动。
	 * 每组内的位置有公式k=(j+i*m)%n算出, j为组号； 
	 */
	public static char[] rotate(char[] s, int m){
		int n = s.length;
		int groupNum = gcd(n, m);
		int elementInSub = n/groupNum;
		int i,j;
		char temp;
		for(j=0; j<groupNum; j++){
			temp = s[j];
			for(i=0; i<elementInSub-1; i++)
				s[(j+i*m)%n] = s[(j+(i+1)*m)%n];
			s[(j+i*m)%n] = temp;
		}
			
		/*
		int temp=0, k=0,i;
		if(gcd(n,m) == 1){
			char tempChar = s[0];
			for(i=0; i<n-1; i++){
				s[i*m%n] = s[(i+1)*m%n];
				//s[temp] = s[k];
				//temp = k;
			}
			//s[k] = tempChar;
			s[i*m%n] = tempChar;
		}
		*/
		return s;
	}

	public static int gcd(int a, int b){
		if(a<b) return gcd(b,a);
		if(b==0) return a;
		else{
			if((a%2) == 0){
				if((b%2) == 0)
					return 2*gcd(a>>1, b>>1);
				else
					return gcd(a>>1, b);
			}else{
				if((b%2) == 0)
					return gcd(a, b>>1);
				else
					return gcd(b, a-b);
			}
		}
	}
	
	public static int oldgcd(int a, int b){
		if(a<b) return oldgcd(b, a);
		if(a%b == 0) return b;
		return oldgcd(b, a%b);
	}
	
	public static void main(String[] args) {
		char[] s = {'a', 'b', 'c', 'd', 'e', 'f','g', 'h'};
		//char[] s = {'1', '2', '3', '4', 'a', 'b', 'c', 'd', 'e'};
		//s = leftRotateString(s, 2);
		s = rotate(s, 7);
		System.out.println(s);
		
		int b;
		long begin, end ;
		begin = System.nanoTime();
		b = gcd(4, 3);
		System.out.println(b);
		end = System.nanoTime();
		System.out.println("new gcd: " + (end-begin));
		
		/*
		begin = System.nanoTime();
		b = gcd(6, 3);
		System.out.println(b);
		end = System.nanoTime();
		System.out.println("old gcd: " + (end-begin));
		*/
	}

}
