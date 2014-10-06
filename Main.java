package me.banana.hub;

import java.io.PrintStream;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.FileConfigurationOptions;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
  private ChatColor color;

  public void onEnable()
  {
    System.out.println("[Hub++] Enabled");
    getConfig().options().copyDefaults(true);
    saveConfig();
    PluginManager pm = getServer().getPluginManager();
    pm.registerEvents(new PlayerCommands(this), this);
  }

  public void onDisable()
  {
    System.out.println("[Hub++] Disabled");
  }

  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
  {
    if ((sender instanceof Player)) {
      Player player = (Player)sender;

      setColor(ChatColor.GOLD);
      String prefix = ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Hub++" + ChatColor.GRAY + "]";
      if (cmd.getName().equalsIgnoreCase("hub")) {
        if (getConfig().getString("spawn") == null) {
          player.sendMessage(prefix + ChatColor.RED + " No SpawnPoint has been set !");
          return true;
        }

        World world = Bukkit.getServer().getWorld(getConfig().getString("spawn.world"));
        double x = getConfig().getDouble("spawn.x");
        double y = getConfig().getDouble("spawn.y");
        double z = getConfig().getDouble("spawn.z");
        player.teleport(new Location(world, x, y, z));
        player.sendMessage(prefix + ChatColor.BLUE + " You have been teleport to spawn!");
      }

      return false;
    }
    return false;
  }

  public ChatColor getColor() {
    return this.color;
  }

  public void setColor(ChatColor color) {
    this.color = color;
  }
}
