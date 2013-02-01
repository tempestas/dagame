import java.util.Timer;
import java.util.TimerTask;



public class Controller {

	Timer timer = new Timer();
	int delay = 0;

	void init(){
		timer.schedule( this.new sTask(this, delay), 100 );
	}
	
	void nextStep(){
		delay++;
		timer.schedule( this.new sTask(this, delay), 100 );
		//timer.schedule( this.new sTask(this), 2000 );
		//timer.schedule( controller.new sTask(controller), 2000 );
	}
	
	public static void main(String[] args){
		new Controller().init();
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