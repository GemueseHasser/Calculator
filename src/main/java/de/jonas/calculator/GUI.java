package de.jonas.calculator;

import de.jonas.Calculator;

import javax.swing.JFrame;

/**
 * Das {@link GUI Graphical-User-Interface}, welches die Visualisierung der Ein- und Ausgabe des {@link Calculator
 * Taschenrechners} regelt.
 */
public class GUI {

    //<editor-fold desc="CONSTANTS">
    /** Die Breite des Fensters. */
    private static final int FRAME_WIDTH = 500;
    /** Die Höhe des Fensters. */
    private static final int FRAME_HEIGHT = 750;
    //</editor-fold>


    //<editor-fold desc="CONSTRUCTORS">

    /**
     * Es wird eine neue und vollständig unabhängige Instanz des {@link GUI Graphical-User-Interface} erstellt. Dieses
     * {@link GUI} ist die grafische Oberfläche des {@link Calculator Taschenrechners}.
     */
    public GUI() {
        final JFrame frame = new JFrame("Taschenrechner");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.addKeyListener(new KeyHandler());

        new PlaceObjects(frame);

        frame.setVisible(true);
    }
    //</editor-fold>

}
