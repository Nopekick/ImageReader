import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;

import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import com.github.sarxos.webcam.Webcam;

public class PictureOptions extends JPanel {
	
		private BufferedImage img = null;
		private File f;
		private JFileChooser jfc;
		private PicCanvas pc;
		
		public PictureOptions() {
			
			JLabel text1 = new JLabel(); 
				text1.setText("Upload Image Options");
			JButton selectUpload = new JButton("   Select Image   ");
			JButton takeScrnsht = new JButton("Take Screenshot");
			JPanel uploadOptions = new JPanel();
			uploadOptions.add(text1);
			uploadOptions.add(selectUpload);
			uploadOptions.add(takeScrnsht);
			uploadOptions.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			JLabel text2 = new JLabel("Upload Image Options");
				text2.setText("Edit Image Options");
			JButton pixelFy = new JButton(  "Pixel-fy the image  ");
			JButton addWtrMrk = new JButton("Add Custom Watermark");
			JButton download = new JButton("   Download   ");
			JPanel editOptions = new JPanel();
			editOptions.add(text2);
			editOptions.add(addWtrMrk);
			editOptions.add(pixelFy);
			editOptions.add(download);
			editOptions.setBorder(BorderFactory.createLineBorder(Color.WHITE));
			
			setBackground(Color.gray);
			setSize(400, 900);
		
		    add(uploadOptions);
            add(editOptions);
           
            
            	jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
	    		jfc.setDialogTitle("Select an image");
	    		jfc.setAcceptAllFileFilterUsed(false);
	    		FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and GIF images", "png", "gif");
	    		jfc.addChoosableFileFilter(filter);
	    		
	    		selectUpload.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {    
		            		jfc.setVisible(true);
		            		int returnValue = jfc.showOpenDialog(null);
			    	    		if (returnValue == JFileChooser.APPROVE_OPTION) {
			    	    			 try{
			    	    				  f = new File(jfc.getSelectedFile().getPath());
			    	    				  System.out.println(jfc.getSelectedFile().getPath());
			    	    			      img = ImageIO.read(f);
			    	    			      
			    	    			      //ImageIO.write(img, "jpg",new File("C:\\out.jpg"));
			    	    		          //ImageIO.write(img, "png",new File("C:\\out.png"));
			    	    			      
			    	    		          pc.setImg(img);
			    	    			    } catch(IOException io){
			    	    			      System.out.println("Error: "+io);
			    	    			    }
			    	    		
			    	    		}
			    	    		
		            }
		     });

	    		takeScrnsht.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {   
		            	System.out.println("end of screenshot listener");
//			           try {
//			        		System.out.println("end of screenshot listener");
//		            		Webcam webcam = Webcam.getDefault();
//			            	webcam.open();
//			            img = webcam.getImage();
//			            ac.setImg(img);
//			          //ImageIO.write(img, "JPG", new File("test.jpg"));
//			            	System.out.println("end of screenshot listener");
//			           } catch(IOException io) {
//			        	   	System.out.println("Error: "+io);
//			           }
		            }
		     });
	    		
	    		addWtrMrk.addActionListener(new ActionListener() {
		            @Override
		            public void actionPerformed(ActionEvent e) {    
		            		pc.setImg(addTextWatermark(img));
		            		System.out.println("reached add watermark");
		            }
		     });
		
		
			download.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {   
	            		try {
	            		String path = JOptionPane.showInputDialog("Enter a file name");
		            	String imgPath = System.getProperty("user.home") + "/Desktop/"+path+".png";
		            	System.out.println(imgPath);
		            	File file = new File(imgPath);
		            	ImageIO.write(img, "png", file);
	            		} catch(IOException io) {
	            			System.out.println("Did not work");
	            		}
	            }
			});
		}
		
		public void inputCanvas(PicCanvas pc) {
			this.pc = pc;
		}
		
		public BufferedImage addTextWatermark(BufferedImage image) {
				String text = JOptionPane.showInputDialog("Enter watermark text");
		        BufferedImage sourceImage = image;
		        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
		        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
		        g2d.setComposite(alphaChannel);
		        g2d.setColor(Color.BLUE);
		        g2d.setFont(new Font("Arial", Font.BOLD, 150));
		        FontMetrics fontMetrics = g2d.getFontMetrics();
		        Rectangle2D rect = fontMetrics.getStringBounds("Sebek", g2d);
		        int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
		        int centerY = sourceImage.getHeight() / 2;
		        g2d.drawString(text, centerX, centerY);
		        g2d.dispose();
		        return sourceImage;
		}
		
}
