package me.pix7.punishcheese.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PunishDatabase {
	
	me.pix7.punishcheese.PunishCheese pl;
	String dbusername;
	String dbpassword;
	String db;
	String ip;
	String port;
	String db_log;
	String db_rules;
	String prefix;
	
	public PunishDatabase(me.pix7.punishcheese.PunishCheese plugin) {

		this.pl = plugin;
		
		this.dbusername = pl.getConfig().getString("database.username");
		this.dbpassword = pl.getConfig().getString("database.password");
		this.db = pl.getConfig().getString("database.databasename");
		this.ip = pl.getConfig().getString("database.ip");
		this.port = pl.getConfig().getString("database.port");
		
		this.db_log = pl.getConfig().getString("temp.db_log");
		this.db_rules = pl.getConfig().getString("temp.db_rules");
		this.prefix = pl.getConfig().getString("temp.PunishCheesePrefix");
		
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
		ResultSet rs = sql.query("SELECT * FROM "+db_rules+" WHERE ruleid="+ruleId);
		if(!(rs.last())){
			/**
			 * rs.last() returns false so thats mean no rule at ruleId and it will try to insert row.
			 */
			try{
				sql.update("INSERT INTO "+db_rules+" (ruleid, shortdetail) VALUES ('"+ruleId+"', '"+shortdetail+"')");
				return true;
			} catch (SQLException e) {
				throw e;
			}
		}else{
			/**
			 * rs.last() returns true so it will update row.
			 */
			try{
				sql.update("UPDATE "+db_rules+" SET shortdetail='"+shortdetail+"' WHERE ruleid='"+ruleId+"'");
				return true;
			} catch (SQLException e) {
				throw e;
			}
		}
		
	}
	
	public boolean addRuleDetail(int ruleId, String detail) throws SQLException {
		
		MySQLAPI sql = new MySQLAPI(dbusername, dbpassword, db, ip, port);
		ResultSet rs = sql.query("SELECT * FROM "+db_rules+" WHERE ruleid="+ruleId);
		if(!(rs.last())){
			return false;
		}else{
			sql.update("UPDATE "+db_rules+" SET detail='"+detail+"' WHERE ruleid='"+ruleId+"'");
			return true;
		}
		
	}

}
