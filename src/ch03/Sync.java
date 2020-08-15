package ch03;

public class Sync {
	public static void main(String[] args) {
		//쓰레드를 상속받은 객체 생성
		ATM atm=new ATM();
		//쓰레드 생성 new Thread (쓰레드구현클래스, 쓰레드 이름)
		Thread mom=new Thread(atm, "mom");
		Thread son=new Thread(atm, "son");
		//쓰레드 실행 요청, start()실행시 자동으로 run()이 호출실행
		mom.start();
		son.start();
	}
}
