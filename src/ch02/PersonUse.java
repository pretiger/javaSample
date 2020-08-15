package ch02;

public class PersonUse {
	public static void main(String[] args) {
		Person p1=new Person();
		p1.name="김철수";
		p1.age=18;
		p1.height=170.6;
		System.out.println("이름:"+p1.name);
		System.out.println("나이:"+p1.age);
		System.out.println("키  :"+p1.height);
	}
}
