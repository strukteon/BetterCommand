package me.strukteon.bettercommand.syntax;
/*
    Created by nils on 02.04.2018 at 20:14.
    
    (c) nils 2018
*/

import net.dv8tion.jda.core.entities.*;

import java.util.HashMap;
import java.util.List;

public class Syntax extends HashMap<String, Object> {

    private int executedBuilder = 0;
    private String help;

    protected void setExecutedBuilder(int executedBuilder) {
        this.executedBuilder = executedBuilder;
    }

    protected void setHelp(String help) {
        this.help = help;
    }

    public String getHelp() {
        return help;
    }

    public int getExecutedBuilder() {
        return executedBuilder;
    }

    /**
     * Returns the object with the provided key casted to int
     * @param name key
     * @return integer
     */

    public int getAsInt(String name){
        return (int) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to long
     * @param name key
     * @return long
     */

    public long getAsLong(String name){
        return (long) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link String}
     * @param name key
     * @return {@link String}
     */

    public String getAsString(String name){
        return (String) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link User}
     * @param name key
     * @return {@link User}
     */

    public User getAsUser(String name){
        return (User) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link Member}
     * @param name key
     * @return {@link Member}
     */

    public Member getAsMember(String name){
        return (Member) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link Guild}
     * @param name key
     * @return {@link Guild}
     */

    public Guild getAsGuild(String name){
        return (Guild) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link TextChannel}
     * @param name key
     * @return {@link TextChannel}
     */

    public TextChannel getAsTextChannel(String name){
        return (TextChannel) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link VoiceChannel}
     * @param name key
     * @return {@link VoiceChannel}
     */

    public VoiceChannel getAsVoiceChannel(String name){
        return (VoiceChannel) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link Role}
     * @param name key
     * @return {@link Role}
     */

    public Role getAsRole(String name){
        return (Role) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link List<Integer>}
     * @param name key
     * @return {@link List<Integer>}
     */

    public List<Integer> getAsListInt(String name){
        return (List<Integer>) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link List<String>}
     * @param name key
     * @return {@link List<String>}
     */

    public List<String> getAsListString(String name){
        return (List<String>) this.get(name);
    }

    /**
     * Returns the object with the provided key joined to a {@link String}
     * Note: this may throw an error when the object is no valid {@link List}!
     * @param name key
     * @return {@link String}
     */

    public String getAsJoinedListString(String name){
        return String.join(" ", getAsListString(name));
    }

    /**
     * Returns the object with the provided key casted to {@link List<User>}
     * @param name key
     * @return {@link List<User>}
     */

    public List<User> getAsListUser(String name){
        return (List<User>) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link List<Member>}
     * @param name key
     * @return {@link List<Member>}
     */

    public List<Member> getAsListMember(String name){
        return (List<Member>) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link List<Guild>}
     * @param name key
     * @return {@link List<Guild>}
     */

    public List<Guild> getAsListGuild(String name){
        return (List<Guild>) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link List<TextChannel>}
     * @param name key
     * @return {@link List<TextChannel>}
     */

    public List<TextChannel> getAsTextChannelList(String name){
        return (List<TextChannel>) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link List<VoiceChannel>}
     * @param name key
     * @return {@link List<VoiceChannel>}
     */

    public List<VoiceChannel> getAsVoiceChanneList(String name){
        return (List<VoiceChannel>) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link List<Role>}
     * @param name key
     * @return {@link List<Role>}
     */

    public List<Role> getAsRoleList(String name){
        return (List<Role>) this.get(name);
    }

    /**
     * Returns the object with the provided key casted to {@link SubCommand}
     * @param name key
     * @return {@link SubCommand}
     */

    public SubCommand getAsSubCommand(String name){
        return (SubCommand) this.get(name);
    }

}
