package com.scht.zry.obejct;

import com.scht.zry.GameWin;

import java.awt.*;

/**
 * @PACKAGE:com.scht.zry.obejct
 * @NAME:詹瑞炀
 * @MONTH:五月
 * @DAY:21
 * @TIME:21:39
 * @PROJECT:snack1
 */
public class GameObj {

    //photos
    Image img;
    //坐标
    int x;
    int y;
    //宽高
    int height=30;
    int width =30;
    //窗口类的引用
    GameWin frame;
    public GameObj() {
    }

    public GameObj(Image img, int x, int y, GameWin frame) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.frame = frame;
    }

    public GameObj(Image img, int x, int y, int height, int width) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.height = height;
        this.width = width;
    }

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    //绘制自身
    public void painSelf(Graphics g){
        g.drawImage(img,x,y,null);
    }
}
