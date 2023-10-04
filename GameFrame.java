import java.awt.*;
import javax.swing.*;

public class GameFrame extends JFrame{

    GamePanel panel; //using other class

    GameFrame(){
        panel = new GamePanel(); //adds the game panel
        this.add(panel); //truly adds it
        this.setTitle("PONG!"); //NAME
        this.setResizable(false); //standard game frame
        this.setBackground(Color.black); //background colour
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //press x to close
        this.pack(); //imports pack so i can do vv
        this.setVisible(true); //this
        this.setLocationRelativeTo(null); //this sets location to middle of screen
    }
}//CLASS