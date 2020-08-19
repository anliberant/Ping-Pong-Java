package com.game;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.Graphics2D;

public class Game extends Canvas implements Runnable {
    public static final int WIDTH = 1200;
    public static final int HEIGHT = 720;
    public boolean isRunning = false;
    private Thread gameThread;
    private Paddle paddle1;
    private Paddle paddle2;
    private Ball ball;

    public Game() {
        canvasInit();
        initPaddles();
        new Window("Ping Pong", this);
        this.addKeyListener(new KeyInput(paddle1, paddle2));
        this.setFocusable(true);
    }


    private void initPaddles() {
        ball = new Ball();
        paddle1 = new Paddle(Color.BLUE, true);
        paddle2 = new Paddle(Color.WHITE, false);
    }

    private void canvasInit() {
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setMaximumSize(new Dimension(WIDTH, HEIGHT));
        this.setMinimumSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    public void run() {
        this.requestFocus();

        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                update();
                delta--;
                draw();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    private void draw() {
        BufferStrategy strategy = this.getBufferStrategy();
        if (strategy == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = strategy.getDrawGraphics();
        drawBackground(g);
        ball.draw(g);
        paddle1.draw(g);
        paddle2.draw(g);
        g.dispose();
        strategy.show();
    }

    private void drawBackground(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        g.setColor(Color.WHITE);
        Stroke dashed = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{10}, 0);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setStroke(dashed);
        graphics2D.drawLine(WIDTH / 2, 0, WIDTH / 2, HEIGHT);
    }

    private void update() {
        ball.update(paddle1, paddle2);
        paddle1.update(ball);
        paddle2.update(ball);
    }

    public void start() {
        gameThread = new Thread(this);
        gameThread.start();
        isRunning = true;
    }

    public void stop() {
        try {
            gameThread.join();
            isRunning = false;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Game();
    }
}
