package game;

import game.piper.Piper;
import game.player.Bird;

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
import java.util.ArrayList;


/**
 * Created by cuonghx2709 on 7/13/2017.
 */
public class GameWindow extends JFrame{

    BufferedImage background;
    BufferedImage bufferedImageBackGround;
    BufferedImage ground;


    private int backgroundX;
    private int background2X;
    private long start;
    private long end;
    private  int x;
    private int z;

    Bird bird = new Bird();
    Piper piper1 = new Piper();

    ArrayList<Piper> pipers = new ArrayList<>();

    boolean enterPress;
    boolean spacePress;
    boolean play;
    boolean tam;
    boolean over;

    Graphics2D graphics2Dbfbackground;

    public GameWindow(){
        setUPWindown();
        loadImage();
        setInPut();

        backgroundX = 0;
        background2X = background.getWidth();
        bird.y = this.getHeight()/2;
        bird.start = System.currentTimeMillis();
        start = System.currentTimeMillis();
        piper1.creatpipe(this.getWidth()-piper1.image.getWidth());
        x = piper1.x;

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
                        play = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        spacePress = true;
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
                    case  KeyEvent.VK_SPACE:
                        spacePress = false;
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
            gameover();
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

        bird.render(graphics2Dbfbackground);
        piper1.render(graphics2Dbfbackground);
        for (Piper piper : pipers) {
            piper.render(graphics2Dbfbackground);
        }

        graphics2Dbfbackground.drawImage(ground,backgroundX, this.getHeight() - ground.getHeight(), null);
        graphics2Dbfbackground.drawImage(ground,background2X, this.getHeight() - ground.getHeight(), null);

        Graphics2D g2d = (Graphics2D) this.getGraphics();
        g2d.drawImage(bufferedImageBackGround, 0, 0, null);
    }
    private void run(){
        end = System.currentTimeMillis();
        backgroundX -=1;
        background2X-=1;
        if(background2X < 0){
            backgroundX = background2X + background.getWidth();
        }
        if(backgroundX < 0){
            background2X = backgroundX + background.getWidth();
        }
        if(enterPress && play){
            bird.x = 200;
        }
        if(play){
            bird.autodown();
            piper1.run();
            if(!tam){
                x = piper1.x;
            }else{
                x = z;
            }
            z--;
            if(x < 400){
                System.out.println("cuong");
                Piper piper = new Piper();
                try {
                    piper.image = ImageIO.read(new File("assets/images/background/pipe.png"));
                    piper.image2 = ImageIO.read(new File("assets/images/background/pipe2.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                piper.creatpipe(x + 200);
                z = piper.x;
                tam = true;
                pipers.add(piper);
            }

            for (Piper piper: pipers) {
                piper.run();
            }
            if(spacePress){
                bird.move(0, -15);
            }
            if(bird.y > this.getHeight()-ground.getHeight()-bird.image1.getHeight()){
                over = true;
            }
        }

    }

    private void loadImage() {
        try {
            background = ImageIO.read(new File("assets/images/background/bg.png"));
            bird.image1 = ImageIO.read(new File("assets/images/background/bird1.png"));
            bird.image2 = ImageIO.read(new File("assets/images/background/bird2.png"));
            bird.image3 = ImageIO.read(new File("assets/images/background/bird3.png"));
            piper1.image = ImageIO.read(new File("assets/images/background/pipe.png"));
            piper1.image2 = ImageIO.read(new File("assets/images/background/pipe2.png"));
            ground = ImageIO.read(new File("assets/images/background/ground.png"));

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
    private void gameover(){
        if(over){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }

}
