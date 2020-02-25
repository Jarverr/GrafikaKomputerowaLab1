import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Transforms2D extends JPanel {

	private class Display extends JPanel {
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2 = (Graphics2D)g;
			g2.translate(300,300);  // Moves (0,0) to the center of the display.
			
			int whichTransform = transformSelect.getSelectedIndex();
			switch (whichTransform)
			{
			case 1:
				g2.scale(0.5,0.5);
				break;
			case 2:
				g2.rotate(Math.toDegrees(35));
				break;
			case 3: 
				g2.scale(0.5, 1);
				g2.rotate(Math.toRadians(180));
				break;
			case 4:
				g2.scale(0.75, 0.75);
				AffineTransform sat = AffineTransform.getTranslateInstance(150, 0);
			      sat.shear(.5, 0);
			     g2.transform(sat);
			     g2.translate(-100,0);
			     
				break;
			case 5:
				g2.scale(1, 0.5);
				g2.translate(0, -450);
				break;
			case 6:
				g2.scale(0.75, 0.75);
				AffineTransform sat2 = AffineTransform.getTranslateInstance(150, 0);
			      sat2.shear(0, -.5);
			     g2.transform(sat2);
			     g2.translate(-100,0);
			     g2.rotate(Math.toRadians(90));
				break;
			case 7:
				g2.scale(0.5, 1);
				g2.rotate(Math.toRadians(180));
				break;
			case 8:
				g2.translate(0, 100);
				g2.rotate(Math.toRadians(10));
				g2.scale(1,0.5);

				break;
			case 9:
				g2.rotate(Math.toRadians(180));
				AffineTransform sat3 = AffineTransform.getTranslateInstance(150, 0);
			      sat3.shear(0, .5);
			     g2.transform(sat3);
			     g2.scale(0.75, 0.75);
			     g2.translate(-465, 250);
				break;
			}
		     // x coordinates of vertices
			    int radius = 150;
			    // Create a Polygon object
			    Polygon polygon = new Polygon();
			    
			    polygon.addPoint(radius, radius-150);
			    
			    polygon.addPoint((int)(radius *

			      Math.cos(2 * Math.PI / 15)), (int)(radius *

			      Math.sin(2 * Math.PI / 15)));

			    polygon.addPoint((int)(radius *

			      Math.cos(2 * 2 * Math.PI / 15)), (int)(radius *

			      Math.sin(2 * 2 * Math.PI / 15)));

			    polygon.addPoint((int)(radius *

			      Math.cos(3 * 2 * Math.PI / 15)), (int)(radius *

			      Math.sin(3 * 2 * Math.PI / 15)));

			    polygon.addPoint((int)(radius *

			      Math.cos(4 * 2 * Math.PI / 15)), (int)(radius *

			      Math.sin(4 * 2 * Math.PI / 15)));

			    polygon.addPoint((int)(radius *

			      Math.cos(5 * 2 * Math.PI / 15)), (int)(radius *

			      Math.sin(5 * 2 * Math.PI / 15)));

			    polygon.addPoint((int)(radius *

			    	Math.cos(6 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(6 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

					Math.cos(7 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(7 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

					Math.cos(8 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(8 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

			    	Math.cos(9 * 2 * Math.PI / 15)), (int)(radius *

			    	Math.sin(9 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

			    	Math.cos(10 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(10 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

					Math.cos(11 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(11 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

					Math.cos(12 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(12 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

					Math.cos(13 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(13 * 2 * Math.PI / 15)));
			    polygon.addPoint((int)(radius *

					Math.cos(14 * 2 * Math.PI / 15)), (int)(radius *

					Math.sin(14 * 2 * Math.PI / 15)));


			    // Draw the polygon
			    g.drawPolygon(polygon);
	        //g2.drawImage(pic, -200, -150, null); // Draw image with center at (0,0).
		}
	}

	private Display display;
	private BufferedImage pic;
	private JComboBox<String> transformSelect;

	public Transforms2D() throws IOException {
		pic = ImageIO.read(getClass().getClassLoader().getResource("shuttle.jpg"));
		display = new Display();
		display.setBackground(Color.YELLOW);
		display.setPreferredSize(new Dimension(600,600));
		transformSelect = new JComboBox<String>();
		transformSelect.addItem("None");
		for (int i = 1; i < 10; i++) {
			transformSelect.addItem("No. " + i);
		}
		transformSelect.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display.repaint();
			}
		});
		setLayout(new BorderLayout(3,3));
		setBackground(Color.GRAY);
		setBorder(BorderFactory.createLineBorder(Color.GRAY,10));
		JPanel top = new JPanel();
		top.setLayout(new FlowLayout(FlowLayout.CENTER));
		top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
		top.add(new JLabel("Transform: "));
		top.add(transformSelect);
		add(display,BorderLayout.CENTER);
		add(top,BorderLayout.NORTH);
	}


	public static void main(String[] args) throws IOException {
		JFrame window = new JFrame("2D Transforms");
		window.setContentPane(new Transforms2D());
		window.pack();
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		window.setLocation( (screen.width - window.getWidth())/2, (screen.height - window.getHeight())/2 );
		window.setVisible(true);
	}

}