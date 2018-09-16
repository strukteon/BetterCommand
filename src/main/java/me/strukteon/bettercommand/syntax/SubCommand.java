package me.strukteon.bettercommand.syntax;
/*
    Created by nils on 04.04.2018 at 17:16.
    
    (c) nils 2018
*/

public class SubCommand {
    private String content;
    private int selection;

    public SubCommand(String content, int selection){
        this.content = content;
        this.selection = selection;
    }

    /**
     * Returns the content of the selection
     * @return content
     */

    public String getContent() {
        return content;
    }

    /**
     * Returns the index of the selection
     * @return selection index
     */

    public int getSelection() {
        return selection;
    }

    @Override
    public String toString() {
        return selection + ": " + content;
    }
}
