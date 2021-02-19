package de.jonas;

import de.jonas.calculator.GUI;

import java.util.HashMap;

/**
 * Die Haupt- und Main-Klasse der Applikation, in der die Anwendung vollständig initialisiert wird, sodass sie für den
 * Nutzer verwendbar ist.
 */
public class Calculator {

    /** Es werden alle Potenzen basierend auf der normalen Zahl gespeichert (von 0 bis 9). */
    public static final HashMap<Integer, String> POTENZEN = new HashMap<>();

    /**
     * Die Anwendung wird initialisiert und für den Nutzer verwendbar gemacht.
     *
     * @param args .
     */
    public static void main(final String[] args) {
        new GUI();
        initializePotenzen();
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static void initializePotenzen() {
        POTENZEN.put(0, "⁰");
        POTENZEN.put(1, "¹");
        POTENZEN.put(2, "²");
        POTENZEN.put(3, "³");
        POTENZEN.put(4, "⁴");
        POTENZEN.put(5, "⁵");
        POTENZEN.put(6, "⁶");
        POTENZEN.put(7, "⁷");
        POTENZEN.put(8, "⁸");
        POTENZEN.put(9, "⁹");
    }

}
