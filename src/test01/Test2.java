package test01;

import java.lang.reflect.Method;

public class Test2 {
	
	public static void main(String[] args) throws Exception {
//		Child child=new Child();
//		Class clazz = Class.forName("test01.Parent");
//		Method method = clazz.getDeclaredMethod("method1");
//		method.invoke(child);
		/*
		 * Exception in thread "main" java.lang.IllegalAccessException: class
		 * test01.Test2 cannot access a member of class test01.Parent with modifiers
		 * "private" at
		 * java.base/jdk.internal.reflect.Reflection.newIllegalAccessException(
		 * Reflection.java:361) at
		 * java.base/java.lang.reflect.AccessibleObject.checkAccess(AccessibleObject.
		 * java:591) at java.base/java.lang.reflect.Method.invoke(Method.java:558) at
		 * test01.Test2.main(Test2.java:11)
		 */
		Child child=new Child();
		Class clazz=Class.forName("test01.Parent");
		Method method=clazz.getDeclaredMethod("method1");
		method.setAccessible(true);
		method.invoke(child);
		
		//Field 변경
		
	}
}
