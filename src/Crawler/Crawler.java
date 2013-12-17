package Crawler;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.*;
import org.apache.http.client.entity.*;
import org.apache.http.client.methods.*;
import org.apache.http.client.params.*;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BufferedHeader;
import org.apache.http.params.*;
import org.apache.http.protocol.*;
import org.apache.http.util.*;
import org.apache.http.impl.client.*;
import org.apache.http.impl.conn.tsccm.*;


public class Crawler {

	Queue<String> queue = new LinkedList<String>();
	
	static Hashtable<String, Integer> hashtable = new Hashtable<String, Integer>();

	String baseUrl = "www.sina.com";
	
	static int count = 0;

	public Crawler(String baseUrl){
		this.baseUrl = baseUrl;
	}
	
	private Crawler(){
	}
	
	public static void main(String[] args) {
		String s = "http://www.sina.com";
		//String s = "http://int.bupt.edu.cn/";
		Crawler crawler = new Crawler(s);
		crawler.queue.add(crawler.baseUrl);
		String md5Url = null;
		String contents = null;
		while(!crawler.queue.isEmpty()){
			String url = crawler.queue.poll();
			md5Url = MD5Url(url);
			System.out.println("url: " + url + " => " + "md5Url: " + md5Url);
			if(hashtable.containsKey(md5Url)){
				continue;
			}
			else{
				contents = getUrlContents(url);
				hashtable.put(md5Url, 1);
				List<String> urlList = parseUrlFromContents(contents);
				crawler.queue.addAll(urlList);
				count++;
			}
		}
		//System.out.println(contents);
	}
	
	@SuppressWarnings("deprecation")
	public static String getUrlContents(String url){
		HttpClient client = new DefaultHttpClient(); 
		HttpGet httpGet = new HttpGet(url);
		StringBuffer strBuf = new StringBuffer();
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.1.2)");
		httpGet.setHeader("Accept-Language", "zh-cn,zh;q=0.5");
		httpGet.setHeader("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7");
		try {
			HttpResponse response = client.execute(httpGet);
			if(HttpStatus.SC_OK == response.getStatusLine().getStatusCode()){
				HttpEntity entity = response.getEntity();
				if(entity!=null){
					BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent(), "UTF-8"));
					String line = null;
					if(entity.getContentLength()>0){
						strBuf = new StringBuffer((int)entity.getContentLength());
						while((line=reader.readLine()) != null){
							strBuf.append(line);
						}
					}
				}
				if(entity != null){
					entity.consumeContent();
				}
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return strBuf.toString();
	}

	public static List<String> parseUrlFromContents(String contents){
		List<String> list = new ArrayList<String>();
		String regex = "href=\"\\s*(http://[^\"]*)\\s*\""; 
		Pattern pattern = Pattern.compile(regex);
		Matcher m = pattern.matcher(contents);
		String str = new String();
		while(m.find()){
			str = m.group(1);
			str = str.replaceAll("\\s", "");
			String md5Url = MD5Url(str);
			if(hashtable.containsKey(md5Url)){
				continue;
			}
			//System.out.println(str);
			list.add(str);
		}
		return list;
	}
	
	public static String MD5Url(String url){
		MessageDigest md5 = null;
		String s = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
			md5.update(url.getBytes());
			byte[] bs = md5.digest();//进行加密运算并返回加密后的字符数组
			StringBuffer sb = new StringBuffer();
			for(int i=0; i<bs.length;i++){
				int v = bs[i]&0xff;
				if(v<16)
					sb.append(0);
				else
					sb.append(Integer.toHexString(v));	
				//System.out.println("bs[i] = " + bs[i] + "  ==>  v = " + v + "  ==>  base 16 = " + Integer.toHexString(v));
			}
			//System.out.println(bs.length);
			s = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		//System.out.println(s);
		return s;
	}
	
	public static void test(){
		String testString = "http://php.weather.sina.com.cn/search.php?city=' + customCity.city + '&dpc=1";
		testString = testString.replaceAll("\\s", "");
		System.out.println(testString);
		System.out.println(getUrlContents(testString));
		System.out.println("Over!! count is " + count);
	}
}
