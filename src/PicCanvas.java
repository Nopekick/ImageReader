import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import net.coobird.thumbnailator.Thumbnails;

public class PicCanvas extends JPanel {
	private BufferedImage img;
	
	public PicCanvas() {
		setSize(800, 900);
		setBackground(Color.LIGHT_GRAY);
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	public void setImg(BufferedImage img) {
		this.img = img;
		//this.img = resize(img, 750, 850);
		this.repaint();
	}
	
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		  try {
			  return Thumbnails.of(img).size(newW, newH).asBufferedImage();
		  } catch(IOException io) {
			  return img;
		  }
	}
	
	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    if (img != null)
	    {
	        int x = this.getParent().getWidth()/2 - img.getWidth(this)/2;
	        int y = this.getParent().getHeight()/2 - img.getHeight(this)/2;
	        g.drawImage(img,x,y,this);
	    }
	}
}
