package com.game;

import java.awt.*;

public class Ball {
    public final static int SIZE = 16;
    private int x, y, vx, vy;
    private int speed = 5;

    public Ball() {
        reset();
    }

    private void reset() {
        x = Game.WIDTH / 2 - SIZE / 2;
        y = Game.HEIGHT / 2 - SIZE / 2;
        vx = (Math.random() * 2 - 1 <= 0) ? -1 : 1;
        vy = (Math.random() * 2 - 1 <= 0) ? -1 : 1;

    }


    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, SIZE, SIZE);
    }

    public void update(Paddle paddle1, Paddle paddle2) {
        x += vx * speed;
        y += vy * speed;
        if(y + SIZE >= Game.HEIGHT || y <= 0){
            vy *= -1;
        }

        if (x + SIZE >= Game.WIDTH ){
            paddle1.addPoint();
            reset();
        }

        if (x <= 0){
            paddle2.addPoint();
            reset();
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }
}
