package test01;

import java.lang.reflect.Method;
import java.lang.reflect.Field;
import java.lang.reflect.Constructor;

public class Test {
	
	public static void main(String args[]) throws Exception
    {
		Class clazz=Child.class;
		System.out.println("Class Name : "+clazz.getName());
		//결과 : Class Name : test01.Child
		Class clazz2=Class.forName("test01.Child");
		System.out.println("Class Name : "+clazz.getName());
		//결과 : Class Name : test01.Child
		Constructor constructor = clazz.getDeclaredConstructor();
		System.out.println("Constructor : "+constructor.getName());
		//결과 : Class Name : test01.Child
		Constructor constructor2 = clazz2.getDeclaredConstructor(String.class);
		System.out.println("Constructor(String) : "+constructor2.getName());
		//결과 : Constructor(String) : test01.Child
		Constructor[] constructors = clazz2.getDeclaredConstructors(); //선언된 모든 생성자를 return
		for(Constructor con : constructors) {
			System.out.println("Get constructor in Child : " + con);
		}
		//결과 : Get constructor in Child : public test01.Child()
		//	       Get constructor in Child : private test01.Child(java.lang.String)
		Constructor[] constructors2 = clazz2.getConstructors(); //public 생성자만 return
		for(Constructor con : constructors2) {
			System.out.println("Get constructor in Child : " + con);
		}
		//결과 : Get constructor in Child : public test01.Child()
		
		Method method1=clazz2.getDeclaredMethod("method4", int.class);
		System.out.println("Find out method4 method in Child : "+method1);
		//Find out method4 method in Child : public int test01.Child.method4(int)
		Method[] methods=clazz2.getDeclaredMethods(); //모든 method를 return
		for(Method method : methods) {
			System.out.println("Get method in Child : "+method);
		}
//		Get method in Child : private int test01.Child.method5(int)
//		Get method in Child : public int test01.Child.method4(int)
		Method[] methods2=clazz2.getMethods();
		for(Method method : methods2) {
			System.out.println("Get public methods in both Parent and Child : "+method);
		}
		
		//Method호출
		Child child=new Child();
		Class clazz10=Class.forName("test01.Child");
		Method method3=clazz10.getDeclaredMethod("method4", int.class);
		int returnValue=(int)method3.invoke(child, 10);
		System.out.println("return value : "+returnValue);
//		method4: 10
//		return value : 10
		
		
    }
}
