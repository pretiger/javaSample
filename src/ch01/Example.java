package ch01;

import java.util.Scanner;

public class Example {

	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		System.out.print("Write down korea Score :");
		int kor=scan.nextInt();
		System.out.print("Write down english Score :");
		int eng=scan.nextInt();
		System.out.print("Write down mathmatics :");
		int mat=scan.nextInt();
		int tot=kor+eng+mat;
		double avg=tot/3.0;
		System.out.println("korea\tenglish\tmathmatics\ttotal\taverage");
		System.out.println(kor+"\t"+eng+"\t"+mat+"\t"+tot+"\t"+String.format("%5.1f",avg));
	}
}
