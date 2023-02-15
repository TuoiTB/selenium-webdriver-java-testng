package javaSDET;

import java.util.HashSet;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Data_Type {

	public static void main(String[] args) {
		//Cách 1: Khai báo trước-khởi tạo sau:
			String fullname;
			fullname = "Automation FC";
		//  Cách 2: Khai báo + khởi tạo cùng lúc:
			String addressName = "123 LÊ LỢI";
		//Kiểu dữ liệu nguyên thủy (Primitive type)
			//Tất cả các kiểu dữ liệu nguyên thủy đều viết thường chữ cái đầu
			// Ký tự: Char
			char a = 'A'; //duy nhất 1 ký tự thì dùng nháy đơn ''
			//Số nguyên: byte, short, int, long
			byte bNumber = 127; // 8 bits:    -128 to 127
			short sNumber = -32768; // 16 bits: 
			int iNumber = 2147483647; // 32 bits
			long lNumber = 222222222; //64 bits
			//Số thực: float, double
			float fNumber = 14.67f; //32 bits
			double dNumber = 12.3d; //64 bits
			//Logic: boolean
			boolean status = true;
			status = false; // vừa khai báo + khởi tạo, sau đó khởi tạo giá trị khác cho biến, thì biến sẽ lưu giá trị cuối cùng
		
		//Kiểu dữ liệu tham chiếu (Reference Type)
			//Tất cả các kiểu dữ liệu tham chiếu đều viết hoa chữ cái đầu
			// Chuỗi ký tự : String
			String cityName = "Hồ Chí Minh";
			System.out.print(cityName);
			// Class
			FirefoxDriver fDriver;
			
			// Interface
			WebDriver driver;
			// Collection: Set, List, Queue
			//HashSet/ LinkedHashSet/ TreeSet
			HashSet hashSet = new HashSet<>();
			
			//ArrayList/ LinkedList/ Vector
			//Deque/...
			//Map: HashMap/ HashTable
			// Object
			Object number;
			//Array
			String[] student = {"Nguyen Van A", "Nguyen Van B"};
			Integer[] point = {13,44,35235};
			driver= new ChromeDriver();

	}

}
