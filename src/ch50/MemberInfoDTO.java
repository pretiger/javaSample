package ch50;

import java.io.Serializable;

public class MemberInfoDTO implements Serializable {
	private String JuminNo;
	private String passwd;
	//getter, setter 생성
	//toStrint
	//생성자
	public String getJuminNo() {
		return JuminNo;
	}
	public void setJuminNo(String juminNo) {
		JuminNo = juminNo;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public String toString() {
		return "MemberInfoDTO [JuminNo=" + JuminNo + ", passwd=" + passwd + "]";
	}
	public MemberInfoDTO(String juminNo, String passwd) {
		JuminNo = juminNo;
		this.passwd = passwd;
	}
	
}
