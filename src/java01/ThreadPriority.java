package java01;

public class ThreadPriority extends Thread {
	public ThreadPriority(String name) {
		super(name);
	}
	@Override
	public void run() {
		for(int i=1; i<=10; i++) {
			System.out.println(
					Thread.currentThread().getName()+"==>"+i);
		}
	}
	
	public static void main(String[] args) {
		ThreadPriority e1=new ThreadPriority("쓰레드1");
		ThreadPriority e2=new ThreadPriority("쓰레드2");
		//쓰레드2에 최고 우선순위 설정
		//최고(10) ~ 최저(1) , 기본(5) 숫자로 대체 가능
		e1.setPriority(MIN_PRIORITY);
		e2.setPriority(MAX_PRIORITY);
		//쓰레드의 우선순위 값 확인
		System.out.println(e1.getPriority());
		System.out.println(e2.getPriority());
		e1.start();
		e2.start();
	}
}
