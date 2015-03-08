package eu.valkyr.CommandCollection;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

public class CommandCollection extends JavaPlugin{

    public void onEnable(){ 
    	this.getLogger().info("CommandCollection wurde aktiviert.");
    }
     
    public void onDisable(){ 
    	this.getLogger().info("CommandCollection wurde deaktiviert.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    	
    	if (cmd.getName().equalsIgnoreCase("clearexcept")) {
    		if (args.length < 2) {
 	           return false;
 	        }
    		Player other = (Bukkit.getServer().getPlayer(args[0]));
	        if (other == null) {
	           sender.sendMessage(ChatColor.RED + "Es ist kein Spieler mit den Namen " + args[0] + " online!");
	           return false;
	        }
	        else {
	        	for (int i=0; i<36; i++) {
	        		Material item = Material.getMaterial(args[1].toUpperCase());
	        		if (other.getInventory().getItem(i) != null) {
	        			if (item != null && !other.getInventory().getItem(i).getType().toString().equals(item.toString())) {
		        			other.getInventory().clear(i);
		        		}
	        		}
	        	}
	        	return true;
	        }
    	}
    	else if (cmd.getName().equalsIgnoreCase("setname")) { 
    		if (args.length < 1) {
  	           return false;
  	        }
    		Player player = null;
        	if (sender instanceof Player) {
        		player = (Player) sender;
        	}
    		if (player == null) {
    			sender.sendMessage(ChatColor.RED + "Dieser Befehl kann nur von einem Spieler ausgeführt werden!");
    			return false;
    		} 
    		else if (player.getItemInHand().getType().equals(Material.AIR)) {
    			sender.sendMessage(ChatColor.RED + "Dieser Befehl kann nur mit einem Item in der Hand ausgeführt werden!");
    			return false;
    		} 
    		else {
    			String name = new String();
    			name = args[0].replace("\\&", "§");
    			for (int i=1; i<args.length; i++) { 
    				name = name + " " + args[i].replace("\\&", "§");
    			}
    			ItemStack item = player.getItemInHand();
    			ItemMeta im = item.getItemMeta();
    			im.setDisplayName(name);
    			item.setItemMeta(im);
    			player.setItemInHand(item);
    			return true;
    		}
    	}
    	else if (cmd.getName().equalsIgnoreCase("setlore")) { 
    		if (args.length < 1) {
  	           return false;
  	        }
    		Player player = null;
        	if (sender instanceof Player) {
        		player = (Player) sender;
        	}
    		if (player == null) {
    			sender.sendMessage(ChatColor.RED + "Dieser Befehl kann nur von einem Spieler ausgeführt werden!");
    			return false;
    		} 
    		else if (player.getItemInHand().getType().equals(Material.AIR)) {
    			sender.sendMessage(ChatColor.RED + "Dieser Befehl kann nur mit einem Item in der Hand ausgeführt werden!");
    			return false;
    		} 
    		else {
    			String text = new String();
    			text = args[0].replace("\\&", "§");
    			for (int i=1; i<args.length; i++) { 
    				text = text + " " + args[i].replace("\\&", "§");
    			}
    			List<String> lore = Arrays.asList(text.split("\\s*\\\\n\\s*"));
    			ItemStack item = player.getItemInHand();
    			ItemMeta im = item.getItemMeta();
    			im.setLore(lore);
    			item.setItemMeta(im);
    			player.setItemInHand(item);
    			return true;
    		}
    	}
    	return false;
    }


	
}
