package com.employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.lang.String;
public class Employee 
{
	public static void main(String[] args) 
	{
		try
		{
			Connection con;
			ResultSet rs,rs1,rs2;
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","");
			PreparedStatement st =con.prepareStatement("select * from emp ");
			rs=st.executeQuery();
			if(!rs.isBeforeFirst())
				System.out.println("No Records Found");
			if(args.length==0)
			{
				while(rs.next())
				{
					System.out.println("Employee Id		: "+rs.getInt(1));
					System.out.println("Employee Name	: "+rs.getString(2));
					System.out.println("Employee Department : "+rs.getString(3));
				}
				
			}
			if(args.length>0)
			{   
				int id=getInt(args[0].toLowerCase());
				if(id==0)
				{
					PreparedStatement st2 =con.prepareStatement("select * from emp where name LIKE '%"+args[0]+"%'");
					rs1=st2.executeQuery();
					if(!rs1.isBeforeFirst())
						System.out.println("No Records Found for name "+args[0]);
					else
						while(rs1.next())
						{
							System.out.println("Employee Id		: "+rs1.getInt(1));
							System.out.println("Employee Name	: "+rs1.getString(2));
							System.out.println("Employee Department : "+rs1.getString(3));
						}
					
				}
				else
				{
					PreparedStatement st1 =con.prepareStatement("select * from emp where id=?");
					st1.setInt(1, Integer.parseInt(args[0]));
					rs2=st1.executeQuery();
					if(!rs2.isBeforeFirst())
						System.out.println("No Records Found for id "+args[0]);
					else
						while(rs2.next())
						{
							System.out.println("Employee Id		: "+rs2.getInt(1));
							System.out.println("Employee Name	: "+rs2.getString(2));
							System.out.println("Employee Department : "+rs2.getString(3));
						}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("No Records Found");
		}
	}
	public static int getInt(String string)
	{
		if (isInt(string)) 
		{
			return Integer.parseInt(string);
		}
		else
		{
			return 0;
		}
	}
	public static boolean isInt(String string)
	{
		try 
		{
			Integer.parseInt(string);
		} 
		catch (NumberFormatException nFE)
		{
			return false;
		}
		return true;
	}
}
	


