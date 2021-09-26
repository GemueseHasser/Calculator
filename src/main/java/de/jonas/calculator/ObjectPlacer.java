package de.jonas.calculator;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Range;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Font;

/**
 * Auf ein bestimmtes {@link JFrame Fenster} werden alle nötigen Objekte, die für einen Taschenrechner nötig sind
 * platziert.
 */
public final class ObjectPlacer {

    //<editor-fold desc="CONSTANTS">
    /** Die Schriftgröße der {@link Font Schriftart} aller Objekte. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int FONT_SIZE = 50;
    /** Die {@link Font Schriftart} in der alle Schriften auf den platzierten Objekten dargestellt werden. */
    @NotNull
    private static final Font FONT = new Font("Arial", Font.BOLD, FONT_SIZE);
    /** Das {@link JTextField Text-Feld}, welches die gesamte Rechnung aufführt. */
    @Getter
    @NotNull
    private static final JTextField CALC_FIELD = new JTextField(" ");
    /** Alle Titel der Buttons, die der Taschenrechner haben soll in der richtigen Reihenfolge. */
    @NotNull
    private static final String @NotNull [] BUTTONS = new String[]{
        "(",
        ")",
        "sin",
        "tan",
        "X²",
        "X³",
        "Xʸ",
        "√",
        "C",
        "÷",
        "×",
        "⌫",
        "7",
        "8",
        "9",
        "-",
        "4",
        "5",
        "6",
        "+",
        "1",
        "2",
        "3",
        " ",
        " ",
        "0",
        ",",
    };
    /** Die Anzahl an Buttons, die sich in einer Zeile befinden sollen. */
    @Range(from = 0, to = Integer.MAX_VALUE)
    private static final int BUTTONS_PER_LINE = 4;
    //</editor-fold>


    //<editor-fold desc="LOCAL-FIELDS">
    /** Das Fenster, in das alle Objekte platziert werden. */
    @NotNull
    private final JFrame frame;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Es wird eine neue und vollständig unabhängige Instanz des {@link ObjectPlacer} erzeugt. In einem bestimmten
     * {@link JFrame Fenster} werden mithilfe dieser Instanz alle nötigen Objekte platziert, die für einen
     * Taschenrechner von Nutzen sind.
     *
     * @param frame Das {@link JFrame Fenster}, in dem die Änderungen vorgenommen werden.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    public ObjectPlacer(@NotNull final JFrame frame) {
        this.frame = frame;
    }
    //</editor-fold>


    /**
     * Platziert alle nötigen Objekte, die für einen Taschenrechner von Nutzen sind.
     */
    public void place() {
        // frame width and height
        final int width = frame.getWidth();
        final int height = frame.getHeight();

        // button width and height (based on frame width and height)
        final int buttonWidth = width / BUTTONS_PER_LINE - 3;
        final int buttonHeight = height / (BUTTONS.length / BUTTONS_PER_LINE + 2) - 5;

        // text field
        CALC_FIELD.setBounds(0, 0, width - 12, buttonHeight);
        CALC_FIELD.setFont(FONT);
        CALC_FIELD.setEnabled(false);
        CALC_FIELD.setBorder(BorderFactory.createLineBorder(Color.BLUE, 3));

        // place default buttons
        for (int i = 0; i < BUTTONS.length; i++) {
            final String text = BUTTONS[i];

            if (text.equals(" ")) {
                continue;
            }

            final int x = (i % BUTTONS_PER_LINE) * buttonWidth;
            final int y = (i / BUTTONS_PER_LINE + 1) * buttonHeight;

            placeButton(buttonWidth, buttonHeight, x, y, text);
        }

        // equals button
        final JButton equals = new JButton("=");

        editButton(
            equals,
            buttonWidth,
            buttonHeight * 2,
            buttonWidth * 3,
            buttonHeight * 6
        );

        // add different components
        frame.add(CALC_FIELD);
        frame.add(equals);
    }


    /**
     * Es wird ein Button in einem bestimmten {@link JFrame Fenster} platziert, an einer bestimmten Position, einer
     * bestimmten Größe und versehen mit einem bestimmten Anzeige-Text. Dieser Button ist ein standard Button des
     * Taschenrechners.
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
        final JButton button = new JButton(text);

        editButton(
            button,
            width,
            height,
            x,
            y
        );

        frame.add(button);
    }

    /**
     * Editiert die Eigenschaften eines bestimmten Buttons, also verändert beispielsweise die Farbe.
     *
     * @param button Der Button, welcher editiert werden soll.
     * @param width  Die Breite des Buttons.
     * @param height Die Höhe des Buttons.
     * @param x      Die X-Koordinate des Buttons.
     * @param y      Die Y-Koordinate des Buttons.
     */
    private void editButton(
        @NotNull final JButton button,
        @Range(from = 0, to = Integer.MAX_VALUE) final int width,
        @Range(from = 0, to = Integer.MAX_VALUE) final int height,
        @Range(from = 0, to = Integer.MAX_VALUE) final int x,
        @Range(from = 0, to = Integer.MAX_VALUE) final int y
    ) {
        button.setOpaque(true);
        button.setBackground(Color.DARK_GRAY);
        button.setFocusable(false);
        button.setBounds(x, y, width, height);
        button.setFont(FONT);
        button.setForeground(Color.WHITE);
        button.addActionListener(new ActionHandler(button));
    }

}
