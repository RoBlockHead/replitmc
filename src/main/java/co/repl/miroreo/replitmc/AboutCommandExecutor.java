package co.repl.miroreo.replitmc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class AboutCommandExecutor implements CommandExecutor {
  private final App plugin;
  public AboutCommandExecutor(App plugin) {
    this.plugin = plugin;
  }
  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    sender.sendMessage("§e------------------------------§r");
    sender.sendMessage("ReplitMC by miroreo");
    sender.sendMessage("Version §6" + plugin.getDescription().getVersion() + "§r");
    sender.sendMessage("§e------------------------------§r");
    return true;
  }
}
