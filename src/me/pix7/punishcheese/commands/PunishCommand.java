package me.pix7.punishcheese.commands;

import me.pix7.punishcheese.PunishMethod;

import org.bukkit.Color;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class PunishCommand implements CommandExecutor{
	
	me.pix7.punishcheese.PunishCheese pl;
	String prefix;
	
	public PunishCommand(me.pix7.punishcheese.PunishCheese plugin){
		
		this.pl = plugin;
		
		this.prefix = pl.getConfig().getString("temp.PunishCheesePrefix");
		
	}
	
	public void usage(CommandSender sender){
		
		sender.sendMessage(Color.ORANGE+"(PunishCheese) "+Color.GRAY+"'/punish' usage.");
		sender.sendMessage(Color.ORANGE+"+-- /punish <parameters>");
		sender.sendMessage(Color.ORANGE+"+-- Parameters: 'p:<username>', 'i:<ruleid>', 't:(times)', 'r:(reason) *Must be last parameter' ");
		sender.sendMessage(Color.ORANGE+"+-- Use for place a punishment to player.");
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	
		if(cmd.getName().equalsIgnoreCase("punish")){ //punish USERNAME RULEID TIMES REASON
		
			if(args.length < 2){
				
				this.usage(sender);
				return true;
				
			}
			
			String username = null;
			int ruleIdint = -1;
			int timesint = -1;
			
			for(String str : args){
				
				if(str.contains("p:")){ //get username
					username = str.replaceAll("p:", "");
				}
				if(str.contains("i:")){ //get ruleId
					ruleIdint = Integer.parseInt(str.replaceAll("i:", ""));
				}
				if(str.contains("t:")){ //get times
					timesint = Integer.parseInt(str.replaceAll("t:", ""));
				}
		
			}
			
			if(timesint == -1){
				timesint = 1;
			}
			if(ruleIdint == -1){
				this.usage(sender);
				return true;
			}
			if(username == null){
				this.usage(sender);
				return true;
			}
			
			username = username.toLowerCase();
			
			PunishMethod punish = new PunishMethod(pl, sender, username, ruleIdint, timesint);
			
		}
		
		return false;

	}
}
