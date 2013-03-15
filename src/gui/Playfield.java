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

/**
 * Responsible for painting the in-game Objects on the game window object
 * 
 * @author Sven Arenz, Marco Stern
 *
 */
public class Playfield extends Canvas
{
	
	private Image dbImage;
	private Image bgImg;
	private Image title;
	private Graphics dbg;
	private Data data;
	private Font scoreFont;
	private String layoutPath = "";
		
	/**
	 * Initializes Images, Score Font and Key Listeners 
	 * @param controller
	 */
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
			bgImg = ImageIO.read(new File(layoutPath+"background.png"));
			title = ImageIO.read(new File(layoutPath+"title.png"));
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
		
		setVisible(true);

	}
	/**
	 * prevents flickering for every repaint process
	 */
	public void update (Graphics g)  {
    // Initialisierung des DoubleBuffers
    if (dbImage == null)
    {
      dbImage = createImage (this.getSize().width, this.getSize().height);
      dbg = dbImage.getGraphics();
    }

    // Bildschirm im Hintergrund l�schen
    dbg.setColor (getBackground ());
    dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

    // Auf gel�schten Hintergrund Vordergrund zeichnen
    dbg.setColor (getForeground());
    paint (dbg);

    // Nun fertig gezeichnetes Bild Offscreen auf dem richtigen Bildschirm anzeigen
    g.drawImage (dbImage, 0, 0, this);
  }
  /**
   * functions which paints all in-game objects onto the screen 
   */
  public void paint (Graphics g)
  {
	  g.drawImage(bgImg, 0, 0, null);
	  if(data.getPlayers().size() == 0) {
		  g.drawImage(title, 70,70,null);
	  }
	  else {
		  g.setColor(Color.CYAN);
		  g.setFont(scoreFont);
		  g.drawString("Score: "+data.getPlayer(0).getScore(),460,50);
	  }
	  for(int i = 0; i < data.getBoxes().size(); i++) {
		  g.drawImage( data.getBox(i).getImage(), data.getBox(i).getCurPos(0),this.getHeight()-data.getBox(i).getCurPos(1)-data.getBox(i).getHeight(), null);
	  }
	  for(int i = 0; i < data.getPlayers().size(); i++) {
		  g.drawImage( data.getPlayer(i).getImage(), data.getPlayer(i).getCurPos(0),this.getHeight()-data.getPlayer(i).getCurPos(1)-data.getPlayer(i).getHeight(), null);
	  }
	  
	  if(data.getPlayer(0).getScore() > 1000) g.drawString("Zu viel Zeit?",250,100);
	  if(data.getPlayer(0).getScore() > 2000) g.drawString("Offensichtlich...",250,130);
	  if(data.getPlayer(0).getScore() > 3000) g.drawString("Ingo ist ein Penis!",230,160);
	  if(data.getPlayer(0).getScore() > 4000) g.drawString("Stirb!!",250,200);
	  
  }
  
  public void setData(Data data) {
	this.data = data;
  }

}
