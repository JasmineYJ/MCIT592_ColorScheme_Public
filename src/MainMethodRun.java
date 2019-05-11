
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.sun.webkit.ThemeClient;

public class MainMethodRun {

    public static void main(String[] args) {

	InterfaceDesign uInterfaceDesign = new InterfaceDesign();

    }
}

/**
 * Retired code that we used during development process are cited out.
 */
/**
 * construct a new ImageReading instance to read image
 */

/*
 * ImageReading imageReading = new ImageReading("white.jpg"); BufferedImage
 * whiteImage = imageReading.getImage(); ArrayList<String> addArrayList = new
 * ArrayList<String>(); addArrayList.add("Pictures/p1.jpg");
 * addArrayList.add("Pictures/p10.jpg"); addArrayList.add("Pictures/p100.jpg");
 * uInterfaceDesign.DisplayResult("test_image.jpg", whiteImage, addArrayList);
 */

/*
 * 
 * int topN = 6;
 * 
 * ImageReading image = new ImageReading("test_image.jpg"); int[][] testI =
 * image.getImageRGB(); int pixelNum = image.getPixelNum();
 * 
 * KmeansCalculator k = new KmeansCalculator(pixelNum, topN, testI);
 */

/**
 * this int[][] result stores the top N color's RGB value. Each row is for one
 * color
 * 
 * int[][] result = k.calculateColor(); the following few lines are for test
 * only for (int i = 0; i < topN; i++) { System.out.print(result[i][0] + ",");
 * System.out.print(result[i][1] + ","); System.out.print(result[i][2]);
 * System.out.println();
 * 
 */

/**
 * the following block is to get name for each color in the int[][] result
 */

/*
 * String[] colorNames = new String[topN];
 * 
 * String tempName; ColorNameLibrary cN = new ColorNameLibrary();
 * 
 * for (int i = 0; i < topN; i++) { tempName = cN.getColorName(result[i][0],
 * result[i][1], result[i][2]); System.out.println("The color " + i + " is "+
 * result[i][0]+","+result[i][1]+","+result[i][2]);
 * //System.out.println(tempName); colorNames[i] = tempName; } // the following
 * few lines are for test only for(String s: colorNames) {
 * System.out.println(s); }
 * //System.out.println("The color results are "+Arrays.toString(colorNames)+"."
 * );
 * //System.out.println("Compiling the 203 picture library. May take 10-15 Min."
 * );
 * 
 * ColorPictureLibrary(cN);
 * 
 * // the following lines are used for test the return of similar pic;
 * System.out.println(cP.similarPic(colorNames));
 * 
 * //DrawColorOfPicture drawColorOfPicture = new DrawColorOfPicture(result,
 * colorNames, topN, false); //BufferedImage showBufferedImage =
 * drawColorOfPicture.getProcessedImage(); ConnectImage connectImage = new
 * ConnectImage(topN, "test.jpg"); BufferedImage showBufferedImage =
 * connectImage.getFinalImage();
 * 
 * ImageIcon iconUserInputImage = new ImageIcon(showBufferedImage); JLabel
 * labelImage1 = new JLabel(); labelImage1.setIcon(iconUserInputImage); JFrame
 * frame = new JFrame("Rectangles");
 * 
 * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); frame.add(labelImage1);
 * frame.setSize(385, 600); frame.setLocationRelativeTo(null);
 * frame.setVisible(true);
 */