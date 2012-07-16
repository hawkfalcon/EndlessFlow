//Made by: Hawkfalcon1. Feel free to use the code
package com.hawkfalcon1.EndlessFlow;


import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class EndlessFlow extends JavaPlugin implements Listener {

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void onDisable() {
	}
	boolean enable = true;
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("efon")) {
			if (sender.hasPermission("EndlessFlow.efon")) {
				getServer().broadcastMessage(ChatColor.WHITE + "[" + ChatColor.RED + "EndlessFlow" + ChatColor.WHITE + "] " + ChatColor.BLUE + "EndlessFlow has been enabled!");
				enable = true;
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permission!");
			}
		}
		if (cmd.getName().equalsIgnoreCase("efoff")) {
			if (sender.hasPermission("EndlessFlow.efoff")) {
				getServer().broadcastMessage(ChatColor.WHITE + "[" + ChatColor.RED + "EndlessFlow" + ChatColor.WHITE + "] " + ChatColor.BLUE + "EndlessFlow has been disabled!");
				enable = false;
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permission!");
			}
		}
		return true;
	}
	@EventHandler
	public void onBlockFromTo(BlockFromToEvent event) {
		if(enable == true){
			if (!event.isCancelled()) {
				Block from = event.getBlock();
				Block to = event.getToBlock();
				if (to.getTypeId() != 0) return;
				int type = from.getTypeId();
				if (type == 9) {
					if (flowLava(from, to)) {
						event.setCancelled(true);
					}
				} else if (type == 11) {
					if (flowWater(from, to)) {
						event.setCancelled(true);
					}
				}
			}
		} 
	}

	public boolean blockEquals(Block b1, Block b2) {
		if(enable == true){
			if (b1.getX() == b2.getX()) {
				if (b1.getY() == b2.getY()) {
					if (b1.getZ() == b2.getZ()) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean flowWater(Block from, Block to) {
		if(enable == true){
			for (BlockFace face : new BlockFace[] {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST}) {
				Block relative = to.getRelative(face);
				if (blockEquals(from, relative)) continue;
				int id = relative.getTypeId();
				if (id == 10) {
					to.setTypeId(11);
					return true;
				}
			}
		}
		return false;
	}
	public boolean flowLava(Block from, Block to) {
		if(enable == true){
			for (BlockFace face : new BlockFace[] {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST}) {
				Block relative = to.getRelative(face);
				if (blockEquals(from, relative)) continue;
				int id = relative.getTypeId();
				if (id == 8) {
					to.setTypeId(9);
					return true;
				}
			}
		}
		return false;
	}
}



