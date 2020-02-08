package com.restassured.api.testbase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadConfig
{

	public Properties properties;


	public ReadConfig() throws IOException
	{
		properties = new Properties();
		FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir") + "/Files/config.properties");
		properties.load(fileInputStream);
	}

	public String getExcelFileName()
	{
		String excelFileName = properties.getProperty("ExcelFileName");
		return excelFileName;
	}

}
