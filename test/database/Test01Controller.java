package com.marondal.servlet.test.database;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.marondal.servlet.ex.common.MysqlService;

@WebServlet("/db/test/test01")
public class Test01Controller extends HttpServlet 
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		response.setContentType("text/plain");
		
		PrintWriter out = response.getWriter();
		
		MysqlService mysqlService = MysqlService.getInstance();
		//접속
		mysqlService.connect();
		
		ResultSet resultSet = mysqlService.select("SELECT * FROM `real_estate` ORDER BY id DESC");
		
		try 
		{
			int count = 0;
			while(resultSet.next() && count < 10)
			{
				String address = resultSet.getString("address");
				int area = resultSet.getInt("area");
				String type = resultSet.getString("type");
				
				out.println("매물주소 : " + address + " 가격 : " + area + " 타입 : " + type);
				count++;
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
}

