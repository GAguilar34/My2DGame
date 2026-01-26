package org.entity;

import org.core.GamePanel;
import org.core.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity{

    GamePanel gamePanel;
    KeyHandler keyH;
    String direction;

    public Player(GamePanel gamePanel, KeyHandler keyH) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage(){
        try{
            up = ImageIO.read(getClass().getResourceAsStream("/player/up/Sprite-0020.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/up/Sprite-0021.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/player/up/Sprite-0022.png"));

            down = ImageIO.read(getClass().getResourceAsStream("/player/down/Sprite-0010.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/down/Sprite-0011.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/player/down/Sprite-0012.png"));

            left = ImageIO.read(getClass().getResourceAsStream("/player/left/Sprite-0030.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left/Sprite-0031.png"));
            left3  = ImageIO.read(getClass().getResourceAsStream("/player/left/Sprite-0032.png"));

            right = ImageIO.read(getClass().getResourceAsStream("/player/right/Sprite-0040.png"));
            right2  = ImageIO.read(getClass().getResourceAsStream("/player/right/Sprite-0041.png"));
            right3  = ImageIO.read(getClass().getResourceAsStream("/player/right/Sprite-0042.png"));

        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void update(){

        if(keyH.upPressed == true ||  keyH.downPressed == true ||  keyH.leftPressed == true ||  keyH.rightPressed == true) {
            if(keyH.upPressed == true){
                direction = "up";
                y -= speed;
            }
            else if(keyH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            else if(keyH.rightPressed == true){
                direction = "right";
                x += speed;
            }
            else if(keyH.leftPressed == true){
                direction = "left";
                x -= speed;
            }

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 3;
                }
                else if(spriteNum == 3){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d){
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                    image = up;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                if(spriteNum == 3){
                    image = up3;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                if(spriteNum == 3){
                    image = down3;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                if(spriteNum == 3){
                    image = right3;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                if(spriteNum == 3){
                    image = left3;
                }
                break;
        }

        g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
