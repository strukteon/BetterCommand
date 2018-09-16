package me.strukteon.bettercommand.tools;
/*
    Created by nils on 01.08.2018 at 21:17.
    
    (c) nils 2018
*/

import me.strukteon.bettercommand.CommandEvent;
import me.strukteon.bettercommand.command.BaseCommand;
import me.strukteon.bettercommand.command.Command;
import me.strukteon.bettercommand.syntax.SyntaxValidateException;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

import java.util.List;

public interface ErrorHandler {

    /**
     * Called when the bot is missing permissions
     * @param event event of the executed command
     * @param missingPermissions permissions that the bot is missing
     * @param command command in which the error occurred
     */

    void missingBotPermissions(CommandEvent event, List<Permission> missingPermissions, BaseCommand command);


    /**
     * Called when the user is missing permissions
     * @param event event of the executed command
     * @param missingPermissions permissions that the user is missing
     * @param command command in which the error occurred
     */

    void missingUserPermissions(CommandEvent event, List<Permission> missingPermissions, BaseCommand command);


    /**
     * Called when the user who executed the command is not in the list provided in the commandinfo
     * @param event event of the executed command
     * @param command command in which the error occurred
     */

    void notInUserlist(CommandEvent event, BaseCommand command);

    boolean validateException(CommandEvent event, BaseCommand command, SyntaxValidateException e);


    /**
     * Called when an exception occurs in a commands {@link Command#onExecute(CommandEvent, String[], User, MessageChannel)}
     * @param event event of the executed command
     * @param command command in which the error occurred
     * @param e occurred exception
     */

    void onException(CommandEvent event, BaseCommand command, Exception e);

}
