package me.strukteon.bettercommand.tools;
/*
    Created by nils on 01.08.2018 at 21:03.
    
    (c) nils 2018
*/

import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class PermissionManager {

    private List<Permission> requiredBotPerms = new ArrayList<>();
    private List<Permission> requiredUserPerms = new ArrayList<>();

    private List<String> limitedUsers = new ArrayList<>();

    /**
     * Initialization
     */

    public PermissionManager() { }


    /**
     * Set a list of users who are allowed to use the command
     * @param userIds list of user ids
     * @return this
     */

    public PermissionManager limitToUsers(String... userIds){
        limitedUsers = Arrays.asList(userIds);
        return this;
    }


    /**
     * Set a list of users who are allowed to use the command
     * @param users list of users
     * @return this
     */

    public PermissionManager limitToUsers(User... users){
        limitedUsers = Arrays.stream(users).map(User::getId).collect(Collectors.toList());
        return this;
    }

    /**
     * Adds required permissions the bot must have
     * @param requiredBotPerms required permissions
     * @return this
     */

    public PermissionManager addRequiredBotPerms(Permission... requiredBotPerms) {
        return addRequiredBotPerms(Arrays.asList(requiredBotPerms));
    }

    /**
     * Adds required permissions the bot must have
     * @param requiredBotPerms required permissions
     * @return this
     */

    public PermissionManager addRequiredBotPerms(Collection<Permission> requiredBotPerms) {
        this.requiredBotPerms.addAll(requiredBotPerms);
        return this;
    }

    /**
     * Adds required permissions the executor must have
     * @param requiredUserPerms required permissions
     * @return this
     */

    public PermissionManager addRequiredUserPerms(Permission... requiredUserPerms) {
        return addRequiredUserPerms(Arrays.asList(requiredUserPerms));
    }

    /**
     * Adds required permissions the executor must have
     * @param requiredUserPerms required permissions
     * @return this
     */

    public PermissionManager addRequiredUserPerms(Collection<Permission> requiredUserPerms) {
        this.requiredUserPerms.addAll(requiredUserPerms);
        return this;
    }

    /**
     * Get the list of users this command is limited to
     * @return allowed user ids
     */

    public List<String> getLimitedUsers() {
        return limitedUsers;
    }

    /**
     * Get the required bot permissions
     * @return list of permissions
     */

    public List<Permission> getRequiredBotPerms() {
        return requiredBotPerms == null ? new ArrayList<>() : requiredBotPerms;
    }

    /**
     * Get the required user permissions
     * @return list of permissions
     */

    public List<Permission> getRequiredUserPerms() {
        return requiredUserPerms == null ? new ArrayList<>() : requiredUserPerms;
    }
}
