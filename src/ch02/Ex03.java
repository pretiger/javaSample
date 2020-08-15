package ch02;

import java.util.Scanner;

//Ctrl + Shift + o => 자동 import
//첫글자 대문자 => 클래스
//Ctrl + / , Ctrl + 7 => 주석처리
//Ctrl + D => 라인 삭제
//실행 방법 : Ctrl + F11 
//main, Ctrl + space
//sysout 마찬가지

public class Ex03 {
	//main : 프로그램의 시작점
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("당신의 이름을 입력하세요:");
		//String name = scan.next(); 입력받은 뒷문자가 공백이면 무시되고 뒤에 영향을 미침
		String name = scan.nextLine();
		System.out.println("당신의 이름은 "+name);
		System.out.println("나이을 출생연도를 입력하세요.");
		int birth = scan.nextInt();
		System.out.println("이름 : "+name+" 나이: "+(2019-birth));
	}
}


