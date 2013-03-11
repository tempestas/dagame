package gui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import main.Controller;
import main.Controller.GLOBALS;
import main.Controller.KEYS;
import engine.*;

public class Playfield extends Canvas
{
	
	private Image dbImage;
	private Graphics dbg;
	private Data data;
		
	public Playfield(final Controller controller) {
		
		setSize(controller.getGlobals(GLOBALS.PLAYFILEDSIZEX),controller.getGlobals(GLOBALS.PLAYFILEDSIZEY));
		setMaximumSize(getSize());
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
		
		setBackground(Color.black);
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
	  g.setColor  (Color.white);
	  for(int i = 0; i < data.getBoxes().size(); i++) {
		  g.fillRect( data.getBox(i).getCurPos(0),this.getHeight()-data.getBox(i).getCurPos(1)-data.getBox(i).getHeight(), data.getBox(i).getWidth(), data.getBox(i).getHeight());
	  }
	  for(int i = 0; i < data.getPlayers().size(); i++) {
		  g.fillRect( data.getPlayer(i).getCurPos(0),this.getHeight()-data.getPlayer(i).getCurPos(1)-data.getPlayer(i).getHeight(), data.getPlayer(i).getWidth(), data.getPlayer(i).getHeight());
	  }
  }
  
  public void setData(Data data) {
	this.data = data;
  }

}
