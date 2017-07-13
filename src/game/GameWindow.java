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

import static sun.misc.PostVMInitHook.run;

/**
 * Created by cuonghx2709 on 7/13/2017.
 */
public class GameWindow extends JFrame{

    BufferedImage background;
    BufferedImage bufferedImageBackGround;

    private int backgroundX = 0;
    private  int check = 1;

    boolean enterPress;

    Graphics2D graphics2Dbfbackground;

    public GameWindow(){
        setUPWindown();
        loadImage();
        setInPut();

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

    private void loadImage() {
        try {
            background = ImageIO.read(new File("assets/images/background/background.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gameLoop() {
        while(true){
            run();
            render();
        }
    }

    private  void run(){
        if(backgroundX > -740) backgroundX-=3;
        if(enterPress&&check == 1){
            backgroundX = -1600;
            check = 0;
        }

    }

    private void render() {
        try {
            Thread.sleep(17);
            graphics2Dbfbackground.setColor(Color.BLACK);
            graphics2Dbfbackground.fillRect(0,0, this.getWidth(), this.getHeight());
            graphics2Dbfbackground.drawImage(background, backgroundX, 0 ,null);
            Graphics2D g2d = (Graphics2D) this.getGraphics();
            g2d.drawImage(bufferedImageBackGround, 0, 0, null);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    private void setUPWindown() {
        this.setTitle("designed by cuonghx");
        this.setSize(800,720);
        this.setResizable(false);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("closing");
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {

    }
}
