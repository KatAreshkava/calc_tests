package tests;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class CalNegativeTests {
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8080/calc/";
		RestAssured.urlEncodingEnabled = false;
	}
	
	@Test(dataProvider = "invalid_data", dataProviderClass = TestData.class)
	public void testAdditionView(String a) {
		given().when().get(baseURI + "+" + a).then().assertThat().statusCode(400);
		String resp = given().when().get(baseURI + "+" + a).then().extract().asString();
		int actResult = Integer.valueOf(resp);
		assertEquals(actResult, "Bad Request", "Сообщение об ошибке некорректно");
	}
	
	@Test(dataProvider = "invalid_data", dataProviderClass = TestData.class)
	public void testSubstractionView(String a) {
		given().when().get(baseURI + "-" + a).then().assertThat().statusCode(400);
		String resp = given().when().get(baseURI + "-" + a).then().extract().asString();
		int actResult = Integer.valueOf(resp);
		assertEquals(actResult, "Bad Request", "Сообщение об ошибке некорректно");
	}
	
	@Test(dataProvider = "invalid_data", dataProviderClass = TestData.class)
	public void testMultiplicationView(String a) {
		given().when().get(baseURI + "*" + a).then().assertThat().statusCode(400);
		String resp = given().when().get(baseURI + "*" + a).then().extract().asString();
		int actResult = Integer.valueOf(resp);
		assertEquals(actResult,"Bad Request", "Сообщение об ошибке некорректно");
	}
	
	@Test(dataProvider = "invalid_data_set", dataProviderClass = TestData.class)
	public void test_invalid_input(String a,String b,String c) {
		given().when().get(baseURI + "-" + a + "/+" +b + "/*" + c).then().assertThat().statusCode(400);
		String resp = given().when().get(baseURI + "-" + a + "/+" +b + "/*" + c).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, "Bad Request", "Сообщение об ошибке некорректно"); 
	}
	
}
