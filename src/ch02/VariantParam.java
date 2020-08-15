package ch02;

public class VariantParam {
	//자료형 ... => 가변사이즈 매개변수 선언
	//method를 호출할때 매개변수의 자료형이 같은 경우
	//배열로 묶어서 받음
	static void printf(String ... n) {
		for(int i=0; i<n.length; i++)
			System.out.println(n[i]);
	}
	static void sum(int ... n) {
		int result = 0;
		for(int i=0; i<n.length; i++) {
			result = result + n[i];
		}
		System.out.println("합계:"+result);
	}
	public static void main(String[] args) {
		printf("JAVA"); //1개
		printf("C#", "JSP"); //2개
		printf("안녕", "하세요!", "좋은 하루에요."); //3개
		sum(10,20,30,50,40);
		sum(10,20,30,40,50,60);
	}
}
