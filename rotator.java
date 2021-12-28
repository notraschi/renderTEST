public class rotator {  

    //=================================================================================== X
    public static Point3D rotateAxisX(Point3D p, boolean CW, double deg){
        
        double radius=Math.sqrt(p.y*p.y+p.z*p.z);
        double theta = Math.atan2(p.y, p.z);
        theta+= Math.PI/180*deg*(CW?1:-1);
        p.y= (radius*Math.sin(theta));
        p.z= (radius*Math.cos(theta));
        return p;
    }

    public static Point3D rotateAxisXAndShrink(Point3D p, boolean CW, double deg){
        
        double radius=Math.sqrt(p.y*p.y+p.z*p.z);
        double theta = Math.atan2(p.y, p.z);
        theta+= Math.PI/180*deg*(CW?1:-1);
        p.y= (int)(radius*Math.sin(theta));
        p.z=(int) (radius*Math.cos(theta));
        return p;
    }
    //=================================================================================== Y
    public static Point3D rotateAxisY(Point3D p, boolean CW, double deg){
        
        double radius=Math.sqrt(p.x*p.x+p.z*p.z);
        double theta = Math.atan2(p.x, p.z);
        theta+= Math.PI/180*deg*(CW?1:-1);
        p.x= (radius*Math.sin(theta));
        p.z= (radius*Math.cos(theta));
        return p;
    }

    public static Point3D rotateAxisYAndShrink(Point3D p, boolean CW, double deg){
        
        double radius=Math.sqrt(p.x*p.x+p.z*p.z);
        double theta = Math.atan2(p.x, p.z);
        theta+= Math.PI/180*deg*(CW?1:-1);
        p.x= (int)(radius*Math.sin(theta));
        p.z=(int) (radius*Math.cos(theta));
        return p;
    }
    //=================================================================================== Z
    public static Point3D rotateAxisZ(Point3D p, boolean CW, double deg){
        
        double radius=Math.sqrt(p.y*p.y+p.x*p.x);
        double theta = Math.atan2(p.y, p.x);
        theta+= Math.PI/180*deg*(CW?1:-1);
        p.y= (radius*Math.sin(theta));
        p.x= (radius*Math.cos(theta));
        return p;
    }
    
    public static Point3D rotateAxisZAndShrink(Point3D p, boolean CW, double deg){
        
        double radius=Math.sqrt(p.y*p.y+p.x*p.x);
        double theta = Math.atan2(p.y, p.x);
        theta+= Math.PI/180*deg*(CW?1:-1);
        p.y= (int)(radius*Math.sin(theta));
        p.x=(int) (radius*Math.cos(theta));
        return p;
    }
}
