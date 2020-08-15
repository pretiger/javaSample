package ch02;


public class callby {
	static void print2(String[] s) {
		for(int i=0; i <s.length; i++) {
			System.out.println(s[i]);
		}
	}
	public static void main(String[] args) {
		String[] s = {"강하나", "김철수", "이청천", "박철구", "개동구","강강원"};
		print2(s);
	}
}
