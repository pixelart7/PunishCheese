package me.pix7.punishcheese;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.command.CommandSender;

import me.pix7.punishcheese.database.MySQLAPI;

public class PunishMethod {
	
	String dbusername;
	String dbpassword;
	String db;
	String ip;
	String port;
	
	String db_log;
	String db_rules;
	String db_punishment;
	String prefix;
	
	String username;
	int ruleIdint;
	int timesint;
	
	public PunishMethod(PunishCheese pl, CommandSender sender, String username, int ruleIdint, int timesint){
		
		this.dbusername = pl.getConfig().getString("database.username");
		this.dbpassword = pl.getConfig().getString("database.password");
		this.db = pl.getConfig().getString("database.databasename");
		this.ip = pl.getConfig().getString("database.ip");
		this.port = pl.getConfig().getString("database.port");
		
		this.db_log = pl.getConfig().getString("temp.db_log");
		this.db_rules = pl.getConfig().getString("temp.db_rules");
		this.db_punishment = pl.getConfig().getString("temp.db_punishment");
		this.prefix = pl.getConfig().getString("temp.PunishCheesePrefix");
		
		this.username = username;
		this.ruleIdint = ruleIdint;
		this.timesint = timesint;
		
	}
	
	public int getRows(){
		
		MySQLAPI sql = new MySQLAPI(dbusername, dbpassword, db, ip, port);
		ResultSet rs = sql.query("SELECT * FROM "+db_punishment+" WHERE player='"+username+"' AND ruleid='"+ruleIdint+"'");
		int rows;
		try {
			if(!(rs.last())){
				rows = 0;
				return rows;
			}else{
				rs.last();
				rows = rs.getRow();
				return rows;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
		
	}

	public void jail(){
		
		
	}
	
	public void kick(){
		
		
	}
	
	public void ban(){
		
		
	}
	
	/* High Priority */
	public void command(){
		
		
	}
	
}
