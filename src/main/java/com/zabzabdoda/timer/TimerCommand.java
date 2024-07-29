package com.zabzabdoda.timer;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.Style;
import net.kyori.adventure.text.format.TextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TimerCommand implements CommandExecutor {

    private Timer bossBarTimer,scoreboardTimer,titleTimer,subtitleTimer;

    public TimerCommand(Timer bossBarTimer, Timer scoreboardTimer, Timer titleTimer, Timer subtitleTimer){
        this.bossBarTimer = bossBarTimer;
        this.scoreboardTimer = scoreboardTimer;
        this.titleTimer = titleTimer;
        this.subtitleTimer = subtitleTimer;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("timer")){
            if(args.length == 0){
                commandSender.sendMessage(Component.text("invalid usage, Type '/timer help' to get a list of commands.", Style.style(TextColor.fromHexString("#D82A3B"))));
                return true;
            }else if(args.length == 1){
                if(args[0].equalsIgnoreCase("get")) {
                    commandSender.sendMessage(Component.text("Current timer's time is: ")
                            .color(TimerConstants.pluginPrimaryColor)
                            .append(Component.text(bossBarTimer.querySeconds())
                            .color(TimerConstants.pluginSecondaryColor)
                            .decorate(TextDecoration.BOLD))
                    );
                    return true;
                }else if(args[0].equalsIgnoreCase("boss")){
                    commandSender.sendMessage(Component.text("Starting timer")
                            .color(TimerConstants.pluginPrimaryColor));
                    bossBarTimer.start(new ArrayList<>(Bukkit.getOnlinePlayers()));
                    return true;
                }else if(args[0].equalsIgnoreCase("score")){
                    commandSender.sendMessage(Component.text("Starting timer")
                            .color(TimerConstants.pluginPrimaryColor));
                    scoreboardTimer.start(new ArrayList<>(Bukkit.getOnlinePlayers()));
                    return true;
                }else if(args[0].equalsIgnoreCase("title")){
                    commandSender.sendMessage(Component.text("Starting timer")
                            .color(TimerConstants.pluginPrimaryColor));
                    titleTimer.start(new ArrayList<>(Bukkit.getOnlinePlayers()));
                    return true;
                }else if(args[0].equalsIgnoreCase("sub")){
                    commandSender.sendMessage(Component.text("Starting timer")
                            .color(TimerConstants.pluginPrimaryColor));
                    subtitleTimer.start(new ArrayList<>(Bukkit.getOnlinePlayers()));
                    return true;
                }
            }
        }
        return false;
    }
}
