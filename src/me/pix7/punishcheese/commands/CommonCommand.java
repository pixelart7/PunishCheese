package me.pix7.punishcheese.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommonCommand implements CommandExecutor {

	me.pix7.punishcheese.PunishCheese pl;
	
	public CommonCommand(me.pix7.punishcheese.PunishCheese plugin){
		
		this.pl = plugin;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		if(cmd.getName().equalsIgnoreCase("punishcheese")){
		
			String prefix = ChatColor.translateAlternateColorCodes('&', (pl.getConfig().getString("temp.PunishCheesePrefix")));	
			sender.sendMessage(prefix+"PunishCheese!");
			//TODO add more info
			
			return true;
		
		}
		
		return false;
		
	}
	
}
