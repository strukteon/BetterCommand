package me.strukteon.bettercommand.tools;
/*
    Created by nils on 31.07.2018 at 23:58.
    
    (c) nils 2018
*/

import me.strukteon.bettercommand.syntax.SyntaxBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandInfo {

    private String label;
    private String help = "no help set";

    private List<String> aliases = new ArrayList<>();

    private SyntaxBuilder syntaxBuilder;
    private String syntax;

    private boolean nsfwOnly = false;

    private long cooldown;


    /**
     * Initialize a command with a given label
     * @param label label for the command
     */

    public CommandInfo(String label){
        this.label = label;
    }


    /**
     * Set a cooldown for this instance
     * @param cooldown cooldown in milliseconds
     * @return this
     */

    public CommandInfo setCooldown(long cooldown) {
        this.cooldown = cooldown;
        return this;
    }


    /**
     * Set a decription for this CommandInfo
     * @param help message
     * @return this
     */

    public CommandInfo setHelp(String help) {
        this.help = help;
        return this;
    }


    /**
     * Set aliases for the command to listen for
     * @param aliases aliases
     * @return this
     */

    public CommandInfo setAliases(List<String> aliases) {
        this.aliases = aliases;
        return this;
    }


    /**
     * Set aliases for the command to listen for
     * @param aliases aliases
     * @return this
     */

    public CommandInfo setAliases(String... aliases) {
        this.aliases = Arrays.asList(aliases);
        return this;
    }


    /**
     * Set a {@link SyntaxBuilder} for this {@link CommandInfo}
     * @param syntaxBuilder {@link SyntaxBuilder}
     * @return this
     */

    public CommandInfo setSyntaxBuilder(SyntaxBuilder syntaxBuilder) {
        this.syntaxBuilder = syntaxBuilder;
        return this;
    }


    /**
     * Set a custom syntax message for this {@link CommandInfo}
     * @param syntax message
     * @return this
     */

    public CommandInfo setSyntax(String syntax) {
        this.syntax = syntax;
        return this;
    }


    /**
     * Enable this command only in NSFW channels
     * @param nsfwOnly only allowed in nsfw channels
     * @return this
     */

    public CommandInfo setNsfwOnly(boolean nsfwOnly) {
        this.nsfwOnly = nsfwOnly;
        return this;
    }


    /**
     * Returns the cooldown
     * @return cooldown
     */

    public long getCooldown() {
        return cooldown;
    }


    /**
     * Returns the label
     * @return label
     */

    public String getLabel() {
        return label;
    }


    /**
     * Returns the description
     * @return description
     */

    public String getHelp() {
        return help;
    }


    /**
     * Returns the aliases
     * @return aliases
     */

    public List<String> getAliases() {
        return aliases;
    }


    /**
     * Returns the {@link SyntaxBuilder}
     * @return syntaxbuilder
     */

    public SyntaxBuilder getSyntaxBuilder() {
        return syntaxBuilder == null ? new SyntaxBuilder() : syntaxBuilder;
    }


    /**
     * Returns the syntax as string
     * @return syntax
     */

    public String getSyntax() {
        return syntax == null ? "No syntax described" : syntax;
    }


    /**
     * Returns whether the command is limited to NSFW channels
     * @return is limited to NSFW channels
     */

    public boolean isNsfwOnly() {
        return nsfwOnly;
    }
}
