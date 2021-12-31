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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class display extends Canvas implements Runnable, KeyListener{

    int OFFSET =0;
    int xrotation = 0;
    int yrotation = 0;
    int zrotation = 0;
    boolean Cw = true;
    boolean help = false;
    boolean randomColors = false;
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

    public display(App f, int offset){
        OFFSET=offset;
        //setPreferredSize(new Dimension(420,420));
        setSize(new Dimension(420,420));
        jframe=f;
        setBackground(Color.white);
    }

    public synchronized void start(){
        running=true;
        thread=new Thread(this, "display");
        thread.start();
        init();
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

        List<Polygon3D> happySortedList = happyPolygon3ds.stream()  //damn all those java streams tutorials coming in clutch
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
        g.fillRect(0, 0, 500, 459);
    }

    private void update() {
    }

    private void init(){

        Point3D[] happyTriangle = {new Point3D(35  +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, 35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, 35 +OFFSET)};
        Point3D[] happyTriangle2 = {new Point3D(35 +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, -35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, 35 +OFFSET)};
        Point3D[] happyTriangle3 = {new Point3D(-35 +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, 35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, 35 +OFFSET)};
        Point3D[] happyTriangle4 = {new Point3D(-35 +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, -35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, 35 +OFFSET)};
        Point3D[] happyTriangle5 = {new Point3D(35 +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, 35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, -35 +OFFSET)};
        Point3D[] happyTriangle6 = {new Point3D(35 +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, -35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, -35 +OFFSET)};
        Point3D[] happyTriangle7 = {new Point3D(-35 +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, 35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, -35 +OFFSET)};
        Point3D[] happyTriangle8 = {new Point3D(-35 +OFFSET, 0 +OFFSET, 0 +OFFSET),new Point3D(0 +OFFSET, -35 +OFFSET, 0 +OFFSET), new Point3D(0 +OFFSET, 0 +OFFSET, -35 +OFFSET)};
        
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

    private void stopRotation() {

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(()-> {
            if (xrotation==0 && yrotation==0 && zrotation==0) executorService.shutdown();
            else if (xrotation>0) xrotation--;
            else if (xrotation<0) xrotation++;
            else if (yrotation>0) yrotation--;
            else if (yrotation<0) yrotation++;
            else if (zrotation>0) zrotation--;
            else if (zrotation<0) zrotation++;
        }, 0, 100, TimeUnit.MILLISECONDS);       
    }

    private void randomizeColorsForefer() {
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(()-> {
            if(randomColors) happyPolygon3ds.forEach(Polygon3D::randomColor);
            else executorService.shutdown();
        }, 0, 250, TimeUnit.MILLISECONDS);
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {        //some of those commands must not be spammable, so i moved them in keyReleased

        if(e.getKeyChar()=='q' && xrotation+yrotation+zrotation<20) xrotation++;
        else if(e.getKeyChar()=='w'&& xrotation+yrotation+zrotation<20) yrotation++;
        else if(e.getKeyChar()=='e'&& xrotation+yrotation+zrotation<20) zrotation++;
        else if(e.getKeyChar()=='a'&& xrotation+yrotation+zrotation>-20) xrotation--;
        else if(e.getKeyChar()=='s'&& xrotation+yrotation+zrotation>-20) yrotation--;
        else if(e.getKeyChar()=='d'&& xrotation+yrotation+zrotation>-20) zrotation--;
    }

    @Override
    public void keyReleased(KeyEvent e) {       
        if(e.getKeyChar()=='c') Cw=!Cw;
        else if(e.getKeyChar()=='r') {
            stopRotation();
        }
        else if(e.getKeyChar()=='t') happyPolygon3ds.forEach(Polygon3D::resetColor);
        else if(e.getKeyChar()=='f') happyPolygon3ds.forEach(Polygon3D::randomColor);
        else if(e.getKeyChar()=='g') {randomColors=!randomColors; randomizeColorsForefer();}
        else if(e.getKeyChar()=='h') {help=!help; jframe.setSize(jframe.getWidth(), (help?576:469)); }
    }
}
