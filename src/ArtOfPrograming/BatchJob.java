package ArtOfPrograming;

public class BatchJob {

	public static int batchJob(int n, int[] a, int[] b){
		int bestTime =1000000;
		int[] x = new int[n+1];
		int[] sum1 = new int[n+1];
		int[] sum2 = new int[n+1];
		for(int i=1; i<=n; i++){
			sum1[i] = 0;
			sum2[i] = 0;
			x[i] = 0;
		}
		sum1[0] = 0; sum2[0] = 0;
		int k = 1 ; 
		while(k>=1){
			x[k] = x[k] + 1;
			while(x[k]<=n)
				if(OK(k, x)){
					sum1[k] = sum1[k-1] + a[x[k]];
					sum2[k] = (sum1[k]>=sum2[k-1] ? sum1[k] : sum2[k-1]) + b[x[k]];
					if(sum2[k] < bestTime) break;
					else x[k] = x[k] + 1;
				}
				else{
					x[k] = x[k] + 1;
				}
			if(x[k] <= n && k<n)
				k = k + 1;
			else{
				if(x[k] <= n && k==n)
					if(bestTime > sum2[k])
						bestTime = sum2[k];
				x[k] = 0;
				k = k-1;
			}
		}
		return bestTime;
	}
	
	public static boolean OK(int k, int[] x){
		for(int i=1; i<k; i++)
			if(x[i] == x[k]) return false;
		return true;
	}
	
	public static void main(String[] args) {
		int[] a = {0,2,3,2};
		int[] b = {0,1,1,3};
		int n = 3;
		int bestTime = batchJob(n, a, b);
		System.out.println(bestTime);
	}
}
