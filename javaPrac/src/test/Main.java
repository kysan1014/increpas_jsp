package test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

	public static void main(String[] args) {
		String name = Employee.class.getName();
		Field[] fields = Employee.class.getDeclaredFields();
		
		System.out.println(name);
		System.out.println(fields.length);
		for(int i = 0; i < fields.length; i++) {
			System.out.println(fields[i].getName());
		}
		
		Employee e = null;
		try {
			e = Employee.class.newInstance();
		} catch (InstantiationException | IllegalAccessException e2) {
			e2.printStackTrace();
		}
		
		Method[] methods = Employee.class.getDeclaredMethods();
		
		for (Method m : methods) {
			if (m.getName().equals("setID")) {
				try {
					m.invoke(e, "id");
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
					e1.printStackTrace();
				}
				break;
			}
		}
		System.out.println(e.getID());
	}
	
}
