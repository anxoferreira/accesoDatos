package ejerciciosBD.conexionSwing.ventanas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PantallaInicio extends JFrame {

    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JTextField txtIp;
    private JTextField txtPort;
    private JComboBox<String> cmbBaseDatos;
    private JButton btnConectar;

    public PantallaInicio() {
        setTitle("MySQL - Conexión");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // panel principal con GridBagLayout (recomendacion de la ia, muy comoda la
        // verdad)
        JPanel panelPrincipal = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // usuario
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelPrincipal.add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        txtUsuario = new JTextField("root", 15);
        panelPrincipal.add(txtUsuario, gbc);

        // contraseña
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelPrincipal.add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        txtContrasena = new JPasswordField(15);
        panelPrincipal.add(txtContrasena, gbc);

        // ip y puerto
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelPrincipal.add(new JLabel("Servidor:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        txtIp = new JTextField("127.0.0.1", 9);
        panelPrincipal.add(txtIp, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        txtPort = new JTextField("3306", 4);
        panelPrincipal.add(txtPort, gbc);

        // base de datos
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelPrincipal.add(new JLabel("Base de datos:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        cmbBaseDatos = new JComboBox<>(new String[] { "sakila", "world", "mysql" });
        panelPrincipal.add(cmbBaseDatos, gbc);

        // boton
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        btnConectar = new JButton("CONECTAR");
        panelPrincipal.add(btnConectar, gbc);

        // listener
        btnConectar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarYConectar();
            }
        });

        add(panelPrincipal);
        pack();
        setLocationRelativeTo(null);
    }

    // comprobar que se cumplen los requisitos
    private void validarYConectar() {
        String usuario = txtUsuario.getText().trim();
        String servidor = txtIp.getText().trim();
        String puertoStr = txtPort.getText().trim();

        // campos vacíos
        if (usuario.isEmpty() || servidor.isEmpty() || puertoStr.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Es necesario cumplimentar los campos de usuario, servidor y puerto.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }

        // comprobar puerto
        try {
            int puerto = Integer.parseInt(puertoStr);
            if (puerto < 0 || puerto > 45003) {
                JOptionPane.showMessageDialog(this,
                        "El puerto debe ser un numero entre 0 y 45003.",
                        "Error de validación",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "El puerto introducido no es un número válido.",
                    "Error de formato",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // hacer conexion si no hay problemas
        JOptionPane.showMessageDialog(this,
                "¡Validaciones superadas! Procediendo a conectar...",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);
    }

    // Getters
    public String getUsuario() {
        return txtUsuario.getText();
    }

    public String getPassword() {
        return new String(txtContrasena.getPassword());
    }

    public String getIp() {
        return txtIp.getText();
    }

    public String getPuerto() {
        return txtPort.getText();
    }

    public String getBaseDatosSeleccionada() {
        return (String) cmbBaseDatos.getSelectedItem();
    }
}