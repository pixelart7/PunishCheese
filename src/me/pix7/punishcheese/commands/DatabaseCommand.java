package me.pix7.punishcheese.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DatabaseCommand implements CommandExecutor{

	me.pix7.punishcheese.PunishCheese pl;
	
	public DatabaseCommand(me.pix7.punishcheese.PunishCheese plugin){
		
		this.pl = plugin;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
	
		if(cmd.getName().equalsIgnoreCase("addrule")){
			
			
			
			return true;
			
		}else if(cmd.getName().equalsIgnoreCase("addruledetail")){
			
			
			
			return true;
			
		}
		
		return false;
		
	}
	
}