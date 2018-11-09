import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.whty.common.excel.PoiExcelUtils;

public class ExcelPoiTest {

	public static void main(String[] args) throws Exception {

		String[] headers = { "到货单号@id", "采购单号@billNum" };

		List<TestBean> list = new ArrayList<TestBean>();

		TestBean bean = new TestBean();
		bean.setBillNum("1");
		bean.setId("bbb");

		list.add(bean);

		bean = new TestBean();
		bean.setBillNum("2");
		bean.setId("ccc");
		list.add(bean);

		// HSSFWorkbook workbook = PoiExcelUtils.createExcel2Export("采购明细",
		// "采购明细", headers,
		// list);
		// PoiExcelUtils poiExcelUtils = new PoiExcelUtils();
		// poiExcelUtils.write2FilePath(workbook, "d:\\aa.xls");

		PoiExcelUtils.createExcel2FilePath("aa", "bb", "d:\\aa.xls", headers, list);
	}

}
