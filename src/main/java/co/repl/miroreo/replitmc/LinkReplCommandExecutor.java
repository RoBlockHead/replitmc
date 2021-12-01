package co.repl.miroreo.replitmc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class LinkReplCommandExecutor implements CommandExecutor {
  private final App plugin;
  public LinkReplCommandExecutor(App plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (!(sender instanceof Player)) {
      sender.sendMessage("This command can only be run by a player.");
      return true;
    }
    final Player player = (Player) sender;
    Bukkit.getScheduler().runTaskAsynchronously(plugin, new Runnable() {
      @Override
      public void run() {
        try {
          URL url = new URL(
            plugin.API_ENDPOINT + "/api/newlink" 
            + "?uuid=" + player.getUniqueId().toString() 
            + "&online=" + plugin.getServer().getOnlineMode());
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();
          connection.setRequestMethod("GET");
          connection.setRequestProperty("User-Agent", "Replit for Minecraft");
          connection.setRequestProperty("Authorization", plugin.getConfig().getString("serverId") + " " + plugin.getConfig().getString("token"));
          connection.setRequestProperty("Accept", "text/plain");
          connection.connect();
          if (connection.getResponseCode() == 200) {
            BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));
            StringBuilder sb = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
              sb.append(output);
            }
            String link = sb.toString();
            player.sendMessage("§a§lReplit§r§7: §fYour link is §a" + link);
          } else {
            player.sendMessage("§a§lReplit§r§7: §fAn error occurred while creating your link.");
          }
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
    return true;
  }
}
