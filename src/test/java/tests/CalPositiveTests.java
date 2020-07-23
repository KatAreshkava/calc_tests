package tests;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CalPositiveTests {
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "http://localhost:8080/calc/";
		RestAssured.urlEncodingEnabled = false;
	}
	
	@Test
	public void sanityChech() {
		String resp = given().when().get(baseURI + "+1/-2/*5").then().extract().asString();
		int actResult = Integer.valueOf(resp);
		assertEquals(actResult, -5);
	}
	
	@Test(dataProvider = "calc_positive", dataProviderClass = TestData.class)
	public void testAdditionView(int value) {
		String resp = given().when().get(baseURI + "+" + value).then().extract().asString();
		int actResult = Integer.valueOf(resp);
		assertEquals(actResult, value);
	}
	
	@Test(dataProvider = "calc_positive", dataProviderClass = TestData.class)
	public void testSubstractionView(int value) {
		String resp = given().when().get(baseURI + "-" + value).then().extract().asString();
		int actResult = Integer.valueOf(resp);
		assertEquals(actResult, -value);
	}
	
	@Test(dataProvider = "calc_positive", dataProviderClass = TestData.class)
	public void testMultiplicationView(int value) {
		String resp = given().when().get(baseURI + "*" + value).then().extract().asString();
		int actResult = Integer.valueOf(resp);
		assertEquals(actResult,0);
	}
	
	@Test(dataProvider = "addition", dataProviderClass = TestData.class)
	public void test_addition_posivite_values(int value1,int value2) {
		String resp = given().when().get(baseURI + "+" + value1 + "/+" + value2).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, value1 + value2);
	}
	
	@Test(dataProvider = "addition", dataProviderClass = TestData.class)
	public void test_addition_negative_positive_values(int value1,int value2) {
		String resp = given().when().get(baseURI + "-" + value1 + "/+" + value2).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, -value1 + value2);
	}
	
	@Test(dataProvider = "substraction", dataProviderClass = TestData.class)
	public void test_subtraction_positive_values(int value1,int value2) {
		String resp = given().when().get(baseURI + "+" + value1 + "/-" + value2).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, value1 - value2);
	}
	
	@Test(dataProvider = "substraction", dataProviderClass = TestData.class)
	public void test_subtraction_negative_positive_values(int value1,int value2) {
		String resp = given().when().get(baseURI + "-" + value1 + "/-" + value2).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, -value1 - value2);
	}
	
	@Test(dataProvider = "multiplication", dataProviderClass = TestData.class)
	public void test_multiplication_positive_values(int value1,int value2) {
		String resp = given().when().get(baseURI + "+" + value1 + "/*" + value2).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, value1 * value2);
	}
	
	@Test(dataProvider = "multiplication", dataProviderClass = TestData.class)
	public void test_multiplication_negative_positive_values(int value1,int value2) {
		String resp = given().when().get(baseURI + "-" + value1 + "/*" + value2).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, -value1 * value2);
	}

	@Test(dataProvider = "combination", dataProviderClass = TestData.class)
	public void test_combination_01(int x,int y,int z) {
		String resp = given().when().get(baseURI + "+" + x + "/-" +y + "/*" + z).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, (x-y)*z);
	}
	
	@Test(dataProvider = "combination", dataProviderClass = TestData.class)
	public void test_combination_02(int x,int y,int z) {
		String resp = given().when().get(baseURI + "-" + x + "/+" +y + "/*" + z).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, (-x+y)*z);
	}
	
	@Test(dataProvider = "combination", dataProviderClass = TestData.class)
	public void test_combination_03(int x,int y,int z) {
		String resp = given().when().get(baseURI + "*" + x + "/+" +y + "/-" + z).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, (0+y)-z);
	}
	
	@Test(dataProvider = "combination", dataProviderClass = TestData.class)
	public void test_combination_04(int x,int y,int z) {
		String resp = given().when().get(baseURI + "*" + x + "/-" +y + "/+" + z).then().extract().asString();
		int actualResp = Integer.valueOf(resp);
		assertEquals(actualResp, (0-y)+z);
	}

}






