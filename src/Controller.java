import java.util.Timer;
import java.util.TimerTask;



public class Controller {

	Timer timer = new Timer();
	int delay = 0;
	public Controller() {
		timer.schedule( this.new sTask(this, delay), 1000 );
	}

	void init(){
		
	}
	
	void nextStep(){
		delay++;
		timer.schedule( this.new sTask(this, delay), 1000 );
		//timer.schedule( this.new sTask(this), 2000 );
		//timer.schedule( controller.new sTask(controller), 2000 );
	}
	
	public static void main(String[] args){
	   
		
		Controller controller = new Controller();

		// Start in 2 Sekunden
		

		// Start in einer Sekunde dann Ablauf alle 5 Sekunden
		//timer.schedule( task, 1000, 5000 );
	   
	}



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