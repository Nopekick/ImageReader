import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame {
	public Frame() {
		setTitle("Image Converter");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1200, 700);
		setResizable(false);
		
		JSplitPane splitPane = new JSplitPane();
		PicCanvas picSpace = new PicCanvas();
		PictureOptions po = new PictureOptions();
		po.inputCanvas(picSpace);
		
		getContentPane().setLayout(new GridLayout()); 
	    getContentPane().add(splitPane);             
	    splitPane.setOrientation(JSplitPane.HORIZONTAL_SPLIT);  
	    splitPane.setDividerLocation(900);                  
	    splitPane.setLeftComponent(picSpace);                  
	    splitPane.setRightComponent(po);     
	    splitPane.setDividerSize(0);
	    splitPane.setEnabled( false );

	    po.setLayout(new BoxLayout(po, BoxLayout.Y_AXIS)); 
	        
	    setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
