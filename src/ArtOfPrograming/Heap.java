package ArtOfPrograming;


public class Heap {

	public static void buildHeap(int[] array, int n){
		for(int i=n/2; i>=0; i--){
			siftHeap(array, i, n);
			//adjustHeap(array, i, n);
		}
	}
	
	/*
	 * 递归实现
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
	
	/*
	 * 非递归实现
	 * 最大堆的调整算法:筛选法
	 * 筛选法：每次从根节点到叶子节点调整
	 */
	public static void siftHeap(int[] a, int k, int n){
		int i = k, j=2*k +1;
		while(j<n){
			if(j<n-1 && a[j]<a[j+1]) j++;
			if(a[i] > a[j]) 
				break;
			else{
				Util.swap(a, i,j);
				i=j;
				j = 2*i +1;
			}
		}
	}
	
	/*
	 * 非递归实现
	 * 插入调整法：每次从新插入的节点向根节点调整
	 */
	public static void insertHeap(int[] a, int k){
		//i为当前新插入的节点，j为i的双亲节点
		int i = k, j;
		while(i>0){
			j = (i-1)/2;
			if(a[i] < a[j])
				break;
			else{
				Util.swap(a, i,j);
				i = j;
			}
		}
	}
	
	/*
	 * 堆排序
	 */
	public static void heapSort(int[] array){
		for(int i=array.length-1; i>0; i--){
			Util.swap(array, 0, i);
			//adjustHeap(array, 0, i);
			siftHeap(array, 0, i);
		}
	}
	
	/*
	 * 测试用
	 */
	public static void main(String[] args) {
		int[] test = {1, 2, 5, 3, 7, 4, 9, 6};
		int[] test1 = {1, 2, 5, 3, 7, 4, 9, 6};
		for(int i=0; i<test.length; i++)
			insertHeap(test, i);
		System.out.println("insert build: ");
		for(int i=0; i<test.length; i++)
			System.out.print( test[i] + "  ");
		
		buildHeap(test1, test1.length);
		System.out.println("\nsift build: ");
		for(int i=0; i<test.length; i++)
			System.out.print( test[i] + "  ");
		
		heapSort(test);
		System.out.println("\nAfter sort: ");
		for(int i=0; i<test.length; i++)
			System.out.print( test[i] + "  ");
	}
	
}
