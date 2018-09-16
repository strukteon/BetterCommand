package me.strukteon.bettercommand;
/*
    Created by nils on 06.07.2018 at 15:46.
    
    (c) nils 2018
*/

import me.strukteon.bettercommand.command.BaseCommand;
import me.strukteon.bettercommand.tools.BetterCommandException;
import me.strukteon.bettercommand.tools.ErrorHandler;
import me.strukteon.bettercommand.tools.Loader;
import me.strukteon.bettercommand.defaults.DefaultErrorHandler;
import me.strukteon.bettercommand.defaults.DefaultHelpCommand;
import me.strukteon.bettercommand.defaults.DefaultLoader;
import net.dv8tion.jda.bot.sharding.ShardManager;
import net.dv8tion.jda.core.JDA;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

public class BetterCommand {
    public static String VALID_PREFIX = "[^\\s][^\t\n\r]+";

    private Object jda; // or shardmanager
    private String defaultPrefix;

    private boolean commandIgnoreCase;
    private boolean deleteUserMsg;
    private long cooldown;

    private Loader.Prefix prefixLoader;
    private Loader.Blacklisted blacklistedLoader;
    private CommandListener commandListener;
    private ErrorHandler errorHandler;

    private List<CommandSection> commandSections;
    private List<BaseCommand> unassignedCommands;

    private boolean active = false;


    /**
     * Initialize a new bettercommand instance
     * @param defaultPrefix default prefix
     * @param ignoreCase ignore command label case
     */

    public BetterCommand(@Nonnull String defaultPrefix, boolean ignoreCase){
        this.defaultPrefix = defaultPrefix;
        this.commandIgnoreCase = ignoreCase;
        this.cooldown = 0;
        this.commandSections = new ArrayList<>();
        this.unassignedCommands = new ArrayList<>();
        this.commandListener = new CommandListener(this);
        this.prefixLoader = new Loader.Prefix() {
            @Override
            public String getGuildPrefix(long guildid) {
                return null;
            }
        };
        this.errorHandler = new DefaultErrorHandler();
    }


    /**
     * Tell the instance to use the provided shardmanager
     * @param manager shard manager
     */

    public BetterCommand useShardManager(@Nonnull ShardManager manager){
        this.jda = manager;
        return this;
    }


    /**
     * Tell the instance to use the provided jda
     * @param jda jda
     */

    public BetterCommand useJDA(@Nonnull JDA jda){
        this.jda = jda;
        return this;
    }


    /**
     * Enable the default help message
     * @param label command label of the help message
     * @param aliases aliases of the help command
     */

    public BetterCommand useDefaultHelpMessage(@Nonnull String label, @Nonnull String... aliases){
        unassignedCommands.add(new DefaultHelpCommand(label, aliases));
        return this;
    }


    /**
     * Enable the default help message
     * @param label command label of the help message
     * @param color color of the embed message
     * @param aliases aliases of the help command
     */

    public BetterCommand useDefaultHelpMessage(@Nonnull String label, @Nonnull Color color, @Nonnull String... aliases){
        unassignedCommands.add(new DefaultHelpCommand(label, color, aliases));
        return this;
    }


    /**
     * Set command label case ignoring
     * @param ignoreCase ignore case
     */

    public BetterCommand setIgnoreCase(boolean ignoreCase){
        this.commandIgnoreCase = ignoreCase;
        return this;
    }


    /**
     * Delete the user message after a successful execute
     * @param deleteUserMsg delete the user message
     */

    public BetterCommand setDeleteUserMessage(boolean deleteUserMsg) {
        this.deleteUserMsg = deleteUserMsg;
        return this;
    }


    /**
     * Add command section to the commands
     * @param commandSection command section
     */

    public BetterCommand addCommandSection(CommandSection commandSection){
        commandSections.add(commandSection);
        return this;
    }


    /**
     * Add command section to the commands
     * @param sectionName name of the section
     * @param commands commands
     */

    public BetterCommand addCommandSection(String sectionName, BaseCommand... commands){
        return addCommandSection(new CommandSection(sectionName, commands));
    }


    /**
     * Add command section to the commands
     * @param sectionName name of the section
     * @param commands commands
     */

    public BetterCommand addCommandSection(String sectionName, Collection<BaseCommand> commands){
        return addCommandSection(new CommandSection(sectionName, commands));
    }


    /**
     * Set a global command cooldown
     * @param millis time in milliseconds
     */

    public BetterCommand setCooldown(long millis){
        this.cooldown = millis;
        return this;
    }


    /**
     * Set this instances prefix loader
     * @param prefixLoader prefix loader
     */

    public BetterCommand setPrefixLoader(Loader.Prefix prefixLoader) {
        this.prefixLoader = prefixLoader;
        return this;
    }


    /**
     * Set this instances blacklisted loader
     * @param blacklistedLoader blacklisted loader
     */

    public BetterCommand setBlacklistedLoader(Loader.Blacklisted blacklistedLoader) {
        this.blacklistedLoader = blacklistedLoader;
        return this;
    }


    /**
     * Set this instances error handler
     * @param errorHandler error handler
     */

    public BetterCommand setErrorHandler(ErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
        return this;
    }


    /**
     * Enable this instance
     */

    public BetterCommand enable(){
        if (this.active)
            throw new BetterCommandException("This instance is already active");
        if (jda == null)
            throw new BetterCommandException("This instance's jda was not set");
        if (jda instanceof ShardManager)
            ((ShardManager) jda).addEventListener(this.commandListener);
        else
            ((JDA) jda).addEventListener(this.commandListener);
        this.active = true;
        return this;
    }


    /**
     * Disable this instance
     */

    public BetterCommand disable(){
        if (this.active)
            throw new BetterCommandException("This instance is not active");
        if (jda instanceof ShardManager)
            ((ShardManager) jda).removeEventListener(this.commandListener);
        else
            ((JDA) jda).removeEventListener(this.commandListener);
        this.active = false;
        return this;
    }


    /**
     * Returns whether this instance is active
     * @return is active
     */

    public boolean isActive() {
        return active;
    }


    /**
     * Returns the global command cooldown (-1 if disabled)
     */

    protected long getCooldown() {
        return cooldown;
    }


    /**
     * Returns the default prefix
     * @return default prefix
     */

    protected String getDefaultPrefix() {
        return defaultPrefix;
    }


    /**
     * Returns the prefix loader
     * @return prefix loader
     */

    protected Loader.Prefix getPrefixLoader() {
        return prefixLoader == null ? new DefaultLoader.Prefix() : prefixLoader;
    }


    /**
     * Returns the blacklisted loader
     * @return blacklisted loader
     */

    protected Loader.Blacklisted getBlacklistedLoader() {
        return blacklistedLoader == null ? new DefaultLoader.Blacklisted() : blacklistedLoader;
    }


    /**
     * Returns the prefix for the specified guild id
     * @param guildId guild id
     * @return prefix
     */

    protected String getPrefix(long guildId){
        String guildPrefix = prefixLoader.getGuildPrefix(guildId);
        if (guildPrefix != null && Pattern.quote(guildPrefix).matches(VALID_PREFIX))
            return guildPrefix;
        return defaultPrefix;
    }


    /**
     * Returns the error handler
     * @return  error handler
     */

    protected ErrorHandler getErrorHandler(){
        return errorHandler;
    }


    /**
     * Returns the commands that are inside of sections
     * @return command sections
     */

    public List<CommandSection> getCommandSections() {
        return commandSections;
    }


    /**
     * Returns the commands which are not assigned to a section
     * @return unassigned commands
     */

    public List<BaseCommand> getUnassignedCommands() {
        return unassignedCommands;
    }


    /**
     * Returns all registered commands
     * @return commands
     */

    public List<BaseCommand> getCommands() {
        List<BaseCommand> commands = new ArrayList<>(unassignedCommands);
        commandSections.forEach(commandSection -> commands.addAll(commandSection.getCommands()));
        return commands;
    }


    /**
     * Returns whether the labels are case insensitive or not
     * @return label case insensitivity
     */

    public boolean getCommandIgnoreCase(){
        return commandIgnoreCase;
    }


    /**
     * Returns whether the bot deletes the command message of the executor or not
     * @return delete message after execution
     */

    public boolean getDeleteUserMsg() {
        return deleteUserMsg;
    }
}
