package me.strukteon.bettercommand.command;
/*
    Created by nils on 01.08.2018 at 23:53.
    
    (c) nils 2018
*/

import me.strukteon.bettercommand.CommandEvent;
import me.strukteon.bettercommand.tools.CommandInfo;
import me.strukteon.bettercommand.tools.PermissionManager;
import net.dv8tion.jda.core.entities.MessageEmbed;

public interface BaseCommand {

    /**
     * Set this commands {@link PermissionManager}
     * @return {@link PermissionManager} for this command
     */

    default PermissionManager getPermissionManager(){
        return new PermissionManager();
    }


    /**
     * Override the default automatic help message
     * @param event the event which occurs when the help message is requested
     * @return message converted to a {@link MessageEmbed}
     */

    default MessageEmbed setOverridenHelp(CommandEvent event){
        return null;
    }


    /**
     * Set this commands {@link CommandInfo}
     * @return {@link CommandInfo} for this command
     */

    CommandInfo getCommandInfo();

}
