package com.restassured.api.dbconnection;

import java.io.IOException;

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;
import com.restassured.api.testbase.ReadConfig;

public class DBConnection {

	ReadConfig readConfig;
	String ExcelFilePath = System.getProperty("user.dir") + "/Files/";
	Connection connection = null;
	Recordset recordset = null;
	
	public Recordset recordset (String strQuery) throws IOException
	{
		Fillo fillo = new Fillo();
		readConfig = new ReadConfig();
		try
		{
			ExcelFilePath = ExcelFilePath + readConfig.getExcelFileName();
			Connection connection = fillo.getConnection(ExcelFilePath);
			recordset = connection.executeQuery(strQuery);
			recordset.next();
			recordset.moveFirst();
		}
		catch (FilloException fe)
		{
			fe.printStackTrace();
		}
		return recordset;
	}
	
	public void closeConnection()
	{
		recordset.close();
		//connection.close();
	}
}
