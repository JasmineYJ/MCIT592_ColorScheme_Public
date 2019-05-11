import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javafx.scene.control.TableView.ResizeFeatures;

public class ConnectImage {
      private int topN;
      BufferedImage finalImage ;
	
	public BufferedImage getFinalImage() {
		return finalImage;
	}


	public ConnectImage(int topN, String add) {
		
		this.topN = topN;  
		
		
		
		String nameString = add;
		
	    ImageReading image = new ImageReading(nameString);
	    
		int[][] testI = image.getImageRGB();
		int pixelNum = image.getPixelNum();
		
		BufferedImage originalImage = image.getImage();
		
		int width = originalImage.getWidth();
		int height = originalImage.getHeight();
		
		KmeansCalculator k = new KmeansCalculator(pixelNum, topN, testI);
        
		/**
		 * this int[][] result stores the top N color's RGB value. Each row is for one
		 * color 
		 */
		 int[][] result = k.calculateColor();
		
		String[] colorNames = new String[topN];
		
		String tempName;
		ColorNameLibrary cN = new ColorNameLibrary();
		
		for (int i = 0; i < topN; i++) {
			tempName = cN.getColorName(result[i][0], result[i][1], result[i][2]);
			//System.out.println("The color " + i + " is "+ result[i][0]+","+result[i][1]+","+result[i][2]);
			//System.out.println(tempName);
			colorNames[i] = tempName;}
		
		

		
	   DrawColorOfPicture drawColorOfPicture = new DrawColorOfPicture(result, colorNames, topN, false);
	   
	   BufferedImage colorImage = drawColorOfPicture.getProcessedImage();
	   
	   BufferedImage resizedColorImage = drawColorOfPicture.resize(colorImage, width, height/3);
	   
	   finalImage = connectBufferedImage(originalImage, resizedColorImage);
				
				
				
				
			
				
				
				
	}
	
	
	public BufferedImage connectBufferedImage (BufferedImage image1, BufferedImage image2){
		
		int imageWidth = image1.getWidth();
		int image1Height = image1.getHeight();
		int image2Height = image2.getHeight();
		
		BufferedImage target= new BufferedImage(imageWidth, image1Height+image2Height+15, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = (Graphics2D) target.getGraphics();
		
		g.drawImage(image1,0,0,null);
		g.drawImage(image2,0,image1Height+15,null);
		
		g.dispose();
		
		return target;
		
		
		
	}
	
	
	
	
}
	
	
	
	
	
	
	
	

