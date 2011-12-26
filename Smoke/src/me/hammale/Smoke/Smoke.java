package me.hammale.Smoke;

import java.util.logging.Logger;

import org.bukkit.Effect;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Smoke extends JavaPlugin {
	  
	  Logger log = Logger.getLogger("Minecraft");
	
	@Override
	public void onEnable() {

		PluginDescriptionFile pdfFile = this.getDescription();
		
		log.info("Smoke Plugin Version: " + pdfFile.getVersion() + " Enabled!");
		
	}
	
	@Override
	public void onDisable() {
		
		PluginDescriptionFile pdfFile = this.getDescription();
		
		log.info("Smoke Plugin Version: " + pdfFile.getVersion() + " Disabled!");
		
	}

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
    	if(cmd.getName().equalsIgnoreCase("smoke")){
    	if(args.length == 1){
    		Player p = (Player) sender;
    		if(p != null){
    			Block b = p.getLocation().getBlock();
    			int val = Integer.parseInt(args[0]);
    			int i = 1;
    			b.getWorld().playEffect(b.getLocation(), Effect.SMOKE, 1000);
    			while(i <= val){
        			Block b1 = b.getRelative(BlockFace.UP, i);
        			b1.getWorld().playEffect(b1.getLocation(), Effect.SMOKE, 1000);
    				i++;
    			}
    			repeatTask(b, val);
    		}
    	}	
    		return true;
    	}
    		return false; 
    }

    public void repeatTask(final Block b, final int val){
    	getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {

    	    public void run() {
    			int i = 1;
    			b.getWorld().playEffect(b.getLocation(), Effect.SMOKE, 1000);
    			while(i <= val){
        			Block b1 = b.getRelative(BlockFace.UP, i);
        			b1.getWorld().playEffect(b1.getLocation(), Effect.SMOKE, 1000);
    				i++;
    			}
    	    }
    	}, 5L, 5L);
    }
	
}