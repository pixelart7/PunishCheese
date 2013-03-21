package me.pix7.punishcheese;

import java.sql.SQLException;

import me.pix7.punishcheese.database.MySQLAPI;

import org.bukkit.plugin.java.JavaPlugin;

public class PunishCheese extends JavaPlugin{
	
	public void onEnable(){
		
		this.saveDefaultConfig();
		this.getConfig().set("PunishCheesePrefix", "&4(Punish) &f");
		getCommand("punishcheese").setExecutor(new me.pix7.punishcheese.commands.CommonCommand(this));
		getCommand("addrule").setExecutor(new me.pix7.punishcheese.commands.DatabaseCommand(this));
		getCommand("addruledetail").setExecutor(new me.pix7.punishcheese.commands.DatabaseCommand(this));
		
		String username = this.getConfig().getString("database.username");
		String password = this.getConfig().getString("database.password");
		String db = this.getConfig().getString("database.databasename");
		String ip = this.getConfig().getString("database.ip");
		String port = this.getConfig().getString("database.port");
		
		MySQLAPI sql = new MySQLAPI(username, password, db, ip, port);
		
		try {
			sql.update("CREATE TABLE IF NOT EXISTS `punishcheese_rules` (`ruleid` int(3) NOT NULL, `shortdetail` varchar(256) NOT NULL, `detail` varchar(512) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		} catch (SQLException e) {
			getLogger().info("PunishCheese cannot create table in MySQL. Please check your MySQL in config.yml");
			//e.printStackTrace();
		}
		
		try {
			sql.update("CREATE TABLE IF NOT EXISTS `punishcheese_log` (`id` int(7) NOT NULL AUTO_INCREMENT, `username` varchar(20) NOT NULL, `operation` varchar(20) NOT NULL, `rulebreaker` varchar(20) NOT NULL, `ruleid` int(3) NOT NULL, `reason` varchar(256) NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
		} catch (SQLException e) {
			getLogger().info("PunishCheese cannot create table in MySQL. Please check your MySQL in config.yml");
			//e.printStackTrace();
		}
		
	}
	
	public void onDisable(){
		
		
		
	}
	
}
