package core.java;

import java.util.*;
import java.io.*;
import java.util.concurrent.*;

public class FutureTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory(e.g. ):");
		String directory = in.nextLine();
		System.out.println("Enter keyword(e.g. volatile): ");
		String keyword = in.nextLine();
		
		FutureTest ft = new FutureTest();
		MatchCounter mc = ft.new MatchCounter(new File(directory), keyword);
		FutureTask<Integer> task = new FutureTask<Integer>(mc);
		Thread t = new Thread(task);
		t.start();
		try{
			System.out.println(task.get() + "matching file.");
		}catch(ExecutionException e){
			e.printStackTrace();
		}catch(InterruptedException e){}
	}

	class MatchCounter implements Callable<Integer>{
		private int count;
		private File directory;
		private String keyword;
		
		public MatchCounter(File dir, String keyword){
			this.directory = dir;
			this.keyword = keyword;
		}
		
		public Integer call(){
			count = 0;
			try{
				File[] files = directory.listFiles();
				ArrayList<Future<Integer>> results = new ArrayList<Future<Integer>>();
				for(File file : files){
					if(file.isDirectory()){
						MatchCounter counter = new MatchCounter(file, keyword);
						FutureTask<Integer> task = new FutureTask<Integer>(counter);
						results.add(task);
						Thread t = new Thread(task);
						t.start();
					}
					else{
						if(search(file)) count++;
					}
				}
				for(Future<Integer> result : results)
					try{
						count += result.get();
					}catch(ExecutionException e){
						e.printStackTrace();
					}
			}catch(InterruptedException e){
			}
			return count;
		}
		
		public boolean search(File file){
			try{
				Scanner in = new Scanner(new FileInputStream(file));
				boolean found = false;
				while( !found && in.hasNextLine()){
					String line = in.next();
					if( line.contains(keyword))
						found = true;
				}
				in.close();
				return found;
			}catch(IOException e){
				return false;
			}
		}
	}
}
