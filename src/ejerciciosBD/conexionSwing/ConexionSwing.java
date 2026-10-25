package ejerciciosBD.conexionSwing;

import ejerciciosBD.conexionSwing.ventanas.PantallaInicio;
import javax.swing.*;

public class ConexionSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaInicio ventana = new PantallaInicio();
            ventana.setVisible(true);
        });
    }
}