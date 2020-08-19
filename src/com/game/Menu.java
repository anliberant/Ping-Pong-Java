package com.game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter {
    public boolean active;
    private Rectangle playBtn;
    private String playText = "Play";
    private boolean pHighLight = false;

    private Rectangle quitBtn;
    private String quitText = "Quit";
    private boolean qHighLight = false;
    private Font font;

    public Menu(Game game) {
        this.active = true;
        game.start();

        int w, h, x, y;
        w = 300;
        h = 150;
        y = Game.HEIGHT / 2 - h / 2;
        x = Game.WIDTH / 4 - w / 2;
        playBtn = new Rectangle(x, y, w, h);

        x = Game.WIDTH * 3 / 4 - w / 2;
        quitBtn = new Rectangle(x, y, w, h);
        font = new Font("Robot", Font.PLAIN, 100);


    }
    public void draw(Graphics g){
        Graphics2D graphics2D = (Graphics2D) g;
        g.setFont(font);
        g.setColor(Color.BLACK);
        if (pHighLight)
            g.setColor(Color.WHITE);

        g.setColor(Color.BLACK);
        graphics2D.fill(playBtn);
        if (qHighLight)
            g.setColor(Color.WHITE);
        graphics2D.fill(quitBtn);

        int strWidth;
        int strHeight;
        strWidth = g.getFontMetrics(font).stringWidth(playText);
        strHeight = g.getFontMetrics(font).getHeight();
        g.setColor(Color.GREEN);
        g.drawString(playText, (int) (playBtn.getX() + playBtn.getWidth() / - strWidth / 2),
                (int) (playBtn.getY() + playBtn.getHeight() / 2 + strHeight / 4 ));


        strWidth = g.getFontMetrics(font).stringWidth(quitText);
        strHeight = g.getFontMetrics(font).getHeight();
        g.setColor(Color.RED);
        g.drawString(quitText, (int) (quitBtn.getX() + quitBtn.getWidth() / - strWidth / 2),
                (int) (quitBtn.getY() + quitBtn.getHeight() / 2 + strHeight / 4 ));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point = e.getPoint();
        if (playBtn.contains(point)){
            active = false;
        } else if (quitBtn.contains(point)){
            System.exit(0);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        Point point = e.getPoint();
        pHighLight =playBtn.contains(point);
        qHighLight = quitBtn.contains(point);


    }
}
