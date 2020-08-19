package com.game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Paddle p1;
    private Paddle p2;
    private boolean up1 = false;
    private boolean down1 = false;

    private boolean up2 = false;
    private boolean down2 = false;

    public KeyInput(Paddle p1, Paddle p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            up2 = true;
            p2.setVel(p2.getSpeed() * -1);
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            down2 = true;
            p2.setVel(p2.getSpeed());
        }
        if (keyCode == KeyEvent.VK_W) {
            up1 = true;
            p1.setVel(p1.getSpeed() * -1);
        }
        if (keyCode == KeyEvent.VK_S) {
            down1 = true;
            p1.setVel(p1.getSpeed());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_UP) {
            up2 = false;
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            down2 = false;
        }
        if (keyCode == KeyEvent.VK_W) {
            up1 = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            down1 = false;
        }

        if (!up1 && !down1)
            p1.stop();
        if (!up2 && !down2)
            p2.stop();
    }
}
