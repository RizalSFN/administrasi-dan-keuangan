/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 *
 * @author RIZAL
 */
public class PdfGenerator {

    public static void generateArusKasPdf(JTable table, File file) throws IOException {
        try (PDDocument document = new PDDocument()) {
            String[] headers = {"Tanggal", "Keterangan", "Tipe", "Jumlah", "Saldo Akhir"};
            float[] colWidths = {70, 190, 80, 90, 90};

            String[][] content = new String[table.getRowCount()][table.getColumnCount()];
            for (int i = 0; i < table.getRowCount(); i++) {
                for (int j = 0; j < table.getColumnCount(); j++) {
                    content[i][j] = table.getValueAt(i, j) != null ? table.getValueAt(i, j).toString() : "";
                }
            }

            drawPaginatedTable(document, "Laporan Arus Kas", null, headers, colWidths, content);
            document.save(file);
        }
    }

    public static void generateNeracaPdf(String tanggal, String[][] dataAset, String[][] dataKE, File file) throws IOException {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                drawHeader(contentStream, page, "Laporan Neraca");
                drawSubtitle(contentStream, page, "Per Tanggal: " + tanggal);

                // Menggambar dua tabel berdampingan
                drawSimpleTable(contentStream, 700, new String[]{"ASET", ""}, new float[]{150, 100}, dataAset, true, 50);
                drawSimpleTable(contentStream, 700, new String[]{"KEWAJIBAN & EKUITAS", ""}, new float[]{150, 100}, dataKE, true, 320);
            }
            document.save(file);
        }
    }

    // --- Alat Bantu Gambar Internal ---
    private static void drawHeader(PDPageContentStream cs, PDPage page, String title) throws IOException {
        PDType1Font fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        cs.beginText();
        cs.setFont(fontBold, 18);
        float titleWidth = fontBold.getStringWidth(title) / 1000 * 18;
        float x = (page.getMediaBox().getWidth() - titleWidth) / 2;
        cs.newLineAtOffset(x, 780);
        cs.showText(title);
        cs.endText();
    }

    private static void drawSubtitle(PDPageContentStream cs, PDPage page, String subtitle) throws IOException {
        PDType1Font fontPlain = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        cs.beginText();
        cs.setFont(fontPlain, 11);
        float subTitleWidth = fontPlain.getStringWidth(subtitle) / 1000 * 11;
        float x = (page.getMediaBox().getWidth() - subTitleWidth) / 2;
        cs.newLineAtOffset(x, 760);
        cs.showText(subtitle);
        cs.endText();
    }

    private static void drawSimpleTable(PDPageContentStream cs, float yStart, String[] headers, float[] colWidths, String[][] content, boolean colorLastRow, float xStart) throws IOException {
        PDType1Font fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        PDType1Font fontPlain = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        float yPos = yStart;
        float rowHeight = 20f;
        float totalWidth = 0;
        for (float w : colWidths) {
            totalWidth += w;
        }

        // Gambar header tabel
        cs.setNonStrokingColor(new Color(220, 220, 220));
        cs.addRect(xStart, yPos, totalWidth, rowHeight * 1.2f);
        cs.fill();
        cs.beginText();
        cs.setFont(fontBold, 10);
        cs.setNonStrokingColor(Color.BLACK);
        cs.newLineAtOffset(xStart + 5, yPos + 7);
        cs.showText(headers[0]);
        cs.endText();

        yPos -= rowHeight * 1.2f;

        for (int i = 0; i < content.length; i++) {
            String[] row = content[i];
            boolean isLastRow = i == content.length - 1;

            cs.setStrokingColor(Color.LIGHT_GRAY);
            cs.addRect(xStart, yPos, totalWidth, rowHeight);
            cs.stroke();

            // Atur font dan warna
            cs.setFont(isLastRow ? fontBold : fontPlain, 10);
            if (colorLastRow && isLastRow && row[1].contains("-")) {
                cs.setNonStrokingColor(Color.RED);
            } else {
                cs.setNonStrokingColor(Color.BLACK);
            }

            cs.beginText();
            cs.newLineAtOffset(xStart + 5, yPos + 6);
            cs.showText(row[0]);
            cs.endText();

            float valueWidth = (isLastRow ? fontBold : fontPlain).getStringWidth(row[1]) / 1000 * 10;
            cs.beginText();
            cs.newLineAtOffset(xStart + colWidths[0] + colWidths[1] - valueWidth - 5, yPos + 6);
            cs.showText(row[1]);
            cs.endText();

            yPos -= rowHeight;
        }
    }

    private static void drawPaginatedTable(PDDocument document, String title, String subtitle, String[] headers, float[] colWidths, String[][] content) throws IOException {
        float margin = 50;
        float yStartNewPage = PDRectangle.A4.getHeight() - margin;
        float tableTopY = yStartNewPage - 60;

        PDPage currentPage = new PDPage(PDRectangle.A4);
        document.addPage(currentPage);
        PDPageContentStream contentStream = new PDPageContentStream(document, currentPage);

        drawHeader(contentStream, currentPage, title);
        if (subtitle != null) {
            drawSubtitle(contentStream, currentPage, subtitle);
        }

        PDType1Font fontBold = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
        PDType1Font fontPlain = new PDType1Font(Standard14Fonts.FontName.HELVETICA);
        float rowHeight = 15f;
        float cellMargin = 5f;
        float yPosition = tableTopY;

        drawTableHeader(contentStream, margin, yPosition, headers, colWidths, fontBold, rowHeight, cellMargin);
        yPosition -= (rowHeight * 1.5f);

        for (String[] row : content) {
            int maxLines = 1;
            for (int j = 0; j < row.length; j++) {
                List<String> wrappedText = wrapText(row[j], fontPlain, 9, colWidths[j] - 2 * cellMargin);
                if (wrappedText.size() > maxLines) {
                    maxLines = wrappedText.size();
                }
            }
            float currentRowHeight = maxLines * rowHeight;

            if (yPosition - currentRowHeight < margin) {
                contentStream.close();
                currentPage = new PDPage(PDRectangle.A4);
                document.addPage(currentPage);
                contentStream = new PDPageContentStream(document, currentPage);
                yPosition = yStartNewPage;
                drawTableHeader(contentStream, margin, yPosition, headers, colWidths, fontBold, rowHeight, cellMargin);
                yPosition -= (rowHeight * 1.5f);
            }

            drawTableRow(contentStream, margin, yPosition, row, colWidths, fontPlain, fontBold, rowHeight, cellMargin, currentRowHeight);
            yPosition -= currentRowHeight;
        }
        contentStream.close();
    }

    private static void drawTableHeader(PDPageContentStream cs, float x, float y, String[] headers, float[] colWidths, PDType1Font font, float rowHeight, float cellMargin) throws IOException {
        float headerHeight = rowHeight * 1.5f;
        for (int i = 0; i < headers.length; i++) {
            cs.setNonStrokingColor(new Color(220, 220, 220));
            cs.addRect(x, y, colWidths[i], headerHeight);
            cs.fill();
            cs.setStrokingColor(Color.BLACK);
            cs.addRect(x, y, colWidths[i], headerHeight);
            cs.stroke();
            cs.setNonStrokingColor(Color.BLACK);
            cs.beginText();
            cs.setFont(font, 9);
            cs.newLineAtOffset(x + cellMargin, y + (headerHeight - 9) / 2);
            cs.showText(headers[i]);
            cs.endText();
            x += colWidths[i];
        }
    }

    private static void drawTableRow(PDPageContentStream cs, float x, float y, String[] row, float[] colWidths, PDType1Font fontPlain, PDType1Font fontBold, float rowHeight, float cellMargin, float currentRowHeight) throws IOException {
        float xPosition = x;
        for (int j = 0; j < row.length; j++) {
            cs.setStrokingColor(Color.LIGHT_GRAY);
            cs.addRect(xPosition, y - currentRowHeight, colWidths[j], currentRowHeight);
            cs.stroke();

            String text = row[j];
            List<String> lines = wrapText(text, fontPlain, 9, colWidths[j] - 2 * cellMargin);
            float textY = y - 12;

            for (String line : lines) {
                if ((j == 3 || j == 4) && line.contains("-")) {
                    cs.setNonStrokingColor(Color.RED);
                } else {
                    cs.setNonStrokingColor(Color.BLACK);
                }
                cs.beginText();
                cs.setFont(fontPlain, 9);

                if (j >= 3) {
                    float textWidth = fontPlain.getStringWidth(line) / 1000 * 9;
                    cs.newLineAtOffset(xPosition + colWidths[j] - cellMargin - textWidth, textY);
                } else {
                    cs.newLineAtOffset(xPosition + cellMargin, textY);
                }
                cs.showText(line);
                cs.endText();
                textY -= rowHeight;
            }
            xPosition += colWidths[j];
        }
    }

    private static List<String> wrapText(String text, PDType1Font font, float fontSize, float maxWidth) throws IOException {
        List<String> lines = new ArrayList<>();
        if (text == null) {
            lines.add("");
            return lines;
        }
        text = text.trim();

        while (text.length() > 0) {
            int breakIndex = text.length();
            float width = font.getStringWidth(text) / 1000 * fontSize;

            if (width > maxWidth) {
                String subString = text;
                while (width > maxWidth && subString.length() > 0) {
                    subString = subString.substring(0, subString.length() - 1);
                    width = font.getStringWidth(subString) / 1000 * fontSize;
                }
                int lastSpace = subString.lastIndexOf(" ");
                if (lastSpace != -1) {
                    breakIndex = lastSpace;
                } else {
                    breakIndex = subString.length();
                }
                lines.add(text.substring(0, breakIndex).trim());
                text = text.substring(breakIndex).trim();
            } else {
                lines.add(text);
                break;
            }
        }
        return lines;
    }

    public static void generateLabaRugiPdf(String periode, String[][] data, File file) throws IOException {
        try (PDDocument document = new PDDocument()) {
            String[] headers = {"Deskripsi", "Jumlah"};
            float[] colWidths = {350, 150};

            drawPaginatedTable(document, "Laporan Laba Rugi", "Periode: " + periode, headers, colWidths, data);
            document.save(file);
        }
    }
}
