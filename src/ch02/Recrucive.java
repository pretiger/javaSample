package ch02;

public class Recrucive {
	//재귀호출을 사용한 코드
	static long fact1(int n) {
		return n == 1 ? 1 : n * fact1(n-1); 
	}
	//재귀호출을 사용하지 않은 코드
	static long fact2(int n) {
		long result = 1;
		for(int i=1; i <= n; i++) {
			result = result * i;
		}
		return result;
	}
	public static void main(String[] args) {
		//디버깅, 중단점(Break Point)
		//F5 step into
		//F6, step over
		System.out.println(fact1(20));
		System.out.println(fact2(20));
	}
}
