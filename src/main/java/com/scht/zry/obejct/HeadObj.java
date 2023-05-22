package com.scht.zry.obejct;

import com.scht.zry.GameWin;
import com.scht.zry.utils.GameUtils;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;

/**
 * @PACKAGE:com.scht.zry.obejct
 * @NAME:詹瑞炀
 * @MONTH:五月
 * @DAY:21
 * @TIME:22:20
 * @PROJECT:snack1
 */
public class HeadObj extends GameObj{
    //方向
    private String dirextion = "right";

    public String getDirextion() {
        return dirextion;
    }

    public void setDirextion(String dirextion) {
        this.dirextion = dirextion;
    }

    public HeadObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
        //键盘监听事件
        this.frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e){
                changeDirection(e);
            }
        });
    }
    //控制蛇的移动方向
    public void changeDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                if (!"down".equals(dirextion)){
                    dirextion="up";
                    img = GameUtils.upImg;
                }
                break;
            case KeyEvent.VK_S:
                if (!"up".equals(dirextion)){
                    dirextion="down";
                    img = GameUtils.downImg;
                }
                break;
            case KeyEvent.VK_A:
                if (!"right".equals(dirextion)){
                    dirextion="left";
                    img = GameUtils.leftImg;
                }
                break;
            case KeyEvent.VK_D:
                if (!"left".equals(dirextion)){
                    dirextion="right";
                    img = GameUtils.rightImg;
                }
                break;

            default:
                break;
        }
    }

    //蛇的移动
    public void move(){
        //蛇身的移动
        List<BodyObj> bodyObjList = this.frame.bodyObjList;
        for (int i = bodyObjList.size()-1; i >=1 ; i--) {
            bodyObjList.get(i).x =bodyObjList.get(i-1).x;
            bodyObjList.get(i).y =bodyObjList.get(i-1).y;
            //蛇头喝身体的碰撞检测
            if (this.x==bodyObjList.get(i).x && this.y == bodyObjList.get(i).y){
                GameWin.state = 3;
            }
        }
        bodyObjList.get(0).x = this.x;
        bodyObjList.get(0).y = this.y;
        //蛇头的移动
        switch (dirextion) {
            case "up" -> y -= height;
            case "down" -> y += height;
            case "right" -> x += width;
            case "left" -> x -= width;
            default -> {
            }
        }
    }

    @Override
    public void painSelf(Graphics g) {
        super.painSelf(g);
        //蛇吃食物
        FoodObj food = this.frame.foodObj;
        //身体最后一节的坐标
        Integer newX = null;
        Integer newY = null;
        if (this.x == food.x && this.y ==food.y){
            this.frame.foodObj =food.gerFood();
            //获取蛇身的最后一个元素
            BodyObj lastBody = this.frame.bodyObjList.get(this.frame.bodyObjList.size()-1);
            newX = lastBody.x;
            newY = lastBody.y;
            //分数+1
            this.frame.score++;
        }
        //通关判断
        if (this.frame.score>=3){
            //通关
            GameWin.state=4;
        }
        move();
        if (newX!=null && newY!=null){
            this.frame.bodyObjList.add(new BodyObj(GameUtils.bodyImg,newX,newY,this.frame));
        }
        //越界处理
        if(x<0){
            x=570;
        }else if (x>570){
            x=0;
        }else if (y<30){
            y=570;
        }else if (y>570){
            y=30;
        }
    }
}
