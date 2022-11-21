package snake.org;

import javax.swing.*;
import java.awt.*;

public class Circle extends Rectangle {

    int initialSpeed = 5;
    int directionFlag;
    int xVelocity;
    int yVelocity;
    //Image image;

    Circle(int x, int y, int width, int height)
    {
        super(x,y,width,height);
        //image = new ImageIcon("apple.png").getImage();
        xVelocity = 0;
        yVelocity = 0;
        directionFlag = 4;
    }

    public void draw(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        //g2d.drawImage(image,x,y,null);
        //g2d.drawImage(image,x,y,width,height,null);
        g.setColor(Color.green);
        g.fillOval(x,y,width,height);
    }

    public void setDirectionFlag(int directionFlag){
        this.directionFlag = directionFlag;
    }

    public void setXVelocity(int xVelocity){
        this.x += xVelocity;
    }

    public void setYVelocity(int yVelocity){
        this.y += yVelocity;
    }


    /*
    public void move(){
        if(directionFlag == 1){
            y -= initialSpeed;
        }
        else if(directionFlag == 2){
            y += initialSpeed;
        }
        else if(directionFlag == 3){
            x -= initialSpeed;
        }
        else if(directionFlag == 4){
            x += initialSpeed;
        }
    }
     */


}
