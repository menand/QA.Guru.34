package lesson09.tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class ZipFileTest {
    private static final Logger log = LoggerFactory.getLogger(ZipFileTest.class);
    private final ClassLoader cl = getClass().getClassLoader();
    private final String ZIPFILENAME = "FilesExample.zip";

    @Test
    public void zipFileTest() throws Exception {
        try (InputStream in = cl.getResourceAsStream(ZIPFILENAME);
             ZipInputStream zis = new ZipInputStream(in)) {

            assertNotNull(in, "ZIP-файл не найден: " + ZIPFILENAME);

            ZipEntry ze;
            while ((ze = zis.getNextEntry()) != null) {
                String name = ze.getName().toLowerCase();
                log.info("Processing file: {}", name);

                // Читаем весь файл в память перед обработкой
                byte[] fileContent = zis.readAllBytes();

                String fileExtension = name.substring(name.lastIndexOf('.') + 1);
                switch (fileExtension) {
                    case "pdf":
                        checkPdf(new ByteArrayInputStream(fileContent));
                        break;
                    case "csv":
                        checkCsv(new ByteArrayInputStream(fileContent));
                        break;
                    case "xls":
                    case "xlsx":
                        checkExcel(new ByteArrayInputStream(fileContent));
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестный формат файла: " + name);
                }
            }
        }
    }

    private void checkExcel(InputStream is) throws IOException {
        XLS xls = new XLS(is);
        Assertions.assertTrue(xls.excel.getNumberOfSheets() > 0,
                "Excel файл не содержит ни одного листа");
        String cellValue = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();
        Assertions.assertNotNull(cellValue, "Первая ячейка равна null");
        Assertions.assertFalse(cellValue.isEmpty(), "Первая ячейка пустая");
        Assertions.assertEquals("Title", cellValue);
    }

    private void checkPdf(InputStream is) throws IOException {
        PDF pdf = new PDF(is);
        assertTrue(pdf.text.contains("CO-BRANDING AAO-HNSF PATIENT HANDOUTS IS AS EASY AS 1-2-3!"),
                "PDF файл не содержит требуемый текст");
        assertEquals(1, pdf.numberOfPages);
    }

    private void checkCsv(InputStream is) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
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