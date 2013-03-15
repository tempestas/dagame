package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import main.Controller;
import main.Controller.GLOBALS;
import main.Controller.KEYS;
import engine.*;

public class Playfield extends Canvas
{
	
	private Image dbImage;
	private Image bgImg;
	private Image title;
	private Graphics dbg;
	private Data data;
	private Font scoreFont;
	private String layoutPath = "";
		
	public Playfield(final Controller controller) {
		
		setSize(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX),controller.getGlobals(GLOBALS.PLAYFILEDSIZEY));
		setMaximumSize(getSize());
		//TODO: Kommentar
		//if (modelNr == 0){
			this.layoutPath = controller.getProjectPath()+"layouts/ZOMBIESETC/";
		//}
		try {
			scoreFont = Font.createFont(Font.TRUETYPE_FONT, new File(layoutPath+"scoreFont.ttf"));
			scoreFont = scoreFont.deriveFont(24f);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.data = controller.getData();
		
		addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
            	if(e.getKeyCode()== KeyEvent.VK_LEFT) {
            		controller.keyHandling(KEYS.LEFT);
            	}
            	if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
            		controller.keyHandling(KEYS.RIGHT);
            	}
            }
            public void keyReleased(KeyEvent e) {
            }
        });
	}
	
	void init() {
		
		
		try {
			bgImg = ImageIO.read(new File(layoutPath+"background.png"));
			title = ImageIO.read(new File(layoutPath+"title.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setVisible(true);
		
		
	}
	
	public void update (Graphics g)  {
    // Initialisierung des DoubleBuffers
    if (dbImage == null)
    {
      dbImage = createImage (this.getSize().width, this.getSize().height);
      dbg = dbImage.getGraphics();
    }

    // Bildschirm im Hintergrund lÃ¶schen
    dbg.setColor (getBackground ());
    dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

    // Auf gelÃ¶schten Hintergrund Vordergrund zeichnen
    dbg.setColor (getForeground());
    paint (dbg);

    // Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
    g.drawImage (dbImage, 0, 0, this);
  }
  
  public void paint (Graphics g)
  {
	  g.drawImage(bgImg, 0, 0, null);
	  if(data.getPlayers().size() == 0) {
		  g.drawImage(title, 75,75,null);
	  }
	  else {
		  g.setColor(Color.CYAN);
		  g.setFont(scoreFont);
		  g.drawString("Score: "+data.getPlayer(0).getScore(),480,50); //TODO: variabel gestalten
	  }
	  for(int i = 0; i < data.getBoxes().size(); i++) {
		  g.drawImage( data.getBox(i).getImage(), data.getBox(i).getCurPos(0),this.getHeight()-data.getBox(i).getCurPos(1)-data.getBox(i).getHeight(), null);
	  }
	  for(int i = 0; i < data.getPlayers().size(); i++) {
		  g.drawImage( data.getPlayer(i).getImage(), data.getPlayer(i).getCurPos(0),this.getHeight()-data.getPlayer(i).getCurPos(1)-data.getPlayer(i).getHeight(), null);
	  }
  }
  
  public void setData(Data data) {
	this.data = data;
  }

}
