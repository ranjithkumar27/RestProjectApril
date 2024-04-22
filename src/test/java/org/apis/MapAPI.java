package org.apis;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.payloads.PayloadMapAPI;
import org.reusableMethods.BaseSteps;
import org.testng.Assert;

public class MapAPI {

	public static void main(String[] args) {

		// 01 Addplace API

		// Given =where we'll give all the inputs ,when =where we'll submit the api
		// using http methods ,then=Where we'll validate the api response

		// Using this to set the base URI

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String addPlaceResponse = given().log().all().queryParams("key", "qaclick123")
				.header("Content-Type", "application/json").body(PayloadMapAPI.addPlacePayload()).when()
				.post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200)
				.header("Server", "Apache/2.4.52 (Ubuntu)").body("scope", equalTo("APP")).extract().response()
				.asString();

		JsonPath js = new JsonPath(addPlaceResponse);
		String field_PlaceID = js.getString("place_id");

		System.out.println("The place ID is ===> " + field_PlaceID);

		// 02 Update place
		String updatedAddress = "Adyar";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(PayloadMapAPI.updatePlacePayload(field_PlaceID, updatedAddress)).when()
				.put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.header("Server", "Apache/2.4.52 (Ubuntu)").body("msg", equalTo("Address successfully updated"));

		// 03 Get place

		String getPlaceResponse = given().log().all().queryParam("key", "qaclick123")
				.queryParam("place_id", field_PlaceID).when().get("maps/api/place/get/json").then().log().all()
				.assertThat().statusCode(200).header("Server", "Apache/2.4.52 (Ubuntu)").extract().response()
				.asString();

		JsonPath jsget = BaseSteps.jsonPath(getPlaceResponse);

		String actualAddress = jsget.getString("address");

		Assert.assertEquals(updatedAddress, actualAddress);

	}

}
