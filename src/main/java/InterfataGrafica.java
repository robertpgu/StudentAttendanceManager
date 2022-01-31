import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import poo.tema3.Student;
import poo.util.ExceptieListaGoala;
import poo.util.ExceptieListaPlina;
import poo.util.ListaDeComparable;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InterfataGrafica extends JFrame {
    private JButton bAdaugare = new JButton("Adaugare");
    private JButton bStergere = new JButton("Stergere");
    private JButton bSortare = new JButton("Sortare");
    private JButton bSalvare = new JButton("Salvare");
    private JLabel lNume = new JLabel("Nume:");
    private JTextField tNume = new JTextField("", 10);
    private JLabel lPrenume = new JLabel("Prenume:");
    private JTextField tPrenume = new JTextField("", 10);
    private JLabel lPrezenta = new JLabel("Prezenta:");
    private JTextField tPrezenta = new JTextField("", 10);
    private JLabel lAuxListaAfisare = new JLabel("Elementele:");
    private JTextArea taListaAfisare = new JTextArea("");

    static ListaDeComparable<Student> lista = new ListaDeComparable<Student>(6, Student.class);
    PrintWriter printWriter = null;
    String nume, prenume;
    int prezenta;

    class AscultatorEvenimenteButon implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String id = ((JButton) e.getSource()).getText();

            try {
                printWriter = new PrintWriter(new FileWriter("Studenti.txt"));
                if (id.equals("Adaugare")) {
                    try {
                        nume = tNume.getText();
                        prenume = tPrenume.getText();
                        prezenta = Integer.parseInt(tPrezenta.getText());
                        lista.adaugareElement(new Student(nume, prenume, prezenta));
                        taListaAfisare.setText(lista.toString());
                    } catch (NumberFormatException exc) {
                        JOptionPane.showMessageDialog(InterfataGrafica.this, "Input invalid! Introduceti un student!",
                                "Input invalid", JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    } catch (ExceptieListaPlina exc) {
                        JOptionPane.showMessageDialog(InterfataGrafica.this,
                                "Lista este plina! Nu se mai pot adauga alte elemente", "Lista plina",
                                JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }

                } else if (id.equals("Stergere")) {
                    try {
                        lista.eliminareElement();
                        taListaAfisare.setText(lista.toString());
                    } catch (ExceptieListaGoala exc) {
                        JOptionPane.showMessageDialog(InterfataGrafica.this,
                                "Lista este goala! Adaugati elemente inainte de a incerca sa stergeti!", "Lista goala",
                                JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }

                } else if (id.equals("Sortare")) {
                    try {
                        lista.sortare();
                        taListaAfisare.setText(lista.toString());
                    } catch (ExceptieListaGoala exc) {
                        JOptionPane.showMessageDialog(InterfataGrafica.this,
                                "Lista este goala! Adaugati elemente inainte de a incerca sa o sortati!", "Lista goala",
                                JOptionPane.ERROR_MESSAGE);
                        exc.printStackTrace();
                    }

                } else {
                    printWriter.println(lista.toString());
                    System.out.println("A fost salvata lista");
                }
            } catch (IOException exc) {
                exc.printStackTrace();
            } finally {
                if (printWriter != null) {
                    printWriter.close();
                }
            }

        }
    }

    private AscultatorEvenimenteButon aeb1 = new AscultatorEvenimenteButon();

    public InterfataGrafica() {
        bAdaugare.addActionListener(aeb1);
        bStergere.addActionListener(aeb1);
        bSortare.addActionListener(aeb1);
        bSalvare.addActionListener(aeb1);

        Box bh1 = Box.createHorizontalBox();
        bh1.add(lNume);
        bh1.add(Box.createHorizontalStrut(48));
        bh1.add(tNume);
        bh1.add(Box.createHorizontalStrut(10));
        bh1.add(Box.createHorizontalGlue());

        Box bh2 = Box.createHorizontalBox();
        bh2.add(lPrenume);
        bh2.add(Box.createHorizontalStrut(30));
        bh2.add(tPrenume);
        bh2.add(Box.createHorizontalStrut(10));
        bh2.add(Box.createHorizontalGlue());

        Box bh3 = Box.createHorizontalBox();
        bh3.add(lPrezenta);
        bh3.add(Box.createHorizontalStrut(30));
        bh3.add(tPrezenta);
        bh3.add(Box.createHorizontalStrut(12));
        bh3.add(Box.createHorizontalGlue());

        Box bh4 = Box.createHorizontalBox();
        bh4.add(lAuxListaAfisare);
        bh4.add(Box.createHorizontalStrut(18));
        bh4.add(taListaAfisare);
        bh4.add(Box.createHorizontalStrut(12));
        bh4.add(Box.createHorizontalGlue());

        Box bv1 = Box.createVerticalBox();
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(bh1);
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(bh2);
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(bh3);
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(bh4);
        bv1.add(Box.createVerticalStrut(10));
        bv1.add(Box.createVerticalGlue());

        Box bv2 = Box.createVerticalBox();
        bv2.add(Box.createVerticalStrut(10));
        bv2.add(bAdaugare);
        bv2.add(Box.createVerticalGlue());
        bv2.add(bStergere);
        bv2.add(Box.createVerticalGlue());
        bv2.add(bSortare);
        bv2.add(Box.createVerticalGlue());
        bv2.add(bSalvare);
        bv2.add(Box.createVerticalGlue());

        Box bh5 = Box.createHorizontalBox();
        bh5.add(Box.createHorizontalStrut(10));
        bh5.add(bv1);
        bh5.add(Box.createHorizontalStrut(10));
        bh5.add(bv2);
        bh5.add(Box.createHorizontalStrut(10));
        bh5.add(Box.createHorizontalGlue());

        Container cp = getContentPane();
        cp.add(BorderLayout.CENTER, bh5);

    }

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("Studenti.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                Pattern pattern = Pattern.compile("([a-z]+=)([a-zA-Z0-9]+)(.{1,2})");
                Matcher matcher = pattern.matcher(line);
                String[] params = new String[3];
                int i = 0;
                while (matcher.find()) {
                    params[i++] = matcher.group(2);
                }
                String nume = params[0];
                String prenume = params[1];
                int prezenta = Integer.parseInt(params[2]);
                lista.adaugareElement(new Student(nume, prenume, prezenta));
            }
            System.out.println(lista.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        InterfataGrafica ig = new InterfataGrafica();
        ig.taListaAfisare.setText(lista.toString());
        ig.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ig.setSize(600, 400);

        ig.setVisible(true);
    }

}
