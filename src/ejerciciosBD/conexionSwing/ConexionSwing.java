package ejerciciosBD.conexionSwing;

import javax.swing.*;
import ejerciciosBD.conexionSwing.ventanas.PantallaInicio; // <-- Importamos la pantalla

public class ConexionSwing {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PantallaInicio pantallaInicio = new PantallaInicio();
            pantallaInicio.setVisible(true);
        });
    }
}