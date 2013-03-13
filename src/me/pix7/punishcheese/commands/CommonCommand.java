package me.pix7.punishcheese.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommonCommand implements CommandExecutor {

	me.pix7.punishcheese.PunishCheese pl;
	
	public CommonCommand(me.pix7.punishcheese.PunishCheese plugin){
		
		this.pl = plugin;
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		return false;
		
	}
	
}
