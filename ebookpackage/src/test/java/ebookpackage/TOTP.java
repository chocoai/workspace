package ebookpackage;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.UndeclaredThrowableException;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class TOTP {

	private TOTP() {
	}

	/**
	 * This method uses the JCE to provide the crypto algorithm. HMAC computes a
	 * Hashed Message Authentication Code with the crypto hash algorithm as a
	 * parameter.
	 * 
	 * @param crypto
	 *            : the crypto algorithm (HmacSHA1, HmacSHA256, HmacSHA512)
	 * @param keyBytes
	 *            : the bytes to use for the HMAC key
	 * @param text
	 *            : the message or text to be authenticated
	 */
	private static byte[] hmac_sha(String crypto, byte[] keyBytes, byte[] text) {
		try {
			Mac hmac;
			hmac = Mac.getInstance(crypto);
			SecretKeySpec macKey = new SecretKeySpec(keyBytes, "RAW");
			hmac.init(macKey);
			return hmac.doFinal(text);
		} catch (GeneralSecurityException gse) {
			throw new UndeclaredThrowableException(gse);
		}
	}

	/**
	 * This method converts a HEX string to Byte[]
	 * 
	 * @param hex
	 *            : the HEX string
	 * 
	 * @return: a byte array
	 */

	private static byte[] hexStr2Bytes(String hex) {
		// Adding one byte to get the right conversion
		// Values starting with "0" can be converted
		byte[] bArray = new BigInteger("10" + hex, 16).toByteArray();

		// Copy all the REAL bytes, not the "first"
		byte[] ret = new byte[bArray.length - 1];
		for (int i = 0; i < ret.length; i++)
			ret[i] = bArray[i + 1];
		return ret;
	}

	private static final int[] DIGITS_POWER
	// 0 1 2 3 4 5 6 7 8
			= { 1, 10, 100, 1000, 10000, 100000, 1000000, 10000000, 100000000 };
	private static BufferedWriter out;

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */

	public static String generateTOTP(String key, String time, String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA1");
	}

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */

	public static String generateTOTP256(String key, String time, String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA256");
	}

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */

	public static String generateTOTP512(String key, String time, String returnDigits) {
		return generateTOTP(key, time, returnDigits, "HmacSHA512");
	}

	/**
	 * This method generates a TOTP value for the given set of parameters.
	 * 
	 * @param key
	 *            : the shared secret, HEX encoded
	 * @param time
	 *            : a value that reflects a time
	 * @param returnDigits
	 *            : number of digits to return
	 * @param crypto
	 *            : the crypto function to use
	 * 
	 * @return: a numeric String in base 10 that includes
	 *          {@link truncationDigits} digits
	 */

	public static String generateTOTP(String key, String time, String returnDigits, String crypto) {
		int codeDigits = Integer.decode(returnDigits).intValue();
		String result = null;

		// Using the counter
		// First 8 bytes are for the movingFactor
		// Compliant with base RFC 4226 (HOTP)
		while (time.length() < 16)
			time = "0" + time;

		// Get the HEX in a Byte[]
		byte[] msg = hexStr2Bytes(time);
		byte[] k = hexStr2Bytes(key);
		byte[] hash = hmac_sha(crypto, k, msg);

		// put selected bytes into result int
		int offset = hash[hash.length - 1] & 0xf;

		int binary = ((hash[offset] & 0x7f) << 24) | ((hash[offset + 1] & 0xff) << 16)
				| ((hash[offset + 2] & 0xff) << 8) | (hash[offset + 3] & 0xff);

		int otp = binary % DIGITS_POWER[codeDigits];

		result = Integer.toString(otp);
		while (result.length() < codeDigits) {
			result = "0" + result;
		}
		return result;
	}

	/**
	 * 杩藉姞鏂囦欢锛氫娇鐢‵ileWriter
	 * 
	 * @param fileName
	 * @param content
	 */
	public static void method2(String fileName, String content) {
		FileWriter writer = null;
		try {
			// 鎵撳紑涓�釜鍐欐枃浠跺櫒锛屾瀯閫犲嚱鏁颁腑鐨勭浜屼釜鍙傛暟true琛ㄧず浠ヨ拷鍔犲舰寮忓啓鏂囦欢
			writer = new FileWriter(fileName, true);
			writer.write(content);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// Seed for HMAC-SHA1 - 20 bytes
		String seed = "3132333435363738393031323334353637383930";
		// Seed for HMAC-SHA256 - 32 bytes
		String seed32 = "3132333435363738393031323334353637383930" + "313233343536373839303132";
		// Seed for HMAC-SHA512 - 64 bytes
		String seed64 = "3132333435363738393031323334353637383930" + "3132333435363738393031323334353637383930"
				+ "3132333435363738393031323334353637383930" + "31323334";
		long T0 = 0;
		long X = 1800*1000;
		long testTime[] = { 59L, 1111111109L, 1111111111L, 1234567890L, 2000000000L, 20000000000L };

		String steps = "0";
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setTimeZone(TimeZone.getTimeZone("UTC"));

		out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/add2.txt", true)));
		try {
			System.out.println("+---------------+-----------------------+" + "------------------+--------+--------+");
			System.out.println("|  Time(sec)    |   Time (UTC format)   " + "| Value of T(Hex)  |  TOTP  | Mode   |");
			System.out.println("+---------------+-----------------------+" + "------------------+--------+--------+");
			int i = 0;
			while (true) {
				Date date = new Date();
				Long nowTime = date.getTime();
				// long T = (testTime[i] - T0) / X;

				long T = (nowTime - T0) / X;
//				System.out.println("+---------------+-----------------------+" + "------------------+--------+--------+");
//				System.out.println(nowTime-T0);
//				System.out.println(X);
//				System.out.println(T);
				steps = Long.toHexString(T).toUpperCase();
//				System.err.println(steps);
//				System.out.println("+---------------+-----------------------+" + "------------------+--------+--------+");
				while (steps.length() < 16)
					steps = "0" + steps;
				String fmtTime = String.format("%1$-11s", nowTime);
				// String utcTime = df.format(new Date(nowTime * 1000));

				String utcTime = df.format(date);

				StringBuffer sb = new StringBuffer();

				sb.append("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
				sb.append(generateTOTP(seed, steps, "6", "HmacSHA1") + "| SHA1   |").append("\n");
				sb.append("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
//				sb.append(generateTOTP(seed32, steps, "6", "HmacSHA256") + "| SHA256 |").append("\n");
//				sb.append("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
//				sb.append(generateTOTP(seed64, steps, "6", "HmacSHA512") + "| SHA512 |").append("\n");

				System.out.print("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
				System.out.println(generateTOTP(seed, steps, "6", "HmacSHA1") + "| SHA1   |");
				System.out.print("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");
//				System.out.println(generateTOTP(seed32, steps, "6", "HmacSHA256") + "| SHA256 |");
//				System.out.print("|  " + fmtTime + "  |  " + utcTime + "  | " + steps + " |");

//				System.out.println(generateTOTP(seed64, steps, "6", "HmacSHA512") + "| SHA512 |");

				System.out
						.println("+---------------+-----------------------+" + "------------------+--------+--------+");

				Thread.sleep(1000);
				method2("d:/add2.txt", sb.toString());
				// out.write(sb.toString());
				i = i + 1;
			}
		} catch (final Exception e) {
			System.out.println("Error : " + e);
		}
	}
}
