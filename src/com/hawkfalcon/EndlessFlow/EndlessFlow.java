package com.hawkfalcon.EndlessFlow;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EndlessFlow extends JavaPlugin implements Listener {
	boolean enable = true;
	boolean water = true;
	boolean lava = true;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void onDisable(){}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		if (cmd.getName().equalsIgnoreCase("ef") || cmd.getName().equalsIgnoreCase("endlessflow") && sender.hasPermission("endlessflow.*")) {
			if (args.length == 0) {		
				message("help");
			}
			if (args.length == 1){
				if (args[0].equalsIgnoreCase("lava")){
					if (lava){
						lava = false;
						message("Lava toggled off!");
					} else {
						lava = true;
						message("Lava toggled on!");
					}
				}
				if (args[0].equalsIgnoreCase("water")){
					if (water){
						water = false;
						message("Water toggled off!");
					} else {
						water = true;
						message("Water toggled on!");
					}
				}
			}
		}
		return true;
	}

	@EventHandler
	public void idk(BlockFromToEvent event) {
		Block b = event.getBlock();
		Block bs = event.getToBlock();
		BlockFace[] face = {BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST, BlockFace.WEST};
		for(BlockFace faces : face) {
			if (b.getRelative(faces).getType().equals(Material.AIR)){
				if ((b.getTypeId() == 8) && (water)){
					if((bs.getRelative(BlockFace.DOWN).getTypeId() != 0)&&(bs.getRelative(BlockFace.DOWN).getTypeId() != 8)&&(bs.getRelative(BlockFace.DOWN).getTypeId() != 9)){
						b.getRelative(faces).setTypeId(8);
					}
				}
				else if ((b.getTypeId() == 10) && (lava)){
					if((bs.getRelative(BlockFace.DOWN).getTypeId() != 0)&&(bs.getRelative(BlockFace.DOWN).getTypeId() != 8)&&(bs.getRelative(BlockFace.DOWN).getTypeId() != 9)){
						b.getRelative(faces).setTypeId(10);
					}
				}
			}
		}   
	}
	public void message(String message){
		getServer().broadcastMessage(ChatColor.YELLOW + "[" + ChatColor.RED + "EndlessFlow" + ChatColor.YELLOW + "] " + ChatColor.BLUE + message);
	}
}