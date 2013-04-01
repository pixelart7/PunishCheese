package me.pix7.punishcheese;

import java.sql.SQLException;

import me.pix7.punishcheese.database.MySQLAPI;

import org.bukkit.plugin.java.JavaPlugin;

public class PunishCheese extends JavaPlugin{
	
	public void onEnable(){
		
		this.saveDefaultConfig();
		
		this.getConfig().set("temp.PunishCheesePrefix", "&4(Punish) &f");
		this.getConfig().set("temp.db_log", "punishcheese_log");
		this.getConfig().set("temp.db_rules", "punishcheese_rules");
		this.getConfig().set("temp.db_punishment", "punishcheese_punishment");
		
		getCommand("punishcheese").setExecutor(new me.pix7.punishcheese.commands.CommonCommand(this));
		getCommand("addrule").setExecutor(new me.pix7.punishcheese.commands.DatabaseCommand(this));
		getCommand("addruledetail").setExecutor(new me.pix7.punishcheese.commands.DatabaseCommand(this));
		
		getCommand("punish").setExecutor(new me.pix7.punishcheese.commands.PunishCommand(this));
		
		String dbusername = this.getConfig().getString("database.username");
		String dbpassword = this.getConfig().getString("database.password");
		String db = this.getConfig().getString("database.databasename");
		String ip = this.getConfig().getString("database.ip");
		String port = this.getConfig().getString("database.port");
		
		MySQLAPI sql = new MySQLAPI(dbusername, dbpassword, db, ip, port);
		
		try {
			sql.update("CREATE TABLE IF NOT EXISTS `"+this.getConfig().getString("temp.db_rules")+"` (`ruleid` int(3) NOT NULL, `shortdetail` varchar(256) NOT NULL, `detail` varchar(512) ) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		} catch (SQLException e) {
			getLogger().info("PunishCheese cannot create table in MySQL. Please check your MySQL setting in config.yml or check your MySQL driver.");
			e.printStackTrace();
		}
		
		try {
			sql.update("CREATE TABLE IF NOT EXISTS `"+this.getConfig().getString("temp.db_log")+"` (`id` int(11) NOT NULL AUTO_INCREMENT, 'time' varchar(256) NOT NULL, `username` varchar(20) NOT NULL, `operation` varchar(20) NOT NULL, `info` varchar(20) NULL, `ruleid` int(3) NOT NULL, `reason` varchar(256) NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
		} catch (SQLException e) {
			getLogger().info("PunishCheese cannot create table in MySQL. Please check your MySQL setting in config.yml or check your MySQL driver.");
			e.printStackTrace();
		}
		
		try {
			sql.update("CREATE TABLE IF NOT EXISTS `"+this.getConfig().getString("temp.db_punishment")+"` (`id` int(11) NOT NULL AUTO_INCREMENT, `ruleid` int(3) NOT NULL, `times` int(2) NOT NULL, `punishtype` varchar(20) NOT NULL, `info` varchar(128) NOT NULL, PRIMARY KEY (`id`) ) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onDisable(){
		
		
		
	}
	
}
