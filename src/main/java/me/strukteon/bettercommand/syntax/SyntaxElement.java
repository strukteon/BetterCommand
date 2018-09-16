package me.strukteon.bettercommand.syntax;
/*
    Created by nils on 02.04.2018 at 17:46.
    
    (c) nils 2018
*/


import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SyntaxElement {

    private SyntaxElementType type;

    private String name;

    private String regexMatcher;


    /**
     * Initialize a new {@link SyntaxElement} object
     * @param name name of the element
     * @param type type of the element
     */

    public SyntaxElement(String name, SyntaxElementType type){
        this.name = name;
        this.type = type;
    }


    /**
     * Returns the name of the element
     * @return name
     */

    public String getName() {
        return name;
    }


    /**
     * Returns the type of the element
     * @return type
     */

    public SyntaxElementType getType() {
        return type;
    }

    @Override
    public String toString() {
        return getName() + " (" + getType() + ")";
    }


    public static class SubCommand extends SyntaxElement {
        private List<String> possibilities;
        private String regexMatcher;

        /**
         * Initialize a new {@link SubCommand} object
         * @param name name of the subcommand
         * @param possibilities permitted possibilities of the content
         */

        public SubCommand(String name, String... possibilities){
            super(name, SyntaxElementType.STRING_OF_LIST);
            this.possibilities = Arrays.asList(possibilities);
            StringBuilder b = new StringBuilder();
            b.append("(");
            for (String s : possibilities)
                b.append(Pattern.quote(s))
                .append("|");
            b.deleteCharAt(b.length()-1)
                    .append(")");
            this.regexMatcher = b.toString();
        }


        /**
         * Returns the regex matcher for this element
         * @return regex matcher as string
         */

        public String getRegexMatcher() {
            return regexMatcher;
        }


        /**
         * Returns the permitted possibilities of the content
         * @return possibilites
         */

        public List<String> getPossibilities() {
            return possibilities;
        }
    }

}
