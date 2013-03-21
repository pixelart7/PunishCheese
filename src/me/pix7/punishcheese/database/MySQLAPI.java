package me.pix7.punishcheese.database;

import java.sql.*;
/**
 * @author iSuzutsuki
 * MySQL Manager Class
 * 
 * Use in PixelArt7's plugin with permission to use and edit from author.
 * 
 */
public class MySQLAPI {

	String username;
	String password;
	String db;
	String port;
	String ip;
	/**
	 * Construct new instance of MySQLAPI using
	 * @param username - Username to connect
	 * @param password - password of @param username
	 * @param db - Database name
	 * @param ip - Address of MySQL Server
	 * @param port - Port of MySQL Server.
	 */
	public MySQLAPI(String username,String password,String db,String ip,String port)
	{
		this.username = username;
		this.password = password;
		this.db = db;
		this.port = port;
		this.ip = ip;
	}
	/**
	 * Query SELECT query to MySQL Server
	 * @param query - Query to send
	 * @return ResultSet of query results
	 */
	public ResultSet query(String query){
		try{			
			Statement stmt = null;
			ResultSet rs = null;
			try {
    			Class.forName("com.mysql.jdbc.Driver");
    			
    		} catch (Exception ex){
    			ex.printStackTrace();
    			return null;
    		}
    		try {
    	StringBuilder sqlurl = new StringBuilder();
    	sqlurl.append("jdbc:mysql://").append(ip).append(":").append(port).append("/").append(db).append("?user=").append(username).append("&password=").append(password);
    	Connection con = DriverManager.getConnection(sqlurl.toString());
        stmt = con.createStatement();
        rs = stmt.executeQuery(query);
        return rs;
    			}
    		catch(Exception e){
    			e.printStackTrace();
    			return null;
    		}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * Query INSERT UPDATE DELETE or query that doesn't have ResultSet as result
	 * @param query - query to send
	 * @return count of affected rows
	 * @throws SQLException
	 */
	public int update(String query) throws SQLException{
		try{
			Statement stmt = null;
			try {
    			Class.forName("com.mysql.jdbc.Driver");
    			
    		} catch (Exception ex){
    			ex.printStackTrace();
    			return 0;
    		}
    		try {
		    	StringBuilder sqlurl = new StringBuilder();
		    	sqlurl.append("jdbc:mysql://").append(ip).append(":").append(port).append("/").append(db).append("?user=").append(username).append("&password=").append(password);
		    	Connection con = DriverManager.getConnection(sqlurl.toString());
		        stmt = con.createStatement();
		        int i = stmt.executeUpdate(query);
		        return i;
    		}catch(Exception e){
    			e.printStackTrace();
    			throw new SQLException("Couldn't complete query");
    		}
		}catch(Exception e){
			e.printStackTrace();
			throw new SQLException("Couldn't complete query");
		}
	}
}
