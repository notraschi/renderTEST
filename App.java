import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class App extends JFrame{

    JLabel info = new JLabel();
    JPanel p = new JPanel(null);

    public static void main(String[] args) throws Exception {
        if (args.length == 0) new App("0");
        else new App(args[0]);
    }

    App(String arg){
        display d = new display(this, Integer.valueOf(arg));
        
        p.setBounds(0, 459, 436, 145);
        p.setBackground(Color.white);
        p.add(info);
        info.setBounds(5, 404, 436, 145);
        info.setText("<html>"+"h => hide/show commands<br/>q / a => +/- rotationX <br/>w / s => +/- rotationY  <br/>e / d => +/- rotationZ  <br/>c => invert rotation  <br/>r => reset rotation  <br/>f / t => randomize/reset colors <br/>g => keep randomizing colors  "+"</html>");
        info.setFont(new Font("SANS", Font.PLAIN, 11));
        add(d);
        add(p);
        setSize(436, 469);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //pack();
        setLocationRelativeTo(null);
        //setResizable(false);
        setVisible(true);
        d.start();
        addKeyListener(d);
    }
}
