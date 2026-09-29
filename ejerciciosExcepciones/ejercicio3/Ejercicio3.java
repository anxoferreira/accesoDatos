package ejerciciosExcepciones.ejercicio3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Ejercicio3 extends JFrame {

    private JTextField textoNumerador;
    private JTextField textoDenominador;
    private JTextField textoResultado;
    private JButton botonContinuar;

    public Ejercicio3() {
        // Configuración ventana
        setTitle("Introduce número");
        setSize(450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Título
        JLabel labelTitulo = new JLabel("Introduce número:");
        labelTitulo.setForeground(java.awt.Color.RED);
        labelTitulo.setBounds(30, 20, 200, 25);
        add(labelTitulo);

        // Numerador
        JLabel labelNum = new JLabel("Numerador (<100):");
        labelNum.setBounds(30, 70, 150, 25);
        add(labelNum);

        textoNumerador = new JTextField();
        textoNumerador.setBounds(180, 70, 100, 25);
        add(textoNumerador);

        // Denominador
        JLabel labelDen = new JLabel("Denominador (>-5):");
        labelDen.setBounds(30, 120, 150, 25);
        add(labelDen);

        textoDenominador = new JTextField();
        textoDenominador.setBounds(180, 120, 100, 25);
        add(textoDenominador);

        // Continuar
        botonContinuar = new JButton("Continuar");
        botonContinuar.setBounds(310, 80, 100, 35);
        add(botonContinuar);

        // Resultado
        JLabel labelRes = new JLabel("Resultado:");
        labelRes.setBounds(30, 190, 150, 25);
        add(labelRes);

        textoResultado = new JTextField();
        textoResultado.setBounds(180, 190, 100, 25);
        textoResultado.setEditable(false);
        add(textoResultado);

        botonContinuar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int numerador = Integer.parseInt(textoNumerador.getText());
                    int denominador = Integer.parseInt(textoDenominador.getText());

                    if (numerador >= 100 || denominador <= -5) {
                        throw new ExcepcionIntervalo("El intervalo no es válido.");
                    }

                    if (denominador == 0) {
                        throw new ArithmeticException("División entre cero.");
                    }

                    double resultado = (double) numerador / denominador;
                    textoResultado.setText(String.valueOf(resultado));
                    JOptionPane.showMessageDialog(null, "Operación Realizada.", "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Se han introducido caracteres no numéricos.", "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                    textoResultado.setText("");
                } catch (ExcepcionIntervalo ex) {
                    JOptionPane.showMessageDialog(null, "Fuera del intervalo.", "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                    textoResultado.setText("");
                } catch (ArithmeticException ex) {
                    JOptionPane.showMessageDialog(null, "División entre cero.", "Mensaje",
                            JOptionPane.INFORMATION_MESSAGE);
                    textoResultado.setText("");
                }
            }
        });
    }

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ejercicio3().setVisible(true);
            }
        });

    }

}