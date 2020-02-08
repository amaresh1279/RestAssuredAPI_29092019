package com.restassured.api.testbase;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import java.util.HashMap;

import io.restassured.response.Response;

public class PostGetCommand {

	HashMap<String, String> hashMap;

	public HashMap<String, String> postMap(String FieldName1, String FieldValue1, String FieldName2, String FieldValue2, 
			String FieldName3, String FieldValue3, String FieldName4, String FieldValue4)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(FieldName1, FieldValue1);
		map.put(FieldName2, FieldValue2);
		map.put(FieldName3, FieldValue3);
		map.put(FieldName4, FieldValue4);
		return map;
	}

	public HashMap<String, String> commentMap(String FieldName1, String FieldValue1, String FieldName2, String FieldValue2,
			String FieldName3, String FieldValue3, String FieldName4, String FieldValue4, String FieldName5, String FieldValue5)
	{
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(FieldName1, FieldValue1);
		map.put(FieldName2, FieldValue2);
		map.put(FieldName3, FieldValue3);
		map.put(FieldName4, FieldValue4);
		map.put(FieldName5, FieldValue5);
		return map;
	}

	public void initiatePost(String BaseURL, String ServiceURL, HashMap<String, String> map, int statusCode, String stringToCheck)
	{
		hashMap = new HashMap<String, String>();
		given().
		headers("Content-Type", "application/json").
		when().
		baseUri(BaseURL).
		basePath(ServiceURL).
		body(map).
		post().
		then().
		assertThat().
		statusCode(statusCode).
		and().
		body(containsStringIgnoringCase(stringToCheck)).
		headers("content-type", "application/json; charset=utf-8");
	}

	public void initiateComment(String BaseURL, String ServiceURL, HashMap<String, String> map, int statusCode, String stringToCheck)
	{
		hashMap = new HashMap<String, String>();
		given().
		headers("Content-Type", "application/json").
		when().
		baseUri(BaseURL).
		basePath(ServiceURL).
		body(map).
		post().
		then().
		assertThat().
		statusCode(statusCode).
		and().
		body(containsStringIgnoringCase(stringToCheck)).
		headers("content-type", "application/json; charset=utf-8");
	}

	public Response initiateGet(String BaseURL, String ServiceURL, String ID)
	{
		Response responseGet = 
				given().
				when().
				baseUri(BaseURL).
				basePath(ServiceURL + ID).
				get();
		return responseGet;
	}
}
