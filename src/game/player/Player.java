package game.player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/13/2017.
 */
public class Player {
    public  int x;
    public  int y;
    private int Y = y;

    BufferedImage image;

    public void move(int dx , int dy){
        x += dx;
        y += dy;
    }
    public void rend(Graphics2D g2d){
        if(y==Y){
        }
    }
}
