package com.bridgeit.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Demo {

	public static void main(String[] args) {

		Connection con = null;
		CallableStatement csmt = null;

		Scanner scan = new Scanner(System.in);

		System.out.println("Enter Employee ID (int):");
		int id = Integer.parseInt(scan.nextLine());
		System.out.println("Enter Employee Name:");
		String name = scan.nextLine();
		System.out.println("Enter Employee Role:");
		String role = scan.nextLine();
		System.out.println("Enter Employee City:");
		String city = scan.nextLine();
		System.out.println("Enter Employee Country:");
		String country = scan.nextLine();

		try {
			con = DBConnection.getConnection();

			csmt = con.prepareCall("{call insertEmployee(?,?,?,?,?,?)}\")}");
			csmt.setInt(1, id);
			csmt.setString(2, name);
			csmt.setString(3, role);
			csmt.setString(4, city);
			csmt.setString(5, country);

			csmt.registerOutParameter(6, java.sql.Types.VARCHAR);
			csmt.executeUpdate();

			String result = csmt.getString(6);

			System.out.println("record save successfully: " + result);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				csmt.close();
				con.close();
				scan.close();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
