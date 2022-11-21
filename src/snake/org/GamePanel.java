package snake.org;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;


public class GamePanel extends JPanel implements Runnable{

    Thread gameThread;
    Image image;
    Graphics graphics;

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREEN_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int circleDiameter = 20;
    Random random;

    boolean gameFlag;

    Snake snake;
    Circle food;

    GamePanel(){
        newSnake();
        newFood();

        gameFlag = true;

        this.setFocusable(true);//??
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREEN_SIZE);
        gameThread = new Thread(this);
        gameThread.start();
    }

    void newSnake(){
        //random = new Random();
        //snake = new Snake(random.nextInt(GAME_WIDTH),random.nextInt(GAME_HEIGHT),circleDiameter,circleDiameter);
        snake = new Snake(500,500,circleDiameter,circleDiameter);
    }

    void newFood(){
        random = new Random();
        food = new Circle(random.nextInt(GAME_WIDTH),random.nextInt(GAME_HEIGHT),circleDiameter, circleDiameter);
    }

    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponents(g);
        image = createImage(getWidth(),getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g2d.drawImage(image,0,0,this);
    }

    public void draw(Graphics g){
        snake.draw(g);
        food.draw(g);
    }

    public void drawGameOverScreen(Graphics g){
        //TODO - make this work
        graphics = image.getGraphics();
        g.setFont(new Font("Ink Free",Font.BOLD,50));
        g.setColor(Color.WHITE);
        g.drawString("Game over",GAME_WIDTH/2,GAME_HEIGHT/2);
        g.drawImage(image,0,0,this);
    }

    public void move(){
        snake.move();
    }

    public void checkCollision(){
        //snake eating food
        if(snake.snake.get(0).intersects(food)){
            snake.Eat(food);
            newFood();
        }
        //keeping snake on game screen
        if(snake.snake.get(0).x >= GAME_WIDTH){
            snake.snake.get(0).x -= GAME_WIDTH;
        }
        if(snake.snake.get(0).x <= 0){
            snake.snake.get(0).x = GAME_WIDTH;
        }
        if(snake.snake.get(0).y >= GAME_HEIGHT){
            snake.snake.get(0).y -= GAME_HEIGHT;
        }
        if(snake.snake.get(0).y <= 0){
            snake.snake.get(0).y = GAME_HEIGHT;
        }
        //snake eats itself
        for(int i = snake.snake.size()-1; i > 1; i--) {
            if ((snake.snake.get(0).x == snake.snake.get(i).x) && (snake.snake.get(0).y == snake.snake.get(i).y)) {
                drawGameOverScreen(graphics);
                System.out.println("Game over");
                gameFlag = false;
            }
        }
    }

    public void run(){
        //game loop
        long lastTime = System.nanoTime();
        double ammountOfTicks = 60.0;
        double ns = 1000000000 / ammountOfTicks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if(delta >=1 && gameFlag){
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    //AL - Action Listener
    public class AL extends KeyAdapter {
        public void keyPressed(KeyEvent e){
            snake.keyPressed(e);
        }
    }


}
