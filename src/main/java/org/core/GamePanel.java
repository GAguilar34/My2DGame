package org.core;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Ajustes de Pantalla
    final int originalTileSize = 16; //16x16 tamaño predeterminado de personajes o objetos
    final int scale = 3; //Rescalamos el tamaño predeterminado para adaptarlo a monitores mas actuales

    final int tileSize = originalTileSize * scale; //48x48

    final int maxScreenCol = 16; //Ancho
    final int minScreenRow = 12; //Largo

    final int screenWidth = tileSize * maxScreenCol; //768 pixeles
    final int screenHeight = tileSize * minScreenRow; //576 pixeles

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    int FPS = 60;

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
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
        if(keyH.upPressed == true){
            playerY -= playerSpeed;
        }
        else if(keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if(keyH.rightPressed == true){
            playerX += playerSpeed;
        }
        else if(keyH.leftPressed == true){
            playerX -= playerSpeed;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g; //La clase Graphics2D extiende de la clase Graphics

        g2d.setColor(Color.white);
        g2d.fillRect(playerX, playerY, tileSize, tileSize);

        g2d.dispose();
    }
}
