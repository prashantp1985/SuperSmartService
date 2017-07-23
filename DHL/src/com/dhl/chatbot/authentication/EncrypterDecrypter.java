package com.dhl.chatbot.authentication;

import java.security.Key;
import java.security.Provider;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.dhl.chatbot.exception.DHLException;
import com.dhl.chatbot.logging.TraceLogger;
import com.dhl.chatbot.util.PropertyFileLoader;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class EncrypterDecrypter implements IAuthentication {

	private static String algorithm = null;
	
	private static String PAD = "PAD";
	
	private static byte[] encryptionByteKey = null;
	
	private static Properties encryptionProperties = null;
	
	private static Key key = null;

	private static Cipher cipher = null;
	
	private static int maxInputLength = 8;
	
	private static Map<Character, String> specialCharactersToStringMap = null;
	
	private static Map<String, Character> specialStringToCharactersMap = null;
	
	/**
	 * This field Indicates Delimeter 
	 */
	private static String encrytionDelimeter = new String(","); 
	
	private static EncrypterDecrypter localEncrypter = new EncrypterDecrypter();
	
	
	
	private static void setUp() throws Exception {
		//src/sri/ram/mandir/config/encryption/
		encryptionProperties = PropertyFileLoader.loadEncryptionPropertyByStream();
		prepareSpecialCharactersToStringMap();
		prepareSpecialStringToCharactersMap();
		Provider sunJce = new com.sun.crypto.provider.SunJCE();
		java.security.Security.addProvider(sunJce);
		encryptionByteKey = getEncryptionByteKey();
		algorithm = getAlgorithm();
		key = new SecretKeySpec(encryptionByteKey, algorithm);
//		System.out.println("key   : " + Arrays.toString(encryptionByteKey) + " " + algorithm);
		cipher = Cipher.getInstance(algorithm, sunJce);

	}
	
	/**
	 * This method is used to prepareSpecialStringToCharactersMap
	 * @return void
	 */
	private static void prepareSpecialStringToCharactersMap() {
		specialStringToCharactersMap = new HashMap<String, Character>();
		specialStringToCharactersMap.put("a",'`'); 
		specialStringToCharactersMap.put("b",'~'); 
		specialStringToCharactersMap.put("c",'!'); 
		specialStringToCharactersMap.put("d",'@'); 
		specialStringToCharactersMap.put("e",'#'); 
		specialStringToCharactersMap.put("f",'$'); 
		specialStringToCharactersMap.put("g",'%'); 
		specialStringToCharactersMap.put("h",'^'); 
		specialStringToCharactersMap.put("i",'&'); 
		specialStringToCharactersMap.put("j",'*'); 
		specialStringToCharactersMap.put("k",'('); 
		specialStringToCharactersMap.put("l",')'); 
		specialStringToCharactersMap.put("m",'-'); 
		specialStringToCharactersMap.put("n",'_'); 
		specialStringToCharactersMap.put("o",'`'); 
		specialStringToCharactersMap.put("p",'{'); 
		specialStringToCharactersMap.put("q",'}'); 
		specialStringToCharactersMap.put("r",'['); 
		specialStringToCharactersMap.put("s",']'); 
		specialStringToCharactersMap.put("t",'|'); 
		specialStringToCharactersMap.put("u",';'); 
		specialStringToCharactersMap.put("v",':'); 
		specialStringToCharactersMap.put( "w",'\'');
		specialStringToCharactersMap.put("x",'"'); 
		specialStringToCharactersMap.put( "y",'\\');
		specialStringToCharactersMap.put("z",'<'); 
		specialStringToCharactersMap.put("A",'>'); 
		specialStringToCharactersMap.put("B",'?'); 
		specialStringToCharactersMap.put("C",','); 
		specialStringToCharactersMap.put("D",'.'); 
		specialStringToCharactersMap.put("E",'/'); 
		specialStringToCharactersMap.put("F",' '); 
		
	}

	/**
	 * This method is used to prepareSpecialCharactersMap
	 * @return void
	 */
	private static void prepareSpecialCharactersToStringMap() {
		specialCharactersToStringMap = new HashMap<Character, String>();
		specialCharactersToStringMap.put('`', "a");
		specialCharactersToStringMap.put('~', "b");
		specialCharactersToStringMap.put('!', "c");
		specialCharactersToStringMap.put('@', "d");
		specialCharactersToStringMap.put('#', "e");
		specialCharactersToStringMap.put('$', "f");
		specialCharactersToStringMap.put('%', "g");
		specialCharactersToStringMap.put('^', "h");
		specialCharactersToStringMap.put('&', "i");
		specialCharactersToStringMap.put('*', "j");
		specialCharactersToStringMap.put('(', "k");
		specialCharactersToStringMap.put(')', "l");
		specialCharactersToStringMap.put('-', "m");
		specialCharactersToStringMap.put('_', "n");
		specialCharactersToStringMap.put('`', "o");
		specialCharactersToStringMap.put('{', "p");
		specialCharactersToStringMap.put('}', "q");
		specialCharactersToStringMap.put('[', "r");
		specialCharactersToStringMap.put(']', "s");
		specialCharactersToStringMap.put('|', "t");
		specialCharactersToStringMap.put(';', "u");
		specialCharactersToStringMap.put(':', "v");
		specialCharactersToStringMap.put('\'', "w");
		specialCharactersToStringMap.put('"', "x");
		specialCharactersToStringMap.put('\\', "y");
		specialCharactersToStringMap.put('<', "z");
		specialCharactersToStringMap.put('>', "A");
		specialCharactersToStringMap.put('?', "B");
		specialCharactersToStringMap.put(',', "C");
		specialCharactersToStringMap.put('.', "D");
		specialCharactersToStringMap.put('/', "E");
		specialCharactersToStringMap.put(' ', "F");
		
	}

	/**
	 * This method is used to getAlgorithm
	 * @return
	 * @return String
	 */
	private static String getAlgorithm() {
		return encryptionProperties.getProperty("algorithm").trim();
	}

	/**
	 * This method is used to getEncryptionByteKey
	 * @return
	 * @return byte[]
	 */
	private static byte[] getEncryptionByteKey() {
		String encryptionKey = encryptionProperties.getProperty("key");
		return getByteArray(encryptionKey);
	}
	
	public static byte[] getByteArray(String value) {
		StringTokenizer stringTokenizer = new StringTokenizer(value.trim(), encrytionDelimeter);
		byte[] encryptionBytes = new byte[stringTokenizer.countTokens()];
		int count = 0; 
		while (stringTokenizer.hasMoreTokens()) {
			String token = stringTokenizer.nextToken().trim();
			encryptionBytes[count] = Byte.parseByte(token);
			count++;
		}
		return encryptionBytes;
	}

	private EncrypterDecrypter() {
		try {
			setUp();
		} catch (Exception e) {
			new TraceLogger(EncrypterDecrypter.class).logError(e);
			e.printStackTrace();
		}
	}

	public static EncrypterDecrypter getInstance() {
		return localEncrypter;
	}
	
	public static void main(String[] args)

	throws Exception {

//		setUp();

		/*if (args.length != 1) {

			System.out.println(

			"USAGE: java LocalEncrypter " +

			"[String]");

			System.exit(1);

		}*/

//		byte[] encryptionBytes = null;

//		String input = "`~!@#$%^ &*()-_+|[]{}:;',./<>?";
		String input = "a #*a";

//		System.out.println("Entered: " + input);

		@SuppressWarnings("unused")
		byte[] encryptionBytes = EncrypterDecrypter.getInstance().encrypt(input);

//		byte[] encryptionBytes1 = encrypt("Prashant");

//		System.out.println("Enc: " + encryptionBytes);

//		System.out.println("Recovered: " + LocalEncrypter.getInstance().decrypt(encryptionBytes));

//		if (LocalEncrypter.getInstance().decrypt(encryptionBytes).equals(decrypt(encryptionBytes1))) {
//
//			System.out.println("true: " + true);
//
//		} else {
//
//			System.out.println("false: " + false);
//
//		}

	}

	public byte[] encrypt(String input) throws DHLException {
		byte[] encryptedBytes = null;
		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			input = addPadding(input);
			input = displaceSpecialCharacters(input);
			byte[] inputBytes = new BASE64Decoder().decodeBuffer(input);
			encryptedBytes = cipher.doFinal(inputBytes);
		} catch (Exception e) {
			throw new DHLException(DHLException.ENCRYPTION_DECRYPTION_OPERATION_ERROR_CODE,
					DHLException.ENCRYPTION_DECRYPTION_OPERATION_MESSAGE, e);
		} 
		return encryptedBytes;
		

	}

	/**
	 * This method is used to displaceSpecialCharacters
	 * @param input
	 * @return
	 * @return String
	 */
	private String displaceSpecialCharacters(String input) {
		StringBuffer specialCharacterBuffer = new StringBuffer(input + "SC");
		String inputWithoutPadding = input.substring(0, maxInputLength).trim();
		int totalSpecialCharacters = 0;
		for (int count = 0; count < inputWithoutPadding.length(); count++) {
			String character = specialCharactersToStringMap.get(inputWithoutPadding.charAt(count));
			if (character != null) {
				specialCharacterBuffer.append(character + count);
				totalSpecialCharacters++;
			} 
		}
		specialCharacterBuffer.insert(maxInputLength + 6, totalSpecialCharacters);
		int length = 4 - (specialCharacterBuffer.length() % 4);
		while (length > 0) {
			specialCharacterBuffer.append("/");
			length--;
		}
		
		return specialCharacterBuffer.toString();
	}

	/**
	 * This method is used to addSpecialCharacters
	 * @param input
	 * @return
	 * @return String
	 */
	private String replaceSpecialCharacters(String input) {
		String inputWithSpecialCharacters = input.substring(maxInputLength + 6).trim();
		int totalSpecialCharaters = Integer.parseInt(String.valueOf(inputWithSpecialCharacters.charAt(0)));
		StringBuffer specialCharacterBuffer = new StringBuffer(input);
		if (totalSpecialCharaters != 0) {
			for (int count = 1; count < inputWithSpecialCharacters.length() && totalSpecialCharaters > 0; count++) {
				String string = String.valueOf(inputWithSpecialCharacters.charAt(count));
				String replaceString = String.valueOf(specialStringToCharactersMap.get(string));
				count++;
				int replaceValue = Integer.parseInt(String.valueOf(inputWithSpecialCharacters.charAt(count)));
				specialCharacterBuffer.replace(replaceValue, replaceValue + 1, replaceString);
				totalSpecialCharaters--;
			}
		}
		return specialCharacterBuffer.toString().substring(0, maxInputLength + 4);
	}

	/**
	 * This method is used to addpadding
	 * @param input
	 * @return
	 * @return String
	 */
	private String addPadding(String input) {
		StringBuffer paddedInput = new StringBuffer(input);
		if (input.length() != maxInputLength) {
			int paddingBits = maxInputLength - input.length();
			while (paddingBits > 0) {
				paddedInput.append(" ");
				paddingBits--;
			}
		} 
		paddedInput.append(PAD + (maxInputLength - input.length()));
		return paddedInput.toString();
	}
	
	/**
	 * This method is used to addpadding
	 * @param input
	 * @return
	 * @return String
	 */
	private String removePadding(String input) {
		int paddedBits = Integer.parseInt(String.valueOf(input.charAt(input.length() - 1)));
		if (paddedBits == 0) {
			input = input.substring(0, maxInputLength).trim();
		} else {
			input = input.substring(0, maxInputLength - paddedBits).trim();
		}
		return input;
	}

	public String decrypt(byte[] encryptionBytes) throws DHLException {
		String recovered = null;
		try {
			Key decryptionKey = new SecretKeySpec(key.getEncoded(), key.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, decryptionKey);
			byte[] recoveredBytes = cipher.doFinal(encryptionBytes);
			recovered = new BASE64Encoder().encode(recoveredBytes);  
			recovered = replaceSpecialCharacters(recovered);
			recovered = removePadding(recovered);
		} catch (Exception e) {
			throw new DHLException(DHLException.ENCRYPTION_DECRYPTION_OPERATION_ERROR_CODE,
					DHLException.ENCRYPTION_DECRYPTION_OPERATION_MESSAGE, e);
		} 
		return recovered;
	}
}


class Utils
{
    private static String digits = "0123456789abcdef";
    
    /**
     * Return length many bytes of the passed in byte array as a hex string.
     * 
     * @param data the bytes to be converted.
     * @param length the number of bytes in the data block to be converted.
     * @return a hex representation of length bytes of data.
     */
    public static String toHex(byte[] data, int length)
    {
        StringBuffer  buf = new StringBuffer();
        
        for (int i = 0; i != length; i++)
        {
            int v = data[i] & 0xff;
            
            buf.append(digits.charAt(v >> 4));
            buf.append(digits.charAt(v & 0xf));
        }
        
        return buf.toString();
    }
    
    /**
     * Return the passed in byte array as a hex string.
     * 
     * @param data the bytes to be converted.
     * @return a hex representation of data.
     */
    public static String toHex(byte[] data)
    {
        return toHex(data, data.length);
    }
}
