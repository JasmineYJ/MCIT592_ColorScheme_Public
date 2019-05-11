import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.Collection;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.sun.prism.Graphics;

public class InterfaceDesign {

	private JButton submitButton;
	private JButton existButton;
	private JLabel label1;
	private JLabel label2;
	private JTextField textField1;
	private JTextField textField2;
	private String nameString;
	private String numberOfReconString;
	private int numberOfRecon = 0;

	public InterfaceDesign() {

		createView();

	}

	private void createView() {

		JFrame frame = new JFrame();
		JPanel panel = new JPanel(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.LINE_START;
		frame.getContentPane().add(panel);

		label1 = new JLabel("Please enter the name of your image (e.g. p10.jpg) : ");
		label1.setFont(label1.getFont().deriveFont(12.0f));
		panel.add(label1, c);
		c.gridy++;

		label2 = new JLabel("Number of colors you wish to see (from 5 to 7) : ");
		label2.setFont(label1.getFont().deriveFont(12.0f));
		panel.add(label2, c);
		c.gridy++;

		c.gridx = 1;
		c.gridy = 0;

		textField1 = new JTextField(8);

		panel.add(textField1, c);
		c.gridy++;

		textField2 = new JTextField(8);

		panel.add(textField2, c);
		c.gridy++;

		c.gridx = 0;
		c.gridy = 3;
		submitButton = new JButton("Submit");
		panel.add(submitButton, c);
		c.gridx++;

		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nameString = textField1.getText();

				numberOfReconString = textField2.getText();
				numberOfRecon = Integer.parseInt(numberOfReconString);

				/*
				 * Insert calculation and recommendation class in here
				 */

				int topN = numberOfRecon;

				ImageReading image = new ImageReading(nameString);
				int[][] testI = image.getImageRGB();
				int pixelNum = image.getPixelNum();

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
					// System.out.println("The color " + i + " is "+
					// result[i][0]+","+result[i][1]+","+result[i][2]);
					// System.out.println(tempName);
					colorNames[i] = tempName;
				}

				ColorPictureLibrary cP = new ColorPictureLibrary(cN);

				ArrayList<String> picsAdd = new ArrayList<String>();

				picsAdd.addAll((Collection<? extends String>) cP.similarPic(colorNames));

				DrawColorOfPicture drawColorOfPicture = new DrawColorOfPicture(result, colorNames, topN, true);

				BufferedImage showColor = drawColorOfPicture.getProcessedImage();

				frame.dispose();
				DisplayResult(nameString, showColor, picsAdd, topN);
			}
		});

		existButton = new JButton("Close Program");
		panel.add(existButton, c);
		c.gridx++;

		existButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();

			}
		});

		frame.setSize(new Dimension(500, 120));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Color Recommendation");

		frame.setVisible(true);

	}

	/*
	 * This method is used to create a new frame to display user input image, to
	 * display recommended color and to display recommended party image
	 */

	public void DisplayResult(String inputImageAdd, BufferedImage imageOfColor, ArrayList<String> nameOfPics,
			int topN) {

		// read input image from the file
		ImageReading im = new ImageReading(inputImageAdd);

		BufferedImage userInputImage = im.getImage();

		int[] resizedUserInputImageDimention = CalculateDimention(450, 450, userInputImage);
		// System.out.println(resizedUserInputImageDimention[0]);
		// System.out.println(resizedUserInputImageDimention[1]);
		// call the scaleImage function to process input image and return resized image
		BufferedImage resizedUserInputImage = scaleImage(userInputImage, resizedUserInputImageDimention[0],
				resizedUserInputImageDimention[1]);

		// System.out.println(imageOfColor.getHeight());
		// System.out.println(imageOfColor.getWidth());

		int[] resizedColorOfImageDimention = CalculateDimention(450, 450, imageOfColor);

		BufferedImage resizedColorOfImage = scaleImage(imageOfColor, resizedColorOfImageDimention[0],
				resizedColorOfImageDimention[1]);

		ConnectImage connectImage1 = new ConnectImage(topN, nameOfPics.get(0));
		BufferedImage reconImage1 = connectImage1.getFinalImage();

		ConnectImage connectImage2 = new ConnectImage(topN, nameOfPics.get(1));
		BufferedImage reconImage2 = connectImage2.getFinalImage();

		ConnectImage connectImage3 = new ConnectImage(topN, nameOfPics.get(2));
		BufferedImage reconImage3 = connectImage3.getFinalImage();

		/*
		 * ImageReading im3 = new ImageReading(nameOfPics.get(1)); BufferedImage
		 * reconImage2 = im3.getImage();
		 * 
		 * ImageReading im4 = new ImageReading(nameOfPics.get(2)); BufferedImage
		 * reconImage3 = im4.getImage();
		 * 
		 * ImageReading testImage = new ImageReading("white1.jpg"); BufferedImage
		 * testBufferedImage = testImage.getImage();
		 */

		int[] resizedreconImage1Dimention = CalculateDimention(450, 450, reconImage1);
		int[] resizedreconImage2Dimention = CalculateDimention(450, 450, reconImage2);
		int[] resizedreconImage3Dimention = CalculateDimention(450, 450, reconImage3);
		// int[] resizeTest = CalculateDimention(300, 300, testBufferedImage);

		BufferedImage resizedreconImage1 = scaleImage(reconImage1, resizedreconImage1Dimention[0],
				resizedreconImage1Dimention[1]);
		BufferedImage resizedreconImage2 = scaleImage(reconImage2, resizedreconImage2Dimention[0],
				resizedreconImage2Dimention[1]);
		BufferedImage resizedreconImage3 = scaleImage(reconImage3, resizedreconImage3Dimention[0],
				resizedreconImage3Dimention[1]);

		// BufferedImage testresizeBufferedImage = scaleImage(testBufferedImage,
		// resizeTest[0], resizeTest[1]);

		ImageIcon iconUserInputImage = new ImageIcon(resizedUserInputImage);
		ImageIcon iconColorOfImage = new ImageIcon(resizedColorOfImage);
		ImageIcon iconReconImage1 = new ImageIcon(resizedreconImage1);
		ImageIcon iconReconImage2 = new ImageIcon(resizedreconImage2);
		ImageIcon iconReconImage3 = new ImageIcon(resizedreconImage3);

		// ImageIcon testIcon = new ImageIcon(testresizeBufferedImage);

		// Create frame and panel for UI
		JFrame frame1 = new JFrame();
		JPanel panel = new JPanel();

		// Set Boarder Layout for the panel design
		panel.setLayout(new BorderLayout());
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));

		frame1.getContentPane().add(panel);

		// Design west of the panel

		JPanel panelWest = new JPanel(new BorderLayout(5, 5));
		JPanel panelCener = new JPanel(new BorderLayout(5, 5));
		JPanel panelEast = new JPanel(new BorderLayout(5, 5));

		panel.add(panelWest, BorderLayout.WEST);
		panel.add(panelCener, BorderLayout.CENTER);
		panel.add(panelEast, BorderLayout.EAST);

		JLabel label1 = new JLabel("Your image : ");
		JLabel label2 = new JLabel("Our recommendations : ");

		label1.setFont(label1.getFont().deriveFont(18.0f));
		label2.setFont(label1.getFont().deriveFont(18.0f));

		JLabel labelImage1 = new JLabel();
		JLabel labelRecomCol = new JLabel();

		JLabel recomImage1 = new JLabel();
		JLabel recomImage2 = new JLabel();
		JLabel recomImage3 = new JLabel();

		JLabel testJLabel = new JLabel();

		labelImage1.setIcon(iconUserInputImage);
		labelRecomCol.setIcon(iconColorOfImage);

		recomImage1.setIcon(iconReconImage1);
		recomImage2.setIcon(iconReconImage2);
		recomImage3.setIcon(iconReconImage3);

		// testJLabel.setIcon(testIcon);

		panelWest.add(labelImage1, BorderLayout.CENTER);
		panelWest.add(labelRecomCol, BorderLayout.EAST);
		panelWest.add(label1, BorderLayout.NORTH);

		// panelCener.add(labelImage1,BorderLayout.CENTER);
		panelEast.add(label2, BorderLayout.NORTH);
		panelEast.add(recomImage1, BorderLayout.WEST);
		panelEast.add(recomImage2, BorderLayout.CENTER);
		panelEast.add(recomImage3, BorderLayout.EAST);

		// panel.add(label1,BorderLayout.NORTH);

		frame1.setSize(new Dimension(1200, 600));
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setTitle("Orginal Input Image");

		frame1.setVisible(true);

	}

	/*
	 * This method is used to calculate zoom-in and zoom-out image based on a panel
	 * size
	 * 
	 */

	private int[] CalculateDimention(int panelHeight, int panelWidth, BufferedImage inputImage) {

		// get height and width of the input image
		double inputImageHeight = inputImage.getHeight();
		double inputImageWidth = inputImage.getWidth();

		// calculate a proper percentage for zoom-in or zoom-out of the image
		double percentage1 = panelHeight / inputImageHeight;
		double percentage2 = panelWidth / inputImageWidth;
		// System.out.println(percentage1 );
		// the percentage of zoom-out is based on the smallest percentage between height
		// and width
		double finalpercentage = 1.0;

		if (percentage2 < percentage1) {
			finalpercentage = percentage2;
		} else {
			finalpercentage = percentage1;
		}

		// System.out.println(finalpercentage);

		// calculate the height and width for resized input image
		int updatedInputImageHeight = (int) Math.round(finalpercentage * inputImageHeight);
		int updatedInputImageWidth = (int) Math.round(finalpercentage * inputImageWidth);

		int[] results = new int[2];
		results[0] = updatedInputImageHeight;
		results[1] = updatedInputImageWidth;

		return results;

	}

	/*
	 * ImagePane imagePane = new ImagePane(); ImageList imageList = new ImageList();
	 * 
	 * imageList.getSelectionModel().addListSelectionListener( new
	 * Listener(imageList.getModel(),imagePane)); new ImageFrame(imageList,
	 * imagePane);
	 */

	public BufferedImage scaleImage(BufferedImage img, int height, int width) {
		Image temp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = resizedImage.createGraphics();
		g2d.drawImage(temp, 0, 0, null);
		g2d.dispose();
		return resizedImage;

	}

	public int getNumberOfRecon() {

		return numberOfRecon;
	}

}
/*
 * class Listener implements ListSelectionListener{
 * 
 * private ListModel listModel; private ImagePane imagePane; public
 * Listener(ListModel<Object> listModelIn, ImagePane imagePaneIn) { listModel =
 * listModelIn; imagePane = imagePaneIn;
 * 
 * }
 * 
 * @Override public void valueChanged(ListSelectionEvent e) { // TODO
 * Auto-generated method stub ListSelectionModel lsmListSelectionModel =
 * (ListSelectionModel)e.getSource();
 * 
 * if(!lsmListSelectionModel.isSelectionEmpty()) { int minIndex =
 * lsmListSelectionModel.getMinSelectionIndex(); int maxIndex =
 * lsmListSelectionModel.getMaxSelectionIndex();
 * 
 * for(int i = minIndex; i<= maxIndex; i++) {
 * if(lsmListSelectionModel.isSelectedIndex(i)) {
 * imagePane.SetImage(listModel.getElementAt(i)); } }
 * 
 * }
 * 
 * 
 * }
 * 
 * }
 */
