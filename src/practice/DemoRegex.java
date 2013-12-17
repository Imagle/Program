package practice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class DemoRegex {

	public static void main(String[] args) {
		
		Pattern regex1 = Pattern.compile("\\b([a-z]+)((?:\\s|\\<[^>]+\\>)+)(\\1\\b)", Pattern.CASE_INSENSITIVE);
		String replace1 = "\\e[7m$1\\e[m$2\\e[7m$3\\e[m";
		Pattern regex2 = Pattern.compile("^(?:[^\\e]*\\n)+", Pattern.MULTILINE);
		Pattern regex3 = Pattern.compile("^([^\\n]+)", Pattern.MULTILINE);
		System.out.println("hha");
		
		for(int i=0;i<args.length; i++){
			try{
				BufferedReader in = new BufferedReader(new FileReader(args[i]));
				String text = null;
				while((text = getPara(in))!= null){
					System.out.println("Befor regex1: " + text);
					text = regex1.matcher(text).replaceAll(replace1);
					System.out.println("After regex1: " + text);
					text = regex2.matcher(text).replaceAll("");
					text = regex3.matcher(text).replaceAll(args[i] + ": $1");
					
					System.out.print(text);
				}
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		String s="Then when you have found the shrubbery, you musthe " + 
				"cut down the mightiest tree in the forest..." + 
				"with... a herring!";
		System.out.println(Arrays.toString(s.split("\\bthe|you\\b")));
		System.out.println(s.replaceAll("[aeiou]", "_"));
	}
	public static String getPara(BufferedReader in) throws IOException{
		StringBuilder buf = new StringBuilder();
		String line = null;
		
		while( (line=in.readLine())!=null && (buf.length()==0||line.length()!=0)){
			buf.append(line + "\n");
		}
		return buf.length()==0 ?null: buf.toString();
	}
}
