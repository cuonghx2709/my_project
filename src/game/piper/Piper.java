package game.piper;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * Created by cuonghx2709 on 7/14/2017.
 */
public class Piper {
    public int x = 900;
    public  int y;
    private int dy;

    public void creatpipe(int dx){
        Random random = new Random();
        int d = random.nextInt(150)+50;
        y = d - image.getHeight();
        dy = random.nextInt(150)+80;
        x = dx;
    }

    public BufferedImage image;
    public BufferedImage image2;

    public  void run(){
        x--;
    }
    public void render(Graphics2D g2d){
        g2d.drawImage(image, x, y, null);
        g2d.drawImage(image2, x, y+image.getHeight()+dy, null);
    }
}
