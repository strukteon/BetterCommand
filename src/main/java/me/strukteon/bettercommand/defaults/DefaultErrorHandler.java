package me.strukteon.bettercommand.defaults;
/*
    @author Nils on 24.08.2018 at 20:45.
    
    (c) nils 2018
*/

import me.strukteon.bettercommand.CommandEvent;
import me.strukteon.bettercommand.command.BaseCommand;
import me.strukteon.bettercommand.tools.ErrorHandler;
import me.strukteon.bettercommand.syntax.SyntaxBuilder;
import me.strukteon.bettercommand.syntax.SyntaxValidateException;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;

import java.awt.*;
import java.util.List;

public class DefaultErrorHandler implements ErrorHandler {

    @Override
    public void missingBotPermissions(CommandEvent event, List<Permission> missingPermissions, BaseCommand command) {
        EmbedBuilder eb = createErrorEmbedBuilder()
                .setTitle("Missing Permissions")
                .setDescription("Whoops, it seems like " + event.getJDA().getSelfUser().getAsMention() + " is missing some permissions! Please permit them to use this command: ");
        for (Permission p : missingPermissions)
            eb.getDescriptionBuilder().append(eb.getDescriptionBuilder().length() > 0 ? ", " : "").append(p.getName());
        event.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void missingUserPermissions(CommandEvent event, List<Permission> missingPermissions, BaseCommand command) {
        EmbedBuilder eb = createErrorEmbedBuilder()
                .setTitle("Missing Permissions")
                .setDescription("Whoops, it seems like you (" + event.getAuthor().getAsMention() + ") are missing some permissions! You need the following perms to execute this command: ");
        for (Permission p : missingPermissions)
            eb.getDescriptionBuilder().append(eb.getDescriptionBuilder().length() > 0 ? ", " : "").append(p.getName());
        eb.appendDescription("``");
        event.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public void notInUserlist(CommandEvent event, BaseCommand command) {
        EmbedBuilder eb = createErrorEmbedBuilder()
                .setTitle("Missing Permissions")
                .setDescription("Sorry, but this command is limited to the developers.");
        event.getChannel().sendMessage(eb.build()).queue();
    }

    @Override
    public boolean validateException(CommandEvent event, BaseCommand cmd, SyntaxValidateException e) {
        EmbedBuilder eb = createErrorEmbedBuilder()
                .setTitle("Wrong syntax!");
        for (SyntaxBuilder sb : cmd.getCommandInfo().getSyntaxBuilder().getAlternateBuilders())
            eb.getDescriptionBuilder().append(eb.getDescriptionBuilder().length() > 0 ? "\n" : "").append(
                    "**" + event.getUsedPrefix() + cmd.getCommandInfo().getLabel() + "** " + sb.toString()
            );
        event.getChannel().sendMessage(eb.build()).queue();
        return false;
    }

    @Override
    public void onException(CommandEvent event, BaseCommand cmd, Exception e) {
        EmbedBuilder eb = createErrorEmbedBuilder()
                .setDescription(String.format("Oh no, it seems like an error ocurred:\n```css\n%s\n```\n\nAn error report was sent to the developers.", e))
                .setFooter(String.format("@%s#%s:%s", e.getStackTrace()[0].getFileName(), e.getStackTrace()[0].getMethodName(), e.getStackTrace()[0].getLineNumber()), event.getJDA().getSelfUser().getEffectiveAvatarUrl());
        event.getChannel().sendMessage(eb.build()).queue();
    }

    private EmbedBuilder createErrorEmbedBuilder(){
        return new EmbedBuilder().setColor(Color.decode("#cc2222"));
    }

}
