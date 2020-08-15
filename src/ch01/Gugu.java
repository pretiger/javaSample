package ch01;

public class Gugu {
	public static void main(String[] args) {
		System.out.println("구구단");
		for(int i=2;i<10;i++) {
			for(int j=1;j<10;j++) {
				if(j==1) System.out.println(i+"단");
				System.out.println(i+"X"+j+"="+i*j);
			}
		}
	}
}
