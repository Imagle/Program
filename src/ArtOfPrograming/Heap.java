package ArtOfPrograming;


public class Heap {

	public static void buildHeap(int[] array, int n){
		for(int i=n/2; i>=0; i--){
			adjustHeap(array, i, n);
		}
	}
	
	/*
	 * 最大堆的对调整算法
	 * i为指定的结点及其相应孩子结点进行调整；
	 */
	public static void adjustHeap(int[] array, int i, int len){
		int max = i;
		int left = i*2+1;
		int right = i*2+2;
		if(left<len && array[i]<array[left])
			max = left;
		if(right<len && array[right]>array[max])
			max = right;
		if(max != i){
			Util.swap(array, i, max);
			adjustHeap(array, max, len);
		}
	}
	
	public static void heapSort(int[] array){
		for(int i=array.length-1; i>0; i--){
			Util.swap(array, 0, i);
			adjustHeap(array, 0, i);
		}
	}
	
	/*
	 * 测试用
	 */
	public static void main(String[] args) {
		int[] test = {1, 2, 5, 3, 7, 4, 9, 6};
		buildHeap(test, test.length);
		System.out.println("After build: ");
		for(int i=0; i<test.length; i++)
			System.out.print( test[i] + "  ");
		heapSort(test);
		System.out.println("\nAfter sort: ");
		for(int i=0; i<test.length; i++)
			System.out.print( test[i] + "  ");
	}
	
}
