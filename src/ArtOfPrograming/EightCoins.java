package ArtOfPrograming;

/*
 * 在8枚外观相同的硬币中，有一枚是假币，并且已知假币与真币的重量不同
 * 但不知道假币与真币相比较是轻还是重
 * 可以通过一架天平来任意比较两组硬币
 */
public class EightCoins {

	/*
	 * 思路：
     * 1. 将前6个分为两组进行比较，比较两组的重量；
     * 2. 如果两组重量相同，则说明假币一定在最后两个元素之中，此时只用通过和前6枚任意一枚比较一次就可以测出假币；
     * 3. 如果两组重量不同，说明假币存在于这前6枚硬币之中，此时递归处理前六枚；
     * 4. 当还剩4枚硬币的时候，将前两个分为两组，比较是否相同；
     * 5. 如果不同，假币肯定在其中一枚，此时通过一次比较就可以测出；
     * 6. 如果相同，假币定在后面2枚中，通过一次比较也可测出。
	 */
	public static int coin(int[] a, int endindex){
		int left=0, right=0;
		int i = 0;
		for(; i<=(endindex-2)/2; i++)
			left += a[i];
		for(; i<=endindex-2; i++)
			right += a[i];
		if(left != right){
			if(a.length != 4) 
				return coin(a, endindex-2);
			else
				return a[0]==a[i]?a[1]:a[0];
		}
		else
			return a[0]==a[i]?a[i+1]:a[i];
	}
	
	public static void main(String[] args) {
		int[] a = {6,6,8,6,6,6,6,6};
		System.out.println(coin(a,7));
	}

}
