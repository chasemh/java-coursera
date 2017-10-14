import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public int getNumPoints (Shape s) {
        // Put code here
        int numPoints = 0;
        for( Point p : s.getPoints() ) {
            numPoints++;
        }
        return numPoints;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        // Get the average length of each side of the shape
        // Implement the perimeter algorith
        double avg = 0.0;
        int numPoints = getNumPoints();
        Point previousPoint = s.getLastPoint();

        for( Point currentPoint : s.getPoints() ) {
          avg += previousPoint.distance( currentPoint );
          previousPoint = currentPoint;
        }

        return avg / (double)numPoints;
    }

    public double getLargestSide(Shape s) {

        double largestLength = 0.0;
        Point previousPoint = s.getLastPoint();

        for( Point currentPoint : s.getPoints() ) {
          double length = previousPoint.distance( currentPoint );
          if( length > largetLength ) {
            largetLength = length;
          }
          previousPoint = currentPoint;
        }
        return largestLength;
    }

    public double getLargestX(Shape s) {
        int largestX = 0;

        for( Point currentPoint : s.getPoints() ) {
          int currentX = currentPoint.getX();
          if( currentX > largestX ) {
            largestX = currentPoint.getX();
          }
        }
        return (double)largestX;
    }

    public double getPerimeter(Shape s) {
      double perimeter = 0.0;
      for( Point currentPoint : s.getPoints() ) {
        currentPerimeter += previousPoint.distance( currentPoint );
        previousPoint = currentPoint;
      }
      return perimeter;
    }

    public double getLargestPerimeterMultipleFiles() {
      double largetPerimeter = 0.0;
      DirectoryResource dr = new DirectoryResource();
      for (File f : dr.selectedFiles()) {
          FileResource fr = new FileResource( f );
          Shape s = new Shape( fr );
          double perimeter = getPerimeter( s );
          if( perimeter > largetPerimeter ) {
            largetPerimeter = perimeter;
          }
      }
        return largestPerimeter;
    }

    public File getFileWithLargestPerimeter() {
      File fileWithLargestPerimeter = null;
      double largestPerimeter = 0.0;

      DirectoryResource dr = new DirectoryResource();
      for (File f : dr.selectedFiles()) {
          FileResource fr = new FileResource( f );
          Shape s = new Shape( fr );
          double perimeter = getPerimeter( s );
          if( perimeter > largetPerimeter ) {
            largetPerimeter = perimeter;
            fileWithLargestPerimeter = f;
          }
      }
        return fileWithLargestPerimeter;
    }

    public void testPerimeter() {
      // Load data from a file
      // Create a shape from the data
      // Pass the shape to the various perimeter methods
      // Print the output of these methods
      FileResource shapeFile = new FileResource('example1.txt');
      Shape s = new Shape( shapeFile );
      System.out.println( "Output of getNumPoints: " + getNumPoints( s ) );
      System.out.println( "Output of getAverageLength: " + getAverageLength( s ) );
      System.out.println( "Output of getLargestSide: " + getLargestSide( s ) );
      System.out.println( "Output of getLargestX: " + getLargestX( s ) );
    }

    public void testLargestPerimeterMultipleFiles() {
        System.out.println( "Largest Shape Perimeter: " + getLargestPerimeterMultipleFiles() );
    }

    public void testFileWithLargestPerimeter() {
        System.out.println( "File with Largest Shape Perimeter: " + getFileWithLargestPerimeter() );
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
