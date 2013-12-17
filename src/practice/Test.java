package practice;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;

public class Test {

	public void test(){
		f(new Tp(){
			public void method(){
				Map<String, String> map = new HashMap<String, String>();
				map.put("dsf", "sdf");
				String s = map.get("sdfsd");
				s.equals("test");
				String ss = null;
				ss.equals("dsfs");
			}
		});
	}
	public static void main(String[] args) {
		Test tt = new Test();
	}
	
	public void f(Tp p){
		p.method();
	}

}

abstract class Tp{
	public abstract void method(); 
}
