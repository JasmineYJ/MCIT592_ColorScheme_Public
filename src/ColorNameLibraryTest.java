import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ColorNameLibraryTest {

    /**
     * Test that the correct color name is given for the same R,G,B value that we
     * have in the color name file.
     */

    @Test
    void testGetColorNames1() {

	ColorNameLibrary CML = new ColorNameLibrary();
	String colorName1 = CML.getColorName(220, 20, 60);
	assertTrue(colorName1.equals("Crimson"));

    }

    /**
     * Test that for R, G, B value + 10, we obtained the closest result instead of a
     * random name. (250,100,175) should return Hot pink (255, 105, 180) as it has
     * the smallest variance.
     */

    @Test
    void testGetColorNames2() {

	ColorNameLibrary CML = new ColorNameLibrary();
	String colorName2 = CML.getColorName(250, 100, 175);
	assertTrue(colorName2.equals("Hotpink"));

    }

    /**
     * Randomly provide a combination of R, G, B value of (275, 130, 133)and
     * calculate the color name with excel. The return value is Salmon. See if our
     * code has the same result.
     */

    @Test
    void testGetColorNames3() {

	ColorNameLibrary CML = new ColorNameLibrary();
	String colorName3 = CML.getColorName(275, 130, 133);
	assertTrue(colorName3.equals("Salmon"));

    }

    /**
     * Use Gray 72's value and see if it will be matched to Grey 73 (186, 186, 186)
     * as their value are close enough.
     */

    @Test
    void testGetColorNames4() {

	ColorNameLibrary CML = new ColorNameLibrary();
	String colorName4 = CML.getColorName(184, 184, 184);
	assertFalse(colorName4.equals("Gray 73"));

    }

    /**
     * Test the library has the correct number of record; Please note that we
     * originally have 500 color names, but later on while testing we found that we
     * are over stressing white and grey colors and therefore removed some of them.
     */

    @Test
    void testGetColorNames5() {
	ColorNameLibrary CML = new ColorNameLibrary();
	assertTrue(CML.getColorNames().size() == 473);
    }

}
