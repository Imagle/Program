package ArtOfPrograming;

import java.util.Random;

/*
 * 输入n个数，寻找一个数组中的最小的k个数
 * 比如：输入1,4,2,6,3,7,5,9,8
 * 输出最小的4个数:1,2,3,4
 */

public class kthSmallNumber {

	private static Random r = new Random();
	/*
	 * 思路一：
	 * 直接用快速排序将数组排好序，然后返回前k位数
	 * 平均时间复杂度为O(nlogn+k)
	 */
	public static int kthSmall(int[] array, int k){
		int n = array.length;
		ContainString.quickSort(array, 0, n-1);
		int i;
		for(i=0; i<k; i++)
			System.out.print( array[i] +" ");
		System.out.println();
		System.out.print("i is :" + i);
		return i;
	}
	
	/*
	 * 思路二
	 * 利用快速排序的划分，一次划分后会返回一个序号i，比较i与k的大小，分3种情况
	 * 1： 当i==k-1时，前i个元素就是所求的最小的k个数(因i的下标从0开始)；
	 * 2: 当i<k-1时，则最小的k个数一定包含在0~i-1个元素中；
	 * 3：当i>k-1时，最小的k个数既存在于前i个数中,又存在于i+1~n-1个数中
	 * 				也即，最小的数包含前面0-i个数和后面k-i-1个数
	 * 
	 * 有点类似利用快速排序的思想, 时间复杂度近似O(n)
	 */
	public static int kthSmallPartition(int[] array, int begin, int end, int k){

		int i = ContainString.partition(array, begin, end);
		if((i-begin)==k-1)
			return array[i];
		else if((i-begin)<k-1){
			return kthSmallPartition(array, i+1, end, k-i+begin-1);
		}
		else{
			return kthSmallPartition(array, begin, i-1, k);
		}
	}

	public static int findkth(int[] array, int begin, int end, int k){
		if(begin < end){
			int index = ContainString.partition(array, begin, end);
			if(index == k-1)
				return array[index];
			else if( index > k-1){
				return findkth(array, begin, index-1, k);
			}
			else if(index < k-1){
				return findkth(array, index+1, end, k);
			}
		}
		return -1;
	}
	
	/*
	 * 思路三
	 * 利用堆排序，
	 * 首先建立k个元素的最大堆；
	 * 然后拿剩下的元素和堆顶的元素比较；
	 * 如果比堆顶元素小，则替换掉堆顶元素，进行堆调整；
	 * 依次进行，最后堆中元素就为最小的k个元素。
	 * 时间复杂度为O(n+(n-k)logk)
	 */
	public static void kthSmallByHeap(int[] array, int k){
		Heap.buildHeap(array, k);
		for(int i=k; i<array.length; i++)
			if(array[i]<array[0]){
				Util.swap(array, i, 0);
				Heap.adjustHeap(array, 0, k);
			}
	}
	
	public static void main(String[] args) {
		int[] test = {1, 2, 5, 3, 7, 4, 9, 6, 8};
		//kthSmall(test, 4);
		int k = 3;
		//kthSmallPartition(test, 0, test.length-1, k);
		findkth(test, 0, test.length-1, k);
		//kthSmallByHeap(test, k);
		for(int i=0; i<k; i++)
			System.out.print(test[i] + " ");
	}

}
