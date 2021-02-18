package de.jonas.calculator;

import org.jetbrains.annotations.NotNull;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Der {@link ActionListener}, der wartet, bis ein Button gedrückt wird, und dann die jeweilige Aktion ausführt.
 */
public class ActionHandler implements ActionListener {

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
        System.out.println(button.getText());
    }
    //</editor-fold>
}
