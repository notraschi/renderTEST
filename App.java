import javax.swing.JFrame;

public class App extends JFrame{
    public static void main(String[] args) throws Exception {
        new App();
    }

    App(){
        display d = new display(this);
        add(d);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        d.start();
        addKeyListener(d);
    }
}
