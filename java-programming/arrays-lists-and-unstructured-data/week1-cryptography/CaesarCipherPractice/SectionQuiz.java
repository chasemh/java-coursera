package chasemh.java.coursera;

/**
 * Exercise solutions to the Week 1 final quiz
 *
 * @author Chase Hennion
 * @version 2017-10-19
 */
public class SectionQuiz {

	public static void main(String[] args) {
		
		// Question 1: Encrypt "Can you imagine life WITHOUT the internet AND computers in your pocket?" with key 15
		String q1 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		CaesarCipher cc1 = new CaesarCipher( 15 );
		System.out.println( "Encrypting w/ key = 15: " + q1 );
		System.out.println( "Encrypted String: " + cc1.encrypt( q1 ) + "\n" );
		
		// Question 2: Encrypt "Can you imagine life WITHOUT the internet AND computers in your pocket?" with keys 21 and 8
		String q2 = "Can you imagine life WITHOUT the internet AND computers in your pocket?";
		CaesarCipherTwo cct2 = new CaesarCipherTwo( 21, 8 );
		System.out.println( "Encrypting w/ key1 = 12 and key2 = 8: " + q2 );
		System.out.println( "Encrypted String: " + cct2.encrypt( q2) + "\n" );
		
		// Question 4: Most common word length in errors.txt
		WordLengths wl = new WordLengths();
		System.out.println( "Most common word lenght in errors.txt:" );
		wl.testCountWordLengths();
		
		System.out.println();
		
		// Question 5: Most common word length in manywords.txt
		System.out.println( "Most common word lenght in manywords.txt:" );
		wl.testCountWordLengths();
		
		System.out.println();
		
		// Question 6: Decrypt "Hfs cpwewloj loks cd Hoto kyg Cyy." using keys 14 and 24
		String q6 = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
		CaesarCipherTwo cct6 = new CaesarCipherTwo( 14, 24 );
		System.out.println( "Decrypting w/ key1 = 14 and key2 = 24: " + q6 );
		System.out.println( "Decrypted String: " + cct6.decrypt( q6 ) + "\n" );
		
		// Question 7: Decrypt "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!" without knowing the two keys.
		String q7 = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
		TestCaesarCipherTwo tcct7 = new TestCaesarCipherTwo();
		tcct7.breakCaesarCipher( q7 );
		
		System.out.println();
		
		// Question 8: Decrypt mysteryTwoKeysQuiz.txt without knowing the two keys
		tcct7.breakCaesarCipher();
		
		// Question 9: Two keys needed to decrypt mysteryTwoKeysQuiz.txt
		// See result from question 8

	}

}
