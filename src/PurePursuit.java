import java.util.ArrayList;
import java.util.List;

public class PurePursuit {
    static class Point {
        double x, y;

        Point(double x, double y) { this.x = x; this.y = y; }

        public String toString() { return String.format("[%.2f, %.2f]", x, y); }

        public Point rotateTransform(Point refPt) {
            double angle = Math.atan2(this.y - refPt.y, this.x - refPt.x);
            double sinAngle = Math.sin(angle);
            double cosAngle = Math.cos(angle);

            double xTran = this.x - refPt.x;
            double yTran = this.y - refPt.y;
            double x, y;

            if (angle > 0.0) { //cw rotation
                x = (xTran * cosAngle) + (yTran * sinAngle);
                y = -(xTran * sinAngle) + (yTran * cosAngle);
            } else { //ccw rotation
                x = (xTran * cosAngle) - (yTran * sinAngle);
                y = (xTran * sinAngle) + (yTran * cosAngle);
            }
            return new Point(x, y);
        }
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

        double getAngleRad() { return angle; }
        double getAngleDeg() { return Math.toDegrees(angle); }

        Point rotate(Point pt) { //todo generalize rotate method --> add 2 param method to Point()?
            double xTran = pt.x - pt1.x;
            double yTran = pt.y - pt1.y;
            double x, y;

			if (angle > 0.0) { //cw rotation
                x = (xTran * cosAngle) + (yTran * sinAngle);
                y = -(xTran * sinAngle) + (yTran * cosAngle);
			} else { //ccw rotation
				x = (xTran * cosAngle) - (yTran * sinAngle);
				y = (xTran * sinAngle) + (yTran * cosAngle);
			}
            return new Point(x, y);
        }
    }

    public static void main(String[] args) {
        int numPts = 6; // number of points not including ref point
        List<Point> points = new ArrayList<>();
        List<Point> transPoints = new ArrayList<>();
        List<Line> lines = new ArrayList<>();

        points.add(new Point(0.0,  0.0));
        points.add(new Point(10.0, 10.0));
        points.add(new Point(20.0, 15.0));
        points.add(new Point(30.0, 30.0));
        points.add(new Point(40.0, 35.0));
        points.add(new Point(40.0, 40.0));

        for(int i = 0; i < numPts; i++) {
            transPoints.add(points.get(i).rotateTransform(points.get(0)));
        }

        for(int i = 0; i < numPts - 1; i++) {
            lines.add(new Line(transPoints.get(i), transPoints.get(i + 1)));
        }

        System.out.println("pt1t: " + transPoints.get(0) + "\tpt2t: " + transPoints.get(1));
        System.out.println("target heading: " + lines.get(0).getAngleDeg() + "\n");

        System.out.println("pt2t: " + transPoints.get(1) + "\tpt3t" + transPoints.get(2));
        System.out.println("target heading: " + lines.get(1).getAngleDeg() + "\n");

        System.out.println("pt3t: " + transPoints.get(2) + "\tpt4t" + transPoints.get(3));
        System.out.println("target heading: " + lines.get(2).getAngleDeg() + "\n");

        System.out.println("pt4t: " + transPoints.get(3) + "\tpt5t" + transPoints.get(4));
        System.out.println("target heading: " + lines.get(3).getAngleDeg() + "\n");

    }

}