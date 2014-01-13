package ArtOfPrograming;

/**
 * 最优二叉查找树
 */
public class OptimalBinarySearchTree {

	/**
	 * 动态规划法解最优二叉查找树
	 * C[n+1][n+1], C[i][j]表示二叉查找树T(i,j)的平均比较次数
	 * R[n+1][n+1], R[i][j]表示二叉查找树T(i,j)的根结点的序号
	 * 动态规划函数
	 * {@code C(i,i-1) = 0, 1<=i<=n+1;} 
	 * {@code C(i,i) = P[i], 1<=i<=n; }
	 * {@code C(i,j) = min( C(i,k-1)+C(k+1,j) + p[i]+...+p[j] ) }
	 * 按{ 对角线逐条 }计算每一个C(i,j),R(i,j)
	 * 使C(i,j)取最小值的k就为二叉查找树T(i,j)的根序号
	 * @param n 节点数量
	 * @param p 每个节点的查找概率, 下标从1开始
	 * @param C 存储每个二叉查找树平均比较次数的二位数组
	 * @param R 二叉查找树的根节点的序号
	 * @author Ruiqiang
	 * @return double 最优二叉查找树的查找概率
	 */
	public static double optimalBST(int n, double p[], double[][] C, int[][] R){
		for(int i=1; i<=n; i++){
			C[i][i] = p[i];
			C[i][i-1] = 0;
			R[i][i] = i;
		}
		int d=1;
		while(d<=n-1){
			for(int i=1; i<=n-d; i++){
				double min=100, sum=0;
				int mink=i, j = i+d;
				for(int k=i; k<=j; k++){
					sum += p[k];
					if(C[i][k-1]+C[k+1][j] < min){
						min =C[i][k-1]+C[k+1][j] ;
						mink = k;
					}
				}
				C[i][j] = sum + min;
				R[i][j] = mink;
			}
			d++;
		}
		return C[1][n];
	}
	
	public static void main(String[] args) {
		double[] p = {0.0, 0.1, 0.2, 0.4, 0.3};
		double[][] C = new double[p.length+1][p.length];
		int[][] R = new int[p.length+1][p.length];
		System.out.println(optimalBST(p.length-1, p, C, R));
	}

}
