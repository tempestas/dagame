import java.util.Timer;
import java.util.TimerTask;


/**
 * 
 * @author Sascha Eckert
 * This class initializes all other instances and starts all subroutines
 */
public class Controller {

	Timer timer = new Timer();
	int interval = 100;
	int delay = 0;
	boolean isStarted = false;

	/**
	 * initializes start sequence
	 */
	void init(){
		if (isStarted) timer.cancel();
		timer.schedule( this.new sTask(this, delay), interval );
		isStarted = true;
	}
	
	/**
	 * will redone after a defined interval
	 */
	void nextStep(){
		delay++;
		timer.schedule( this.new sTask(this, delay), interval );
	}
	
	public static void main(String[] args){
		new Controller().init();
	}



	/**
	 * task for timer ... executes function nextStep() after a certain interval
	 * @author Sascha Eckert
	 *
	 */
	public class sTask extends TimerTask
	{
	
		Controller controller;
		int delay;
		public sTask(Controller controller, int delay) {
			this.controller = controller;
			this.delay = delay;
		}

		@Override
		public void run() {
			System.out.println(delay);
			controller.nextStep();
		}

	}
}