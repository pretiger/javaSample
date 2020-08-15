package ch01;

//Ctrl + Shift + o => 자동 import
//첫글자 대문자 => 클래스
//Ctrl + / , Ctrl + 7 => 주석처리
//Ctrl + D => 라인 삭제
//실행 방법 : Ctrl + F11 
//main, Ctrl + space
//sysout 마찬가지
//F3, 소스코드보기  F4, 계층구조보기
//일반적으로 파일 하나에 클래스 하나 작성, 이때 파일명은 클래스명과 동일하게 작성
//Project Explorer에서 해당 javafile를 선택 후 F2, 클래스 이름 바꾸기
//범위 설정후 Alt + 화살표 아래위로 하면 아래위로 위치변경
//다른 클래스에서 상속받은 메소드를 사용하고자 할시 메소드명을 타이핑후 Ctrl + space 클릭하여 선택시 @override 표시되며 문구생성
//String 비교: A==B 는 A,B주소값비교, A.equals(B) 내용 비교 

public class Hello {
	//main : 프로그램의 시작점
	public static void main(String[] args) {
		System.out.println("첫번째 자바 프로그램");
//		Scanner s=new Scanner(System.in);
//		System.out.print("학번:");
//		String str=s.nextLine();
//		int num=Integer.parseInt(str);
//		System.out.print("이름:");
//		String name=s.nextLine();
//		System.out.print("비고:");
//		String memo=s.nextLine();
//		System.out.print("졸업일자:");
//		int year=s.nextInt();
//		System.out.println("이름:"+name+",비고:"+memo+",졸업일자:"+year+",학번:"+num);
	}
}