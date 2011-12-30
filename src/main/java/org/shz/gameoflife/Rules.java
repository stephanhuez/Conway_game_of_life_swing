package org.shz.gameoflife;

public class Rules {

    public boolean apply(boolean occupied, int neighbours) {
        if (!occupied && 3 == neighbours) {
            return true;
        }
        if (occupied && (2 == neighbours || 3 == neighbours)) {
            return true;
        }
        return false;
    }

}
