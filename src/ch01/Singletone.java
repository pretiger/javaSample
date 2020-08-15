package ch01;

public class Singletone {

	private static Singletone instance;
	
	private Singletone() {
		System.out.println("생성자 호출");
	}
	
	public static Singletone getInstance() {
		if(instance == null) {
			instance=new Singletone();
		}
		return instance;
	}
}
