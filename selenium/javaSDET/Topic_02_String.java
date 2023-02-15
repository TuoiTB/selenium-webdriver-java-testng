package javaSDET;

public class Topic_02_String {
	public static void main(String[] args ){
		
	String firstName = "Automation";
	String lastName = "Testing";
	System.out.println(lastName.contains("ing"));
	System.out.println(firstName.contains("Ato"));
	System.out.println(firstName.concat(lastName));
	System.out.println(firstName + lastName);
	//AND
	boolean  a, b;
	a = true;
	b = true;
	System.out.println(a && b);
	//OR
	boolean c, d;
	c = true;
	d = false;
	System.out.println(c || d);
	}

}
