import javax.annotation.Resource;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import com.whty.assis.api.model.EbpUserClass;
import com.whty.common.mongodb.MongoDBBaseDao;

/*
 * 将分组数据导出到aam
 */
public class GroupDataExportToAAMiF {

	@Resource(name = "mongoDBBaseDao")
	private static MongoDBBaseDao mongoDBBaseDao;

	public static void main(String[] args) {
		// Query query = new Query(Criteria.where("platformCode").is("888888"));

		// mongoDBBaseDao.

	}

}
