import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * This class is used to display the calculated topN color from a given image.
 */

public class ColorRectangle extends JPanel {

	int[][] result;
	int width;
	int height;
	int topN;

	/**
	 * This is the constructor of this class, int[][] result is the integer 2d array
	 * which store topN color's RGB value. int width is for the total width of the
	 * color display; int height is for the total height of the color display; int
	 * topN is the same as the the topN from K-means calculator, which is got from
	 * the user.
	 */
	public ColorRectangle(int[][] result, int width, int height, int topN) {
		this.result = result;
		this.width = width;
		this.height = height;
		this.topN = topN;
	}

	/**
	 * Divide the total height to topN, then can get topN smaller rectangles, each
	 * rectangle display a color.
	 */
	public void paintComponent(Graphics g) {
		for (int i = 0; i < result.length; i++) {
			g.setColor(new Color(result[i][0], result[i][1], result[i][2]));
			System.out.println(result[i][0]);
			int eachRectHeight = height / topN;
			/**
			 * 20,20 is to set the starting point. 5 controls the distance between each
			 * color
			 */
			g.fillRect(20, 20 + i * (eachRectHeight + 5), width, eachRectHeight);
		}
	}

	
	
	
	public static void main(String[] args) {
		int topN = 5;
		/**
		 * construct a new ImageReading instance to read image
		 */
		ImageReading image = new ImageReading("test_image.jpg");
		int[][] testI = image.getImageRGB();
		int pixelNum = image.getPixelNum();

		KmeansCalculator k = new KmeansCalculator(pixelNum, topN, testI);

		int[][] result = k.calculateColor();
		/**
		 * construct a rectangle to display the calculated color.
		 */
		ColorRectangle rects = new ColorRectangle(result, 180, 400, topN);
		JFrame frame = new JFrame("Rectangles");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(rects);
		frame.setSize(360, 300);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}