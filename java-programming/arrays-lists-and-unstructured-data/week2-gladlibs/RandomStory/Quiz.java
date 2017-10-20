package chasemh.java.coursera;

/**
 * Exercise solutions to the Week 2 Quizzes
 *
 * @author Chase Hennion
 * @version 2017-10-20
 */
public class Quiz {
	
	public void quiz1() {
		// 1. How many unique words are in the file likeit.txt?  4932
		// 2. Which word occurs the most often in the file likeit.txt? the
		// 3. How many times does most common word occur in likeit.txt? 692
		WordFrequencies wf = new WordFrequencies();
		wf.tester();
		
		// 4. What is the name of the character with the most speaking parts in the file likeit.txt?  ROSALIND
		// 5.How many speaking parts does character with most speaking parts have in likeit.txt? 200
		// 6. Name one of the five characters in the file likeit.txt who has at least 10 speaking lines, but no more than 15 lines. ADAM
		CharactersInPlay cip = new CharactersInPlay();
		cip.tester();	
		
	}
	
	public void quiz2() {
		// 1. Using the file dnaMystery1, which reading frame results in the most unique codons? 2  CORRECT
		// 2. Using the file dnaMystery1 with reading frame 2, enter the codon that appears exactly 4 times TTA  CORRECT
		// 3. Using the file dnaMystery1 with reading frame 1, two codons occur most frequently—that is, 6 times. Enter one of these two codons. TTA
		//CodonCount cc = new CodonCount();
		//cc.tester();
		
		// 4. Consider the five files with plays by Shakespeare: caesar.txt, hamlet.txt, likeit.txt, macbeth.txt, and romeo.txt.
		// How many words are there that occur in five files? 880   CORRECT
		// 5. How many words are there that each appear in four of the five files? 780  CORRECT
		// 6. In which file does the word “sad” NOT appear? hamlet.txt  CORRECT
	    // 7. In which files does the word “red” appear? (Select all that are correct.) caesar.txt  hamlet.txt  likeit.txt CORRECT
		WordsInFiles wif = new WordsInFiles();
		wif.buildWordFileMap();
		System.out.println( "Num words that appear in five files: " + wif.wordsInNumFiles( 5 ).size() );
		System.out.println( "Num words that appear in four files: " + wif.wordsInNumFiles( 4 ).size() );
		wif.printFilesIn( "sad" );
		wif.printFilesIn( "red" );
		
		
	}
	
	public void  quiz3() {
		// 3. WordFrequencies: How many unique words are in the file errors.txt? 3721
		// 4. Which word occurs the most often in the file errors.txt?  of
		// 5. How many times does the most common word occur?  609
		WordFrequencies wf = new WordFrequencies();
		wf.tester();
		
		// 6. CharactersinPlay: Of the characters who have fewer than 100 lines in the file errors.txt,
		//    which of these characters has the most speaking parts? ADRIANA 79
		// 7. Find the name of the character with the third most speaking parts in the file errors.txt.  ADRIANA
		//    How many speaking parts does this person have?  79
		// 8. How many characters in the file errors.txt have at least 10 speaking parts, but no more than 15 speaking parts? 3
		CharactersInPlay cip = new CharactersInPlay();
		cip.tester();
		
		// 9. CountCodon: dnamystery2: How many unique codons are there if you use a reading frame that starts at position 1? 32
		// 10. dnamystery2: What is the number of occurrences of the codon that occurs the most often using a 
		//     reading frame that starts at position 2?  12
		// 11. dnamystery2 Using a reading frame that starts at position 0, which of the following codons occur 7 times? CAA CAG
		CodonCount cc = new CodonCount();
		cc.tester();
		
		// 12. WordsInFile: Consider the seven files: caesar.txt, confucius.txt, errors.txt, hamlet.txt, likeit.txt, macbeth.txt and romeo.txt
		//     How many words are there that each occur in all seven files? 570
		// 13. How many words are there that each occur in four of the seven files?  826 
		// 14. In which file does the word “sea” NOT appear?  likeit.txt
		// 15. In which of the following files does the word “tree” appear? confucius.txt likeit.txt macbeth.txt romeo.txt
		WordsInFiles wif = new WordsInFiles();
		wif.buildWordFileMap();
		System.out.println( "Num words that appear in seven files: " + wif.wordsInNumFiles( 7 ).size() );
		System.out.println( "Num words that appear in four files: " + wif.wordsInNumFiles( 4 ).size() );
		wif.printFilesIn( "sea" );
		wif.printFilesIn( "tree" );
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Quiz q = new Quiz();
		//q.quiz1();
		//q.quiz2();
		q.quiz3();

	}

}
