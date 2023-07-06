package annotations;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Annotation_02 { //test class

	@BeforeClass
	public void beforeClass() {
		
	}
	
	@Test
	public void TC_01_Register() {//test method/ testcase
		System.out.println("Testcase 01");
	}
	@Test
	public void TC_02_Login() {
		System.out.println("Testcase 02");
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("beforeMethod");
	}
	
	@BeforeSuite
	public void beforeSuite() {
		System.out.println("beforeSuite");
	}
	
	@BeforeGroups
	public void beforeGroups() {
		System.out.println("beforeGroups");
	}
	
	@BeforeTest
	public void beforeTest() {
		System.out.println("beforeTest");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}
	
	@AfterGroups
	public void afterGroup() {
		System.out.println("afterGroup");
	}
	
	@AfterMethod
	public void afterMethod() {
		System.out.println("afterMethod");
	}
	
	@AfterSuite
	public void afterSuite() {
		System.out.println("afterSuite");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("afterTest");
	}
	
}
