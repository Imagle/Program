package practice;

public class BM {
	
	private String pattern;
	private String text;
	int bmGs[] ;
	int bmBc[] ;
	int m;
	int suff[];
	
	public BM(){
	}
	
	public BM(String pattern, String text){
		this.pattern = pattern;
		this.text = text;
		this.m = pattern.length();
		bmGs = new int[m];
		bmBc = new int[256];
		suff = new int[m];
	}
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
		this.m = pattern.length();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void boyerMoore(String text, String pattern){
		int j=0, i=0;
		while(j < (text.length()-pattern.length())){
			for(i=pattern.length()-1; i>=0 && text.charAt(i + j)==pattern.charAt(i);--i) ;
			if(i<0){
				System.out.println("find it, the position is " + j);
				return;
			}else{
				if(text.charAt(i+j) >= 255){
					System.out.println((int)text.charAt(i+j));
				}
				int temp = bmBc[(int)text.charAt(i+j)]-m+1+i;
				j += Max(bmGs[i], temp);
			}
		}
		System.out.println("not find it");
	}
	
	public void preBadChar(){
		int m = pattern.length();
		//先初始化为m
		for(int i=0; i<256; i++)
			bmBc[i] = m;
		//将bmGc设置成pattern出现的字符在pattern的最后一个位置距离m-1的距离
		for(int i=0; i<m; i++)
			bmBc[(int)pattern.charAt(i)] = m-1-i;
		/*
		for(int i=0; i<256; i++)
			if(bmBc[i] != m)
				System.out.println("i:" +i + "bmBc:" + bmBc[i] + " ");
		System.out.print("Over!");
		*/
	}
	
	public void preGoodSuffix(){
		int i,j=0;
		//先全部初始化成m
		for(i=0; i<m;i++){
			bmGs[i] = m;
		}
		for(i=m-1; i>=0; i--){
			if(suff[i] == i+1){
				for(; j<m-1-i; j++)
					if(bmGs[j] == m)
						bmGs[j] = m-1-i;
			}
		}
		for(i=0; i<= m-2; i++){
			bmGs[m-1-suff[i]] = m-1-i;
		}
	}
	
	public void preSuff(){
		int i=m-2;
		suff[m-1] = m;
		while(i>=0){
			int p, j;
			for(j=m-1, p=i; j>=0 && p>=0 && pattern.charAt(j)==pattern.charAt(p); j--,p--) ;
			suff[i--] = m-1-j;
		}
		/*
		for(i=0; i<m; i++){
			System.out.print(" " + suff[i]);
		}
		*/
	}
	
	public int Max(int a, int b){
		if(a>b)
			return a;
		else
			return b;
	}
	
	public static void main(String[] args) {
		//BM bm = new BM("bcababab", "sadljflsdjlfjlsajldfjlkasjl");
		BM bm = new BM("babac", "abbadcababacab");
		bm.preSuff();
		bm.preBadChar();
		bm.preGoodSuffix();
		bm.boyerMoore(bm.getText(), bm.getPattern());
	}
}
