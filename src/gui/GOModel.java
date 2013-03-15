package gui;

import java.awt.image.*;
import java.io.*;

import javax.imageio.*;
/**
 * Class which sets width and height of in-game Objects from their images
 * @author Sven Arenz
 *
 */
public class GOModel {

	private int width;
	private int height;
	private BufferedImage img;
	
	public GOModel(String pfad) {
		
		try {
			
			this.img = ImageIO.read(new File(pfad));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.width = img.getWidth();
		this.height = img.getHeight();
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImg() {
		return img;
	}

	public int getWidth() {
		return width;
	}
	
}
