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
        Point pt1 = new Point(0.0, 0.0);
        Point pt2 = new Point(10.0, 10.0);
        Point pt3 = new Point(20.0, 15.0);
        Point pt4 = new Point(30.0, 30.0);

        Line ln12 = new Line(pt1, pt2);
        Line ln13 = new Line(pt1, pt3);
        Line ln14 = new Line(pt1, pt4);

        Point pt2t12 = ln12.rotate(pt2);
        Point pt3t13 = ln13.rotate(pt3);
        Point pt4t14 = ln14.rotate(pt4);

        System.out.println("pt1: " + pt1 + "\tpt2: " + pt2);
        System.out.println("target heading: " + ln12.angle + "\n");

        Line ln2t3t = new Line(pt2t12, pt3t13);

        System.out.println("pt2: " + pt2 + "\tpt3: " + pt3);
        System.out.println("pt2t12: " + pt2t12 + "\tpt3t13" + pt3t13);
        System.out.println("target heading: " + ln2t3t.angle + "\n");

        Line ln3t4t = new Line(pt3t13, pt4t14);

        System.out.println("pt3: " + pt3 + "\tpt4: " + pt4);
        System.out.println("pt3t13: " + pt3t13 + "\tpt4t14" + pt4t14);
        System.out.println("target heading: " + ln3t4t.angle + "\n");

    }

}