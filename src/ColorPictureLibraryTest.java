import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ColorPictureLibraryTest {

    /**
     * See if the correct picture got recommended with 6 same colors.
     */

    @Test
    void testSimilarPic1() {
	ColorNameLibrary CML = new ColorNameLibrary();
	ColorPictureLibrary CPL = new ColorPictureLibrary(CML);
	String[] testNameStrings = { "Azure 2", "Peachpuff 3", "epia", "Crimson", "Lightgrey", "Darkolivegreen" };
	ArrayList<String> resultFile = CPL.similarPic(testNameStrings);
	assertTrue(resultFile.contains("Pictures/p14.jpg"));
    }

    /**
     * See if the correct picture got recommended with 5 same colors.
     */

    @Test
    void testSimilarPic2() {
	ColorNameLibrary CML = new ColorNameLibrary();
	ColorPictureLibrary CPL = new ColorPictureLibrary(CML);
	String[] testNameStrings = { "Burlywood", "Cornsilk 2", "Rosybrown", "Antiquewhite 3", "Gray 95", "Azure 2" };
	ArrayList<String> resultFile = CPL.similarPic(testNameStrings);
	assertTrue(resultFile.contains("Pictures/p18.jpg"));
    }

    /**
     * See if picture with only one same color will be recommended (should not be
     * cause if only one color matches, it is not similar enough).
     */

    @Test
    void testSimilarPic3() {
	ColorNameLibrary CML = new ColorNameLibrary();
	ColorPictureLibrary CPL = new ColorPictureLibrary(CML);
	String[] testNameStrings = { "Gray 60", "Sgi Brightgray", "Warmgrey", "Gray 50", "Snow 3", "Azure 3" };
	ArrayList<String> resultFile = CPL.similarPic(testNameStrings);
	assertFalse(resultFile.contains("Pictures/p54.jpg"));
    }

    /**
     * See if picture with only two same colors will be recommended (should not be)
     * when there are five other pictures share three of more colors. 
     * Picture tested : 46
     * 
     * Other similar pictures : 
     * 179 - Light grey, Dark sea green, Gainsboro, Gray 99, Gray 20, Light grey; 
     * 6 - Gainsboro, Silver, Gainsboro, Gray 30, Gray 25, Gray 70;
     * 58 - Gray 20, Sgi Gray 16, Gray 5, Gray 10, Gray 30, Gray 40;
     * 142 - Snow 1, Darkseagreen 4, Gray 20, Gray 25, Gray 30, Gainsboro;
     * 36 - Gray 75, Lightgrey Gray 20, Gray 80, Sgi Gray 16, Gray 5;
     * 
     * Against:
     * 25 - Sgi Lightgray, Lightgrey, Lightyellow 2, Darkolivegreen, Gainsboro, Darkolivegreen 3
     */

    @Test
    void testSimilarPic4() {
	ColorNameLibrary CML = new ColorNameLibrary();
	ColorPictureLibrary CPL = new ColorPictureLibrary(CML);
	String[] testNameStrings = {"Lightgrey","Gray 70","Gainsboro","Gray 30","Gray 20","Sgi Gray 16"};
	ArrayList<String> resultFile = CPL.similarPic(testNameStrings);
	assertTrue(resultFile.contains("Pictures/p6.jpg"));
	assertTrue(resultFile.contains("Pictures/p46.jpg"));
	assertTrue(resultFile.contains("Pictures/p58.jpg"));
	assertFalse(resultFile.contains("Pictures/p25.jpg"));
    }

    /**
     * Test the library has the correct number of record; Please note that we
     * originally have 500 color names, but later on while testing we found that we
     * are over stressing white and grey colors and therefore removed some of them.
     */

    @Test
    void testColorPictureLibrary() {
	ColorNameLibrary CML = new ColorNameLibrary();
	ColorPictureLibrary CPL = new ColorPictureLibrary(CML);
	assertTrue(CPL.getColorToPicture().size() == 203);
    }

}
