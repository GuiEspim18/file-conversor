package com.conversor.utils.convert;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import java.io.*;

public class OFXToXLSFile {

    public static void convert(String inputFile, String outputFile) {
        try {
            // Carregar o arquivo OFX
            File ofxFile = new File(inputFile);
            FileInputStream inputStream = new FileInputStream(ofxFile);

            // Criar um workbook do Excel
            Workbook workbook = new HSSFWorkbook();
            Sheet sheet = workbook.createSheet("OFX Data");

            // Ler o conteúdo do arquivo OFX
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            int rowNum = 0;
            boolean inTransaction = false;
            while ((line = reader.readLine()) != null) {
                // Verificar se estamos dentro de uma transação bancária
                if (line.contains("<STMTTRN>")) {
                    inTransaction = true;
                    continue;
                }
                if (line.contains("</STMTTRN>")) {
                    inTransaction = false;
                    continue;
                }
                // Ignorar as linhas que não estão dentro de uma transação
                if (!inTransaction) {
                    continue;
                }
                // Pular as tags XML
                if (line.startsWith("<") && line.endsWith(">")) {
                    continue;
                }
                // Preencher a tabela do Excel com os dados da transação
                Row row = sheet.createRow(rowNum++);
                String[] values = line.split(">");
                for (int i = 0; i < values.length; i++) {
                    String value = values[i].replaceAll("<.*", "").trim();
                    row.createCell(i).setCellValue(value);
                }
            }

            // Fechar o leitor
            reader.close();

            // Escrever o workbook para um arquivo XLS
            FileOutputStream outputStream = new FileOutputStream(new File(outputFile));
            workbook.write(outputStream);

            // Fechar os fluxos de entrada e saída
            inputStream.close();
            outputStream.close();

            System.out.println("Arquivo XLS gerado com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
