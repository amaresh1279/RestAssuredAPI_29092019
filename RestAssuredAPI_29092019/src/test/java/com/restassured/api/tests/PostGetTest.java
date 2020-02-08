package com.restassured.api.tests;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Recordset;
import com.restassured.api.dbconnection.DBConnection;
import com.restassured.api.testbase.PostGetCommand;
import com.restassured.api.testbase.TestBase;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

@Listeners (ListenersTest.class)
public class PostGetTest extends TestBase {

	Properties prop;
	String strQuery;
	DBConnection dbConnection;
	PostGetCommand postGetCommand;

	@Test (priority = 0)
	public void postTest() throws FilloException, IOException
	{
		dbConnection = new DBConnection();
		postGetCommand = new PostGetCommand();
		strQuery = "Select * from Posts";
		Recordset recordset = dbConnection.recordset(strQuery);
		HashMap<String, String >map = postGetCommand.postMap(recordset.getFieldNames().get(3), recordset.getField("userId"), recordset.getFieldNames().get(4), recordset.getField("id"), recordset.getFieldNames().get(5), recordset.getField("title"), recordset.getFieldNames().get(6), recordset.getField("body"));
		postGetCommand.initiatePost(recordset.getField("BaseURL"), recordset.getField("ServiceURL"), map, RESPONSE_STATUS_CODE_201, recordset.getField("title"));
		dbConnection.closeConnection();
	}

	@Test (priority = 1)
	public void commentTest() throws FilloException, IOException
	{
		dbConnection = new DBConnection();
		postGetCommand = new PostGetCommand();
		strQuery = "Select * from Comments";
		Recordset recordset = dbConnection.recordset(strQuery);
		HashMap<String, String >map = postGetCommand.commentMap(recordset.getFieldNames().get(3), recordset.getField("postId"), 
		recordset.getFieldNames().get(4), recordset.getField("id"), recordset.getFieldNames().get(5), recordset.getField("name"),
		recordset.getFieldNames().get(6), recordset.getField("email"), recordset.getFieldNames().get(7), recordset.getField("body"));
		postGetCommand.initiateComment(recordset.getField("BaseURL"), recordset.getField("ServiceURL"), map, RESPONSE_STATUS_CODE_201, recordset.getField("body"));
		dbConnection.closeConnection();
	}

	@Test (priority = 2)
	public void getPostTest() throws FilloException, IOException
	{
		prop = new Properties();
		dbConnection = new DBConnection();
		postGetCommand = new PostGetCommand();
		strQuery = "Select * from Posts";
		Recordset recordset = dbConnection.recordset(strQuery);
		Response responseGet = postGetCommand.initiateGet(recordset.getField("BaseURL"), recordset.getField("ServiceURL"), recordset.getField("id"));
		int statusCode = responseGet.getStatusCode();
		assertEquals(statusCode, RESPONSE_STATUS_CODE_200);

		String bodyString = responseGet.body().asString();
		System.out.println("Get Post Test body is: " + bodyString);
	}

	@Test (priority = 3)
	public void getCommentTest() throws FilloException, IOException
	{
		prop = new Properties();
		dbConnection = new DBConnection();
		postGetCommand = new PostGetCommand();
		strQuery = "Select * from Comments";
		Recordset recordset = dbConnection.recordset(strQuery);
		Response responseGet = postGetCommand.initiateGet(recordset.getField("BaseURL"), recordset.getField("ServiceURL"), recordset.getField("postId"));

		int statusCode = responseGet.getStatusCode();
		assertEquals(statusCode, RESPONSE_STATUS_CODE_200);

		String bodyString = responseGet.body().asString();
		System.out.println("Get Comment Test body is: " + bodyString);
	}
}
