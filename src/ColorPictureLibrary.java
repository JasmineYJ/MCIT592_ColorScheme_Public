import java.util.*;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import java.io.*;

/**
 * Recommend similar picture by comparing the color names. This class goes hand
 * in hand with the color name library, we read and store the color names of
 * each picture during the developing process, which then moved to generated
 * file to improve the processing time (i.e. we only process the user input
 * instead of looping through all 200 pictures). Please note that the
 * performance of this program will improve by expanding the picture library, as
 * we have 500 color names versus 200 pictures).
 *
 */

public class ColorPictureLibrary {
    public HashMap<Integer, Picture> colorToPicture;
    public String libraryColorResult = "203PictureLibrary.csv";
    private int numPictures = 203;

    /**
     * Read through stored CSV file which contains 203 pictures and corresponding
     * color names;
     * 
     * @param cNames
     */

    public ColorPictureLibrary(ColorNameLibrary cNames) {
	colorToPicture = new HashMap<Integer, Picture>();

	File file = new File(libraryColorResult);

	try {
	    Scanner scanner = new Scanner(file);
	    Integer i = 1;
	    while (scanner.hasNextLine()) {
		String colorRow = scanner.nextLine();
		String[] columnData = colorRow.split(",");
		String picName = columnData[0];
		String c1 = columnData[1];
		String c2 = columnData[2];
		String c3 = columnData[3];
		String c4 = columnData[4];
		String c5 = columnData[5];
		String c6 = columnData[6];
		Picture newPic = new Picture(i, c1, c2, c3, c4, c5, c6);
		colorToPicture.put(i, newPic);
		i++;
	    }
	} catch (Exception e) {
	    e.printStackTrace(); // Exception handled during testing process
	}

	/**
	 * Retired code: read through the picture library and store the result to the
	 * CSV file. for (Integer i = 1; i <= numPictures; i++) { Picture pi = new
	 * Picture(i, cNames); colorToPicture.put(i, pi); }
	 * printOutColors(colorToPicture); writeColorToCSV(colorToPicture);
	 */

    }

    /**
     * Method that returns a similar picture as user input. During the testing
     * process, we experimented with different pictures and step through this method
     * to see if the returned results are good and what pictures have been
     * considered during this "competition". We also tried with difference number of
     * colors and addressed the following: (1) over emphasis of white / gray colors;
     * (2) picture with only one matched color but was recommended. 
     * 
     * @param userPicture
     * @return
     */

    public ArrayList<String> similarPic(String[] userPicture) {
	Integer ref1 = 0;
	Integer ref2 = 0;
	Integer ref3 = 0;
	int benchmark1 = 1;
	int benchmark2 = 1;
	int benchmark3 = 1;
	int len = userPicture.length;

	ArrayList<String> result = new ArrayList<String>();

	for (Integer i : colorToPicture.keySet()) {
	    Integer count = 0;
	    Picture thisP = colorToPicture.get(i);

	    for (String s : userPicture) {

		if (thisP.getC1().equals(s) | thisP.getC2().equals(s) | thisP.getC3().equals(s)
			| thisP.getC4().equals(s) | thisP.getC5().equals(s) | thisP.getC6().equals(s)) {
		    count++;
		}

	    }

	    if (count > benchmark1) {
		benchmark2 = benchmark1;
		ref2 = ref1;
		ref1 = i;
		benchmark1 = count;
		// System.out.println("benchmark1 now is " + benchmark1);
	    } else if (count > benchmark2) {
		benchmark3 = benchmark2;
		ref3 = ref2;
		ref2 = i;
		benchmark2 = count;
		// System.out.println("benchmark2 now is " + benchmark2);
	    } else if (count > benchmark3) {
		ref3 = i;
		benchmark3 = count;
		// System.out.println("benchmark3 now is " + benchmark3);
	    }
	}

	Picture sP1 = colorToPicture.get(ref1);
	result.add(sP1.getPictureFileName());
	Picture sP2 = colorToPicture.get(ref2);
	result.add(sP2.getPictureFileName());
	Picture sP3 = colorToPicture.get(ref3);
	result.add(sP3.getPictureFileName());

	return result;
    }

    public HashMap<Integer, Picture> getColorToPicture() {
        return colorToPicture;
    }

    /**
     * Retired Code: print out the colors read for testing; public void
     * printOutColors(HashMap<Integer, Picture> pictureLib) { for (Integer i :
     * pictureLib.keySet()) { Picture thisP = pictureLib.get(i);
     * System.out.print(i+","); System.out.print(thisP.getC1() + ",");
     * System.out.print(thisP.getC2() + ","); System.out.print(thisP.getC3() + ",");
     * System.out.print(thisP.getC4() + ","); System.out.print(thisP.getC5() + ",");
     * System.out.println(thisP.getC6()); } }
     * 
     * Method that write the resulted hash map to the CSV file. public void
     * writeColorToCSV(HashMap<Integer, Picture> pictureLib) { File out = new
     * File(libraryColorResult);
     * 
     * try (PrintWriter pw = new PrintWriter(out)) {
     * 
     * // Prints each line in answers for (Integer i : pictureLib.keySet()) {
     * pw.print(i + ","); Picture thisP = pictureLib.get(i); pw.print(thisP.getC1()
     * + ","); pw.print(thisP.getC2() + ","); pw.print(thisP.getC3() + ",");
     * pw.print(thisP.getC4() + ","); pw.print(thisP.getC5() + ","); }
     * 
     * } catch (IOException e) { e.printStackTrace(); System.out.println("Could not
     * write the File out. Check permissions, or contact course staff for help"); }
     * }
     */
}
