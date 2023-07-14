package dependenciesTestcase;

import org.testng.Assert;
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

public class Depends { //test class

	@BeforeClass
	public void beforeClass() {
		
	}
	
	//Chỉ dùng depend khi chạy những testcase chạy theo luồng/có phụ thuộc dữ liệu của nhau
	@Test
	public void TC_01_Create_New_Product() {//test method/ testcase
		System.out.println("Create_New_Product");
		Assert.assertTrue(false);
	}
	
	@Test(dependsOnMethods = "TC_01_Create_New_Product")
	public void TC_02_View_Existing_Product() {
		System.out.println("View_Existing_Product");
	}
	
	@Test(dependsOnMethods = "TC_02_View_Existing_Product")
	public void TC_03_Move_Existing_Product_To_New_Category() {
		System.out.println("Move_Existing_Product_To_New_Category");
	}
	
	@Test(dependsOnMethods = "TC_03_Move_Existing_Product_To_New_Category")
	public void TC_04_Edit_Existing_Product() {
		System.out.println("Edit_Existing_Product");
	}
	
	@Test(dependsOnMethods = {"TC_04_Edit_Existing_Product","TC_03_Move_Existing_Product_To_New_Category","TC_02_View_Existing_Product","TC_01_Create_New_Product"})
	public void TC_05_Delete_Existing_Product() {
		System.out.println("TC_05_Delete_Existing_Product");
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("afterClass");
	}
}
