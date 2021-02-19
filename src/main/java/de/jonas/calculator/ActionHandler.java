package de.jonas.calculator;

import de.jonas.Calculator;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Der {@link ActionListener}, der wartet, bis ein Button gedrückt wird, und dann die jeweilige Aktion ausführt.
 */
public class ActionHandler implements ActionListener {

    //<editor-fold desc="CONSTANTS">

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
        if (text.equalsIgnoreCase("=")) {
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            Object result = null;
            try {
                result = engine.eval(
                    eval
                        .replace("÷", "/")
                        .replace("×", "*")
                        .replace(",", ".")
                );
            } catch (final Exception e) {
                System.out.println("Bitte gib einen mathematisch richtigen Ausdruck an!");
            }
            final String finalEval = " " + String.valueOf(result).replace(".", ",");
            PlaceObjects.getCalcField().setText(finalEval);
            eval = finalEval;
            return;
        }

        if (text.equalsIgnoreCase("C")) {
            PlaceObjects.getCalcField().setText(" ");
            eval = " ";
            return;
        }

        if (text.equalsIgnoreCase("⌫")) {
            PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText().substring(
                0,
                PlaceObjects.getCalcField().getText().length() - 1
                )
            );
            eval = eval.substring(0, eval.length() - 1);
            return;
        }

        if (text.equalsIgnoreCase("X²")) {
            PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText() + "²");
            eval += "*" + getLastNumber(eval);
            return;
        }

        if (text.equalsIgnoreCase("X³")) {
            PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText() + "³");
            eval += "*" + getLastNumber(eval) + "*" + getLastNumber(eval);
            return;
        }

        if (text.equalsIgnoreCase("Xʸ")) {
            askPotenz();
            return;
        }
        PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText() + text);
        eval += text;
    }

    private String getLastNumber(@NotNull final String text) {
        String subText = text;
        StringBuilder number = new StringBuilder();

        while (!(
            subText.endsWith("+")
                || subText.endsWith("-")
                || subText.endsWith("×")
                || subText.endsWith("÷")
                || subText.endsWith(" ")
                || subText.endsWith("²")
                || subText.endsWith("³")
        )) {
            number.append(subText.charAt(subText.length() - 1));
            subText = subText.substring(0, subText.length() - 1);
        }

        StringBuilder result = new StringBuilder();

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
        finish.addActionListener(actionEvent -> {
            final String number = getLastNumber(eval);
            final int potenz = Integer.parseInt(field.getText());
            for (int i = 1; i < potenz; i++) {
                eval += "*" + number;
            }
            String stringPotenz = "";
            for (int i1 = 0; i1 < String.valueOf(potenz).length(); i1++) {
                int num = Integer.parseInt(String.valueOf(String.valueOf(potenz).charAt(i1)));
                stringPotenz += Calculator.POTENZEN.get(num);
            }
            PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText() + stringPotenz);
            frame.dispose();
        });

        frame.add(field);
        frame.add(finish);
        frame.setVisible(true);
    }
}
