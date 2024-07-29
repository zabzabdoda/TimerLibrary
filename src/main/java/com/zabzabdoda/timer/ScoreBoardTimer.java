package com.zabzabdoda.timer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;

public class ScoreBoardTimer extends Timer{

    private ScoreboardManager scoreboardManager;
    private Scoreboard scoreboard;
    private Score score;
    private Objective objective;

    public ScoreBoardTimer(JavaPlugin plugin, int startTime, boolean playSound, TimerFinishedCallback callback) {
        super(plugin, startTime, playSound, callback);
        scoreboardManager = Bukkit.getScoreboardManager();
        scoreboard = scoreboardManager.getMainScoreboard();
        if(scoreboard.getObjective("timer") == null){
            scoreboard.registerNewObjective("timer",Criteria.DUMMY, Component.text("Timer").color(TimerConstants.pluginPrimaryColor), RenderType.INTEGER);
        }
        objective = scoreboard.getObjective("timer");
        if(scoreboard.getTeam("timer") == null){
            scoreboard.registerNewTeam("timer").color(NamedTextColor.GREEN);
        }
        scoreboard.getTeam("timer").addEntry("Time Left: ");
        score = objective.getScore("Time Left: ");
        score.setScore(startTime);
    }

    @Override
    public void display(ArrayList<Player> players) {
        for(Player p : players){
            p.setScoreboard(scoreboard);
        }
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
    }

    @Override
    public void updateTime() {
        score = objective.getScore("Time Left: ");
        score.setScore(querySeconds());
    }

    @Override
    public void hide(ArrayList<Player> players) {
        scoreboard.clearSlot(DisplaySlot.SIDEBAR);
    }
}
