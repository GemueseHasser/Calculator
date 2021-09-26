package de.jonas.calculator;

import de.jonas.Calculator;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Der {@link ActionListener}, der wartet, bis ein Button gedrückt wird, und dann die jeweilige Aktion ausführt.
 */
public final class ActionHandler implements ActionListener {

    //<editor-fold desc="CONSTANTS">
    /** Die Anzahl an Nachkommastellen, die der Taschenrechner haben soll. */
    private static final int DECIMAL_PLACES = 14;

    //<editor-fold desc="potenz-frame">
    /** Die Breite des {@link JFrame Fensters}, welches nach einer entsprechenden Potenz fragt. */
    private static final int POTENZ_FRAME_WIDTH = 300;
    /** Die Höhe des {@link JFrame Fensters}, welches nach einer entsprechenden Potenz fragt. */
    private static final int POTENZ_FRAME_HEIGHT = 150;
    //</editor-fold>

    //<editor-fold desc="potenz-frame-field">
    /** Die X-Koordinate des {@link JTextField Text-Feldes}, welche nach einer entsprechenden Potenz fragt. */
    private static final int POTENZ_FRAME_FIELD_X = 10;
    /** Die Y-Koordinate des {@link JTextField Text-Feldes}, welche nach einer entsprechenden Potenz fragt. */
    private static final int POTENZ_FRAME_FIELD_Y = 10;
    /** Die Breite des {@link JTextField Text-Feldes}, welche nach einer entsprechenden Potenz fragt. */
    private static final int POTENZ_FRAME_FIELD_WIDTH = 270;
    /** Die Höhe des {@link JTextField Text-Feldes}, welche nach einer entsprechenden Potenz fragt. */
    private static final int POTENZ_FRAME_FIELD_HEIGHT = 35;
    //</editor-fold>

    //<editor-fold desc="potenz-frame-button">
    /** Die X-Koordinate des {@link JButton Buttons}, welcher die Abfrage nach einer entsprechenden Potenz beendet. */
    private static final int POTENZ_FRAME_BUTTON_X = 10;
    /** Die Y-Koordinate des {@link JButton Buttons}, welcher die Abfrage nach einer entsprechenden Potenz beendet. */
    private static final int POTENZ_FRAME_BUTTON_Y = 55;
    /** Die Breite des {@link JButton Buttons}, welcher die Abfrage nach einer entsprechenden Potenz beendet. */
    private static final int POTENZ_FRAME_BUTTON_WIDTH = 270;
    /** Die Höhe des {@link JButton Buttons}, welcher die Abfrage nach einer entsprechenden Potenz beendet. */
    private static final int POTENZ_FRAME_BUTTON_HEIGHT = 35;
    //</editor-fold>

    //</editor-fold>


    //<editor-fold desc="STATIC-FIELDS">
    /** Der String, der im Hintergrund läuft und für den Nutzer unsichtbar ist, womit aber alles berechnet wird. */
    private static String eval = " ";
    //</editor-fold>


    //<editor-fold desc="LOCAL-FIELDS">
    /** Der {@link JButton Button}, der angeklickt wurde. */
    private final JButton button;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Erzeugt eine neue Instanz des {@link ActionHandler}, womit das anklicken der Buttons im {@link GUI} geregelt
     * wird.
     *
     * @param button Der {@link JButton Button}, der angeklickt wurde.
     */
    public ActionHandler(@NotNull final JButton button) {
        this.button = button;
    }

    /**
     * Erzeugt eine neue Instanz des {@link ActionHandler}, womit auf die Klasse und dessen Methoden zugegriffen werden
     * kann. Hiermit wird kein Button-Klick gehandhabt!
     */
    public ActionHandler() {
        this.button = null;
    }
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
        assert button != null;
        performAction(button.getText());
    }
    //</editor-fold>

    /**
     * Führt aufgrund einer bestimmten Eingabe in Form eines Textes eine bestimmte Aktion aus.
     *
     * @param text Der Text, auf dem die Aktion basiert.
     */
    @SneakyThrows
    @SuppressWarnings("checkstyle:IllegalCatch")
    public void performAction(@NotNull final String text) {
        switch (text) {
            case "=":
                double defaultResult = eval(
                    eval
                        .replace("÷", "/")
                        .replace("×", "*")
                        .replace(",", ".")
                        .replace("√", "sqrt")
                );

                final double factor = Math.pow(10, DECIMAL_PLACES);
                defaultResult *= factor;
                final double result = Math.round(defaultResult) / factor;

                final String finalEval = " " + String.valueOf(result).replace(".", ",");
                ObjectPlacer.getCALC_FIELD().setText(finalEval);
                eval = finalEval;
                break;

            case "C":
                ObjectPlacer.getCALC_FIELD().setText(" ");
                eval = " ";
                break;

            case "⌫":
                if (ObjectPlacer.getCALC_FIELD().getText().length() <= 1) {
                    break;
                }
                ObjectPlacer.getCALC_FIELD().setText(ObjectPlacer.getCALC_FIELD().getText().substring(
                    0,
                    ObjectPlacer.getCALC_FIELD().getText().length() - 1
                    )
                );
                eval = eval.substring(0, eval.length() - 1);
                break;

            case "X²":
                ObjectPlacer.getCALC_FIELD().setText(ObjectPlacer.getCALC_FIELD().getText() + "²");
                addBracketBeforeLastNumber(true);
                eval += "*" + getLastNumber(eval) + ")";
                break;

            case "X³":
                ObjectPlacer.getCALC_FIELD().setText(ObjectPlacer.getCALC_FIELD().getText() + "³");
                final String last = getLastNumber(eval);
                addBracketBeforeLastNumber(false);
                eval += "*" + last + "*" + last + ")";
                break;

            case "Xʸ":
                askPotenz();
                break;

            default:
                ObjectPlacer.getCALC_FIELD().setText(ObjectPlacer.getCALC_FIELD().getText() + text);
                eval += text;
                break;
        }
    }

    private void addBracketBeforeLastNumber(final boolean gerade) {
        final String last = getLastNumber(eval);

        eval = eval.substring(0, eval.length() - last.length())
            + (gerade ? "-(" : "+(")
            + eval.substring(eval.length() - last.length());
    }

    private String getLastNumber(@NotNull final String text) {
        String subText = text;

        final StringBuilder number = new StringBuilder();
        final StringBuilder result = new StringBuilder();

        if (subText.endsWith(")")) {
            while (!subText.endsWith("(")) {
                number.append(subText.charAt(subText.length() - 1));
                subText = subText.substring(0, subText.length() - 1);
            }
            number.append("(");
        } else {
            while (!(
                subText.endsWith("+")
                    || subText.endsWith("-")
                    || subText.endsWith("×")
                    || subText.endsWith("÷")
                    || subText.endsWith(" ")
                    || subText.endsWith("²")
                    || subText.endsWith("³")
                    || subText.endsWith("(")
            )) {
                number.append(subText.charAt(subText.length() - 1));
                subText = subText.substring(0, subText.length() - 1);
            }

            if (subText.endsWith("-")) {
                number.append("-");
            }
        }

        for (int i = number.toString().length() - 1; i >= 0; i--) {
            result.append(number.charAt(i));
        }

        return result.toString();
    }

    private void askPotenz() {
        final JFrame frame = new JFrame("Wählen sie eine Potenz");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setBounds(0, 0, POTENZ_FRAME_WIDTH, POTENZ_FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        final JTextField field = new JTextField();
        field.grabFocus();
        field.setBounds(
            POTENZ_FRAME_FIELD_X,
            POTENZ_FRAME_FIELD_Y,
            POTENZ_FRAME_FIELD_WIDTH,
            POTENZ_FRAME_FIELD_HEIGHT
        );

        final JButton finish = new JButton("Fertig");
        finish.setBounds(
            POTENZ_FRAME_BUTTON_X,
            POTENZ_FRAME_BUTTON_Y,
            POTENZ_FRAME_BUTTON_WIDTH,
            POTENZ_FRAME_BUTTON_HEIGHT
        );
        finish.setOpaque(true);
        finish.setBackground(Color.LIGHT_GRAY);
        finish.setFocusable(false);
        finish.addActionListener(actionEvent -> {
            final StringBuilder evalBuilder = new StringBuilder();
            final String number = getLastNumber(eval);
            final int potenz = Integer.parseInt(field.getText());

            addBracketBeforeLastNumber(potenz % 2 == 0);

            for (int i = 1; i < potenz; i++) {
                evalBuilder.append("*").append(number);
            }

            eval += evalBuilder + ")";

            final StringBuilder stringPotenz = new StringBuilder();

            for (int i1 = 0; i1 < String.valueOf(potenz).length(); i1++) {
                int num = Integer.parseInt(String.valueOf(String.valueOf(potenz).charAt(i1)));
                stringPotenz.append(Calculator.POTENZEN.get(num));
            }
            ObjectPlacer.getCALC_FIELD().setText(ObjectPlacer.getCALC_FIELD().getText() + stringPotenz);
            frame.dispose();
        });

        frame.add(field);
        frame.add(finish);
        frame.setVisible(true);
    }

    /**
     * Berechnet einen String wie eval().
     *
     * @param term Der String der mathematisch berechnet wird.
     *
     * @return Das Ergebnis der Rechnung.
     */
    public static double eval(@NotNull final String term) {
        return new Object() {
            private int pos = -1;
            private int ch;

            void nextChar() {
                ch = (++pos < term.length()) ? term.charAt(pos) : -1;
            }

            boolean eat(final int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();

                if (pos < term.length()) {
                    return 0;
                }

                return x;
            }

            double parseExpression() {
                double x = parseTerm();
                for (; ; ) {
                    if (eat('+')) {
                        x += parseTerm();
                    } else if (eat('-')) {
                        x -= parseTerm();
                    } else {
                        return x;
                    }
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (; ; ) {
                    if (eat('*')) {
                        x *= parseFactor();
                    } else if (eat('/')) {
                        x /= parseFactor();
                    } else {
                        return x;
                    }
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor();
                if (eat('-')) return -parseFactor();

                double x;
                int startPos = this.pos;
                if (eat('(')) {
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') {
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(term.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') {
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = term.substring(startPos, this.pos);
                    x = parseFactor();
                    switch (func) {
                        case "sqrt":
                            x = Math.sqrt(x);
                            break;
                        case "sin":
                            x = Math.sin(Math.toRadians(x));
                            break;
                        case "cos":
                            x = Math.cos(Math.toRadians(x));
                            break;
                        case "tan":
                            x = Math.tan(Math.toRadians(x));
                            break;
                        default:
                            throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
                    return 0;
                }

                if (eat('^')) x = Math.pow(x, parseFactor());

                return x;
            }
        }.parse();
    }
}
