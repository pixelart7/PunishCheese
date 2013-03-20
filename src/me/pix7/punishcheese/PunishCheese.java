package me.pix7.punishcheese;

import java.sql.SQLException;

import me.pix7.punishcheese.database.MySQLAPI;

import org.bukkit.plugin.java.JavaPlugin;

public class PunishCheese extends JavaPlugin{
	
	public void onEnable(){
		
		this.getConfig().set("PunishCheesePrefix", "&4(Punish) &f");
		getCommand("punishcheese").setExecutor(new me.pix7.punishcheese.commands.CommonCommand(this));
		getCommand("addrule").setExecutor(new me.pix7.punishcheese.commands.DatabaseCommand(this));
		getCommand("addruledetail").setExecutor(new me.pix7.punishcheese.commands.DatabaseCommand(this));
		
		//TODO Change to get these from config
		String username = "root";
		String password = "pixel";
		String db = "bukkit";
		String ip = "localhost";
		String port = "3306";
		
		MySQLAPI sql = new MySQLAPI(username, password, db, ip, port);
		
		try {
			sql.update("CREATE TABLE IF NOT EXISTS `punishcheese_rules` (`ruleid` int(3) NOT NULL, `shortdetail` varchar(256) NOT NULL, `detail` varchar(512) NOT NULL) ENGINE=InnoDB DEFAULT CHARSET=utf8;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			sql.update("CREATE TABLE IF NOT EXISTS `punishcheese_log` (`id` int(7) NOT NULL AUTO_INCREMENT, `username` varchar(20) NOT NULL, `operation` varchar(20) NOT NULL, `rulebreaker` varchar(20) NOT NULL, `ruleid` int(3) NOT NULL, `reason` varchar(256) NOT NULL, PRIMARY KEY (`id`)) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onDisable(){
		
		
		
	}
	
}
