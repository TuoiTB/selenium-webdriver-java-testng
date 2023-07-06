package groupTest;

import org.testng.annotations.Test;

public class payment {
	@Test(groups = "pay")
	public void TC_01() {
		System.out.println("Testcase 01");
	}
	@Test(groups = "pay")
	public void TC_02() {
		System.out.println("Testcase 02");
	}
	
	@Test(groups = "pay")
	public void TC_03() {
		System.out.println("Testcase 03");
	}
	
	@Test(groups = "pay")
	public void TC_04() {
		System.out.println("Testcase 04");
	}
	
	@Test(groups = "pay")
	public void TC_05() {
		System.out.println("Testcase 05");
	}
	
	@Test(groups = {"pay","other"}) // testcase 06 thuộc group "pay" và cũng thuộc group "other"
	public void TC_06() {
		System.out.println("Testcase 06");
	}
	
	@Test
	public void TC_07() {
		System.out.println("Testcase 07");
	}
	
}
