import java.util.ArrayList;
import java.util.List;

public class MatrixTest {
    static class Point {
        double x, y;
        Point(double x, double y) { this.x = x; this.y = y; }
        double[] asArray() { return new double[] { x, y }; }
        public String toString() { return String.format("[%.2f, %.2f]", x, y); }
    }

    static class Line {
        Point pt1;
        Point pt2;
        private double angle;
        private double sinAngle;
        private double cosAngle;

        Line(Point pt1, Point pt2) {
            this.pt1 = pt1;
            this.pt2 = pt2;
            this.angle = Math.atan2(pt2.y - pt1.y, pt2.x - pt1.x);
            this.sinAngle = Math.sin(angle);
            this.cosAngle = Math.cos(angle);
        }

        double getAngle() { return angle; }
        double getAngleDeg() { return Math.toDegrees(angle); }

        Point rotate(Point pt) {
            double xTran = pt.x - pt1.x;
            double yTran = pt.y - pt1.y;
            double x, y;
//			if (angle > 0.0) {
            x = (xTran * cosAngle) + (yTran * sinAngle);
            y = -(xTran * sinAngle) + (yTran * cosAngle);
//			} else {
//				x = (xTran * cosAngle) - (yTran * sinAngle);
//				y = (xTran * sinAngle) + (yTran * cosAngle);
//			}
            return new Point(x, y);
        }
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        points.add(new Point( 10.0,   0.0));
        points.add(new Point( 10.0,  10.0));
        points.add(new Point(  0.0,  10.0));
        points.add(new Point( 10.0, -10.0));
        points.add(new Point(  0.0, -10.0));
        points.add(new Point(-10.0,   0.0));

        Point pt1 = new Point(0.0, 0.0);
        Point pt2 = new Point(10.0, 0.0);
        Point pt3 = new Point(0.0, 4.0);
        Line ln = new Line(pt1, pt2);
        Point pt3t = ln.rotate(pt3);
        Point pt2t = ln.rotate(pt2);
        System.out.printf("Point 2=%s;  Point 2t=%s\n", pt2.toString(), pt2t.toString());
        System.out.printf("  Angle=%5.2f, robot: %s,  rotated robot: %s \n", ln.getAngleDeg(), pt3.toString(), pt3t.toString());

//		for (Point pt2a : points) {
//			ln = new Line(pt1, pt2a);
//			pt2t = ln.rotate(pt2a);
//			pt3t = ln.rotate(pt3);
//			System.out.printf("\nPoint 2=%s;  Point 2t=%s\n", pt2a.toString(), pt2t.toString());
//			System.out.printf("  Angle=%5.2f, robot: %s,  rotated robot: %s \n", ln.getAngleDeg(), pt3.toString(), pt3t.toString());
//		}

        pt1 = new Point(100.0, 200.0);
        pt2 = new Point(0.0, 100.0);
        pt3 = new Point(95.0, 195.0);
        ln = new Line(pt1, pt2);
        pt3t = ln.rotate(pt3);
        pt2t = ln.rotate(pt2);
        System.out.printf("\nPoint 2=%s;  Point 2t=%s\n", pt2.toString(), pt2t.toString());
        System.out.printf("  Angle=%5.2f, robot: %s,  rotated robot: %s \n", ln.getAngleDeg(), pt3.toString(), pt3t.toString());
    }

}