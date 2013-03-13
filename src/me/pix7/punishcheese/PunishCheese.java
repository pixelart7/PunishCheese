package me.pix7.punishcheese;

import org.bukkit.plugin.java.JavaPlugin;

public class PunishCheese extends JavaPlugin{

	public void onEnable(){
		
		this.getConfig().set("PunishCheesePrefix", "&4(Punish) &f");
		getCommand("punishcheese").setExecutor(new me.pix7.punishcheese.commands.CommonCommand(this));
		
	}
	
	public void onDisable(){
		
		
		
	}
	
}
