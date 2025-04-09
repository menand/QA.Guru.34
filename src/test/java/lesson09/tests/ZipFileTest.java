package lesson09.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ZipFileTest {
    private final ClassLoader cl = getClass().getClassLoader();
    private final String ZIPFILENAME = "FilesExample.zip";

    @Test
    public void zipFileTest() throws Exception {
        try (InputStream in = cl.getResourceAsStream(ZIPFILENAME)) {
            assertNotNull(in, "ZIP-файл не найден: " + ZIPFILENAME);
        }
    }

    @Test
    void checkExcelTest() throws IOException {
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream(ZIPFILENAME))
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xls") || entry.getName().endsWith(".xlsx")) {
                    XLS xls = new XLS(zis);
                    Assertions.assertTrue(xls.excel.getNumberOfSheets() > 0,
                            "Excel файл не содержит ни одного листа");
                    String cellValue = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
                    Assertions.assertNotNull(cellValue, "Первая ячейка равна null");
                    Assertions.assertFalse(cellValue.isEmpty(), "Первая ячейка пустая");
                    Assertions.assertEquals("Title", cellValue);
                }
            }
        }
    }

    @Test
    void checkPdfTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream(ZIPFILENAME))
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf")) {
                    PDF pdf = new PDF(zis);
                    assertTrue(pdf.text.contains("CO-BRANDING AAO-HNSF PATIENT HANDOUTS IS AS EASY AS 1-2-3!"),
                            "PDF файл не содержит требуемый текст");
                    assertEquals(1, pdf.numberOfPages);
                }
            }
        }
    }

    @Test
    void checkCsvTest() throws Exception {
        try (ZipInputStream zis = new ZipInputStream(
                Objects.requireNonNull(cl.getResourceAsStream(ZIPFILENAME))
        )) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader reader = new CSVReader(new InputStreamReader(zis, StandardCharsets.UTF_8));
                    List<String[]> lines = reader.readAll();
                    Assertions.assertTrue(lines.size() > 5,
                            "CSV файл должен содержать более 5 строк. Фактическое количество: " + lines.size());

                    String[] targetRow = lines.stream()
                            .filter(row -> row.length > 0 && row[0].contains("Sep"))
                            .findFirst()
                            .orElse(null);

                    Assertions.assertNotNull(targetRow,
                            "Не найдена строка, содержащая 'Sep' в первом столбце");

                    for (int i = 1; i < targetRow.length; i++) {
                        String cellValue = targetRow[i].trim();
                        Assertions.assertFalse(cellValue.equals("0") || cellValue.equals("0.0"),
                                "Найден ноль в столбце " + i + ". Строка: " + String.join(",", targetRow));
                    }
                }
            }
        }
    }
}