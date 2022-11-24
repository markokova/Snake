package snake.org;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Snake {

    ArrayList<Circle> snake;
    static final int initialSpeed = 8;
    int headXVelocity;
    int headYVelocity;
    int directionFlag;
    int foodEaten;

    public Snake(int x, int y, int height, int width) {
        //super(x,y,height,width);
        snake = new ArrayList<>(5);

        directionFlag = 0; // 0 => not moving
        foodEaten = 0;
        for(int i = 0; i < 10; i++){
            snake.add(new Circle(x,y,height,width));
            x-=25;
        }
    }


    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;

        for(int i = 0; i < snake.size(); i++){
            if(i == 0){
                g2d.setPaint(Color.blue);
                g2d.fillOval(snake.get(i).x,snake.get(i).y,snake.get(i).width,snake.get(i).height);
            }
            else{
                g2d.setPaint(new Color(135,206,250));
                g2d.fillOval(snake.get(i).x,snake.get(i).y,snake.get(i).width,snake.get(i).height);
            }
        }

        //draw Score
        g2d.setPaint(Color.white);
        g2d.setFont(new Font("Ink free",Font.BOLD,50));
        g2d.drawString("Score: " + foodEaten,400,50);
    }

    public void move(){
        //TODO - make the snake parts further apart (it is okay without the move() method, but when the move method is added they are to close to each other)
        //for moving at the beginning of the game
        if(directionFlag == 0){
            headXVelocity = initialSpeed;
            directionFlag = 4;
        }

        //moving the rest of the body in a gradual way
        for(int i = snake.size()-1; i > 0; i--){
            snake.get(i).x = snake.get(i-1).x;
            snake.get(i).y = snake.get(i-1).y;
        }
        //moving the head
        if(directionFlag == 1 || directionFlag == 2){
            snake.get(0).y += headYVelocity;
        }
        else{
            snake.get(0).x += headXVelocity;
        }

    }

    public void Eat(Circle food){
        foodEaten++;

        //to make the snake grow faster
        for(int i = 0; i < 5; i++){
            Circle foodMore = new Circle(food.x,food.y,snake.get(0).width,snake.get(0).height);
            snake.add(foodMore);
        }


    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_UP && directionFlag != 2){
            directionFlag = 1;
            for(int i = 0; i < snake.size()-1; i++){
                snake.get(i).setDirectionFlag(1);
            }
            headYVelocity = -initialSpeed;
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_DOWN && directionFlag != 1){
            directionFlag = 2;
            for(int i = 0; i < snake.size()-1; i++){
                snake.get(i).setDirectionFlag(2);
            }
            headYVelocity = initialSpeed;
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT && directionFlag != 4){
            directionFlag = 3;
            for(int i = 0; i < snake.size()-1; i++){
                snake.get(i).setDirectionFlag(3);
            }
            headXVelocity = -initialSpeed;
            move();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT && directionFlag != 3){
            for(int i = 0; i < snake.size()-1; i++){
                snake.get(i).setDirectionFlag(4);
            }
            directionFlag = 4;
            headXVelocity = initialSpeed;
            move();
        }
    }

}
