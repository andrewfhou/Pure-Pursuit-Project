import java.util.ArrayList;
import java.util.List;

public class PurePursuit {

    static class Point {
        double x, y;

        Point(double x, double y) { this.x = x; this.y = y; }

        public String toString() { return String.format("[%.2f, %.2f]", x, y); }
    }

    static class Line {
        Point pt1;
        Point pt2;
        private double angle;
        private double lookAheadDist;

        Line(Point pt1, Point pt2, double lookAheadDist) {
            this.pt1 = pt1;
            this.pt2 = pt2;
            this.angle = Math.atan2(pt2.y - pt1.y, pt2.x - pt1.x);
            this.lookAheadDist = lookAheadDist;
        }

        double getAngleRad() { return angle; }
        double getAngleDeg() { return Math.toDegrees(angle); }

        double getDesiredHeading(double botHeadingDeg, double botX, double botY) {
            double theta = (Math.atan2(botX - pt1.x, botY - pt1.y)) - getAngleDeg(); //angle from pos to start point, cw w/ respect to x-axis
            double dist = Math.sqrt( ( Math.pow(botX - pt1.x, 2) ) + ( Math.pow(botY - pt1.y, 2) ) );

            double yOffset = Math.sin(theta) * dist;

            return Math.asin(yOffset / lookAheadDist) + angle;
        }

        boolean continue(double botX, double botY) {
          double dist = Math.sqrt( ( Math.pow(botX - pt2.x, 2) ) + ( Math.pow(botY - pt2.y, 2) ) );

          if(dist < lookAheadDist) {
            return false;
          } else {
            return true;
          }
        }
    }

    public static void main(String[] args) {
        List<Point> points = new ArrayList<>();
        List<Line> lines = new ArrayList<>();

        points.add(new Point(0.0,  0.0));
        points.add(new Point(10.0, 10.0));
        points.add(new Point(20.0, 15.0));
        points.add(new Point(30.0, 30.0));
        points.add(new Point(40.0, 35.0));
        points.add(new Point(40.0, 40.0));

        for(int i = 0; i < points.size() - 1; i++) {
            lines.add(new Line(lines.get(i), lines.get(i + 1)));
        }

        System.out.println("Line segment: (0,0) --> (10,10)");
        System.out.println("heading: 80deg | pos: (6,7) | corrected heading: " + lines.get(0).getDesiredHeading(80, 6, 7));
        System.out.println("at end of line: " + lines.get(0).continue(6,7));

        System.out.println("Line segment: (0,0) --> (10,10)");
        System.out.println("heading: 0deg | pos: (9,9) | corrected heading: " + lines.get(1).getDesiredHeading(0, 9, 9));
        System.out.println("at end of line: " + lines.get(0).continue(9,9));
    }

}
