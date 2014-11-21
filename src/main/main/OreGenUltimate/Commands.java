package main.OreGenUltimate;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {
    private OreGenUltimate _ogu;

    public Commands(OreGenUltimate ogu) {
        _ogu = ogu;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
        if(commandLabel.equalsIgnoreCase("ogu")){
            if(args.length == 0){
                if(sender.hasPermission("ogu.reload")){
                    sender.sendMessage(ChatColor.GREEN + "######## OreGenUltimate Usage ######## \n" + "/ogu reload   Reloads the config file.");
                }else{
                    sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                }
                return true;
            }else{
                if(args[0].equalsIgnoreCase("reload")){
                    Player player = null;
                    //Check if it was a player
                    if(sender instanceof Player){
                        player = (Player) sender;
                        //Check if they have permission
                        if(player.hasPermission("ogu.reload")){
                            //If so...reload
                            _ogu.reloadConfig();
                            sender.sendMessage(ChatColor.GREEN + "OreGenUltimate config file reloaded.");
                        }else{
                            //Otherwise send an error message
                            sender.sendMessage(ChatColor.RED + "You do not have permission to use this command");
                        }
                    }else{
                        //If it wasn't a player it must have been from the console.
                        _ogu.reloadConfig();
                        _ogu.log.info("OreGenUltimate config file reloaded.");
                    }
                }
            }
        }
        return true;
    }
}