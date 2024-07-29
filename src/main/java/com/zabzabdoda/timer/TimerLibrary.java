package com.zabzabdoda.timer;

import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public final class TimerLibrary extends JavaPlugin {

    private Timer bossBarTimer,scoreboardTimer,titleTimer,subtitleTimer;
    public static TimerLibrary plugin;

    @Override
    public void onEnable() {
        plugin = this;
        bossBarTimer = new BossBarTimer(this, 10, true, new TimerFinishedCallback() {
            @Override
            public void onTimerFinished() {
                timerFinished(bossBarTimer);
            }
        });
        scoreboardTimer = new ScoreBoardTimer(this, 10,true, new TimerFinishedCallback() {
            @Override
            public void onTimerFinished() {
                timerFinished(scoreboardTimer);
            }
        });
        titleTimer = new TitleTimer(this, false, 10,true, new TimerFinishedCallback() {
            @Override
            public void onTimerFinished() {
                timerFinished(titleTimer);

            }
        });
        subtitleTimer = new TitleTimer(this, true, 10,true, new TimerFinishedCallback() {
            @Override
            public void onTimerFinished() {
                timerFinished(subtitleTimer);

            }
        });
        this.getCommand("timer").setExecutor(new TimerCommand(bossBarTimer,scoreboardTimer,titleTimer,subtitleTimer));
    }

    @Override
    public void onDisable() {
    }

    public void timerFinished(Timer timer){
        for(Player p : Bukkit.getOnlinePlayers()) {
            p.sendMessage(Component.text("The timer has finished now the wold will end!").color(TimerConstants.pluginPrimaryColor));
        }
        new BukkitRunnable(){
            int runs = 0;
            @Override
            public void run() {
                if(runs >= 10){
                    this.cancel();
                }else if(runs == 0){
                    timer.hide(new ArrayList<>(Bukkit.getOnlinePlayers()));
                }
                for(Player p : Bukkit.getOnlinePlayers()){
                    TNTPrimed tnt = (TNTPrimed) p.getWorld().spawnEntity(p.getLocation(), EntityType.TNT);
                    tnt.setFuseTicks(30);
                }
                runs++;
            }
        }.runTaskTimer(plugin,20,10);
    }
}
