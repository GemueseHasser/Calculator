package de.jonas.calculator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

/**
 * Auf ein bestimmtes {@link JFrame Fenster} werden alle nötigen Objekte, die für einen Taschenrechner nötig sind
 * platziert.
 */
public class PlaceObjects {

    //<editor-fold desc="CONSTANTS">
    /** Die Schriftgröße der {@link Font Schriftart} aller Objekte. */
    private static final int FONT_SIZE = 40;
    //</editor-fold>


    //<editor-fold desc="LOCAL-FIELDS">
    /** Das Fenster, in das alle Objekte platziert werden. */
    private final JFrame frame;
    /** Die {@link Font Schriftart} in der alle Schriften auf den platzierten Objekten dargestellt werden. */
    private final Font font;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">
    /**
     * Es wird eine neue und vollständig unabhängige Instanz des {@link PlaceObjects} erzeugt. In einem bestimmten
     * {@link JFrame Fenster} werden mithilfe dieser Instanz alle nötigen Objekte platziert, die für einen
     * Taschenrechner von Nutzen sind.
     *
     * @param frame Das {@link JFrame Fenster}, in dem die Änderungen vorgenommen werden.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public PlaceObjects(@NotNull final JFrame frame) {
        this.frame = frame;
        this.font = new Font("Arial", Font.BOLD, FONT_SIZE);
        final int width = frame.getWidth();
        final int height = frame.getHeight();

        final int buttonWidth = width / 4 - 3;
        final int buttonheight = height / 7 - 5;

        // text field
        JTextField field = new JTextField();
        field.setBounds(0, 0, width - 12, buttonheight);
        field.setFont(font);
        field.setEnabled(false);
        frame.add(field);

        // line 1
        placeButton(buttonWidth, buttonheight, 0, buttonheight, "X²");
        placeButton(buttonWidth, buttonheight, buttonWidth, buttonheight, "X³");
        placeButton(buttonWidth, buttonheight, buttonWidth * 2, buttonheight, "Xʸ");
        placeButton(buttonWidth, buttonheight, buttonWidth * 3, buttonheight, "√");

        // line 2
        placeButton(buttonWidth, buttonheight, 0, buttonheight * 2, "C");
        placeButton(buttonWidth, buttonheight, buttonWidth, buttonheight * 2, "÷");
        placeButton(buttonWidth, buttonheight, buttonWidth * 2, buttonheight * 2, "×");
        placeButton(buttonWidth, buttonheight, buttonWidth * 3, buttonheight * 2, "⌫");

        // line 3
        placeButton(buttonWidth, buttonheight, 0, buttonheight * 3, "7");
        placeButton(buttonWidth, buttonheight, buttonWidth, buttonheight * 3, "8");
        placeButton(buttonWidth, buttonheight, buttonWidth * 2, buttonheight * 3, "9");
        placeButton(buttonWidth, buttonheight, buttonWidth * 3, buttonheight * 3, "-");

        // line 4
        placeButton(buttonWidth, buttonheight, 0, buttonheight * 4, "4");
        placeButton(buttonWidth, buttonheight, buttonWidth, buttonheight * 4, "5");
        placeButton(buttonWidth, buttonheight, buttonWidth * 2, buttonheight * 4, "6");
        placeButton(buttonWidth, buttonheight, buttonWidth * 3, buttonheight * 4, "+");

        // line 5
        placeButton(buttonWidth, buttonheight, 0, buttonheight * 5, "1");
        placeButton(buttonWidth, buttonheight, buttonWidth, buttonheight * 5, "2");
        placeButton(buttonWidth, buttonheight, buttonWidth * 2, buttonheight * 5, "3");

        // line 6
        placeButton(buttonWidth, buttonheight, buttonWidth, buttonheight * 6, "0");
        placeButton(buttonWidth, buttonheight, buttonWidth * 2, buttonheight * 6, ",");

        // equals button
        JButton equals = new JButton("=");
        equals.setOpaque(true);
        equals.setBackground(Color.DARK_GRAY);
        equals.setFocusable(false);
        equals.setBounds(buttonWidth * 3, buttonheight * 5, buttonWidth, buttonheight * 2);
        equals.setFont(font);
        equals.setForeground(Color.WHITE);
        frame.add(equals);
    }
    //</editor-fold>


    /**
     * Es wird ein Button in einem bestimmten {@link JFrame Fenster} platziert, an einer bestimmten Position, einer
     * bestimmten Größe und versehen mit einem bestimmten Anzeige-Text.
     *
     * @param width  Die Breite des Buttons.
     * @param height Die Höhe des Buttons.
     * @param x      Die X-Koordinate des Buttons.
     * @param y      Die Y-Koordinate des Buttons.
     * @param text   Der Anzeige-Text, der auf dem buttons angezeigt wird.
     */
    private void placeButton(
        @Range(from = 0, to = Integer.MAX_VALUE) final int width,
        @Range(from = 0, to = Integer.MAX_VALUE) final int height,
        @Range(from = 0, to = Integer.MAX_VALUE) final int x,
        @Range(from = 0, to = Integer.MAX_VALUE) final int y,
        @NotNull final String text
    ) {
        JButton button = new JButton(text);
        button.setOpaque(true);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusable(false);
        button.setBounds(x, y, width, height);
        button.setFont(font);
        button.setForeground(Color.WHITE);
        frame.add(button);
    }

}
