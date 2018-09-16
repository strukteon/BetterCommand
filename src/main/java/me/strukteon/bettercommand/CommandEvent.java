package me.strukteon.bettercommand;
/*
    Created by nils on 08.08.2018 at 21:13.
    
    (c) nils 2018
*/

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public class CommandEvent extends MessageReceivedEvent {
    
    private BetterCommand betterCommand;
    private String usedPrefix;
    private boolean isGuild;
    
    public CommandEvent(MessageReceivedEvent event, BetterCommand parent, boolean isGuild){
        super(event.getJDA(), event.getResponseNumber(), event.getMessage());
        betterCommand = parent;
        this.isGuild = isGuild;
    }

    protected void setUsedPrefix(String usedPrefix) {
        this.usedPrefix = usedPrefix;
    }


    /**
     * Returns the executor {@link BetterCommand} object
     * @return {@link BetterCommand} object
     */

    public BetterCommand getBetterCommand() {
        return betterCommand;
    }


    /**
     * Returns the used prefix of the command
     * @return prefix
     */

    public String getUsedPrefix() {
        return usedPrefix;
    }


    /**
     * Returns whether the command was executed in a guild
     * @return command was executed in a guild
     */

    public boolean isGuild() {
        return isGuild;
    }
}
