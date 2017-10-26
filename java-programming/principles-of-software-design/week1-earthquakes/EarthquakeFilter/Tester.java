package chasemh.java.coursera;

/**
 * Exercise solutions for Assignment: Filtering Data
 * https://www.coursera.org/learn/java-programming-design-principles/supplement/yzFlm/programming-exercise-filtering-data
 *
 * @author Chase Hennion
 * @version 2017-10-25
 */
public class Tester {
	
	public void testQuakesWithFilter() {
		EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
		eqc2.quakesWithFilter();
	}
	
	public void testTestMatchAllFilter() {
		EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
		eqc2.testMatchAllFilter();
	}
	
	public void testTestMatchAllFilter2() {
		EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
		eqc2.testMatchAllFilter2();
	}
	
	public void quiz() {
		EarthQuakeClient2 eqc2 = new EarthQuakeClient2();
		eqc2.quakesWithFilter();
		
		System.out.println();
		
		eqc2.testMatchAllFilter();
		
		System.out.println();
		
		eqc2.testMatchAllFilter2();
		
	}

	public static void main(String[] args) {
		Tester t = new Tester();
		//t.testQuakesWithFilter();
		//t.testTestMatchAllFilter();
		//.testTestMatchAllFilter2();
		t.quiz();

	}

}
