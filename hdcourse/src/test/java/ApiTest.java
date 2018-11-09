
/**
 * @ClassName: SysModularTest2
 * @author: zjd
 * @date: 2018年6月2日 下午5:40:52
 */

import com.whty.assis.manage.dao.HdCodeDao;
import com.whty.assis.manage.dao.HdCodeListenDao;
import com.whty.assis.manage.dao.ModularDao;
import com.whty.assis.manage.model.HdCode;
import com.whty.assis.manage.model.HdCodeListen;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class) // 使用junit4进行测试
@ContextConfiguration(locations = { "classpath:spring/applicationContext*.xml" }) // 加载配置文件
public class ApiTest {

	@Autowired
	private HdCodeListenDao hdCodeListenDao;
	@Autowired
	private HdCodeDao hdCodeDao;

	@Test
	public void test() {
		List<HdCodeListen> hdCodeListens = hdCodeListenDao.getList("qr2h-z6Be-BkFM");
		int sum = 0;
		for (HdCodeListen HdCodeListen : hdCodeListens) {
			sum = sum | HdCodeListen.getCodeId();
		}
		System.out.println(sum);

	}
}
