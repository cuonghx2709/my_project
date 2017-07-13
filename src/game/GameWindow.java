package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by cuonghx2709 on 7/13/2017.
 */
public class GameWindow extends JFrame{

    BufferedImage background;
    BufferedImage bufferedImageBackGround;
    BufferedImage background2;

    private int backgroundX;
    private int background2X;


    boolean enterPress;

    Graphics2D graphics2Dbfbackground;

    public GameWindow(){
        setUPWindown();
        loadImage();
        setInPut();

        backgroundX = 0;
        background2X = background.getWidth();

        bufferedImageBackGround = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_ARGB);
        graphics2Dbfbackground = (Graphics2D) bufferedImageBackGround.getGraphics();

        this.setVisible(true);
    }

    private void setInPut() {
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_ENTER:
                        enterPress = true;
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_ENTER:
                        enterPress = false;
                        break;
                    default:
                        break;
                }
            }
        });
    }

    public void loop(){
        while (true){
            run();
            render();
        }
    }

    private void render() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        graphics2Dbfbackground.setColor(Color.BLACK);
        graphics2Dbfbackground.fillRect(0,0,this.getWidth(), this.getHeight());
        graphics2Dbfbackground.drawImage(background,backgroundX, 0, null);
        graphics2Dbfbackground.drawImage(background, background2X, 0, null);

        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(bufferedImageBackGround, 0, 0, null);
    }
    private void run(){
        backgroundX -=1;
        background2X-=1;
        if(background2X < 0){
            backgroundX = background2X + background.getWidth();
        }
        if(backgroundX < 0){
            background2X = backgroundX + background.getWidth();
        }
    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("assets/images/background/bg.png"));
            background2 = background;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void setUPWindown() {
        this.setTitle("designed by cuonghx");
        this.setSize(480,600);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing");
                System.exit(0);
            }
        });
    }

}
