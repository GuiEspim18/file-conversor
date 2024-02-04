package com.conversor.windows;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener {
    private JButton openButton;
    private JFileChooser fileChooser;

    public Main () {
        super("File Converter");

        openButton = new JButton("Abrir arquivo");
        openButton.addActionListener(this);

        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Arquivos", "ofx"));

        JPanel panel = new JPanel();
        panel.add(openButton);

        add(panel, BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == openButton) {

        }
    }

    public void open() {
        SwingUtilities.invokeLater(Main::new);
    }
}
