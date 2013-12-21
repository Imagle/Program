package ArtOfPrograming;

public class Util {

	/*
	 * 交换两个数
	 */
	public static void swap(int a, int b){
		int temp = a;
		a = b;
		b = temp;
	}	
	
	public static void swap(int[] array, int srcindex, int desindex){
		int temp = array[srcindex];
		array[srcindex] = array[desindex];
		array[desindex] = temp;
	}
	
	/*
	 * 求三个数中的最大值，中值，最小值
	 * flag=0时，返回最小值；
	 * flag=1时，返回中值；
	 * flag=2时，返回最大值；
	 */
	public static int extrema(int a, int b, int c, int flag){
		if(flag==0){
			return a < b
					? ( a < c ? a : c)
					: ( b < c ? b : c);
		}else if(flag==1){
			return a < b
					?  a > c ? a : b > c ? c : b
					:  b > c ? b : a > c ? c : a;
			
		}else if(flag==2){
			return a > b
					? ( a > c ? a : c)
					: ( b > c ? b : c);
		}else{
			return -1;
		}
	}
	
	public static void main(String[] args) {
		int value = extrema(1, 3, 2, 1);
		System.out.println("value: " + value);
	}

}
