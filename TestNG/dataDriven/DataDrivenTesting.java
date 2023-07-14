package dataDriven;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

public class DataDrivenTesting {
  @Test(dataProvider = "loginData")
  public void TC_01(Integer STT, String HoTen, String DiaChi) {
	  System.out.println(STT);
	  System.out.println(HoTen);
	  System.out.println(DiaChi);
  }
  
  @Test(dataProvider = "admin")
  public void TC_02(Integer STT, String HoTen, String DiaChi) {
	  System.out.println(STT);
	  System.out.println(HoTen);
	  System.out.println(DiaChi);
  }

  @DataProvider(name = "loginData")
  public Object[][] getData() {
    return new Object[][] {
      new Object[] { 1, "Bui Van A", "HCM" },
      new Object[] { 2, "Nguyen Thi B", "HN" },
      new Object[] { 3, "Le Van C", "DN" },
    };
  }
    
    @DataProvider(name = "admin")
    public Object[][] getDataAdmin() {
      return new Object[][] {
        new Object[] { 1, "Bui Van A", "HCM" },
        new Object[] { 2, "Nguyen Thi B", "HN" },
        new Object[] { 3, "Le Van C", "DN" },
      };
    }
}
    
  
  
  
  
  

