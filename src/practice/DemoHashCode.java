
//本程序是为了验证equals和hashCode两个函数。
package practice;

public class DemoHashCode {
	public static void main(String[] args){
		HashNot hn1 = new HashNot("you are");
		HashNot hn2 = new HashNot("you are");
		HashYes hy1 = new HashYes("you are");
		HashYes hy2 = new HashYes("you are");
		HashYes hy3 = hy1;
		System.out.println("HashNot");
		System.out.println("hn1 == hn2: " + (hn1 == hn2));
		System.out.println(hn1.hashCode());
		System.out.println(hn2.hashCode());
		System.out.println("hn1.equals(hn2): " + (hn1.equals(hn2)));
		System.out.println("--------------");
		System.out.println("HashYes");System.out.println("hy1 == hy2: " + (hy1 == hy2));
		System.out.println("hy1 == hy3: " + (hy1 == hy3));		
		System.out.println("hy1.equals(hy2): " +(hy1.equals(hy2)));
		System.out.println(hy1.hashCode());
		System.out.println(hy2.hashCode());
		
		
	}
}

class HashNot{
	private String value;
	
	public HashNot(String value){
		this.value = value;
	}
	
	public boolean equals(Object obj){
		HashNot hn = (HashNot) obj;
		if(value.equals(hn.getValue())) 
			return true;
		else return false;		
	}
	
	public String getValue(){
		return this.value;
	}
}

class HashYes{
	private String value;
	
	public HashYes(String value){
		this.value = value;
	}
	@Override
	public boolean equals(Object obj){
		HashYes hy = (HashYes) obj;
		if(value.equals(hy.getValue()))
			return true;
		else return false;		
	}
	@Override
	public int hashCode(){
		final int off = 31;
		return value.hashCode()*off;
	}
	public String getValue(){
		return this.value;
	}
}
