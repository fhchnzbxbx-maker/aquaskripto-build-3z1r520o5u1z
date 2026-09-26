package com.aquaskripto.aquaplugin;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class AquaPlugin extends JavaPlugin {

    private Command diamentCommand;

    @Override
    public void onEnable() {
        diamentCommand = new Command("diament") {
            @Override
            public boolean execute(CommandSender sender, String label, String[] args) {
                if (!(sender instanceof Player)) {
                    sender.sendMessage("Ta komenda jest dostępna tylko dla graczy.");
                    return true;
                }

                Player player = (Player) sender;
                ItemStack diamond = new ItemStack(Material.DIAMOND, 1);

                for (ItemStack leftover :
                        player.getInventory().addItem(diamond).values()) {
                    player.getWorld().dropItemNaturally(
                            player.getLocation(), leftover);
                }

                player.sendMessage("Otrzymałeś jeden diament!");
                return true;
            }
        };

        diamentCommand.setDescription("Daje graczowi jeden diament.");
        diamentCommand.setUsage("/diament");
        getServer().getCommandMap().register("aquaplugin", diamentCommand);
    }

    @Override
    public void onDisable() {
        if (diamentCommand != null) {
            diamentCommand.unregister(getServer().getCommandMap());
        }
    }
}