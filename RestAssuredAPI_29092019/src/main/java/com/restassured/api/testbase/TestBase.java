package com.restassured.api.testbase;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

public class TestBase
{

	public Properties prop;
	DateFormat dateFormat = new SimpleDateFormat("ddMMyyyy_HHmmss");
	Calendar cal = Calendar.getInstance();
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;

	public TestBase()
	{
		try
		{
			prop = new Properties();
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/Files/config.properties");
			prop.load(fis);
		}
		catch (FileNotFoundException fnfe)
		{
			fnfe.printStackTrace();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public void Filewriter(String text_to_write) throws Exception
	{
		String Filepath = "Files\\Results.txt";
		String date = dateFormat.format(cal.getTime());
		File file = new File(Filepath);
		if (!file.exists())
		{
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		text_to_write = "Executed on: " + date + "	" + text_to_write;
		System.out.println("Test result is written at :" + file.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.newLine();
		bw.write(text_to_write);
		bw.close();
	}
}
