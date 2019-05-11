import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class InterfaceDesign {

	private JButton submitButton;
	private JButton existButton;
	private JLabel label1;
	private JLabel label2;
	private JTextField textField1;
	private JTextField textField2;

	public InterfaceDesign() {

		createView();

	}

	private void createView() {
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();

		frame.getContentPane().add(panel);

		label1 = new JLabel("Please enter name of the  image: ");
		panel.add(label1);

		textField1 = new JTextField();
		textField1.setPreferredSize(new Dimension(150, 30));
		panel.add(textField1);

		// label2 = new JLabel("Please enter a number ");
		submitButton = new JButton("Submit");
		panel.add(submitButton);
		submitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String nameString = textField1.getText();

				frame.dispose();
				DisplayResult(nameString);
			}
		});

		existButton = new JButton("Close Program");
		panel.add(existButton);
		existButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.dispose();

			}
		});

		frame.setSize(new Dimension(400, 200));
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Color Recommendation");
		frame.setResizable(false);
		frame.setVisible(true);

	}

	private void DisplayResult(String name) {

		String nameString = name;
		ImageReading img = new ImageReading(nameString);
		ImageIcon icon = new ImageIcon(img.getImage());

		JFrame frame1 = new JFrame();
		JPanel panel = new JPanel();

		frame1.getContentPane().add(panel);
		label2 = new JLabel("Original Image: ");
		panel.add(label2);

		JLabel lbLabel = new JLabel();
		lbLabel.setIcon(icon);
		panel.add(lbLabel);

		frame1.setSize(new Dimension(800, 400));
		frame1.setLocationRelativeTo(null);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setTitle("Color Recommendation");
		frame1.setResizable(false);
		frame1.setVisible(true);
	}

}
