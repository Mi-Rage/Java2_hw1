package ru.gb.jtwo.la.online.circles;

import java.awt.*;

public class BackGround extends Sprite {
    private Color color;
    private int colorNow = 0;
    private boolean direction = true;

    BackGround() {
        color = new Color(colorNow, 100, 100);

    }
    @Override
    public void update(MainCanvas canvas, float deltaTime) {
        //Высчитываем шаг изменение цвета в зависимости от разницы времени показа фреймов.
        int STEP_COLOR = 100;
        int deltaColor = (int)(deltaTime * STEP_COLOR);
        //Определим направление изменения цвета: увеличивать цифру или уменьшать
        if (colorNow + deltaColor > 255 && direction) {
            direction = false;            
        } else if (colorNow - deltaColor < 0 && !direction) {
            direction = true;            
        }
        //В зависимости от направления изменения вычисляем новый цвет
        if (direction) {
            colorNow += deltaColor;
        } else {
            colorNow -= deltaColor;
        }
        //Создаём новый цвет исходя из высчитанных значений
        color = new Color(colorNow, 100, 100);
        // Получаем координаты вывода бэкграунда
        x = canvas.getX();
        y = canvas.getY();
        halfWidth = canvas.getWidth();
        halfHeight = canvas.getHeight();
    }

    @Override
    public void render(MainCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillRect((int) getLeft(), (int) getTop(),
                (int) getWidth(), (int) getHeight());
    }


}
