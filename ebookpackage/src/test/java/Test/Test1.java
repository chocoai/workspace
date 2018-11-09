package Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.whty.ebp.manage.dao.BrowserDao;
import com.whty.ebp.manage.model.Browser;

public class Test1 {

	public static void main(String[] args) {
		String s = "2018_02_26_10_54_58";
		System.out.println(s.contains("_"));

		if(s.contains("_")){
			System.out.println("1");
		}else{
			System.out.println("2");
		}
		
		
		String str1 = "abcdefg";
		int result1 = str1.indexOf("a");
		if (result1 != -1) {
			System.out.println("字符串str中包含子串“a”" + result1);
		} else {
			System.out.println("字符串str中不包含子串“a”" + result1);
		}
	}

	public void test1() {
		String aa = null;
		Map<String, Object> p = new HashMap<String, Object>();
		p.put("a", aa);
		System.out.println(p.get("a"));

	}
}
