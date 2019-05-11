import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class DrawColorOfPicture {
	private int[][] result;
	private int whiteHight ;
	private int whiteWidth;
	private int topN;
	private String[] nameOfColor;
	BufferedImage whiteImage;
	BufferedImage rawImage;
	BufferedImage processedImage;

	
   int width;
   int height;

	public DrawColorOfPicture(int[][] result, String[] nameOfColor, int topN , Boolean upRight) {
		this.result= result;
		this.nameOfColor = nameOfColor;
		this.topN = topN;

		ImageReading imageReading = new ImageReading("white.jpg");
		whiteImage  = imageReading.getImage();
		
     		if (upRight == true) {
     			BufferedImage rawImage = resize(whiteImage, 200, 600);
     			processedImage = colorImage1(200, 600, rawImage);
     		   AddTex1(200,600);
		}else {
			BufferedImage rawImage = resize(whiteImage, 600, 200);
 			processedImage = colorImage2(600, 200, rawImage);
 			  AddTex2(600,200);
		}
     		
	}





	public BufferedImage colorImage1(int width, int height, BufferedImage rawImage) {
		
		int heightOfBar = (int) height/topN;
     for (int i = 0; i < topN; i++) {
		for (int j = 0; j < width; j++) {
			for (int k = heightOfBar*i; k < heightOfBar*(i+1)-20; k++) {
				java.awt.Color myColor = new java.awt.Color(result[i][0],result[i][1],result[i][2]);
				rawImage.setRGB(j, k, myColor.getRGB());		

			}

		}

		} 
		return rawImage; 
	}



	public void AddTex1(int width, int height ) {
		
		int heightOfBar = (int) height/topN;
		int xPosition = width/2-50;
		int yPosition = 0;
		
		
		for(int i = 0; i < topN ; i++) {
			
			yPosition = heightOfBar/2+heightOfBar*i;
			Graphics g = processedImage.getGraphics();
			g.setFont(g.getFont().deriveFont(20f));
			g.drawString(nameOfColor[i], xPosition, yPosition);
			g.dispose();
		}
			
		
	}
	
	
	public BufferedImage colorImage2(int width, int height, BufferedImage rawImage) {
		int widthOfBar =  (int) width/topN;
		
		for (int i = 0; i < topN; i++) {
			for (int j = 0; j < height; j++) {
				for (int k = widthOfBar*i; k < widthOfBar*(i+1)-10; k++) {	
					java.awt.Color myColor = new java.awt.Color(result[i][0],result[i][1],result[i][2]);
					rawImage.setRGB(k, j, myColor.getRGB());		

				}

			}

			} 
			return rawImage; 				
	}

	
public void AddTex2(int width, int height ) {
		
		int widthOfBar = (int) width/topN;
		int xPosition = 0;
		int yPosition = height/2-60;
		
		
		for(int i = 0; i < topN ; i++) {
			
			xPosition = widthOfBar/2+widthOfBar*i;
			Graphics g = processedImage.getGraphics();
			Font font = new Font(null, Font.PLAIN,20);
			AffineTransform affineTransform = new AffineTransform();
			affineTransform.rotate(Math.toRadians(90),0,0);
			Font rotatedFont = font.deriveFont(affineTransform);
			g.setFont(rotatedFont);
			g.drawString(nameOfColor[i], xPosition, yPosition);
			g.dispose();
		}
 }

	
	
	
	public  BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
		Image tem= img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
		BufferedImage resultImage = new BufferedImage(newWidth, newHeight,BufferedImage.TYPE_INT_RGB);
		
		Graphics2D g = resultImage.createGraphics();
		g.drawImage(tem,0,0,null);
		g.dispose();
		
		return resultImage;
		
		
	}



 

	public BufferedImage getProcessedImage() {
		return processedImage;
	}
	
	
	
	
	
	
	
	


}

