package sounds;

import sun.audio.*;
import java.io.*;

public class Mp3 {

	private String filename;
	public Mp3(String filename){
		this.filename = filename;
	}
	public void play() {
		try {
			InputStream in = new FileInputStream(filename);
    		AudioStream as = new AudioStream(in);
    		AudioPlayer.player.start(as); 
		}
		catch (IOException e) {
    		e.printStackTrace();
    	}          
	}
}
