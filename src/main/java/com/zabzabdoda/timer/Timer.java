package com.zabzabdoda.timer;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public abstract class Timer {

    private BukkitRunnable runnable;
    private JavaPlugin plugin;
    private int currentTime;
    private int startTime;
    private boolean playSound;

    private TimerFinishedCallback callback;

    public Timer(JavaPlugin plugin, int startTime,  boolean playSound, TimerFinishedCallback callback){
        this.plugin = plugin;
        this.callback = callback;
        this.startTime = startTime;
        this.playSound = playSound;

    }

    public abstract void display(ArrayList<Player> players);

    public abstract void updateTime();

    public abstract  void hide(ArrayList<Player> players);


    public void start(ArrayList<Player> players){
        display(players);
        this.currentTime = startTime;
        runnable = new BukkitRunnable() {
            @Override
            public void run() {

                updateTime();
                if(playSound && currentTime != startTime){
                    for(Player p : players){
                        if(currentTime != 0) {
                            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_HAT, SoundCategory.RECORDS, 1, 1);
                        }else{
                            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, SoundCategory.RECORDS, 1, 1);
                        }
                    }
                }
                if(currentTime <= 0){
                    callback.onTimerFinished();
                    this.cancel();
                }
                currentTime--;
            }
        };
        runnable.runTaskTimer(plugin, 0, 20);

    }

    public void stop() {
        runnable.cancel();
    }

    public int querySeconds(){
        return currentTime;
    }

    public float queryPercent(){
        return ((float)currentTime/startTime);
    }

}
