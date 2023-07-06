package assertions;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Assertions {
	@Test
	public void TC_01() {
		//boolean: isDisplayed/ isSelected/ isEnable/ isMultiple/ user defined
		//Assert.assertTrue/assertFalse: Tham số nhận vào là boolean
		Assert.assertTrue(isEmailTextboxDisplayed1());// assertTrue của true là pass
		Assert.assertFalse(isEmailTextboxDisplayed1());// assertFalse của true là fail
		
		Assert.assertTrue(isEmailTextboxDisplayed2());// assertTrue của false là fail
		Assert.assertFalse(isEmailTextboxDisplayed2());// assertFalse của false là pass
		
		
		//int/ string/ float
		//Assert.assertEquals: mong đợi và thực tế như nhau
		
	}
	public boolean isEmailTextboxDisplayed1() {
		//Action
		//
		return true;
	}
	
	public boolean isEmailTextboxDisplayed2() {
		//Action
		//
		return false;
	}
	
	public String getSuccessMessage() {
		
		return "Done";
		
	}
}
