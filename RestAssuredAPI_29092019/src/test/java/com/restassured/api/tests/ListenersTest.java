package com.restassured.api.tests;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.restassured.api.testbase.TestBase;

public class ListenersTest implements ITestListener{

	TestBase testBase = new TestBase();

	public void onTestStart(ITestResult result) {
		//System.out.println("test start");

	}

	public void onTestSuccess(ITestResult result) {
		String text_to_write = "Test Name: " + result.getName() + " Has passed";
		try
		{
			testBase.Filewriter(text_to_write);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void onTestFailure(ITestResult result) {
		String text_to_write = "Test Name: " + result.getName() + " Has failed";
		try
		{
			testBase.Filewriter(text_to_write);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		//System.out.println("test skip");

	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		//System.out.println("on start");

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub

	}

}