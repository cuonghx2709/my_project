package game.player;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by cuonghx2709 on 7/14/2017.
 */
public class Bird {
    public int x = 100;
    public int y;
    public BufferedImage image1;
    public BufferedImage image2;
    public BufferedImage image3;

    public long start;
    private long end;

    private boolean i1 = true;
    private boolean i2;
    private boolean i3;

    public void move(int dx, int dy){
        x+=dx;
        y+=dy;
    }
    public void autodown(){
        y+=4;
    }
    public void render(Graphics2D g2d){
        end = System.currentTimeMillis();

        if(i1){
            g2d.drawImage(image1, x, y , null);
        }else if (i2){
            g2d.drawImage(image2, x, y, null);
        }else if (i3){
            g2d.drawImage(image3, x ,y, null);
        }
        if(end-start > 200){
            if(i1){
                y -= 5;
                x += 3;
                i1  = false;
                i2 = true;
                start = System.currentTimeMillis();
            }else if( i2 ){
                i2 = false;
                i3 = true;
                start = System.currentTimeMillis();
            }else if (i3){
                y += 5;
                x -= 3;
                i3 = false;
                i1 = true;
                start = System.currentTimeMillis();
            }
        }
    }
}
