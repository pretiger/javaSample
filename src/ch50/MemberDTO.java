package ch50;

import java.io.Serializable;

public class MemberDTO implements Serializable {
	private String name;
	private int age;
	//포함 오브젝트
	private MemberInfoDTO memberInfo;
//	transient : 일시적인, 잠시 (객체 직열화에서 제외되는 요소)
//	private transient MemberInfoDTO memberInfo;
	//getter, setter
	//toString()
	//매개변수가 있는 생성자
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public MemberInfoDTO getMemberInfo() {
		return memberInfo;
	}
	public void setMemberInfo(MemberInfoDTO memberInfo) {
		this.memberInfo = memberInfo;
	}
	@Override
	public String toString() {
		return "MemberDTO [name=" + name + ", age=" + age + ", memberInfo=" + memberInfo + "]";
	}
	public MemberDTO(String name, int age, String Jumin, String pwd) {
		super();
		this.name = name;
		this.age = age;
		this.memberInfo = new MemberInfoDTO(Jumin, pwd);
	}
	
}
