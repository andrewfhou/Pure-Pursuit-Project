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

        double getAngle() { return angle; }
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
        Point refPt = new Point(0.0,  0.0); //reference point
        Point pt2 = new Point(10.0, 10.0);
        Point pt3 = new Point(20.0, 15.0);
        Point pt4 = new Point(30.0, 30.0);

        Point pt2t = pt2.rotateTransform(refPt);
        Point pt3t = pt3.rotateTransform(refPt);
        Point pt4t = pt4.rotateTransform(refPt);

        Line ln12 = new Line(refPt, pt2t);

        System.out.println("refPt: " + refPt + "\tpt2t: " + pt2t);
        System.out.println("target heading: " + ln12.angle + "\n");

        Line ln23 = new Line(pt2t, pt3t);

        System.out.println("pt2t: " + pt2t + "\tpt3t" + pt3t);
        System.out.println("target heading: " + ln23.angle + "\n");

        Line ln34 = new Line(pt3t, pt4t);

        System.out.println("pt3t: " + pt3t + "\tpt4t" + pt4t);
        System.out.println("target heading: " + ln34.angle + "\n");

    }

}