package mostactivecookie;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class most_active_cookieTest {

    /**
     * test for function getMostActive
     */
    @Test
    void testGetMostActive() {
        // populate arraylist for input
        ArrayList<String> testInput = new ArrayList<String>();
        testInput.add("AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00");
        testInput.add("SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00");
        testInput.add("5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00");
        testInput.add("AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00");
        // populate arraylist for expected output
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("AtY0laUfhglK3lC7");

        ArrayList<String> actual = most_active_cookie.getMostActive(testInput);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * test for function parseFileForValidDates
     */
    @Test
    void testParseFileForValidDates() {
        String filename = "cookie_log1.csv";
        // set up expected
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00");
        expected.add("SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00");
        expected.add("5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00");
        expected.add("AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00");

        try{
            ArrayList<String> actual = most_active_cookie.parseFileForValidDates(filename, "2018-12-09");
            Assertions.assertEquals(expected, actual);
        }catch(Exception e){
            System.out.println("This should never not run as cookie_log1.csv should always be in root for test purposes");
        }
    }
}