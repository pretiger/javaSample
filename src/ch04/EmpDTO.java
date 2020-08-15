package ch04;

import java.sql.Date;

public class EmpDTO {
	private int empno;
	private String ename;
	private String job;
	private Date hiredate;
	private int sal;
	private int deptno;
	private String dname;
	private String img_path;
	
	public EmpDTO() {
	}

	public EmpDTO(int empno, String ename, String job, Date hiredate, int sal, String dname, String img_path) {
		super();
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.hiredate = hiredate;
		this.sal = sal;
		this.dname = dname;
		this.img_path = img_path;
	}

	public int getDeptno() {
		return deptno;
	}

	public void setDeptno(int deptno) {
		this.deptno = deptno;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getImg_path() {
		return img_path;
	}

	public void setImg_path(String img_path) {
		this.img_path = img_path;
	}

	@Override
	public String toString() {
		return "EmpDTO [empno=" + empno + ", ename=" + ename + ", job=" + job + ", hiredate=" + hiredate + ", sal="
				+ sal + ", dname=" + dname + ", img_path=" + img_path + "]";
	}


}
