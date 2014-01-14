package ArtOfPrograming;

public class MergeSort {

	/*
	 * 递归版本
	 * @param temp-临时数组，存放排序前的元素
	 * @param s, 起始位
	 * @param t，结束位
	 */
	public static void mergeSort(int[] a, int[] temp, int s, int t){
		if(s<t){
			int m = (s+t)/2;
			mergeSort(a,temp,s, m);
			mergeSort(a,temp,m+1,t);
			merge(a, temp, s, m,t);
		}
	}
	
	public static void mergeSortUnRecursive(int[] a){
		if(a == null || a.length < 1) return;
		int step = 1;
		int[] temp = new int[a.length];
		while(step <= a.length){
			for(int i=0; i+step<=a.length-1; i += step*2){
				int low = i, mid = i+step-1, high = i+step*2-1;
				if(high > a.length-1) high = a.length-1;
				merge(a, temp, low, mid, high);
			}
			step *= 2;
		}
		temp = null;
	}
	
	/*
	 * merge函数
	 */
	public static void merge(int[] a, int[] temp, int s, int m, int t){
		int i = s, j = m+1, k = s;
		while(i<=m && j<=t){
			if(a[i]<=a[j])
				temp[k++] = a[i++];
			else
				temp[k++] = a[j++];
		}
		if(i<=m)
			while(i<=m)
				temp[k++] = a[i++];
		else
			while(j<=t)
				temp[k++] = a[j++];
		/*
		 * 将temp数组内已排好的元素复制到a数组中
		 */
		k--;
		for(; k>=s;k--)
			a[k] = temp[k];
	}
	
	public static void main(String[] args) {
		int a[] = {3, 5, 3, 6, 4, 7, 5, 7, 4};
		int[] temp = new int [a.length];
		System.out.print("Before sort: ");
		Util.print(a);
		//mergeSort(a, temp, 0, a.length-1);
		mergeSortUnRecursive(a);
		System.out.print("After sort, a:  ");
		Util.print(a);
		System.out.print("\nAfter sort, temp:  ");
		Util.print(temp);
		
	}

}
