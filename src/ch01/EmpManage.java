package ch01;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class EmpManage {
	void list() {
		EmpDAO dao=new EmpDAO();
		List<EmpDTO> items=dao.listEmp();
		System.out.println("사번\t사원명\t입사일\t\t급여");
		for(int i=0; i<items.size();i++) {
			EmpDTO dto=items.get(i);
			System.out.print(dto.getEmpno()+"\t");
			System.out.print(dto.getEname()+"\t");
			System.out.print(dto.getHiredate()+"\t");
			System.out.println(dto.getSal());
		}
		
//		for(EmpDTO dto : items) {
//			System.out.print(dto.getEmpno()+"\t");
//			System.out.print(dto.getEname()+"\t");
//			System.out.print(dto.getHiredate()+"\t");
//			System.out.println(dto.getSal());
//		}
	}
	
	void insert() {
		Scanner scan=new Scanner(System.in);
		System.out.print("사번:");
		int empno=scan.nextInt();
//		String str=scan.nextLine(); //scan.nextInt()를 첫줄에 사용하면 다음다음라인으로 그냥 넘어가서 편법사용
//		int empno=Integer.parseInt(str);
		System.out.print("사원이름:");
		String ename=scan.next();//nextLine()를 사용하면 윗줄 nextInt()에서 숫자+Enter를 입력받은 영향을 받게된다
		System.out.print("입사일자:");
		String hiredate=scan.next();
//		Date hiredate=Date.valueOf(scan.nextLine());//날짜 형식 표현 yyyy-mm-dd
		System.out.print("급여:");
		int sal=scan.nextInt();

		EmpDTO dto=new EmpDTO(empno, ename, Date.valueOf(hiredate), sal);
		EmpDAO dao=new EmpDAO();
		dao.insertEmp(dto);
	}
	
	void delete() {
		Scanner scan=new Scanner(System.in);
		System.out.println("삭제할 사번을 입력하세요:");
		int empno=scan.nextInt();
		
		EmpDAO dao=new EmpDAO();
		int result=dao.deleteEmp(empno);
		if(result==1) System.out.println("삭제 되었습니다.");
		else System.out.println("존재하지 않는 사번 입니다.");
	}
	public static void main(String[] args) {
		EmpManage manage=new EmpManage();
		Scanner scan=new Scanner(System.in);
		while(true) {
			System.out.print("작업을 선택하세요.(1.조회 2.추가 3.삭제 0.종료)");
			int num=scan.nextInt();
			switch(num) {
			case 1 : manage.list();
				break;
			case 2 : manage.insert();
				break;
			case 3 : manage.delete();
				break;
			case 0 : scan.close();
					 System.out.println("프로그램을 종료합니다.");
					 System.exit(0);
				break;
			}
			
		}
		
	}
}
