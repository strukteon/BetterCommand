package me.strukteon.bettercommand.tools;
/*
    Created by nils on 23.09.2018 at 12:45.
    
    (c) nils 2018
*/

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

public interface EmbedProducer {
    EmbedBuilder get(MessageReceivedEvent event);
}
