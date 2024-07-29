package com.zabzabdoda.timer;

import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.bossbar.BossBarViewer;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public class BossBarTimer extends Timer{

    private BossBar bossBar;

    public BossBarTimer(JavaPlugin plugin, int startTime, boolean playSound, TimerFinishedCallback callback) {
        super(plugin, startTime, playSound, callback);
        this.bossBar = BossBar.bossBar(Component.text("Timer").color(TimerConstants.pluginPrimaryColor),0.5f,BossBar.Color.GREEN, BossBar.Overlay.PROGRESS);
    }

    @Override
    public void display(ArrayList<Player> players) {
        for(Player p : players){
            p.showBossBar(bossBar);
        }
    }

    @Override
    public void updateTime() {
        bossBar.name(Component.text(querySeconds()+" seconds left!").color(TimerConstants.pluginPrimaryColor));
        bossBar.progress(queryPercent());
    }

    @Override
    public void hide(ArrayList<Player> players) {
        for(BossBarViewer v : bossBar.viewers()){
            if(v instanceof Player){
                Player p = (Player)v;
                bossBar.removeViewer(p);
            }

        }
    }

}
