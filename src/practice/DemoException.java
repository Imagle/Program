package practice;

public class DemoException {

	private static final String MESSAGE="taobao";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DemoException de = new DemoException();
		try{
			de.test();
		}catch(Exception e){
			e.printStackTrace();
			//System.out.println(e);
		}
		
		String a = "taobao";
		String b = "tao";
		String c = "bao";		
		System.out.println( a==MESSAGE);
		//System.out.println(a.)
		System.out.println(a.hashCode());
		String d = b+c;
		System.out.println(d.hashCode());
		System.out.println(MESSAGE.hashCode());
		System.out.println( d==MESSAGE );
		System.out.println((b+"bao")==MESSAGE);
		StringBuilder s;
	}
	
	public void test(){
		String s = null;
		try{
			s.trim();
		}catch(NullPointerException e){
			System.out.println("catch exception");
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
