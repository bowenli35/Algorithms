package MergeSort;

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private LineSegment[] lines;
    private int lineSize;
    private final ArrayList<Point> collinear = new ArrayList<>();

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] points) {
        Point[] toStore = validate(points);
        Point[] toSort = toStore.clone();
        lineSize = 0;
        lines = new LineSegment[4];
        if (points.length > 3) {
            for (Point point : toStore) {
                Arrays.sort(toSort, point.slopeOrder());
                findCollinearPoints(toSort);
            }
        }
    }

    private void findCollinearPoints(Point[] points) {
        Point origin = points[0];
        double slopeBefore = 0.0;
        for (int i = 1; i < points.length; i++) {
            double slopeNow = origin.slopeTo(points[i]);
            if (slopeBefore != slopeNow) checkAdjacent(origin);
            collinear.add(points[i]);
            slopeBefore = slopeNow;
        }
        // the last several points in the array are collinear
        checkAdjacent(origin);
    }

    // Check if any 3 (or more) adjacent points in the sorted order have equal slopes with respect to p.
    // If so, these points, together with p, are collinear.
    private void checkAdjacent(Point p) {
        if (collinear.size() >= 3) {
            enqueue(getEndPoints(p), p);
        }
        collinear.clear();
    }

    private Point[] getEndPoints(Point p) {
        Point[] temp = new Point[collinear.size() + 1];
        temp[0] = p;
        for (int i = 0; i < collinear.size(); i++) {
            temp[i+1] = collinear.get(i);
        }
        Arrays.sort(temp);
        return new Point[]{temp[0], temp[collinear.size()]};
    }


    // the number of line segments
    public int numberOfSegments() {
        return lineSize;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(lines, lineSize);
    }

    // add element to lines
    private void enqueue(Point[] ps, Point p) {
        if (ps[0].compareTo(p) == 0) {
            if (lineSize == lines.length) resize(2 * lineSize);
            lines[lineSize++] = new LineSegment(ps[0], ps[1]);
        }
    }

    // resize a array
    private void resize(int max) {
        lines = Arrays.copyOf(lines, max);
    }

    // check IllegalArgument and return a copy
    private Point[] validate(Point[] points) {
        if (points == null) throw new IllegalArgumentException();
        Point[] copy = points.clone();
        for (Point p : copy) {
            if (p == null) throw new IllegalArgumentException();
        }
        Arrays.sort(copy);
        for (int i = 1; i < points.length; i++) {
            if (copy[i].compareTo(copy[i - 1]) == 0) throw new IllegalArgumentException();
        }
        return copy;
    }


}