package java01;

public class ExternThread {
	public static void main(String[] args) {
		//쓰레드를 구현한 객체 생성
		MyThread t1=new MyThread("쓰레드 1");
		MyThread t2=new MyThread("쓰레드 2");
		//쓰레드 작업 할당 요청
		t1.start();//run()이 호출됨
		t2.start();
	}

}
