package ch40;

public class SingleThread {
	static void print() {
		System.out.println("시작 쓰레드명:"+Thread.currentThread().getName());
		for(int i=0; i<5; i++) {
			System.out.println(i);
		}
	}
	public static void main(String[] args) {
		print();
		print();
	} 
}
