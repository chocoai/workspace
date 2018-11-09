import com.whty.assis.api.utils.HttpUtils;
import com.whty.assis.api.utils.ImageUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class TimerTaskTest {

	public static void main(String[] args) throws Exception {
		String path ="http://whty.bj.bcebos.com/hdcourse/off/2018912/1539308269071Koala.jpg";

		byte[] aaa =HttpUtils.getInstance().httpGetByte(path);
		FileOutputStream fileOutputStream = new FileOutputStream("测试.jpg");
		fileOutputStream.write(aaa);
		fileOutputStream.flush();
		fileOutputStream.close();
		System.out.println(aaa);
		File filePicName = new File(path);
		ImageUtil.changeImge(filePicName,192,108);
	}

}
