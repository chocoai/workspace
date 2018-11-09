import java.util.Timer;
import java.util.TimerTask;

public class TimerDemo {
	public static void main(String[] args) {
		// creating timer task, timer
		TimerTask tasknew = new UserJFTask("111");
		Timer timer = new Timer();

		// scheduling the task at interval
		timer.schedule(tasknew, 100);
	}

	// this method performs the task
	public void run() {
		System.out.println("timer working");
	}
}
