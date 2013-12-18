package practice;
/*
public class TestMain {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("test\n");
	}
	
	public int main(int t){
		return t;
	}

	public int you(){
		int a = main(23);
		return 0;
	}
}
*/
import java.util.*;

public class TestMain{
	public void func1(){
		Map<String, String> map = new HashMap<String, String>();
		Iterator it = map.keySet().iterator();
		while(it.hasNext()){
			Object key = it.next();
		}
	}

	public void func2(){
		Map<String, String> map = new HashMap<String, String>();
		Set<String> keys = map.keySet();
		for(String key : keys){
			System.out.println("key: " + key);
		}
	}

	public void func3(){
		Map<String, String> map = new HashMap<String, String>();
		for(Iterator it = map.keySet().iterator(); it.hasNext();){
			System.out.println("key: " + it.next());
		}
	}
	
	public static void main(String[] args){
		System.out.println("OK\n");
	}
}