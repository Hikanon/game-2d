package com.main.engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;
import java.util.Set;

import static java.awt.event.KeyEvent.*;

public class KeyHandler implements KeyListener{

    private final Set<Integer> pressedKeys = new HashSet<>();

    byte[] playerOffset = new byte[5];

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pressedKeys.add(e.getKeyCode());

        for (Integer pressedKey : pressedKeys) {
            switch (pressedKey) {
                case VK_W, VK_UP -> playerOffset[0] = -1;
                case VK_S, VK_DOWN -> playerOffset[1] = 1;
                case VK_A, VK_LEFT -> playerOffset[2] = -1;
                case VK_D, VK_RIGHT -> playerOffset[3] = 1;
                case VK_SHIFT -> playerOffset[4] = 1;
                case VK_ESCAPE -> System.exit(0);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pressedKeys.remove(e.getKeyCode());

        switch (e.getKeyCode()) {
            case VK_W, VK_UP -> playerOffset[0] = 0;
            case VK_S, VK_DOWN -> playerOffset[1] = 0;
            case VK_A, VK_LEFT -> playerOffset[2] = 0;
            case VK_D, VK_RIGHT -> playerOffset[3] = 0;
            case VK_SHIFT -> playerOffset[4] = 1;
        }
    }

    public byte[] getPlayerOffset() {
        return playerOffset;
    }
}
