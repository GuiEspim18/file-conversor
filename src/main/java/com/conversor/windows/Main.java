package com.conversor.windows;

import com.conversor.utils.convert.OFXToXLSFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
        // if the event belongs from open button
        if (e.getSource() == openButton) {
            // openning the dialog and getting the result
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String outputPath = selectedFile.getAbsolutePath().replace(".ofx", ".xls");
                OFXToXLSFile.convert(selectedFile.getAbsolutePath(), outputPath);
                JOptionPane.showMessageDialog(this, "Arquivo convertido com sucesso!");
            }
        }
    }

    public void open() {
        SwingUtilities.invokeLater(Main::new);
    }
}
