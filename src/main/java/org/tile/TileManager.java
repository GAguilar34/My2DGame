package org.tile;

import org.core.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {

    GamePanel gamePanel;
    Tile[] tile;
    int [][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tile = new Tile[4];
        mapTileNum = new int[gamePanel.maxScreenCol][gamePanel.maxScreenRow];
        getTileImage();
        loadMap("/maps/map01.txt");
    }

    public void getTileImage(){

        try{

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass2.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Rock.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/Sea.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void loadMap(String routeMap){
        try{
            InputStream is = getClass().getResourceAsStream(routeMap);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            //Leemos linea por linea
            while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow) {
                String line = br.readLine();

                //Leemos cuadro por cuadro del mapa
                while (col < gamePanel.maxScreenCol) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }

                if (col == gamePanel.maxScreenCol) {
                    col = 0;
                    row++;
                }
            }

            br.close();
             
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2d){

        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while(col < gamePanel.maxScreenCol && row < gamePanel.maxScreenRow){

            int tileNum = mapTileNum[col][row];

            g2d.drawImage(tile[tileNum].image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
            col++;
            x += gamePanel.tileSize;

            if(col == gamePanel.maxScreenCol){
                col = 0;
                x = 0;
                row++;
                y += gamePanel.tileSize;
            }
        }
    }
}
