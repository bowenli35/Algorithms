package MergeSort;


import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] lines;
    private int index;

    // finds all line segments containing 4 points
    public BruteCollinearPoints(Point[] points) {
        validate(points);
        index = 0;
        Point[] p = points.clone();
        Arrays.sort(p);
        lines = new LineSegment[4];
        if (points.length > 3) {
            for (int i = 0; i < p.length-3; i++) {
                for (int j = i+1; j < p.length-2; j++) {
                    for (int k = j+1; k < p.length-1; k++) {
                        for (int s = k+1; s < p.length; s++) {
                            if (collinear(p[i], p[j], p[k], p[s])) {
                                enqueue(p[i], p[s]);
                            }
                        }
                    }
                }
            }
        }
    }

    private void enqueue(Point p, Point q) {
        if (index == lines.length) resize(2 * index);
        lines[index++] = new LineSegment(p, q);
    }

    private void resize(int max) {
        lines = Arrays.copyOf(lines, max);
    }

    private boolean collinear(Point p1, Point p2, Point p3, Point p4) {
        double l1 = p1.slopeTo(p2);
        double l2 = p1.slopeTo(p3);
        double l3 = p1.slopeTo(p4);
        return l1 == l2 && l1 == l3;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments().length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(lines, index);
    }

    private void validate(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] copy = points.clone();
        for (Point p : copy) {
            if (p == null) throw new IllegalArgumentException();
        }
        Arrays.sort(copy);
        for (int i = 1; i < points.length; i++) {
            if (copy[i].compareTo(copy[i-1]) == 0) throw new IllegalArgumentException();
        }
    }

}
