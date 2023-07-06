package groupTest;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class user {
	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		System.out.println("Before Class");
	}
	
	@Test(priority = 1)
	public void TC_Create() {
		System.out.println("Testcase 01");
	}
	@Test(priority = 2)
	public void TC_Update() {
		System.out.println("Testcase 02");
	}
	
	@Test(priority = 3)
	public void TC_Delete() {
		System.out.println("Testcase 03");
	}
	
	@Test(enabled = false)
	public void TC_04() {
		System.out.println("Testcase 04");
	}
	
	@Test(groups = "user")
	public void TC_05() {
		System.out.println("Testcase 05");
	}
	
	@Test
	public void TC_06() {
		System.out.println("Testcase 06");
	}
	
	@Test
	public void TC_07() {
		System.out.println("Testcase 07");
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		System.out.println("After Class");
	}
}
