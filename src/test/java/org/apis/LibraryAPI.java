package org.apis;

import org.payloads.PayloadLibraryAPI;
import org.reusableMethods.BaseSteps;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;




public class LibraryAPI {
	
	@Test
	public void addBookRequest() {
		
		RestAssured.baseURI="http://216.10.245.166";
		
		String addBookResponse = given().log().all().header("Content-Type","application/json").body(PayloadLibraryAPI.addBookPayload())
		.when().post("Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).header("Server", "Apache").extract().response().asString();
		
		System.out.println(addBookResponse);
		
		JsonPath addBookFields = BaseSteps.jsonPath(addBookResponse);
		String addBookFields_ID = addBookFields.getString("ID");
		
		System.out.println("AddBookFieldsID is ===> "+addBookFields_ID);
	}

}
