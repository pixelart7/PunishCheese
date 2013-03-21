package me.pix7.punishcheese.database;

import java.sql.SQLException;

public class PunishLogger {
	
	me.pix7.punishcheese.PunishCheese pl;
	String dbusername;
	String dbpassword;
	String db;
	String ip;
	String port;
	
	public PunishLogger(me.pix7.punishcheese.PunishCheese plugin) {

		this.pl = plugin;
		
		this.dbusername = pl.getConfig().getString("database.username");
		this.dbpassword = pl.getConfig().getString("database.password");
		this.db = pl.getConfig().getString("database.databasename");
		this.ip = pl.getConfig().getString("database.ip");
		this.port = pl.getConfig().getString("database.port");
		
	}
	
	/**
	 * PunishLogger.log() will INSERT log into punishcheese_log
	 * which return true if INSERT goes well
	 * @throws SQLException 
	 */
	public boolean log(String username, String operation, String rulebreaker, int ruleid, String reason) throws SQLException{
		
		MySQLAPI sql = new MySQLAPI(dbusername, dbpassword, db, ip, port);
		try{
			sql.update("INSERT INTO "+pl.getConfig().getString("db_log")+" (username, operation, rulebreaker, ruleid, reason) VALUES ('"+username+"', '"+operation+"', '"+rulebreaker+"', '"+ruleid+"', '"+reason+"')");
			return true;
		} catch (SQLException e) {
			throw e;
		}
		
	}
	
	/**
	 * PunishLogger.addRule() will INSERT or UPDATE rule into punishcheese_rules (Without adding a detail)
	 * which return true if INSERT or UPDATE goes well
	 * @throws SQLException 
	 */
	public boolean addRule(int ruleId, String shortdetail) throws SQLException{
		
		MySQLAPI sql = new MySQLAPI(dbusername, dbpassword, db, ip, port);
		//TODO add to check if exist
		try{
			sql.update("INSERT INTO "+pl.getConfig().getString("db_rules")+" (ruleid, detail) VALUES ('"+ruleId+"', '"+shortdetail+"')");
			return true;
		} catch (SQLException e) {
			throw e;
		}
		
	}

}
