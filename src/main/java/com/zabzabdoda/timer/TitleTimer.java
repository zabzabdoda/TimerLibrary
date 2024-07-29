package com.zabzabdoda.timer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;
import java.util.ArrayList;

public class TitleTimer extends Timer{

    private ArrayList<Player> players = new ArrayList<>();
    private boolean subtitle;

    public TitleTimer(JavaPlugin plugin, boolean subtitle, int startTime, boolean playSound, TimerFinishedCallback callback) {
        super(plugin, startTime, playSound, callback);
        this.subtitle = subtitle;
    }

    @Override
    public void display(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public void updateTime() {
        if(subtitle){
            for(Player p : players){
                p.showTitle(Title.title(Component.text(""),Component.text(querySeconds()).color(TimerConstants.pluginPrimaryColor), Title.Times.times(Duration.ZERO,Duration.ofMillis(1100),Duration.ZERO)));
            }
        }else{
            for(Player p : players){
                p.showTitle(Title.title(Component.text(querySeconds()).color(TimerConstants.pluginPrimaryColor),Component.text(""), Title.Times.times(Duration.ZERO,Duration.ofMillis(1100),Duration.ZERO)));
            }
        }

    }

    @Override
    public void hide(ArrayList<Player> players) {

    }
}
