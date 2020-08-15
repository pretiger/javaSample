package java01;

public class RunnableExam implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	public static void main(String[] args) {
		//현재클래스의 인스턴스 객체 생성
		RunnableExam s1=new RunnableExam();
//		Thread t1=new Thread(s1,"쓰레드 1");
//		Thread t2=new Thread(s1,"쓰레드 2");
		//쓰레드 생성 new Thread(Runnable를 구현한 객체)
		Thread t1=new Thread(s1);//쓰레드가 추가
		Thread t2=new Thread(s1);
		t1.setName("쓰레드 1");
		t2.setName("쓰레드 2");
		t1.start();//쓰레드 실행요청 -> run() 실행
		t2.start();
	}
}
