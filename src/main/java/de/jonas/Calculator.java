package de.jonas;

import de.jonas.calculator.GUI;

/**
 * Die Haupt- und Main-Klasse der Applikation, in der die Anwendung vollständig initialisiert wird, sodass sie für
 * den Nutzer verwendbar ist.
 */
public class Calculator {

    /**
     * Die Anwendung wird initialisiert und für den Nutzer verwendbar gemacht.
     * @param args .
     */
    public static void main(final String[] args) {
        new GUI();
    }

}
