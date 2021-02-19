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
        final ActionHandler action = new ActionHandler();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
            case KeyEvent.VK_NUMPAD1:
                action.performAction("1");
                break;

            case KeyEvent.VK_2:
            case KeyEvent.VK_NUMPAD2:
                action.performAction("2");
                break;

            case KeyEvent.VK_3:
            case KeyEvent.VK_NUMPAD3:
                action.performAction("3");
                break;

            case KeyEvent.VK_4:
            case KeyEvent.VK_NUMPAD4:
                action.performAction("4");
                break;

            case KeyEvent.VK_5:
            case KeyEvent.VK_NUMPAD5:
                action.performAction("5");
                break;

            case KeyEvent.VK_6:
            case KeyEvent.VK_NUMPAD6:
                action.performAction("6");
                break;

            case KeyEvent.VK_7:
            case KeyEvent.VK_NUMPAD7:
                action.performAction("7");
                break;

            case KeyEvent.VK_8:
            case KeyEvent.VK_NUMPAD8:
                action.performAction("8");
                break;

            case KeyEvent.VK_9:
            case KeyEvent.VK_NUMPAD9:
                action.performAction("9");
                break;

            case KeyEvent.VK_0:
            case KeyEvent.VK_NUMPAD0:
                action.performAction("0");
                break;

            case KeyEvent.VK_COMMA:
            case KeyEvent.VK_PERIOD:
            case KeyEvent.VK_SEPARATER:
                action.performAction(",");
                break;

            case KeyEvent.VK_PLUS:
            case KeyEvent.VK_ADD:
                action.performAction("+");
                break;

            case KeyEvent.VK_MINUS:
            case KeyEvent.VK_SUBTRACT:
                action.performAction("-");
                break;

            case KeyEvent.VK_MULTIPLY:
                action.performAction("×");
                break;

            case KeyEvent.VK_DIVIDE:
                action.performAction("÷");
                break;

            case KeyEvent.VK_ENTER:
                action.performAction("=");
                break;

            case KeyEvent.VK_ESCAPE:
                action.performAction("C");
                break;

            case KeyEvent.VK_BACK_SPACE:
            case KeyEvent.VK_DELETE:
                action.performAction("⌫");
                break;

            default:
                break;
        }
    }

    @Override
    public void keyReleased(final KeyEvent e) {

    }
}
