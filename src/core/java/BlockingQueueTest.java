package core.java;

import java.io.*;
import java.util.*;
import java.util.concurrent.*;


public class BlockingQueueTest {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.print("Enter base directory(e.g. ):");
		String directory = in.nextLine();
		System.out.println("Enter keyword(e.g. volatile): ");
		String keyword = in.nextLine();
		final int FILE_QUEUE_SIZE = 10;
		final int SEARCH_THREADS = 100;
		BlockingQueue<File> queue = new ArrayBlockingQueue<File>(FILE_QUEUE_SIZE);
		BlockingQueueTest bqt = new BlockingQueueTest();
		FileEnumerationTask fet = bqt.new FileEnumerationTask(queue, new File(directory));
		new Thread(fet).start();
		for(int i=1; i<= SEARCH_THREADS; i++)
			new Thread(bqt.new SearchTask(queue, keyword)).start();

	}

	class FileEnumerationTask implements Runnable{
		private BlockingQueue<File> queue;
		private File startingDirectory; 
		public File DUMMY = new File("");
		
		public FileEnumerationTask(BlockingQueue<File> queue, File dir){
			this.queue = queue;
			this.startingDirectory = dir;
		}
		
		public void run(){
			try {
				enumerate(startingDirectory);
				queue.put(DUMMY);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public void enumerate(File file) throws InterruptedException{
			File[] list = file.listFiles();
			for( File f : list){
				if( f.isDirectory())
					enumerate(f);
				queue.put(f);
			}
		}
	}
	
	class SearchTask implements Runnable{
		private BlockingQueue<File> queue;
		private String keyword;
		private File DUMMY = new File("");
		
		public SearchTask(BlockingQueue<File> queue, String keyword){
			this.queue = queue;
			this.keyword = keyword;
		}
		
		public void run(){
			try{
				boolean done = false;
				while(!done){
					File file = queue.take();
					if(file == DUMMY){
						queue.put(file);
						done = true;
					}
					else{
						search(file);
					}
						
				}
			}catch(IOException e){
				e.printStackTrace();
			}catch(InterruptedException e){
			}
		}
		
		public void search(File file) throws IOException{
			Scanner in = new Scanner(new FileInputStream(file));
			int lineNumber = 0;
			while(in.hasNextLine()){
				lineNumber++;
				String line = in.next();
				if( line.contains(keyword))
					System.out.printf("%s:%d:%s%n", file.getPath(), lineNumber, line);
			}
			in.close();
		}
	}
}
