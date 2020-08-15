package ch02;

import java.util.Scanner;

public class SchoolDegreed {
	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		
		System.out.println("이름을 입력하세요: ");
		String name=scan.nextLine();
		System.out.println("국어성적: ");
		int kor=scan.nextInt();
		System.out.println("영어성적: ");
		int eng=scan.nextInt();
		System.out.println("수학성적: ");
		int mat=scan.nextInt();
		
		int tot=kor+eng+mat;
		double avg=tot/3.0;
		
		System.out.println("국어\t영어\t수학\t총점\t평균\n");
		System.out.println(kor+"\t"+eng+"\t"+mat+"\t"+tot+"\t"+String.format("%5.1f",avg));
	}
}
