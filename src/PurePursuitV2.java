import java.util.ArrayList;
import java.util.List;

public class PurePursuitV2 {

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

        double getDesiredHeading(double botX, double botY) {
            double theta = Math.atan2(botY - pt1.y, botX - pt1.x) - getAngleRad(); //i dont understand how atan2 works, clearly
            double dist = Math.sqrt( ( Math.pow(botX - pt1.x, 2) ) + ( Math.pow(botY - pt1.y, 2) ) );

            double yOffset = Math.sin(theta) * dist;

            return Math.toDegrees(Math.asin(yOffset / lookAheadDist)) + getAngleDeg();
        }

        boolean continueRun(double botX, double botY) {
          double dist = Math.sqrt( ( Math.pow(botX - pt2.x, 2) ) + ( Math.pow(botY - pt2.y, 2) ) );

          if(dist < lookAheadDist) {
            return true;
          } else {
            return false;
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
            lines.add(new Line(points.get(i), points.get(i + 1), 2));
        }

        System.out.println("Line segment: " + points.get(0) + " --> " + points.get(1));
        System.out.println("pos: (6,7) | corrected heading: " + lines.get(0).getDesiredHeading(6, 7));

        System.out.println("at end of line: " + lines.get(0).continueRun(6.0, 7.0));

        System.out.println("Line segment: " + points.get(0) + " --> " + points.get(1));
        System.out.println("pos: (9,9) | corrected heading: " + lines.get(0).getDesiredHeading(9, 9));
        System.out.println("at end of line: " + lines.get(0).continueRun(9.0, 9.0));
    }

}
