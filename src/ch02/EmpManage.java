package ch02;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmpManage {
	void list() {
		EmpDAO dao=new EmpDAO();
		List<EmpDTO> items=new ArrayList<>();
		System.out.println("사번\t사원이름\t입사일자\t\t급여");
		items=dao.empList();
		for(int i=0; i<items.size(); i++) {
			EmpDTO dto=items.get(i);
			System.out.print(dto.getEmpno()+"\t");
			System.out.print(dto.getEname()+"\t");
			System.out.print(dto.getHiredate()+"\t");
			System.out.println(dto.getSal());
		}
	}
	void insert() {
		EmpDAO dao=new EmpDAO();
		Scanner s=new Scanner(System.in);
		System.out.print("사번:");
		int empno=s.nextInt();
		System.out.print("사원이름:");
		String ename=s.next();
		System.out.print("입사일자:");
		String hiredate=s.next();
		System.out.print("급여:");
		int sal=s.nextInt();
		EmpDTO dto=new EmpDTO(empno, ename, Date.valueOf(hiredate), sal);
		dao.empInsert(dto);
	}
	void delete() {
		EmpDAO dao=new EmpDAO();
		int result=0;
		Scanner s=new Scanner(System.in);
		System.out.println("삭제할 사번을 입력하세요.:");
		int empno=s.nextInt();
		result=dao.empDelete(empno);
		if(result==1) System.out.println("삭제 성공");
		else System.out.println("해당하는 사번이 존재하지 않습니다.");
	}
	
	public static void main(String[] args) {
		EmpManage manage=new EmpManage();
		Scanner s=new Scanner(System.in);
		while(true) {
			System.out.print("메뉴를 선택하세요.(1.조회 2.추가 3.삭제 0.종료):");
			int num=s.nextInt();
			switch(num) {
			case 0:
				s.close();
				System.exit(0);
				break;
			case 1:
				manage.list();
				break;
			case 2:
				manage.insert();
				break;
			case 3:
				manage.delete();
				break;
			}
		}
	}
}
