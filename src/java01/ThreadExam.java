package java01;

public class ThreadExam extends Thread{
//	public ThreadExam(String name) {
//		//부모클래스(Thread)의 생성자 호출, 주석처리 Ctrl + /
//		super(name);
//	}
	//class 자식 extends 부모
	//ThreadExam is a Thread
	public void run() {
		//현재 실행중인 Thread이름
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String[] args) {
		//쓰레드 생성
		ThreadExam s1=new ThreadExam();
		ThreadExam s2=new ThreadExam();
		ThreadExam s3=new ThreadExam();
		s1.setName("쓰레드1");
		s2.setName("쓰레드2");
		s3.setName("쓰레드3");
//생성자를 사용할시 사용하는 방법
//		ThreadExam s1=new ThreadExam("쓰레드 1");
//		ThreadExam s2=new ThreadExam("쓰레드 2");
//		ThreadExam s3=new ThreadExam("쓰레드 3");
		
		//쓰레드 실행 
		s1.start();//쓰레드 객체를 생헝수 start()를 호출하면 run()이 다음에 자동호출
		s2.start();
		s3.start();
		
	}

}
