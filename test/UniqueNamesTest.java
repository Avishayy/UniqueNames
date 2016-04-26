import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Avishay on 4/26/2016.
 */
public class UniqueNamesTest {

    @Test
    public void testCountUniqueNames() throws Exception { // BEFORE TESTING, UNCOMMENT LINE 99 at UniqueNames !!!
        assertEquals(1, UniqueNames.countUniqueNames("Deborah", "Egli", "Deborah", "Egli", "Deborah Egli"));
        assertEquals(2, UniqueNames.countUniqueNames("Deborah S", "Egli", "Deborah", "G Egli", "Deborah Egli"));
        assertEquals(1, UniqueNames.countUniqueNames("Deborah", "Egli", "Debbie", "Egli", "Debbie Egli"));
        assertEquals(1, UniqueNames.countUniqueNames("Deborah", "Egni", "Deborah", "Egli", "Deborah Egli"));
        assertEquals(2, UniqueNames.countUniqueNames("Deborah Bra Lo", "Ehni", "Deborah", "S Egli", "Deborah Egli"));
        assertEquals(1, UniqueNames.countUniqueNames("Deborah", "Egli", "Deborah", "Egli", "Deborah Gangsta Man Egli"));
        assertEquals(3, UniqueNames.countUniqueNames("Deborah Bra Lo", "Egni", "Deborah", "S Egli", "Deborah G Egli"));
        assertEquals(1, UniqueNames.countUniqueNames("Deborah Ahi", "Tov Egni", "Deborah", "Egli", "Deborah Ahi Tov Egli"));
        assertEquals(2, UniqueNames.countUniqueNames("Deborah Ahi", "Tov Egni", "Deborah", "Egli", "Deborah AhiTov Egli"));
        assertEquals(1, UniqueNames.countUniqueNames("Deborah S", "Egli", "Deborah", "Egli", "Egli Deborah"));
        assertEquals(2, UniqueNames.countUniqueNames("Michele", "Egli", "Deborah", "Egli", "Michele Egli"));
    }

    @org.junit.Test
    public void testGetHammingDistance() throws Exception {
        assertEquals(3, UniqueNames.getHammingDistance("avishay", "abisluy"));
        assertEquals(2, UniqueNames.getHammingDistance("a", "abc"));
        assertEquals(3, UniqueNames.getHammingDistance("", "abc"));
        assertEquals(0, UniqueNames.getHammingDistance("", ""));
    }

    @Test
    public void testIsANicknameOf() throws Exception {
        assertEquals(true, UniqueNames.isANicknameOf("ron", "aaron"));
        assertEquals(true, UniqueNames.isANicknameOf("debbie", "deborah"));
        assertEquals(false, UniqueNames.isANicknameOf("democrates", "mock"));
    }

    @Test
    public void testIsNameOnCard() throws Exception {
        assertEquals(true, UniqueNames.isNameOnCard("deborah", "egli", "debbie egli"));
        assertEquals(true, UniqueNames.isNameOnCard("debbie", "egli", "debbie egli"));
    }

    @Test
    public void testEqualMiddleNames() throws Exception {
        assertEquals(true, UniqueNames.equalMiddleNames("The Legend", ""));
        assertEquals(true, UniqueNames.equalMiddleNames("", ""));
        assertEquals(false, UniqueNames.equalMiddleNames("Yosi", "Mosi"));

    }

    @Test
    public void testContainsSameFirst() throws Exception {
        assertEquals(true, UniqueNames.containsSameFirst("odo amus", "odell"));
        assertEquals(true, UniqueNames.containsSameFirst("muskei largeds", "mushey"));
    }

    // test cases were not added for all functions, some were just not necessary.
}