package ArtOfPrograming;

public class ContainString {

	/*
	 * 思路一:
	 * 用一个数组做标记, 时间复杂度为O(m+n)
	 */
	public static boolean isContainsByArray(String s1, String s2){
		int n = s1.length();
		int m = s2.length();
		int[] flag = new int[26];
		for(int i=0; i<n; i++){
			flag[s1.charAt(i)-'A'] = 1;
		}
		for(int i=0; i<m; i++){
			if(flag[s2.charAt(i)-'A'] == 0)
				return false;
		}
		return true;
	}
	
	/*
	 * 思路二：
	 * 先对两个数组进行排序，然后在依次轮训比较
	 */
	public static boolean isContainsBySort(int[] s1, int[] s2){
		int i=0, j=0;
		int n = s1.length;
		int m = s2.length;
		quickSort(s1, 0, n-1);
		System.out.println(s1);
		quickSort(s2, 0, m-1);
		System.out.println(s2);
		while(i<n && j<m){
			while(i<n && j<m && s1[i] < s2[j]) i++;
			while(i<n && j<m && s1[i] > s2[j]) j++;
			i++;j++;
		}
		if(j != m) return false;
		return true;
	}
	
	/*
	 * 递归快速排序
	 */
	public static void quickSort(int[] s, int begin, int end){
		if(begin >= end) return;
		int i = partition(s, begin, end);
		quickSort(s, begin, i-1);
		quickSort(s, i+1, end);
	}
	
	/*
	 * 快速排序的一次划分，是快速排序的核心
	 * 写的时候需要注意的地方：
	 * 1. 第一个while循环退出的条件；
	 * 2. 内部循环也必须要加上i<j的条件；
	 * 3. 内部循环中s[i],s[j]和temp大小关系，不要搞反了；
	 * 4. 内部赋值也不要搞反了；
	 * 记住：一次划分结束后
	 * 标志位之前的数一定比它小1
	 * 标志位之后的数一定比它大
	 */
	public static int partition(int[] s, int begin, int end){
		if(begin>end) return -1;
		int i=begin, j=end;
		int temp = s[begin];
		while(i<j){
			while(i<j && s[j]>=temp) j--; /* 标志位之前的数一定比它小*/
			if(i<j) 
				s[i] = s[j]; /*后面的往前移*/
			while(i<j && s[i]<temp) i++; /* 标志位之前的数一定比它小*/
			if(i<j) 
				s[j] = s[i]; /*前面的往后移*/
		}
		s[i] = temp;
		return i;
	}
	
	/*
	 * 主函数测试用
	 */
	public static void main(String[] args) {
		String s1 = "ABCDKDJFJLSDFL";
		String s2 = "ABOPU";
		boolean bool = isContainsByArray(s1, s2);
		System.out.println("By Array, s1 is contains s2:" + bool);
		String ss = "EFGHIJKABCD";
		String sp = "CDFK";
		//System.out.println("By Sort: " + isContainsBySort(ss.toCharArray(), sp.toCharArray()));
		System.out.println("====================");
		int[] ch = {'4', '5', '6', '7', '8', '9','1','2', '3'};
		int index = 0;
		quickSort(ch, 0, ch.length-1);
		System.out.print("index is: " + index + " partition: ");
		System.out.println(ch);
	}

}
