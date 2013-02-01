package sandbox;

import java.awt.*;

import javax.swing.JFrame;


public class Playfield extends Component
{
	int y_pos = 10;   // x - Position des Balles
	int x_pos = 100;  // y - Position des Balles
	int radius = 20;  // Radius des Balles
	int [] x_poses = {10, 50, 80, 100, 150, 250};
	int [] y_poses = {-20, -60, 20, 0, -60, -80};
//	  ArrayList
	int anzObjekte = 6;
	private Image dbImage;
	private Graphics dbg;	
		
	public void update (Graphics g)
  {
    // Initialisierung des DoubleBuffers
    if (dbImage == null)
    {
      dbImage = createImage (this.getSize().width, this.getSize().height);
      dbg = dbImage.getGraphics ();
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
    for(int i = 0; i < anzObjekte; i++) {
    g.fillOval (x_poses[i] - radius / 2, y_poses[i] - radius / 2, radius, radius);
    }
  }

  
  public static void main(String[] args){
	  JFrame frame = new JFrame();
	  Playfield pf = new Playfield();
	  
	  frame.setSize(500, 500);
	  frame.add(pf);
	  
	  frame.show();
  }

}