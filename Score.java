import java.awt.*;

public class Score extends Rectangle{

    static int gamewidth;
    static int gameheight;
    int player1;
    int player2;

    Score(int gamewidth, int gameheight){
        Score.gamewidth = gamewidth;
        Score.gameheight = gameheight;      //score
    }
    public void draw(Graphics g) {
        g.setColor(Color.white);
        g.setFont(new Font("DialogInput",Font.BOLD,60)); //font sizes
        g.drawLine(500, 0, 500, 800);

        g.drawString(String.valueOf(player1/10)+String.valueOf(player1%10), (gamewidth/2)-85, 50); // SCOOOORE
        g.drawString(String.valueOf(player2/10)+String.valueOf(player2%10), (gamewidth/2)+20, 50); // where to put it middle and stuff

        String[] Controls = {"W", "S", "UP", "DOWN"}; //ARRAY

        g.setFont(new Font("Arial",Font.ITALIC,20));
        g.drawString(Controls[0], (gamewidth/2)-115, 30);
        g.drawString(Controls[1], (gamewidth/2)-115, 55);       //CONTROLS
        g.drawString(Controls[2], (gamewidth/2)+100, 30);
        g.drawString(Controls[3], (gamewidth/2)+100, 50);
    }

}//CLASS