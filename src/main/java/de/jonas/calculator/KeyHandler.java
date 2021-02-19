package de.jonas.calculator;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Über diesen {@link KeyListener} wird überprüft welche tasten gedrückt werden und welche Aktionen bei dem jeweiligen
 * Tastendruck ausgeführt werden sollen.
 */
public class KeyHandler implements KeyListener {
    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
            case KeyEvent.VK_NUMPAD1:
                ActionHandler.performAction("1");
                break;

            case KeyEvent.VK_2:
            case KeyEvent.VK_NUMPAD2:
                ActionHandler.performAction("2");
                break;

            case KeyEvent.VK_3:
            case KeyEvent.VK_NUMPAD3:
                ActionHandler.performAction("3");
                break;

            case KeyEvent.VK_4:
            case KeyEvent.VK_NUMPAD4:
                ActionHandler.performAction("4");
                break;

            case KeyEvent.VK_5:
            case KeyEvent.VK_NUMPAD5:
                ActionHandler.performAction("5");
                break;

            case KeyEvent.VK_6:
            case KeyEvent.VK_NUMPAD6:
                ActionHandler.performAction("6");
                break;

            case KeyEvent.VK_7:
            case KeyEvent.VK_NUMPAD7:
                ActionHandler.performAction("7");
                break;

            case KeyEvent.VK_8:
            case KeyEvent.VK_NUMPAD8:
                ActionHandler.performAction("8");
                break;

            case KeyEvent.VK_9:
            case KeyEvent.VK_NUMPAD9:
                ActionHandler.performAction("9");
                break;

            case KeyEvent.VK_0:
            case KeyEvent.VK_NUMPAD0:
                ActionHandler.performAction("0");
                break;

            case KeyEvent.VK_COMMA:
            case KeyEvent.VK_PERIOD:
            case KeyEvent.VK_SEPARATER:
                ActionHandler.performAction(",");
                break;

            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_ADD:
                ActionHandler.performAction("+");
                break;

            case KeyEvent.VK_MINUS:
            case KeyEvent.VK_SUBTRACT:
                ActionHandler.performAction("-");
                break;

            case KeyEvent.VK_MULTIPLY:
                ActionHandler.performAction("×");
                break;

            case KeyEvent.VK_DIVIDE:
                ActionHandler.performAction("÷");
                break;

            case KeyEvent.VK_ENTER:
                ActionHandler.performAction("=");
                break;

            case KeyEvent.VK_ESCAPE:
                ActionHandler.performAction("C");
                break;

            case KeyEvent.VK_BACK_SPACE:
            case KeyEvent.VK_DELETE:
                ActionHandler.performAction("⌫");
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
