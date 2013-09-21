import javax.swing.JFrame;
import javax.swing.JPanel;

public class Fenetre extends JFrame {

    /** Construit une fenetre vide */
    public Fenetre (){
        this.setTitle("Swing2");
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Panneau());
    }

    /** rend la fenetre visible */
    public void visible() {
        this.setVisible(true);
    }



}
