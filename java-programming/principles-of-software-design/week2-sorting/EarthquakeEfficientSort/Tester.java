package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Sorting at Scale
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/OnNGh/programming-exercise-sorting-at-scale
 *
 * @author Chase Hennion
 * @version 2017-10-28
 */
public class Tester {
	

	public static void main(String[] args) {
		DifferentSorters ds = new DifferentSorters();
		//ds.sortWithCompareTo();
		//ds.sortWithTitleAndDepth();
		//ds.sortByLastWordInTitleThenByMagnitude();
		
		//ds.quizSortWithCompareTo("data/earthQuakeDataDec6sample2.atom", 50 );
		//System.out.println();
		//ds.quizSortByTitleAndDepth( "data/earthQuakeDataDec6sample1.atom", 50 );
		//System.out.println();
		//ds.quizSortByLastWordInTitleThenByMagnitude( "data/earthQuakeDataDec6sample1.atom", 50 );
		
		// Section Quiz
		//ds.quizSortWithCompareTo( "data/quiz/earthQuakeDataWeekDec6sample1.atom", 600 );
		//ds.quizSortByTitleAndDepth( "data/quiz/earthQuakeDataWeekDec6sample2.atom", 500 );
		ds.quizSortByLastWordInTitleThenByMagnitude( "data/quiz/earthQuakeDataWeekDec6sample2.atom", 500 );

	}

}
