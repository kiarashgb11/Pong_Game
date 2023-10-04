import java.awt.*;
import java.awt.event.*;
import java.io.File;        //imports
import java.util.*;
import javax.swing.*;

public class GamePanel extends JPanel implements Runnable{

    static final int gamewidth = 1000; //Game screen width
    static final int gameheight = (int)(gamewidth * (0.5555)); //DIMENSION FOR PONG TABLE LEARNED FROM COM SCI CLUB
    static final Dimension SCREEN_SIZE = new Dimension(gamewidth,gameheight); //screen size dimension for variable use
    static final int BALL_DIAMETER = 20; // ball diameter
    static final int PADDLE_WIDTH = 25; //paddle sizes
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;        //SO MANY VARIABLES
    Graphics graphics; //standard ones everyone has
    Random random; //for ball direction
    Paddle paddle1;
    Paddle paddle2; //classes n stuff
    Ball ball;
    Score score;

    GamePanel(){
        newPaddles();  //makes new paddles
        newBall(); //sends the new ball
        score = new Score(gamewidth,gameheight); //new score and where
        this.setFocusable(true);
        this.addKeyListener(new AL()); //so key presses work
        this.setPreferredSize(SCREEN_SIZE); //makes it so the screen fits the images
        gameThread = new Thread(this); //from runnable implement so game runs
        gameThread.start();
    }

    public void newBall() {
        random = new Random(); //size and direction
        ball = new Ball((gamewidth/2)-(BALL_DIAMETER/2),random.nextInt(gameheight-BALL_DIAMETER),BALL_DIAMETER,BALL_DIAMETER);
    }
    public void newPaddles() {
        paddle1 = new Paddle(0,(gameheight/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1); //it wouldve been easier to guess lol
        paddle2 = new Paddle(gamewidth-PADDLE_WIDTH,(gameheight/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);
    }
    public void paint(Graphics g) {
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();         // paints stuff on screen
        draw(graphics);
        g.drawImage(image,0,0,this);
    }
    public void draw(Graphics g) {
        paddle1.draw(g);
        paddle2.draw(g);    //draws stuff on screen
        ball.draw(g);
        score.draw(g);
    }

    public void move() {
        paddle1.move();
        paddle2.move(); //so the graphics mvoe
        ball.move();
    }
    public void checkCollision() {

        //bounce ball off top & bottom window edges
        if(ball.y <=0) {
            ball.setYDirection(-ball.yVelocity);
        }
        if(ball.y >= gameheight-BALL_DIAMETER) {
            ball.setYDirection(-ball.yVelocity);
        }
        //bounce ball off paddles
        if(ball.intersects(paddle1)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)) {
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.xVelocity++;
            if(ball.yVelocity>0)
                ball.yVelocity++;
            else
                ball.yVelocity--;
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        //stops paddles at window edges
        if(paddle1.y<=0)
            paddle1.y=0;
        if(paddle1.y >= (gameheight-PADDLE_HEIGHT))
            paddle1.y = gameheight-PADDLE_HEIGHT;
        if(paddle2.y<=0)
            paddle2.y=0;
        if(paddle2.y >= (gameheight-PADDLE_HEIGHT))
            paddle2.y = gameheight-PADDLE_HEIGHT;
        //give a player 1 point and creates new paddles & ball
        if(ball.x <=0) {

            score.player2++;
            newPaddles();
            newBall();
        }
        if(ball.x >= gamewidth-BALL_DIAMETER) {
            score.player1++;
            newPaddles();
            newBall();
        }
    }
    public void run() {
        //game loop learned from com sci club
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }
    public class AL extends KeyAdapter{
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);              //KEYS KEYS KEYS
        }
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }


}//CLASS