package practice;

import java.io.File;

public class NPD {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "dsfsdfs";
		String[] ss = s.split("x");
		String[] gss = null;
		/*
		for(String g: gss){
			System.out.println(g);
		}
		*/
		File file = new File("test");
		file.getName();
		//System.out.println("" + ss.length + ss);
		System.out.println(file.getName());
	}

}
