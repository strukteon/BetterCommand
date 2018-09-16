package me.strukteon.bettercommand.syntax;
/*
    Created by nils on 02.04.2018 at 17:52.
    
    (c) nils 2018
*/

import me.strukteon.bettercommand.utils.Checks;
import me.strukteon.bettercommand.utils.CommandTools;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.util.List;

public class SyntaxValidator {

    /**
     * Initializes a new syntax validator
     * @param toValidate string to validate
     * @param element element to validate
     * @param event command event
     * @return successfully parsed object
     * @throws SyntaxValidateException when something goes wrong
     */

    public Object validate(String toValidate, SyntaxElement element, MessageReceivedEvent event) throws SyntaxValidateException {
        if (toValidate.isEmpty())
            throw new SyntaxValidateException(SyntaxValidateException.Cause.EMPTY);
        switch (element.getType()){
            case INT:
                return validateInt(toValidate);
            case STRING:
                return validateString(toValidate);
            case LONG:
                return validateLong(toValidate);
            case ID:
                return validateId(toValidate);
            case USER:
                return validateUser(toValidate, event.getJDA(), event);
            case MEMBER:
                return Checks.syntaxNotNull(() -> validateMember(toValidate, event.getGuild()), toValidate, event.getGuild());
            case GUILD:
                return validateGuild(toValidate, event.getJDA());
            case TEXTCHANNEL:
                return Checks.syntaxNotNull(() -> validateTextChannel(toValidate, event.getGuild()), toValidate, event.getGuild());
            case VOICECHANNEL:
                return Checks.syntaxNotNull(() -> validateVoiceChannel(toValidate, event.getGuild()), toValidate, event.getGuild());
            case ROLE:
                return Checks.syntaxNotNull(() -> validateRole(toValidate, event.getGuild()), toValidate, event.getGuild());
            case STRING_OF_LIST:
                return validateSubCommand(toValidate, ((SyntaxElement.SubCommand) element).getPossibilities());
            default:
                throw new SyntaxValidateException(SyntaxValidateException.Cause.UNDEFINED);
        }

    }


    /**
     * Tries to parse the string to an int
     * @param toValidate string to validate
     * @return successfully parsed int
     * @throws SyntaxValidateException when something goes wrong
     */

    public int validateInt(String toValidate) throws SyntaxValidateException {
        try {
            return Integer.parseInt(toValidate);
        } catch (Exception e){
            throw new SyntaxValidateException(SyntaxValidateException.Cause.INVALID);
        }
    }


    /**
     * Tries to parse the string to an long
     * @param toValidate string to validate
     * @return successfully parsed long
     * @throws SyntaxValidateException when something goes wrong
     */

    public long validateLong(String toValidate) throws SyntaxValidateException {
        try {
            return Long.parseLong(toValidate);
        } catch (Exception e){
            throw new SyntaxValidateException(SyntaxValidateException.Cause.INVALID);
        }
    }


    /**
     * Tries to parse the string to an {@link String}
     * @param toValidate string to validate
     * @return successfully parsed {@link String}
     * @throws SyntaxValidateException when something goes wrong
     */

    public String validateString(String toValidate) throws SyntaxValidateException {
        if (toValidate.isEmpty())
            throw new SyntaxValidateException(SyntaxValidateException.Cause.EMPTY);
        return toValidate;
    }


    /**
     * Tries to validate the string to an id
     * @param toValidate string to validate
     * @return successfully parsed id as a {@link String}
     * @throws SyntaxValidateException when something goes wrong
     */

    public String validateId(String toValidate) throws SyntaxValidateException {
        if (!toValidate.matches("[0-9]+"))
            throw new SyntaxValidateException(SyntaxValidateException.Cause.INVALID);
        return toValidate;
    }


    /**
     * Tries to parse the string to an {@link User}
     * @param toValidate string to validate
     * @return successfully parsed {@link User}
     * @throws SyntaxValidateException when something goes wrong
     */

    public User validateUser(String toValidate, JDA jda, MessageReceivedEvent event) throws SyntaxValidateException {
        if (event.getChannelType().equals(ChannelType.TEXT))
            try {
                return validateMember(toValidate, event.getGuild()).getUser();
            } catch (SyntaxValidateException ignored){ }
        if (!(CommandTools.isMention(toValidate) || CommandTools.isId(toValidate)))
            throw new SyntaxValidateException(SyntaxValidateException.Cause.INVALID);
        User user = jda.getUserById(CommandTools.mentionToId(toValidate));
        if (user == null)
            throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
        return user;
    }


    /**
     * Tries to parse the string to an {@link Member}
     * @param toValidate string to validate
     * @return successfully parsed {@link Member}
     * @throws SyntaxValidateException when something goes wrong
     */

    public Member validateMember(String toValidate, Guild guild) throws SyntaxValidateException {
        Member member = null;
        if (CommandTools.isMention(toValidate))
            member = guild.getMemberById(CommandTools.mentionToId(toValidate));
        if (member == null)
            if (CommandTools.isMention(toValidate))
                throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
            else {
                List<Member> possibles = guild.getMembersByName(toValidate, true);
                if (possibles.size() == 0) {
                    for (Member m : guild.getMembers())
                        if (m.getEffectiveName().toLowerCase().contains(toValidate.toLowerCase()))
                            return m;
                    throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
                }
                member = possibles.get(0);
            }
        return member;
    }


    /**
     * Tries to parse the string to an {@link Guild}
     * @param toValidate string to validate
     * @return successfully parsed {@link Guild}
     * @throws SyntaxValidateException when something goes wrong
     */

    public Guild validateGuild(String toValidate, JDA jda) throws SyntaxValidateException {
        if (!CommandTools.isId(toValidate))
            throw new SyntaxValidateException(SyntaxValidateException.Cause.INVALID);
        Guild guild = jda.getGuildById(toValidate);
        if (guild == null)
            throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
        return guild;
    }


    /**
     * Tries to parse the string to an {@link TextChannel}
     * @param toValidate string to validate
     * @return successfully parsed {@link TextChannel}
     * @throws SyntaxValidateException when something goes wrong
     */

    public TextChannel validateTextChannel(String toValidate, Guild guild) throws SyntaxValidateException {
        TextChannel textChannel = null;
        if (CommandTools.isChannelMention(toValidate) || CommandTools.isId(toValidate))
            textChannel = guild.getTextChannelById(CommandTools.channelMentionToId(toValidate));
        if (textChannel == null)
            if (CommandTools.isMention(toValidate) || CommandTools.isId(toValidate))
                throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
            else {
                List<TextChannel> possibles = guild.getTextChannelsByName(toValidate, true);
                if (possibles.size() == 0) {
                    for (TextChannel c : guild.getTextChannels())
                        if (c.getName().toLowerCase().contains(toValidate.toLowerCase()))
                            return c;
                    throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
                }
                textChannel = possibles.get(0);
            }
        return textChannel;
    }


    /**
     * Tries to parse the string to an {@link VoiceChannel}
     * @param toValidate string to validate
     * @return successfully parsed {@link VoiceChannel}
     * @throws SyntaxValidateException when something goes wrong
     */

    public VoiceChannel validateVoiceChannel(String toValidate, Guild guild) throws SyntaxValidateException {
        VoiceChannel voiceChannel = null;
        if (CommandTools.isId(toValidate))
            voiceChannel = guild.getVoiceChannelById(CommandTools.channelMentionToId(toValidate));
        if (voiceChannel == null)
            if (CommandTools.isId(toValidate))
                throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
            else {
                List<VoiceChannel> possibles = guild.getVoiceChannelsByName(toValidate, true);
                if (possibles.size() == 0) {
                    for (VoiceChannel c : guild.getVoiceChannels())
                        if (c.getName().toLowerCase().contains(toValidate.toLowerCase()))
                            return c;
                    throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
                }
                voiceChannel = possibles.get(0);
            }
        return voiceChannel;
    }


    /**
     * Tries to parse the string to an {@link Role}
     * @param toValidate string to validate
     * @return successfully parsed {@link Role}
     * @throws SyntaxValidateException when something goes wrong
     */

    public Role validateRole(String toValidate, Guild guild) throws SyntaxValidateException {
        toValidate = toValidate.toLowerCase();
        Role role = null;
        if (CommandTools.isRoleMention(toValidate) || CommandTools.isId(toValidate))
            role = guild.getRoleById(CommandTools.roleToId(toValidate));
        if (role == null)
            if (CommandTools.isMention(toValidate) || CommandTools.isId(toValidate))
                throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
            else {
                List<Role> possibles = guild.getRolesByName(toValidate, true);
                if (possibles.size() == 0) {
                    for (Role r : guild.getRoles())
                        if (r.getName().toLowerCase().contains(toValidate))
                            return r;
                    throw new SyntaxValidateException(SyntaxValidateException.Cause.NOT_FOUND);
                }
                role = possibles.get(0);
            }
        return role;
    }


    /**
     * Tries to parse the string to an {@link SubCommand}
     * @param toValidate string to validate
     * @return successfully parsed {@link SubCommand}
     * @throws SyntaxValidateException when something goes wrong
     */

    public SubCommand validateSubCommand(String toValidate, List<String> possibilities) throws SyntaxValidateException {
        if (possibilities.contains(toValidate))
            return new SubCommand(toValidate, possibilities.indexOf(toValidate));
        throw new SyntaxValidateException(SyntaxValidateException.Cause.INVALID);
    }
}
