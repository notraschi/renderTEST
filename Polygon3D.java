import java.awt.Polygon;
import java.awt.Color;
import java.util.Arrays;

public class Polygon3D extends Polygon{
    
    Point3D[] points;
    Color color;

    public Polygon3D(int xpoints[], int ypoints[], int npoints, Point3D[] points, Color c) {

        if (npoints > xpoints.length || npoints > ypoints.length) {
            throw new IndexOutOfBoundsException("npoints > xpoints.length || "+
                                                "npoints > ypoints.length");
        }
        if (npoints < 0) {
            throw new NegativeArraySizeException("npoints < 0");
        }
        this.points = points;
        this.npoints = npoints;
        this.xpoints = Arrays.copyOf(xpoints, npoints);
        this.ypoints = Arrays.copyOf(ypoints, npoints);
        color=c;
    }

    public void rotate(boolean CW, double degx, double degy, double degz){
        
        for (int i=0;i<points.length;i++) {
            rotator.rotateAxisY(points[i], CW, degy);
            rotator.rotateAxisX(points[i], CW, degx);
            rotator.rotateAxisZ(points[i], CW, degz);
            xpoints[i] = Point3D.convert(points[i]).x;
            ypoints[i] = Point3D.convert(points[i]).y; 
        }
    }

    public double getAverageX(){
        double ax =0;
        for (Point3D p : points) {
            ax+=p.x;
        }
        return ax;
    }

    public void rotateAndShrink(boolean CW, double degx){ //, double degy, double degz){
        
        for (int i=0;i<points.length;i++) {
            rotator.rotateAxisXAndShrink(points[i], CW, degx);
            xpoints[i] = Point3D.convert(points[i]).x;
            ypoints[i] = Point3D.convert(points[i]).y; 
        }
    }
}
