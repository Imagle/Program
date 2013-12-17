package practice;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DemoRegex1 {

	public static void main(String[] args) {
		/*
		print("1 " +"abc".matches("a?"));
		print("2 " +"".matches("a*"));
		print("3 " + "ss".matches("."));
		print("4 " + "ssss".matches("s?"));
		print("5 " + "sss".matches("s{1,4}"));
		print("6 " + "ss".matches("s+"));
		
		print("1 " + "s".matches("[a-x]"));
		print("2 " + "s".matches("[^abc]"));
		print("3 " + "s".matches("[^sabc]"));
		print("4 " + "A".matches("[a-z[A-Z]]"));
		print("5 " + "A".matches("[A-Z&&[RF]]"));
		print("6 " + "F".matches("[A-Z&&[RF]]"));
		
		print("sf9898sd".replaceAll("\\d", "-"));
		print("sf9898sd".replaceFirst("\\d", "-"));
		print("------------");
		print(" \n\r\t".matches("\\s{4}"));
		print("\\".matches("\\\\"));
		
		print("hello world".matches("^h.*"));
		print("hello world".matches(".*ld$"));
		print("hello world".matches("^h[a-x]{3}o\\s\\w+"));
		print(" dsfd\n".matches("^[\\s&&[^\\n]]\\w+\\n$"));
		print("hello world".matches(".*\\b\\w+$"));
		 */
		/*
		String s = "13554-6657-6554-01";
		String s1 = "Java JAVA java jAvA thi siJava soga";
		Pattern pattern = Pattern.compile("java", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(s1);
		//print(matcher.matches());
		//matcher.reset();
		//�ж������������ʽ  "[\\w[-.]]+@[\\w[-.]]+\\.\\w+"
		print("a_b.new.b@gmail.com".matches("[\\w[-.]]+@[\\w[-.]]+\\.\\w+"));
		print("s._l@..c".matches("\\w[\\w[-.]]+\\w@[\\w[-.]]+\\.\\w+"));
		StringBuffer buf = new StringBuffer(); 
		int i=0;
		try{
			while(matcher.find()){
				i++;
				if( i%2 == 0){
					matcher.appendReplacement(buf, "java");
				}else{
					matcher.appendReplacement(buf, "JAVA");
				}
				print(i +" "+ buf.toString());
			}
			matcher.appendTail(buf);
			print(i + " " + buf);
		}catch(IllegalStateException e){
			
		} 
		matcher.reset();
		print(matcher.find());
		print(matcher.start() + " - " + matcher.end());
		print(matcher.find());
		print(matcher.start() + " - " + matcher.end());
		print(matcher.find());
		print(matcher.start() + " - " + matcher.end());
		print(matcher.find());
		
		print(matcher.lookingAt());
		*/
		String sp = "Null pointer dereference of 'mf(dsf)ds a dsf' where null is returned from a method";
		//Pattern patternp = Pattern.compile("(?<=')(\\w+)(?=')");
		Pattern patternp = Pattern.compile("(?<=')([^']*)(?=')");
		Matcher m = patternp.matcher(sp);
		if(m.find()){
			System.out.println(m.group());
		}
		Integer intger = null;
		Object dob = (Object)intger;
	}
	
	public static void print(Object obj){
		System.out.println(obj);
	}

}