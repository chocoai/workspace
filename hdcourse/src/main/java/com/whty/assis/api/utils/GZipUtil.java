package com.whty.assis.api.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GZipUtil {
	private static Logger log = LoggerFactory.getLogger(GZipUtil.class);
	// private static Logger log = Logger.getLogger(GZipUtil.class);

	/**
	 * 将字符串压缩后Base64
	 * 
	 * @param primStr
	 *            待加压加密函数
	 * @return
	 * @throws Exception
	 */
	public static String gzipString(String primStr) throws Exception {
		if (primStr == null || primStr.length() == 0) {
			return primStr;
		}
		ByteArrayOutputStream out = null;
		GZIPOutputStream gout = null;
		try {
			out = new ByteArrayOutputStream();
			gout = new GZIPOutputStream(out);
			gout.write(primStr.getBytes("UTF-8"));
			gout.flush();
		} catch (IOException e) {
			log.error("对字符串进行加压加密操作失败：", e);
			return null;
		} finally {
			if (gout != null) {
				try {
					gout.close();
				} catch (IOException e) {
					log.error("对字符串进行加压加密操作，关闭gzip操作流失败：", e);
				}
			}
		}

		return Base64Utils.encode(out.toByteArray());

	}

	/**
	 * 将压缩并Base64后的字符串进行解密解压
	 * 
	 * @param compressedStr
	 *            待解密解压字符串
	 * @return
	 * @throws Exception
	 */
	public static final String ungzipString(String compressedStr) throws Exception {
		if (compressedStr == null) {
			return null;
		}
		ByteArrayOutputStream out = null;
		ByteArrayInputStream in = null;
		GZIPInputStream gin = null;
		String decompressed = null;
		try {
			byte[] compressed = Base64Utils.decode(compressedStr);

			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(compressed);
			gin = new GZIPInputStream(in);
			byte[] buffer = new byte[1024];
			int offset = -1;
			while ((offset = gin.read(buffer)) != -1) {
				out.write(buffer, 0, offset);
			}
			decompressed = out.toString("UTF-8");
		} catch (IOException e) {
			log.error("对字符串进行解密解压操作失败：", e);
			decompressed = null;
		} finally {
			if (gin != null) {
				try {
					gin.close();
				} catch (IOException e) {
					log.error("对字符串进行解密解压操作，关闭压缩流失败：", e);
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("对字符串进行解密解压操作，关闭输入流失败：", e);
				}
			}
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					log.error("对字符串进行解密解压操作，关闭输出流失败：", e);
				}
			}
		}
		return decompressed;
	}

	public static void main(String[] args) throws Exception {
		// String str =
		// "[{\"studentId\":\"cb617982401f4e8bb9d3586b16e4208c\",\"studentName\":\"学生00\",\"cardNumber\":1,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"e3862228b50145b7a3cbed40f2389daa\",\"studentName\":\"学生01\",\"cardNumber\":2,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"5a5c77685bbd4aa8a51107b72a66afbd\",\"studentName\":\"学生02\",\"cardNumber\":3111111111,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"ec90ad5c1a674f5cacf25d48cad9e67c\",\"studentName\":\"学生03\",\"cardNumber\":4,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"f0578cbb9c6f4b7898d82813b6e0e7f2\",\"studentName\":\"学生04\",\"cardNumber\":5,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"724128d24c424cc5af7399fe4b49ad0e\",\"studentName\":\"学生05\",\"cardNumber\":6,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"6be8107f83354a75a330325afd721f6c\",\"studentName\":\"学生06\",\"cardNumber\":7,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"3d1a4ba1182c4ef7b3d8da069586fdb6\",\"studentName\":\"学生07\",\"cardNumber\":8,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"8878230cae4f4fdaa9d54f65e770c78b\",\"studentName\":\"学生08\",\"cardNumber\":9,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"c1bd99cc139f42c4a068be40d56e2489\",\"studentName\":\"学生09\",\"cardNumber\":10,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"50c00f193c20451cb8faf9f33be78bc0\",\"studentName\":\"学生10\",\"cardNumber\":11,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"3bf72b30cc4c4959adfaa08d3bc90642\",\"studentName\":\"学生11\",\"cardNumber\":12,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"f7a60a97a29141b7a77248eda3f426ba\",\"studentName\":\"学生12\",\"cardNumber\":13,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"cfdf970463bb4bd4a92284c92c021bd0\",\"studentName\":\"学生13\",\"cardNumber\":14,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"ed11609d3a9549e5962acb8bfbaa078e\",\"studentName\":\"学生14\",\"cardNumber\":15,\"sexType\":0,\"source\":1,\"groupId\":\"c2a474519bd1479f86b6313235a38be0\"},{\"studentId\":\"dedbfdd61bfe4839b6d1bddb2f91d11f\",\"studentName\":\"学生15\",\"cardNumber\":16,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"e2836335cf4c4ef692cc78ce033aa891\",\"studentName\":\"学生16\",\"cardNumber\":17,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"bb3eab2c8bae46f8890a5325610145d6\",\"studentName\":\"学生17\",\"cardNumber\":18,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"c06c018ee35249f68e76ad1dc55c6fd8\",\"studentName\":\"学生18\",\"cardNumber\":19,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"b320f0ffb59748c5bc6112481a5c9794\",\"studentName\":\"学生19\",\"cardNumber\":20,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"cd2815d83322431ca144dfcab324edb1\",\"studentName\":\"学生20\",\"cardNumber\":21,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"87b33cb41e3843cba1697c3c6a0f3a19\",\"studentName\":\"学生21\",\"cardNumber\":22,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"2201ef9b7d2e4adb93fd4bf633248216\",\"studentName\":\"学生22\",\"cardNumber\":23,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"6fc1c7e48b884923ac5bc5be03a8cf8d\",\"studentName\":\"学生23\",\"cardNumber\":24,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"fe1d83cd723f4310abab3c82aa27b33c\",\"studentName\":\"学生24\",\"cardNumber\":25,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"ba3a884eeb1d4be29e73202c5b7a2128\",\"studentName\":\"学生25\",\"cardNumber\":26,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"f06be8497dd64223a731710356583622\",\"studentName\":\"学生26\",\"cardNumber\":27,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"84ccaf036bb3476baead8aab5ceceef4\",\"studentName\":\"学生27\",\"cardNumber\":28,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"6bd51c24217d4605a2d72cbe4410ae15\",\"studentName\":\"学生28\",\"cardNumber\":29,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"ea57913594834c1591a37ba20262530c\",\"studentName\":\"学生29\",\"cardNumber\":30,\"sexType\":0,\"source\":1,\"groupId\":\"bad7e4fdf16443649e6efff9e80a10aa\"},{\"studentId\":\"176c1bf634fe4d6ba4887c76cd734f31\",\"studentName\":\"学生30\",\"cardNumber\":31,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"9c4fea2d9658489d90dfe1ecce9377f5\",\"studentName\":\"学生31\",\"cardNumber\":32,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"d35f248659c74cb995985d6b009d5634\",\"studentName\":\"学生32\",\"cardNumber\":33,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"38a6d379f69c483eba4345e069962f49\",\"studentName\":\"学生33\",\"cardNumber\":34,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"302417b9191245559bbc84a63291e3fd\",\"studentName\":\"学生34\",\"cardNumber\":35,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"0455026ab39e4c4ab6ed23c17805f9ac\",\"studentName\":\"学生35\",\"cardNumber\":36,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"a4112499fbd8457bb2f1e2050ed82831\",\"studentName\":\"学生36\",\"cardNumber\":37,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"c2891adb7c4a4780872c9c40935884cb\",\"studentName\":\"学生37\",\"cardNumber\":38,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"7cfad6560f60488284ba4b8ab98c7610\",\"studentName\":\"学生38\",\"cardNumber\":39,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"4e1438aa401846ddbc623cc2907192c1\",\"studentName\":\"学生39\",\"cardNumber\":40,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"ea849f92dc3948428499f0095d144dbe\",\"studentName\":\"学生40\",\"cardNumber\":41,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"1d0f289d7edb4af4be960f6815f9d243\",\"studentName\":\"学生41\",\"cardNumber\":42,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"c43d9adfe21c43cd906c0518f92cc7dd\",\"studentName\":\"学生42\",\"cardNumber\":43,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"fc8a94a1cc1d46f49eab9b1af4fb5401\",\"studentName\":\"学生43\",\"cardNumber\":44,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"55dae047243246f8aa52fdb063c67fb6\",\"studentName\":\"学生44\",\"cardNumber\":45,\"sexType\":0,\"source\":1,\"groupId\":\"a79e06ac18354a84abe355352250a861\"},{\"studentId\":\"275851c869374e1f873d6b1a716de6b7\",\"studentName\":\"学生45\",\"cardNumber\":46,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"81feed3445b849db94e70d437dcf6f11\",\"studentName\":\"学生46\",\"cardNumber\":47,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"d4c2f25405944d74826466e57cd26069\",\"studentName\":\"学生47\",\"cardNumber\":48,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"57098599c04b4c02a03fac085c0200b7\",\"studentName\":\"学生48\",\"cardNumber\":49,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"13eb91fb8c1d46f594498b3240cae6ea\",\"studentName\":\"学生49\",\"cardNumber\":50,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"45a4be4e241d40c489964a7205fbc338\",\"studentName\":\"学生50\",\"cardNumber\":51,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"fd4f969c55334faaa34d694bea17c560\",\"studentName\":\"学生51\",\"cardNumber\":52,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"b2b1f410d51945daaab4b5ca9b5cd203\",\"studentName\":\"学生52\",\"cardNumber\":53,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"7b08a9a3c5704a0ab94909c68d953dd9\",\"studentName\":\"学生53\",\"cardNumber\":54,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"7bd161c552144dc39f36b0535d71f51a\",\"studentName\":\"学生54\",\"cardNumber\":55,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"f1449229cc3342feb3388d53d963140f\",\"studentName\":\"学生55\",\"cardNumber\":56,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"1ee0de2fb7cf4cbf9cfd08396a6db0c6\",\"studentName\":\"学生56\",\"cardNumber\":57,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"79ce615745e34fe3b1243322c32e73b9\",\"studentName\":\"学生57\",\"cardNumber\":58,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"649c86eca52a4f7eb47d7d333959a18c\",\"studentName\":\"学生58\",\"cardNumber\":59,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"},{\"studentId\":\"fe462604e28941769bb2d045a6706624\",\"studentName\":\"学生59\",\"cardNumber\":60,\"sexType\":0,\"source\":1,\"groupId\":\"ef8d585cb75d4b1c9d1989e7360dc680\"}]";
		// System.out.println(str.length());

		// System.out.println(gzipString(str).length());
		// System.out.println(ungzipString(gzipString(str)));

		System.out.println(gzipString("111"));

		// byte[] bb = new String("张").getBytes("UTF-8");
		//
		// byte[] cc = new String("11").getBytes();

		System.out.println(1);

		// System.out.println(Base64Utils.encode(new
		// String("111").getBytes("utf-8")));

	}
}
