import java.util.TimerTask;

import com.whty.assis.demo.model.Ta_user;

public class UserJFTask extends TimerTask {
	private String taUser;

	public UserJFTask(String taUser) {
		this.taUser = taUser;
	}

	@Override
	public void run() {
		System.out.println(taUser);
		cancel();
	}

}
