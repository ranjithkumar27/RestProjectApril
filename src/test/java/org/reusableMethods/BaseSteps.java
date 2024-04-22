package org.reusableMethods;

import io.restassured.path.json.JsonPath;

public class BaseSteps {

	public static JsonPath jsonPath(String parser) {

		JsonPath js = new JsonPath(parser);
		
		return js;

	}

}
