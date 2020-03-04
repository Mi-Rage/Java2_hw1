package ru.gb.jtwo.la.online.circles;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainCircles extends JFrame {
    private static final int POS_X = 400;
    private static final int POS_Y = 200;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    private Sprite[] sprites;
    private int spritesNum = 1;                         //Начальное количество шариков. Пусть будет 1.
    private BackGround backGround = new BackGround();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainCircles();
            }
        });
    }

    private MainCircles() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(POS_X, POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setTitle("Circles");

        initApplication();
        MainCanvas canvas = new MainCanvas(this);
        add(canvas);
        //Добавляем слушатель мышки с инкрементом к-ва шариков и перезапуском полученного количества
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getButton() == MouseEvent.BUTTON1){           //Левой кнопкой добавляем к-во шариков
                    spritesNum++;
                } else if (e.getButton() == MouseEvent.BUTTON3) { //Правой кнопкой уменьшаем к-во шариков
                    if(spritesNum > 0) {
                        spritesNum--;
                    }
                }
                initApplication();
            }
        });

        setVisible(true);
    }

    private void initApplication() {
        sprites = new Sprite[spritesNum]; //Создадим к-во шариков исходя их того, сколько накликали мышкой
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }

    }

    public void onCanvasRepainted(MainCanvas canvas, Graphics g, float deltaTime) {
        update(canvas, deltaTime);
        render(canvas, g);
    }

    private void update(MainCanvas canvas, float deltaTime) {
        backGround.update(canvas, deltaTime);               //Добавляем изменения бекграунда
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].update(canvas, deltaTime);
        }

    }

    private void render(MainCanvas canvas, Graphics g) {
        backGround.render(canvas, g);                       //Добавляем отрисовку бэкгранда
        for (int i = 0; i < sprites.length; i++) {
            sprites[i].render(canvas, g);
        }

    }
}
