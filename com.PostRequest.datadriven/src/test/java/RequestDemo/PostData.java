package RequestDemo;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostData 
{
	public static String url="https://api.stripe.com/v1/customers";
	static String key="sk_test_51KTiTYSJzc4q2JqArd4zbvyBrZlMqFgzL5tGZ2USrd10VB4vzP8jj8b9H0a2OyZ5lxftqLsflx49wxTgwOMZacXX00pfxtdoHw";
	@Test
	public void Create() throws IOException {
		PostRequest req=new PostRequest();

		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\TestData\\Test.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheetAt(0);
		String name = sheet.getRow(1).getCell(0).getStringCellValue();
		String job = sheet.getRow(1).getCell(1).getStringCellValue();
		
		Response response = given().auth().basic(key, "").formParam("name", name).
                formParam("job",job).when().post(url);

		//Print Response
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is:"+responseBody);

		//Validate Status code
		int statusCode=response.getStatusCode();
		System.out.println("Status Code is: " + statusCode);
		Assert.assertEquals(statusCode, 200);

	
	}

}

