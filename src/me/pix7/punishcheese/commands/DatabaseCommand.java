package me.pix7.punishcheese.commands;

import java.sql.SQLException;
import java.util.Arrays;

import me.pix7.punishcheese.database.PunishDatabase;
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
		
		PunishDatabase logger = new PunishDatabase(pl);
	
		if(cmd.getName().equalsIgnoreCase("addrule")){

			if(args.length < 2){
				sender.sendMessage(prefix+"This command needs more parameters.");
				return false;	
			}
			try{
				Integer.parseInt(args[0]);
			} catch (NumberFormatException e){
				sender.sendMessage(prefix+"Rule ID must be a number.");
				//e.printStackTrace();
				return true;
			}
			int ruleId = Integer.parseInt(args[0]);
			int ruleIdLength = Integer.toString(ruleId).length();
			if(ruleIdLength > 3){
				sender.sendMessage(prefix+"Rule ID must be less than 3 digits (0-999).");
				return true;		
			}
			
			String[] argsNew = Arrays.copyOfRange(args, 1, args.length);
			
			StringBuilder strfinal = new StringBuilder(); 
			for(String str : argsNew) {
				strfinal.append(str);
				strfinal.append(" ");
			}
			strfinal.deleteCharAt(strfinal.length()-1);
			String shortdetail = strfinal.toString();
			
			if(shortdetail.length() > 256){
				sender.sendMessage(prefix+"Short detail must be less than 256 characters.");
				return true;
			}
			
			try {
				boolean resultLogger = logger.addRule(ruleId, shortdetail);
				if(resultLogger){
					sender.sendMessage(prefix+"Added/Updated successful.");
					return true;
				}
				sender.sendMessage(prefix+"Something wrong while trying to add row into MySQL database.");
			} catch (SQLException e) {
				sender.sendMessage(prefix+"Something wrong please check message at console.");
				e.printStackTrace();
			}
			
		}else if(cmd.getName().equalsIgnoreCase("addruledetail")){
			

			if(args.length < 2){
				sender.sendMessage(prefix+"This command needs more parameters.");
				return false;	
			}
			try{
				Integer.parseInt(args[0]);
			} catch (NumberFormatException e){
				sender.sendMessage(prefix+"Rule ID must be a number.");
				//e.printStackTrace();
				return true;
			}
			int ruleId = Integer.parseInt(args[0]);
			int ruleIdLength = Integer.toString(ruleId).length();
			if(ruleIdLength > 3){
				sender.sendMessage(prefix+"Rule ID must be less than 3 digits (0-999).");
				return true;		
			}
			
			String[] argsNew = Arrays.copyOfRange(args, 1, args.length);
			
			StringBuilder strfinal = new StringBuilder(); 
			for(String str : argsNew) {
				strfinal.append(str);
				strfinal.append(" ");
			}
			strfinal.deleteCharAt(strfinal.length()-1);
			String detail = strfinal.toString();
			
			try {
				boolean resultLogger = logger.addRuleDetail(ruleId, detail);
				if(resultLogger){
					sender.sendMessage(prefix+"Updated detail for your specific rule.");
					return true;
				}else{
					sender.sendMessage(prefix+"You needs to create rule by using '/addrule' first before using this command.");
					return true;
				}
			} catch (SQLException e) {
				sender.sendMessage(prefix+"Something wrong while trying to add row into MySQL database.");
				return true;
			}
			
		}
		
		return false;
		
	}
	
}
