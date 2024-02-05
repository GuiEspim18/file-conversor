package com.conversor.utils.convert;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class OFXToXLSFile {

    public static void convert(String inputFile, String outputFile) {
        try {
            // Carregar arquivo OFX
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(inputFile);

            // Extrair dados relevantes do arquivo OFX
            NodeList transactionList = doc.getElementsByTagName("STMTTRN");

            // Criar um novo arquivo XLS
            try (FileOutputStream fos = new FileOutputStream(outputFile)) {
                for (int i = 0; i < transactionList.getLength(); i++) {
                    Element transaction = (Element) transactionList.item(i);
                    String date = transaction.getElementsByTagName("DTPOSTED").item(0).getTextContent();
                    String description = transaction.getElementsByTagName("MEMO").item(0).getTextContent();
                    String amount = transaction.getElementsByTagName("TRNAMT").item(0).getTextContent();
                    fos.write((date + "\t" + description + "\t" + amount + "\n").getBytes());
                }
            }

            System.out.println("Arquivo XLS criado com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
