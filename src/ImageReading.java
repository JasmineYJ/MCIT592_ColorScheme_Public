import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is to read the an image. It will read through every pixel and
 * store each pixel's RGB value to an 2d integer array. Also it will pass
 * through this image's pixel numbers to the next class for processing.
 */

public class ImageReading {

	private BufferedImage image = null;
	private String address;
	private int width;
	private int height;
	private int numPixels;
	private int[][] imageRGB;

	public ImageReading(String add) {
		address = add;

		BufferedImage img = null;
		File imageFile = new File(address);
		try {
			img = ImageIO.read(imageFile);
		} catch (IOException e) {
		}
		image = img;
		width = img.getWidth();
		height = img.getHeight();
		numPixels = width * height;

		/**
		 * reading every pixel from an image
		 */
		int[] pixels;
		byte[] pixelByte = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		/**
		 * when reading an image, the default output is a byte array. So store the data
		 * in a byte array first, then convert to an int array saves a lot of run time.
		 * byte ranges from -128 to 127, in total 256 values, same as the range of R,G,B
		 * value.
		 */
		pixels = byteArrayIntoIntArray(pixelByte);

		int[][] RGB = new int[numPixels][3];
		int count = 0;

		/**
		 * insert the each pixel's RGB value to an 2d integer array
		 */
		for (int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				RGB[count][2] = pixels[3 * (width * h + w)];
				RGB[count][1] = pixels[3 * (width * h + w) + 1];
				RGB[count][0] = pixels[3 * (width * h + w) + 2];
				count++;
			}
		}
		imageRGB = RGB;
	}

	/** convert a byte array to an integer array */
	private static int[] byteArrayIntoIntArray(byte[] s) {
		int[] rgb = new int[s.length];
		for (int i = 0; i < s.length; i++) {
			rgb[i] = s[i] & 0xFF;
		}
		return rgb;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getPixelNum() {
		return numPixels;
	}

	public int[][] getImageRGB() {
		return imageRGB;
	}

	public BufferedImage getImage() {
		return image;
	}

}