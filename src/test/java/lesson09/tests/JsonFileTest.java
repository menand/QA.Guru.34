package lesson09.tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import lesson09.model.JsonShop;
import lesson09.model.StaffJson;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class JsonFileTest {

    @Test
    public void testParsingJson() {
        try (InputStream in = getClass().getClassLoader().getResourceAsStream("Shop.json")) {
            ObjectMapper mapper = new ObjectMapper();
            JsonShop js = mapper.readValue(in, JsonShop.class);

            assertEquals("menand's shop", js.getTitle());
            assertEquals(5, js.getAddress().getHouse());

            StaffJson[] staff = js.getStaff();
            int sellersCount = 0;
            for (StaffJson staffJson : staff) {
                assertTrue(staffJson.getSalary() > 0);
                assertNotNull(staffJson.getJobTitle());
                if (staffJson.getJobTitle().equals("Seller")) sellersCount++;
            }

            assertTrue(sellersCount >= 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
