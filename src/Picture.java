/**
 * This class included two constructors one for reading the 200 images during 
 * developing process and one for read out the data stored in the generated file.
 * Given that we have 500 color names (much much more than the number of pictures,
 * including more color clusters increasing the chance we get a better match. Round
 * of iteration was set as 50 during development, so that we made sure the data 
 * stored for picture library is the most accurate.  

 */

public class Picture {
    private String c1;
    private String c2;
    private String c3;
    private String c4;
    private String c5;
    private String c6;
    private String pictureFileName;
    private ColorNameLibrary CML;
    private int numOfColor = 8;

    private int roundOfIteration = 30;

    /**
     * This class was used to read through the 200 pictures pulled
     * To ensure the most accurate results to be stored in our CSV file, the
     * iteration and number of colors are higher than what are used for user input.
     */

    public Picture(int picRef, ColorNameLibrary CNL) {

	pictureFileName = "Pictures/p" + picRef + ".jpg";

	// System.out.println(pictureFileName);

	ImageReading reader = new ImageReading(pictureFileName);
	int[][] colorResult = reader.getImageRGB();
	int pixelNum = reader.getPixelNum();
	KmeansCalculator kC = new KmeansCalculator(pixelNum, numOfColor, colorResult);

	int[][] firstCenter = kC.firstPathCenter();
	int[] label = kC.lablePixels(firstCenter);

	int[][] result = new int[numOfColor][3]; // Each row represents a color;

	int num = 0;
	while (num < roundOfIteration) {
	    result = kC.calculateCenter(label);
	    label = kC.lablePixels(result);
	    num++;
	}

	for (int i1 = 0; i1 < numOfColor; i1++) {
	    int rValue = result[i1][0];
	    int gValue = result[i1][1];
	    int bValue = result[i1][2];
	    String colorName = CNL.getColorName(rValue, gValue, bValue);
	    if (i1 == 1) {c1 = colorName;}
	    if (i1 == 2) {c2 = colorName;}
	    if (i1 == 3) {c3 = colorName;}
	    if (i1 == 4) {c4 = colorName;}
	    if (i1 == 5) {c5 = colorName;}
	    if (i1 == 6) {c6 = colorName;}
	}
    }

    /**
     * This constructor takes the color names that we previously read and stored to
     * the generated file.
     */

    public Picture(Integer i, String c1, String c2, String c3, String c4, String c5, String c6) {
	pictureFileName = "Pictures/p" + i + ".jpg";
	this.c1 = c1;
	this.c2 = c2;
	this.c3 = c3;
	this.c4 = c4;
	this.c5 = c5;
	this.c6 = c6;
    }

    /**
     * This constructor takes the result of user input from main. Slightly different
     * from the picture library as it is from CSV file.
     * 
     * @param userR
     */

    public Picture(int[][] userR) {
	int num = 0;

	for (int i1 = 0; i1 < numOfColor; i1++) {
	    int rValue = userR[i1][0];
	    int gValue = userR[i1][1];
	    int bValue = userR[i1][2];
	    String colorName = CML.getColorName(rValue, gValue, bValue);
	    if (i1 == 1) {c1 = colorName;}
	    if (i1 == 2) {c2 = colorName;}
	    if (i1 == 3) {c3 = colorName;}
	    if (i1 == 4) {c4 = colorName;}
	    if (i1 == 5) {c5 = colorName;}
	    if (i1 == 6) {c6 = colorName;}

	}
    }

    public String getC1() {
	return c1;
    }

    public String getC2() {
	return c2;
    }

    public String getC3() {
	return c3;
    }

    public String getC4() {
	return c4;
    }

    public String getC5() {
	return c5;
    }

    public String getC6() {
	return c6;
    }

    public String getPictureFileName() {
	return pictureFileName;
    }

}