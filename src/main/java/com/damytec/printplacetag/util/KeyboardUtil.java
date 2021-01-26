package com.damytec.printplacetag.util;

import sun.awt.ExtendedKeyCodes;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * @author lgdamy@raiadrogasil.com on 26/01/2021
 */
public interface KeyboardUtil {

    default KeyEventDispatcher shortcuts () {
        return e -> {
            if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyCode() == 10) {
                enter();
            }
            return false;
        };
    }

    default void enter() {}
}
