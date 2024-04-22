package org.payloads;

public class PayloadLibraryAPI {
	
public static  String addBookPayload() {
	String addBookReq= "{\r\n"
			+ "\r\n"
			+ "\"name\":\"Learn Appium Automation with Java\",\r\n"
			+ "\"isbn\":\"ddd\",\r\n"
			+ "\"aisle\":\"1342\",\r\n"
			+ "\"author\":\"Venket\"\r\n"
			+ "}\r\n"
			+ "";
	
	return addBookReq;

}

}
