package dao;

import java.sql.PreparedStatement;
import java.util.*;
import java.beans.Statement;
import java.sql.*;

import dbconnect.*;
import setget.*;
public class EmployeeDao {
	public boolean isValid(String username,String password) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String data = "select * from login";
		int flag = 0;
		try {
			connection = DbConnect.getConnection();
			preparedStatement = connection.prepareStatement(data);
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				if (resultSet.getString("userid").equals(username) && resultSet.getString("password").equals(password)) {
					flag = 1;
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DbConnect.close(connection, preparedStatement, resultSet);
		}

		if (flag == 1)
			return true;
		else
			return false;
	}

	public void insertEmployeeDetails(List<String> list) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			String sql = "insert into employee (Name,Age,DOB,Address,Salary,Description,Email,PhoneNumber)values('"+list.get(0)+"','"+list.get(1)+"','"+list.get(2)+"','"+list.get(3)+"','"+list.get(4)+"','"+list.get(5)+"','"+list.get(6)+"','"+list.get(7)+"')";
					
			connection = DbConnect.getConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement(sql);
			//preparedStatement.setString(1, list.get(0));
			////preparedStatement.setInt(2, Integer.parseInt(list.get(1)));
			//preparedStatement.setString(3, list.get(2));
			//preparedStatement.setString(4, list.get(3));
			//preparedStatement.setString(5, list.get(4));
			//preparedStatement.setString(6, list.get(5));
			//preparedStatement.setString(7, list.get(6));
			//preparedStatement.setString(8, list.get(7));
			preparedStatement.executeUpdate();
			connection.commit();

		}

		catch (Exception e) {
			try {
				connection.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DbConnect.close(connection, preparedStatement, null);
		}
	}

	public List<Employee> lessThanInputAgeEmployess(int inputAge){
		List<Employee> employeeList = new ArrayList<>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			String data = "select * from employee";
			connection = DbConnect.getConnection();
			preparedStatement = connection.prepareStatement(data);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				Employee employee = new Employee();
				if(resultSet.getInt("age") < inputAge) {
					employee.setEmpId(resultSet.getInt("EmpId"));
					employee.setName(resultSet.getString("Name"));
					employee.setAge(resultSet.getInt("Age"));
					employee.setEmail(resultSet.getString("Email"));
					employee.setPhoneNumber(resultSet.getString("PhoneNumber"));
					employeeList.add(employee);
					}
				
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			DbConnect.close(connection, preparedStatement, resultSet);
		}
	
		return employeeList;
	}
}