package com.conversor.utils.convert;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;

public class OFXToXLSFile {

    public static void convert(String inputFile, String outputFile) {

    }

    private static String extractValue (String line, String tag) {
        int statIndex = line.indexOf("<" + tag + ">") + tag.length() + 2;
        int endIndex = line.indexOf("</" + tag + ">");
        return line.substring(statIndex, endIndex);
    }

}
