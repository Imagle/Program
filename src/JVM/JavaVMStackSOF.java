package JVM;

/*
 * VM Args: -Xss128k
 */
public class JavaVMStackSOF {

	private int stackLength = 1;
	
	public void stackLeak(){
		stackLength++;
		stackLeak();
	}
	
	public static void main(String[] args) throws Throwable {
		JavaVMStackSOF jvs = new JavaVMStackSOF();
		try{
			jvs.stackLeak();
		}catch(Throwable e){
			System.out.println("stack length: " + jvs.stackLength);
			throw e;
		}
	}

}
