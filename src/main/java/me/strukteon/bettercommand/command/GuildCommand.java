package me.strukteon.bettercommand.command;
/*
    Created by nils on 31.07.2018 at 23:27.
    
    (c) nils 2018
*/

import me.strukteon.bettercommand.CommandEvent;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;

public interface GuildCommand extends Command {

    @Override
    default void onExecute(CommandEvent event, String[] args, User author, MessageChannel channel) { }

    /**
     * Called when the command is executed
     * @param event command event
     * @param args passed arguments of the message
     * @param author executor
     * @param channel channel in which the command was executed
     * @throws Exception exception
     */

    void onExecute(CommandEvent event, String[] args, Member author, TextChannel channel) throws Exception;

}
