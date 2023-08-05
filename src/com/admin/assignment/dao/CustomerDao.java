package com.admin.assignment.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.admin.assignment.bean.Customer;

public class CustomerDao {
	
      private String jdbcURL="jdbc:mysql://localhost:3306/assignment?useSSL=false";
      private String jdbcUsername = "root";
      private String jdbcPassword = "Skb@2505";
      private String jdbcDriver = "com.mysql.jdbc.Driver";
      
      private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customerDb" + " (first_name, last_name, street, address, city, state, email, phone) VALUES "
  			+ " (?, ?, ?, ?, ?, ?, ?, ?);";

  	private static final String SELECT_CUSTOMER_BY_ID = "select id,first_name,last_name,street,address,city,state,email,phone from customerDb where id =?";
  	private static final String SELECT_ALL_CUSTOMER = "select * from customerDb";
  	private static final String DELETE_USERS_SQL = "delete from customerDb where id = ?;";
  	private static final String UPDATE_USERS_SQL = "update customerDb set first_name = ?,last_name= ?, street =?, address = ?,city= ?, state =?, email = ?,phone= ? where id = ?;";

  	public CustomerDao() {
  	}

  	protected Connection getConnection() {
  		Connection connection = null;
  		try {
  			Class.forName("com.mysql.cj.jdbc.Driver");
  			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
  		} catch (SQLException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		} catch (ClassNotFoundException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		return connection;
  	}

  	public void insertUser(Customer customer) throws SQLException {
  		System.out.println(INSERT_CUSTOMER_SQL);
  		// try-with-resource statement will auto close the connection.
  		try (Connection connection = getConnection();
  				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL)) {
  			preparedStatement.setString(1, customer.getFirst_name());
  			preparedStatement.setString(2, customer.getLast_name());
  			preparedStatement.setString(3, customer.getStreet());
  			preparedStatement.setString(4, customer.getAddress());
  			preparedStatement.setString(5, customer.getCity());
  			preparedStatement.setString(6, customer.getState());
  			preparedStatement.setString(7, customer.getEmail());
  			preparedStatement.setString(8, customer.getPhone());
  			System.out.println(preparedStatement);
  			preparedStatement.executeUpdate();
  		} catch (SQLException e) {
  			printSQLException(e);
  		}
  	}

  	public Customer selectUser(int id) {
  		Customer customer = null;
  		// Step 1: Establishing a Connection
  		try (Connection connection = getConnection();
  				// Step 2:Create a statement using connection object
  				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);) {
  			preparedStatement.setInt(1, id);
  			System.out.println(preparedStatement);
  			// Step 3: Execute the query or update query
  			ResultSet rs = preparedStatement.executeQuery();

  			// Step 4: Process the ResultSet object.
  			while (rs.next()) {
  				String first_name = rs.getString("first_name");
  				String last_name = rs.getString("last_name");
  				String street = rs.getString("street");
  				String address = rs.getString("address");
  				String city = rs.getString("city");
  				String state = rs.getString("state");
  				String email = rs.getString("email");
  				String phone = rs.getString("phone");
  				customer = new Customer(first_name , last_name, street,  address, city, state, email, phone);
  			}
  		} catch (SQLException e) {
  			printSQLException(e);
  		}
  		return customer;
  	}

  	public List<Customer> selectAllUsers() {

  		// using try-with-resources to avoid closing resources (boiler plate code)
  		List<Customer> customers = new ArrayList<>();
  		// Step 1: Establishing a Connection
  		try (Connection connection = getConnection();

  				// Step 2:Create a statement using connection object
  			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMER);) {
  			System.out.println(preparedStatement);
  			// Step 3: Execute the query or update query
  			ResultSet rs = preparedStatement.executeQuery();

  			// Step 4: Process the ResultSet object.
  			while (rs.next()) {
  				int id = rs.getInt("id");
  				String first_name = rs.getString("first_name");
  				String last_name = rs.getString("last_name");
  				String street = rs.getString("street");
  				String address = rs.getString("address");
  				String city = rs.getString("city");
  				String state = rs.getString("state");
  				String email = rs.getString("email");
  				String phone = rs.getString("phone");
  				customers.add(new Customer(id, first_name, last_name, street, address, city, state, email, phone));
  			}
  		} catch (SQLException e) {
  			printSQLException(e);
  		}
  		return customers;
  	}

  	public boolean deleteUser(int id) throws SQLException {
  		boolean rowDeleted;
  		try (Connection connection = getConnection();
  				PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
  			statement.setInt(1, id);
  			rowDeleted = statement.executeUpdate() > 0;
  		}
  		return rowDeleted;
  	}

  	public boolean updateUser(Customer customer) throws SQLException {
  		boolean rowUpdated;
  		try (Connection connection = getConnection();
  				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL);) {
  			System.out.println("updated USer:"+preparedStatement);
  			preparedStatement.setString(1, customer.getFirst_name());
  			preparedStatement.setString(2, customer.getLast_name());
  			preparedStatement.setString(3, customer.getStreet());
  			preparedStatement.setString(4, customer.getAddress());
  			preparedStatement.setString(5, customer.getCity());
  			preparedStatement.setString(6, customer.getState());
  			preparedStatement.setString(7, customer.getEmail());
  			preparedStatement.setString(8, customer.getPhone());
  			preparedStatement.setInt(9, customer.getId());
  			

  			rowUpdated = preparedStatement.executeUpdate() > 0;
  		}
  		return rowUpdated;
  	}

  	private void printSQLException(SQLException ex) {
  		for (Throwable e : ex) {
  			if (e instanceof SQLException) {
  				e.printStackTrace(System.err);
  				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
  				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
  				System.err.println("Message: " + e.getMessage());
  				Throwable t = ex.getCause();
  				while (t != null) {
  					System.out.println("Cause: " + t);
  					t = t.getCause();
  				}
  			}
  		}
  	}
}
