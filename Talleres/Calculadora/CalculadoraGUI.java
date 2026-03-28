import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraGUI extends JFrame implements ActionListener {

    private JTextField pantalla;
    private JComboBox<Integer> comboBaseEntrada;
    private JComboBox<Integer> comboBaseSalida;
    private JButton[] botonesNumeros;
    private JButton btnSumar, btnRestar, btnMultiplicar, btnDividir, btnIgual, btnLimpiar;

    private String operador = "";
    private String num1Str = "";
    private boolean nuevaOperacion = true;

    public CalculadoraGUI() {
        setTitle("Calculadora de Bases (2-10)");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new BorderLayout());
        
        pantalla = new JTextField("0");
        pantalla.setFont(new Font("Arial", Font.BOLD, 36));
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setEditable(false);
        pantalla.setBackground(Color.WHITE);
        panelSuperior.add(pantalla, BorderLayout.NORTH);

        JPanel panelBases = new JPanel(new FlowLayout());
        panelBases.add(new JLabel("Base Entrada:"));
        
        Integer[] bases = {2, 3, 4, 5, 6, 7, 8, 9, 10};
        comboBaseEntrada = new JComboBox<>(bases);
        comboBaseEntrada.setSelectedItem(10); 
        comboBaseEntrada.addActionListener(e -> actualizarBotonesHabilitados());
        panelBases.add(comboBaseEntrada);

        panelBases.add(new JLabel("Base Salida:"));
        comboBaseSalida = new JComboBox<>(bases);
        comboBaseSalida.setSelectedItem(10); 
        comboBaseSalida.addActionListener(e -> actualizarBotonesHabilitados());

        panelSuperior.add(panelBases, BorderLayout.SOUTH);
        add(panelSuperior, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(4, 4, 5, 5));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        botonesNumeros = new JButton[10];
        for (int i = 0; i < 10; i++) {
            botonesNumeros[i] = new JButton(String.valueOf(i));
            botonesNumeros[i].setFont(new Font("Arial", Font.BOLD, 24));
            botonesNumeros[i].addActionListener(this);
        }

        btnSumar = crearBotonOperacion("+");
        btnRestar = crearBotonOperacion("-");
        btnMultiplicar = crearBotonOperacion("*");
        btnDividir = crearBotonOperacion("/");
        btnIgual = crearBotonOperacion("=");
        btnLimpiar = crearBotonOperacion("C");

        panelBotones.add(botonesNumeros[7]);
        panelBotones.add(botonesNumeros[8]);
        panelBotones.add(botonesNumeros[9]);
        panelBotones.add(btnDividir);
        panelBotones.add(botonesNumeros[4]);
        panelBotones.add(botonesNumeros[5]);
        panelBotones.add(botonesNumeros[6]);
        panelBotones.add(btnMultiplicar);
        panelBotones.add(botonesNumeros[1]);
        panelBotones.add(botonesNumeros[2]);
        panelBotones.add(botonesNumeros[3]);
        panelBotones.add(btnRestar);
        panelBotones.add(btnLimpiar);
        panelBotones.add(botonesNumeros[0]);
        panelBotones.add(btnIgual);
        panelBotones.add(btnSumar);

        add(panelBotones, BorderLayout.CENTER);

        actualizarBotonesHabilitados();
    } 

    private JButton crearBotonOperacion(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Arial", Font.BOLD, 24));
        btn.setBackground(new Color(220, 220, 220));
        btn.addActionListener(this);
        return btn;
    }

    private void actualizarBotonesHabilitados() {
        int base = (int) comboBaseEntrada.getSelectedItem();
        for (int i = 0; i < 10; i++) {
            botonesNumeros[i].setEnabled(i < base);
        }
        btnLimpiar.doClick();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        String textoPantalla = pantalla.getText();

        if (source == btnLimpiar) {
            pantalla.setText("0");
            num1Str = "";
            operador = "";
            nuevaOperacion = true;
            return;
        }

        for (int i = 0; i < 10; i++) {
            if (source == botonesNumeros[i]) {
                if (pantalla.getText().equals("0") || pantalla.getText().equals("Error") || nuevaOperacion) {
                    pantalla.setText(String.valueOf(i));
                    nuevaOperacion = false;
                } else {
                    pantalla.setText(textoPantalla + i);
                }
                return;
            }
        }

        if (source == btnSumar || source == btnRestar || source == btnMultiplicar || source == btnDividir) {
            num1Str = pantalla.getText();
            operador = ((JButton) source).getText();
            nuevaOperacion = true;
            return;
        }

        if (source == btnIgual) {
            if (operador.isEmpty() || num1Str.isEmpty()) return;
            
            String num2Str = pantalla.getText();
            int baseEntrada = (int) comboBaseEntrada.getSelectedItem();
            int baseSalida = (int) comboBaseSalida.getSelectedItem();

            try {
                Numero n1 = new Numero(Integer.parseInt(num1Str), baseEntrada);
                Numero n2 = new Numero(Integer.parseInt(num2Str), baseEntrada);

                Operacion op;
                switch (operador) {
                    case "+": op = new Suma(n1, n2); break;
                    case "-": op = new Resta(n1, n2); break;
                    case "*": op = new Multiplicacion(n1, n2); break;
                    case "/": op = new Division(n1, n2); break;
                    default: return;
                }

                Numero res = op.operar();
                Numero resFinal = Numero.deBase10(res.aBase10(), baseSalida);
                pantalla.setText(String.valueOf(resFinal.getValor()));
                
                num1Str = "";
                operador = "";
                nuevaOperacion = true;

            } catch (Exception ex) {
                pantalla.setText("Error");
                nuevaOperacion = true;
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalculadoraGUI calc = new CalculadoraGUI();
            calc.setVisible(true);
        });
    }
}