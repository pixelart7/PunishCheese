package me.pix7.punishcheese.database;

public class PunishLogger {
	
	me.pix7.punishcheese.commands.DatabaseCommand pl;
	
	public PunishLogger(me.pix7.punishcheese.commands.DatabaseCommand plugin) {

		this.pl = plugin;
		
	}
	
	/**
	 * PunishLogger.log() will INSERT log into punishcheese_log
	 * which return true if INSERT goes well
	 */
	public boolean log(String username, String operation, String rulebreaker, int ruleid, String reason){
		
		String dbusername = pl.getConfig().getString("database.username");
		String dbpassword = pl.getConfig().getString("database.password");
		String db = pl.getConfig().getString("database.databasename");
		String ip = pl.getConfig().getString("database.ip");
		String port = pl.getConfig().getString("database.port");
		
		MySQLAPI sql = new MySQLAPI(reason, reason, reason, reason, reason);
		
		return false; //return false when error
		
	}

}
