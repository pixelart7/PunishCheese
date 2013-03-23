package me.pix7.punishcheese.commands;

import java.sql.SQLException;
import java.util.Arrays;

import me.pix7.punishcheese.database.PunishLogger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DatabaseCommand implements CommandExecutor{

	me.pix7.punishcheese.PunishCheese pl;
	String prefix;
	
	public DatabaseCommand(me.pix7.punishcheese.PunishCheese plugin){
		
		this.pl = plugin;
		
		this.prefix = pl.getConfig().getString("temp.PunishCheesePrefix");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		PunishLogger logger = new PunishLogger(pl);
	
		if(cmd.getName().equalsIgnoreCase("addrule")){

			if(args.length < 2){
				sender.sendMessage(prefix+"Wrong parameters length.");
				return false;	
			}
			try{
				Integer.parseInt(args[0]);
			} catch (NumberFormatException e){
				sender.sendMessage(prefix+"Rule ID must be a number.");
				//e.printStackTrace();
				return false;
			}
			int ruleId = Integer.parseInt(args[0]);
			int ruleIdLength = Integer.toString(ruleId).length();
			if(ruleIdLength > 3){
				sender.sendMessage(prefix+"Your rule id have more than 3 digits.");
				return false;		
			}
			
			String[] argsNew = Arrays.copyOfRange(args, 1, args.length);
			
			StringBuilder strfinal = new StringBuilder(); 
			for(String str : argsNew) {
				strfinal.append(str);
				strfinal.append(" ");
			}
			strfinal.deleteCharAt(strfinal.length()-1);
			String shortdetail = strfinal.toString();
			
			try {
				boolean resultLogger = logger.addRule(ruleId, shortdetail);
				if(resultLogger)return true;
				sender.sendMessage("Something wrong while trying to add row into MySQL database.");
				return false;
			} catch (SQLException e) {
				sender.sendMessage("Something wrong please check message at console.");
				e.printStackTrace();
				return false;
			}
			
		}else if(cmd.getName().equalsIgnoreCase("addruledetail")){
			
			
			
			return true;
			
		}
		
		return false;
		
	}
	
}
