package gui;

import java.awt.*;
import java.util.ArrayList;
import engine.*;

public class Playfield extends Canvas
{
	
	int anzObjekte = 6;
	private Image dbImage;
	private Graphics dbg;
	private Data data;
		
	public Playfield(int dimX, int dimY) {
		setSize(dimX,dimY);
		setMaximumSize(getSize());
	}
	public void init() {
		
		setBackground(Color.black);
		setVisible(true);
		
		
	}
	public void update (Graphics g)
  {
    // Initialisierung des DoubleBuffers
    if (dbImage == null)
    {
      dbImage = createImage (this.getSize().width, this.getSize().height);
      dbg = dbImage.getGraphics();
    }

    // Bildschirm im Hintergrund löschen
    dbg.setColor (getBackground ());
    dbg.fillRect (0, 0, this.getSize().width, this.getSize().height);

    // Auf gelöschten Hintergrund Vordergrund zeichnen
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
public Data getData() {
	return data;
}
public void setData(Data data) {
	this.data = data;
}

}
