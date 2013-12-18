package practice;

public class CPULine {
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		for(;;){
//			for(int i=0;i<800000000;i++) ;
//			Thread.sleep(10);
//		}
		int busyTime = 150;
		int idleTime = 10;
		long start = 0;
		while(true)
		{
			start = System.currentTimeMillis();
			while((System.currentTimeMillis() - start) <= busyTime); 
			Thread.sleep(idleTime);
//				System.out.println(System.currentTimeMillis() - start);
			//System.out.println(" ");
			//System.out.println(start);
		}
	}

}
