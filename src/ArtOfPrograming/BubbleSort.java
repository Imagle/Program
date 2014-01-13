package ArtOfPrograming;

public class BubbleSort {

	/*
	 * 方法一：原始的冒泡排序
	 */
	public static void bubbleSort1(int[] r){
		int n = r.length;
		for(int i=1; i<n; i++)
				for(int j=0; j<n-i; j++){
					if(r[j] > r[j+1]){
						int temp = r[j];
						r[j] = r[j+1];
						r[j+1] = temp;
					}
				}
	}
	
	/*
	 * 方法二: 巧妙：
	 * 记录下上次最后交换的位置，该位置之后肯定是有序的
	 * 所有的交换都会发生在该位置之前
	 */
	public static void bubbleSort2(int[] r){
		int n = r.length;
		int exchange = n-1;
		while(exchange != 0){
			int bound = exchange;
			exchange = 0;
			for(int i=0; i< bound; i++)
				if(r[i]>r[i+1]){
					int temp = r[i];
					r[i] = r[i+1];
					r[i+1] = temp;
					exchange = i;
				}
		}
	}
	
	/*
	 * 方法三：对方法一的改进，如果在一轮比较中，如果没有交换则说明序列已经有序
	 */
	public static void bubbleSort3(int[] r){
		int n = r.length;
		boolean flag = true;
		while(flag){
			flag = false;
			for(int j=1; j< n ; j++){
				if(r[j-1] > r[j]){
					int temp = r[j-1];
					r[j-1] = r[j];
					r[j] = temp;
					flag = true;
				}
			}
			n--;
		}
	}
	
	public static void main(String[] args) {
		int[] test = {1,8,3,4,9,5,6,2,7};
		bubbleSort3(test);
		for(int i=0; i< test.length ; i++)
			System.out.println(test[i]);
	}

}
