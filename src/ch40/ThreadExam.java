package ch40;

public class ThreadExam extends Thread{
	public ThreadExam(String n) {
		super(n);
	}
	@Override
	public void run() {
		for(int i=0; i<5; i++) {
			System.out.println(Thread.currentThread().getName());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public static void main(String[] args) {
		ThreadExam e1= new ThreadExam("쓰레드1");
		ThreadExam e2= new ThreadExam("쓰레드2");
		ThreadExam e3= new ThreadExam("쓰레드3");
		e1.start();
		e2.start();
		e3.start();
	}
}
