package co.repl.miroreo.replitmc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GetReplCommandExecutor implements CommandExecutor{
  private final App plugin;

  public GetReplCommandExecutor(App plugin) {
    this.plugin = plugin;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    if (args.length == 0) {
      sender.sendMessage("Usage: /getrepl <player>");
      return true;
    }
    Player senderPlayer = (Player) sender;
    senderPlayer.sendMessage("§aGetting Repl...");
    return true;
  }
}
