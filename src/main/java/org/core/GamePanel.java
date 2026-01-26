package org.core;

import org.entity.Player;
import org.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Ajustes de Pantalla
    final int originalTileSize = 16; //16x16 tamaño predeterminado de personajes o objetos
    final int scale = 3; //Rescalamos el tamaño predeterminado para adaptarlo a monitores mas actuales

    public final int tileSize = originalTileSize * scale; //48x48

    public final int maxScreenCol = 16; //Ancho
    public final int maxScreenRow = 12; //Largo
    public final int screenWidth = tileSize * maxScreenCol; //768 pixeles
    public final int screenHeight = tileSize * maxScreenRow; //576 pixeles

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    Player player = new Player(this, keyH);

    TileManager tileM = new TileManager(this);

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.white);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS; //0.01666 segundos
        double nextDrawTime = System.nanoTime() + drawInterval;

        while(gameThread != null){
            //Actualizamos la imagen en pantalla
            update();

            //Redibujamos nuestro panel
            repaint();

            try{
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if(remainingTime < 0){
                    remainingTime = 0;
                }

                Thread.sleep((long)remainingTime);

                nextDrawTime += drawInterval;

            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; //La clase Graphics2D extiende de la clase Graphics
        tileM.draw(g2d);
        player.draw(g2d);
        g2d.dispose();
    }
}
