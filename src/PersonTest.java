import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PersonTest {
    private Person person;

    @Before
    public void setUp() {
        person = new Person("000001", "John", "Doe", "Mr.", 1990);
    }

    @Test
    public void testFullName() {
        assertEquals("John Doe", person.fullName());
    }

    @Test
    public void testFormalName() {
        assertEquals("Mr. John Doe", person.formalName());
    }

    @Test
    public void testGetAge() {
        int currentYear = 2023;
        int expectedAge = currentYear - 1990;
        assertEquals(expectedAge, person.getAge());
    }

    @Test
    public void testGetAgeForSpecificYear() {
        int specificYear = 2030;
        int expectedAge = specificYear - 1990;
        assertEquals(expectedAge, person.getAge(specificYear));
    }

    @Test
    public void testToCSVDataRecord() {
        String expectedCSV = "000001,John,Doe,Mr.,1990";
        assertEquals(expectedCSV, person.toCSVDataRecord());
    }
}
