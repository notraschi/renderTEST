import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class display extends Canvas implements Runnable, KeyListener{

    int xrotation = 0;
    int yrotation = 0;
    int zrotation = 0;
    boolean Cw = true;
    private static final long serialVersionUID=1L;
    boolean running = false;
    Thread thread;
    App jframe;

    // happy shapes im going to draw :)
    Polygon3D triangle1;
    Polygon3D triangle2;
    Polygon3D triangle3;
    Polygon3D triangle4;
    Polygon3D triangle5;
    Polygon3D triangle6;
    Polygon3D triangle7;
    Polygon3D triangle8;
    ArrayList<Polygon3D> happyPolygon3ds;

    public display(App f){
        setPreferredSize(new Dimension(420,420));
        jframe=f;
    }

    public synchronized void start(){
        running=true;
        thread=new Thread(this, "display");
        thread.start();
        init();
    }

    public synchronized void stop(){
        running=false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void render() {
        
        BufferStrategy bs = this.getBufferStrategy();   //helps us draw
        if (bs==null) {
            this.createBufferStrategy(2);
            return;
        }

        Graphics gg = bs.getDrawGraphics();
        Graphics2D g = (Graphics2D) gg;
        
        g.setStroke(new BasicStroke(5));
        clearCanvas(g);        

        List<Polygon3D> happySortedList = happyPolygon3ds.stream()
            .sorted(Comparator.comparingDouble(Polygon3D::getAverageX))
            .collect(Collectors.toList());

        for (Polygon3D poly : happySortedList) {    //prints all the triangles BUT before doing this we want to sort to see the x and who has to be drawn first 
            g.setColor(poly.color);
            poly.rotate(Cw, xrotation, yrotation, zrotation); //rotaion values
            g.fill(poly);
        }
    
        g.dispose();
        bs.show();
    }

    private void clearCanvas(Graphics g){
        g.setColor(Color.white);
        g.fillRect(0, 0, 500, 500);
    }

    private void update() {
    }

    private void init(){

        Point3D[] happyTriangle = {new Point3D(35, 0, 0),new Point3D(0, 35, 0), new Point3D(0, 0, 35)};
        Point3D[] happyTriangle2 = {new Point3D(35, 0, 0),new Point3D(0, -35, 0), new Point3D(0, 0, 35)};
        Point3D[] happyTriangle3 = {new Point3D(-35, 0, 0),new Point3D(0, 35, 0), new Point3D(0, 0, 35)};
        Point3D[] happyTriangle4 = {new Point3D(-35, 0, 0),new Point3D(0, -35, 0), new Point3D(0, 0, 35)};
        Point3D[] happyTriangle5 = {new Point3D(35, 0, 0),new Point3D(0, 35, 0), new Point3D(0, 0, -35)};
        Point3D[] happyTriangle6 = {new Point3D(35, 0, 0),new Point3D(0, -35, 0), new Point3D(0, 0, -35)};
        Point3D[] happyTriangle7 = {new Point3D(-35, 0, 0),new Point3D(0, 35, 0), new Point3D(0, 0, -35)};
        Point3D[] happyTriangle8 = {new Point3D(-35, 0, 0),new Point3D(0, -35, 0), new Point3D(0, 0, -35)};
        
        happyPolygon3ds = new ArrayList<>(Arrays.asList(
            triangle1 = new Polygon3D(Point3D.toPointArray('x', happyTriangle), Point3D.toPointArray('y', happyTriangle), happyTriangle.length, happyTriangle, Color.cyan),
            triangle2 = new Polygon3D(Point3D.toPointArray('x', happyTriangle2), Point3D.toPointArray('y', happyTriangle2), happyTriangle2.length, happyTriangle2, Color.pink),
            triangle3 = new Polygon3D(Point3D.toPointArray('x', happyTriangle3), Point3D.toPointArray('y', happyTriangle3), happyTriangle3.length, happyTriangle3, Color.magenta),
            triangle4 = new Polygon3D(Point3D.toPointArray('x', happyTriangle4), Point3D.toPointArray('y', happyTriangle4), happyTriangle4.length, happyTriangle4, Color.red),
            triangle5 = new Polygon3D(Point3D.toPointArray('x', happyTriangle5), Point3D.toPointArray('y', happyTriangle5), happyTriangle5.length, happyTriangle5, Color.blue),
            triangle6 = new Polygon3D(Point3D.toPointArray('x', happyTriangle6), Point3D.toPointArray('y', happyTriangle6), happyTriangle6.length, happyTriangle6, Color.GREEN),
            triangle7 = new Polygon3D(Point3D.toPointArray('x', happyTriangle7), Point3D.toPointArray('y', happyTriangle7), happyTriangle7.length, happyTriangle7, Color.ORANGE),
            triangle8 = new Polygon3D(Point3D.toPointArray('x', happyTriangle8), Point3D.toPointArray('y', happyTriangle8), happyTriangle8.length, happyTriangle8, Color.yellow)
        ));
    }
    
    @Override
    public void run() {

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0/144;
        double delta = 0;
        int frame =0;

        while(running){
            long currentTime = System.nanoTime();
            delta += (currentTime-lastTime)/ns;
            lastTime = currentTime;
            while(delta>=1){
                delta--;
                update();
                render();
                frame++;
            }

            if(System.currentTimeMillis()-timer>1000){
                timer+=1000;
                jframe.setTitle("current fps: "+frame);
                frame=0;
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        
        if(e.getKeyChar()=='q') xrotation++;
        else if(e.getKeyChar()=='w') yrotation++;
        else if(e.getKeyChar()=='e') zrotation++;
        else if(e.getKeyChar()=='a') xrotation--;
        else if(e.getKeyChar()=='s') yrotation--;
        else if(e.getKeyChar()=='d') zrotation--;
        else if(e.getKeyChar()=='c') Cw=!Cw;
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
