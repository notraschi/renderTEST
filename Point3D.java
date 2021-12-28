import java.awt.Point;
public class Point3D {

    public double x,y,z;
    Point3D(double x, double y, double z){
        this.x=x;
        this.y=y;
        this.z=z;
    }

    public static Point convert(Point3D point){
        
        Point p = new Point((int)((420/2)+point.y),(int) ((420/2)-point.z));
        
        return p;
    }

    public static int[] toPointArray(char xyz, Point3D...points){
        
        int[] array = new int[points.length];
        if (xyz=='x') {
            for (int i=0;i<points.length;i++) {
                array[i]=convert(points[i]).x;
            }  
        } else if (xyz=='y') {
            for (int i=0;i<points.length;i++) {
                array[i]=convert(points[i]).y;
            }  
        }
         
        return array;
    }
}
