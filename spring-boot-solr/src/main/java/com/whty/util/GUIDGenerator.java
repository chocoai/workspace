package com.whty.util;
/*
 * RandomGUID
 * @version 1.2 01/29/02
 * @author Marc A. Mnich
 *
 * From www.JavaExchange.com, Open Software licensing
 *
 * 01/29/02 -- Bug fix: Improper seeding of nonsecure Random object
 *             caused duplicate GUIDs to be produced.  Random object
 *             is now only created once per JVM.
 * 01/19/02 -- Modified random seeding and added new constructor
 *             to allow secure random feature.
 * 01/14/02 -- Added random function seeding with JVM run time
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/*
 * In the multitude of java GUID generators, I found none that guaranteed
 * randomness. GUIDs are guaranteed to be globally unique by using ethernet
 * MACs, IP addresses, time elements, and sequential numbers. GUIDs are not
 * expected to be random and most often are easy/possible to guess given a
 * sample from a given generator. SQL Server, for example generates GUID that
 * are unique but sequencial within a given instance.
 *
 * GUIDs can be used as security devices to hide things such as files within a
 * filesystem where listings are unavailable (e.g. files that are served up from
 * a Web server with indexing turned off). This may be desireable in cases where
 * standard authentication is not appropriate. In this scenario, the RandomGUIDs
 * are used as directories. Another example is the use of GUIDs for primary keys
 * in a database where you want to ensure that the keys are secret. Random GUIDs
 * can then be used in a URL to prevent hackers (or users) from accessing
 * records by guessing or simply by incrementing sequential numbers.
 *
 * There are many other possiblities of using GUIDs in the realm of security and
 * encryption where the element of randomness is important. This class was
 * written for these purposes but can also be used as a general purpose GUID
 * generator as well.
 *
 * RandomGUID generates truly random GUIDs by using the system's IP address
 * (name/IP), system time in milliseconds (as an integer), and a very large
 * random number joined together in a single String that is passed through an
 * MD5 hash. The IP address and system time make the MD5 seed globally unique
 * and the random number guarantees that the generated GUIDs will have no
 * discernable pattern and cannot be guessed given any number of previously
 * generated GUIDs. It is generally not possible to access the seed information
 * (IP, time, random number) from the resulting GUIDs as the MD5 hash algorithm
 * provides one way encryption.
 *
 * ----> Security of RandomGUID: <----- RandomGUID can be called one of two ways --
 * with the basic java Random number generator or a cryptographically strong
 * random generator (SecureRandom). The choice is offered because the secure
 * random generator takes about 3.5 times longer to generate its random numbers
 * and this performance hit may not be worth the added security especially
 * considering the basic generator is seeded with a cryptographically strong
 * random seed.
 *
 * Seeding the basic generator in this way effectively decouples the random
 * numbers from the time component making it virtually impossible to predict the
 * random number component even if one had absolute knowledge of the System
 * time. Thanks to Ashutosh Narhari for the suggestion of using the static
 * method to prime the basic random generator.
 *
 * Using the secure random option, this class compies with the statistical
 * random number generator tests specified in FIPS 140-2, Security Requirements
 * for Cryptographic Modules, secition 4.9.1.
 *
 * I converted all the pieces of the seed to a String before handing it over to
 * the MD5 hash so that you could print it out to make sure it contains the data
 * you expect to see and to give a nice warm fuzzy. If you need better
 * performance, you may want to stick to byte[] arrays.
 *
 * I believe that it is important that the algorithm for generating random GUIDs
 * be open for inspection and modification. This class is free for all uses.
 *
 *  - Marc
 */

public class GUIDGenerator extends Object {
	private static final Log LOG = LogFactory.getLog(GUIDGenerator.class);
	public String valueBeforeMD5 = "";

	public String valueAfterMD5 = "";

	private static Random myRand;

	private static SecureRandom mySecureRand;

	/*
	 * Static block to take care of one time secureRandom seed. It takes a few
	 * seconds to initialize SecureRandom. You might want to consider removing
	 * this static block or replacing it with a "time since first loaded" seed
	 * to reduce this time. This block will run only once per JVM instance.
	 */

	static {
		mySecureRand = new SecureRandom();
		long secureInitializer = mySecureRand.nextLong();
		myRand = new Random(secureInitializer);
	}

	/*
	 * Default constructor. With no specification of security option, this
	 * constructor defaults to lower security, high performance.
	 */
	public GUIDGenerator() {
		getRandomGUID(false);
	}

	/*
	 * Constructor with security option. Setting secure true enables each random
	 * number generated to be cryptographically strong. Secure false defaults to
	 * the standard Random function seeded with a single cryptographically
	 * strong random number.
	 */
	public GUIDGenerator(boolean secure) {
		getRandomGUID(secure);
	}

	public static String getUUID32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/*
	 * Method to generate the random GUID
	 */
	private void getRandomGUID(boolean secure) {
		MessageDigest md5 = null;
		StringBuffer sbValueBeforeMD5 = new StringBuffer();

		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// e.printStackTrace();
			LOG.error(e.getMessage(), e);
			return;
		}

		try {
			InetAddress inetAddress = InetAddress.getLocalHost();
			long time = System.currentTimeMillis();
			long rand = 0;

			if (secure) {
				rand = mySecureRand.nextLong();
			} else {
				rand = myRand.nextLong();
			}

			// This StringBuffer can be a long as you need; the MD5
			// hash will always return 128 bits. You can change
			// the seed to include anything you want here.
			// You could even stream a file through the MD5 making
			// the odds of guessing it at least as great as that
			// of guessing the contents of the file!
			sbValueBeforeMD5.append(inetAddress.toString());
			sbValueBeforeMD5.append(':');
			sbValueBeforeMD5.append(Long.toString(time));
			sbValueBeforeMD5.append(':');
			sbValueBeforeMD5.append(Long.toString(rand));

			valueBeforeMD5 = sbValueBeforeMD5.toString();
			MessageDigest MD5 = md5;
			MD5.update(valueBeforeMD5.getBytes());

			byte[] array = MD5.digest();
			StringBuffer stringBuffer = new StringBuffer();
			for (int j = 0; j < array.length; j++) {
				int byt = array[j] & 0xFF;
				if (byt < 0x10) {
					stringBuffer.append('0');
				}
				stringBuffer.append(Integer.toHexString(byt));
			}

			valueAfterMD5 = stringBuffer.toString();

		} catch (UnknownHostException e) {
			// e.printStackTrace();
			LOG.error(e.getMessage(), e);
		}
	}

	/*
	 * Convert to the standard format for GUID (Useful for SQL Server
	 * UniqueIdentifiers, etc.) Example: C2FEEEAC-CFCD-11D1-8B05-00600806D9B6
	 */
	public String toString() {
		String raw = valueAfterMD5.toUpperCase();
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(raw.substring(0, 8));
		// sb.append("-");
		stringBuffer.append(raw.substring(8, 12));
		// sb.append("-");
		stringBuffer.append(raw.substring(12, 16));
		// sb.append("-");
		stringBuffer.append(raw.substring(16, 20));
		// sb.append("-");
		stringBuffer.append(raw.substring(20));

		return stringBuffer.toString();
	}

	public static String getGUID() {
		GUIDGenerator myGUID = new GUIDGenerator();
		return myGUID.toString();
	}

	public static String getUUID() {
		return UUID.randomUUID().toString();
	}

	/*
	 * Demonstraton and self test of class
	 */
	public static void main(String args[]) {
		GUIDGenerator myGUID = new GUIDGenerator();
		Date start = new Date();
		/*
		 * System.out.println("RandomGUID=" + GUIDGenerator.getGUID());
		 * System.out.println("RandomGUID=" + myGUID.toString());
		 * System.out.println("UUID=" + UUID.randomUUID().toString());
		 */
		LOG.info("RandomGUID=" + GUIDGenerator.getGUID());
		LOG.info("RandomGUID=" + myGUID.toString());
		LOG.info("UUID=" + UUID.randomUUID().toString());
		LOG.info("UUID=" + getUUID());
		Date end = new Date();
		// System.out.println("Time:" + (end.getTime() - start.getTime()) +
		// "ms.");
		LOG.info("Time:" + (end.getTime() - start.getTime()) + "ms.");
	}

}