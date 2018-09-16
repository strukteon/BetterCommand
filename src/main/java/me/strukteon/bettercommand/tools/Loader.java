package me.strukteon.bettercommand.tools;
/*
    Created by nils on 31.07.2018 at 22:50.
    
    (c) nils 2018
*/

public class Loader {

    public interface Prefix {

        /**
         * Called when a prefix is asked
         * @param guildId id of the guild
         * @return prefix
         */

        String getGuildPrefix(long guildId);
    }

    public interface Blacklisted {

        /**
         * Decides if an user can execute commands
         * @param userId id of the user
         * @return whether allowed to use it or not
         */

        boolean isUserBlacklisted(long userId);


        /**
         * Decides if an users can execute commands in this channel
         * @param channelId id of the channel
         * @return whether allowed to use it or not
         */

        boolean isChannelBlacklisted(long channelId);
    }

}
