package java01;

public class ThreadJoin extends Thread {
	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"쓰레드 시작...");
		for(int i=1; i<=3; i++) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName()+"쓰레드 종료 ...");
	}
	public static void main(String[] args) {
		System.out.println(Thread.currentThread().getName()+"쓰레드 시작...");
		ThreadJoin t1=new ThreadJoin();
		t1.setName("백그라운드 쓰레드");
		t1.start();
		try {
			//현재 쓰레드의 백그라운드가 종료될 때까지 다른 쓰레드(메인쓰레드)의 실행을 중지시킴
			//join()을 사용하면 항상 try catch문을 사용해준다
			t1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"쓰레드 종료...");
	}
}
