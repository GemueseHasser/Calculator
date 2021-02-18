package de.jonas.calculator;

import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Der {@link ActionListener}, der wartet, bis ein Button gedrückt wird, und dann die jeweilige Aktion ausführt.
 */
public class ActionHandler implements ActionListener {

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
    //</editor-fold>


    //<editor-fold desc="implementation">
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
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
    public static void performAction(@NotNull final String text) {
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
            PlaceObjects.getCalcField().setText(" " + String.valueOf(result).replace(".", ","));
            eval = " " + String.valueOf(result).replace(".", ",");
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
            String subText = PlaceObjects.getCalcField().getText();
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

            PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText() + "²");
            eval += "*" + result.toString();
            return;
        }

        if (text.equalsIgnoreCase("X³")) {
            String subText = PlaceObjects.getCalcField().getText();
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

            PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText() + "³");
            eval += "*" + result.toString() + "*" + result.toString();
            return;
        }
        PlaceObjects.getCalcField().setText(PlaceObjects.getCalcField().getText() + text);
        eval += text;
    }
}
