package co.repl.miroreo.replitmc;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class App extends JavaPlugin {
  public final String API_ENDPOINT = "https://mclink.roblockhead.repl.co";
  @Override
  public void onEnable() {
    saveDefaultConfig();
    getLogger().info("Hello from Replit MC!");
    getLogger().info("Checking for connection...");
    Bukkit.getScheduler().runTaskAsynchronously(this, new Runnable() {
      @Override
      public void run() {
        try {
          URL url = new URL(API_ENDPOINT + "/api/ping");
          if(getConfig().getBoolean("insecureMode")) {
            getLogger().log(Level.WARNING, "Insecure mode is enabled. This is not recommended.");
            TrustAllCertificates.install();
          }
          HttpURLConnection con = (HttpURLConnection) url.openConnection();
          con.setRequestMethod("GET");
          con.connect();
          con.getResponseCode();
          if (con.getResponseCode() == 200 || con.getResponseCode() == 202) {
            getLogger().log(Level.INFO, "API is up!");
          } else {
            getLogger().log(Level.SEVERE, "API is down! Disabling Plugin.");
            getServer().getPluginManager().disablePlugin(App.this);
          }
        } catch (Exception e) {
          e.printStackTrace();
          getLogger().log(Level.SEVERE, "Error while verifying API Connection! Disabling Plugin.");
          getServer().getPluginManager().disablePlugin(App.this);
        }
      }
    });
    getCommand("getrepl").setExecutor(new GetReplCommandExecutor(this));
    getCommand("linkrepl").setExecutor(new LinkReplCommandExecutor(this));
    getCommand("replit").setExecutor(new AboutCommandExecutor(this));
  }
  
  @Override
  public void onDisable() {
    getLogger().info("Replit MC Shutting Down.");
  }


}
