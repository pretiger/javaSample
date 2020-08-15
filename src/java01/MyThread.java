package java01;
//쓰레드를 구현한 부분을 외부클래스로 분리
public class MyThread extends Thread {
	public MyThread(String name) {
		super(name);
	}
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			//현재 실행중인 쓰레드의 이름
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
