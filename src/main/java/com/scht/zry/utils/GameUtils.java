package com.scht.zry.utils;

import java.awt.*;

/**
 * @PACKAGE:com.scht.zry.utils
 * @NAME:詹瑞炀
 * @MONTH:五月
 * @DAY:21
 * @TIME:21:43
 * @PROJECT:snack1
 */
public class GameUtils {
    //蛇头
    public static Image upImg =Toolkit.getDefaultToolkit().getImage("img/snake-up.png");
    public static Image leftImg =Toolkit.getDefaultToolkit().getImage("img/snake-left.png");
    public static Image downImg =Toolkit.getDefaultToolkit().getImage("img/snake-down.png");
    public static Image rightImg =Toolkit.getDefaultToolkit().getImage("img/snake-right.png");
    //蛇身
    public static Image bodyImg =Toolkit.getDefaultToolkit().getImage("img/snake-body.png");
    //食物
    public static Image foodImg =Toolkit.getDefaultToolkit().getImage("img/gege.png");

    //关卡
    public static int level =1;
    //绘制文字
    public static void drawWord(Graphics g,String str, Color color,int size, int x,int y){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(str,x,y);
    }
}
