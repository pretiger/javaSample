package ch04;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarExam {
	public static void main(String[] args) {
		Calendar cal=Calendar.getInstance();
		Date date=cal.getTime();
		System.out.println(date.getYear()+1900);
		GregorianCalendar gc=new GregorianCalendar();
		if(gc.isLeapYear(cal.get(Calendar.YEAR))) {
			System.out.println(cal.get(Calendar.YEAR)+" 올해는 윤년입니다.");
		}else {
			System.out.println(cal.get(Calendar.YEAR)+" 올해는 평년입니다.");
		}
	}
}
