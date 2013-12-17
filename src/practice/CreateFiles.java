package practice;

import java.io.File;

public class CreateFiles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String root = System.getProperty("user.dir") + "\\src\\";
		System.out.println(root);
		File rootFiles = new File(root);
		if(rootFiles.exists()){
			System.out.println("存在目录    "+ rootFiles.getAbsolutePath());
		}else{
			System.out.println("创建目录     " + rootFiles.mkdirs());
		}
		System.out.println("******************************");
		String my = root + "try/test" ;
		File myPackage = new File(my);
		if(myPackage.exists()){
			System.out.println("存在目录      "+ myPackage.getAbsolutePath());
		}else{
			System.out.println("创建目录      "+ myPackage.mkdirs());
		}
	}
}
