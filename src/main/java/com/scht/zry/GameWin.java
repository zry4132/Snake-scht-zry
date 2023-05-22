package com.scht.zry;

import com.scht.zry.obejct.BodyObj;
import com.scht.zry.obejct.FoodObj;
import com.scht.zry.obejct.HeadObj;
import com.scht.zry.utils.GameUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @PACKAGE:com.scht.zry
 * @NAME:詹瑞炀
 * @MONTH:五月
 * @DAY:21
 * @TIME:21:29
 * @PROJECT:snack1
 */
public class GameWin extends JFrame {

    //分数
    public int score =0;
    //游戏状态0未开始1游戏中2暂停3失败4通过5.失败后重新开始6.下一关
    public static int state =0;
    //定义双缓存的图片
    Image offScreenImage = null;
    //窗口宽高
    int winWidth = 800;
    int winHeight = 600;
    //蛇头对象

    //创建蛇头
    HeadObj headObj = new HeadObj(GameUtils.rightImg,60,570,this);

    //蛇的身体集合
    public List<BodyObj> bodyObjList =new ArrayList<>();
    //食物
    public FoodObj foodObj = new FoodObj().gerFood();

    public void  launch() throws InterruptedException {
        //设置窗口是否可见
        this.setVisible(true);
        //设置窗口的大小
        this.setSize(winWidth,winHeight);
        //设置窗口的位置在屏幕上居中
        this.setLocationRelativeTo(null);
        //设置窗口的标题
        this.setTitle("贪吃蛇");
        //蛇身体的初始化
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,30,570,this));
        bodyObjList.add(new BodyObj(GameUtils.bodyImg,30,570,this));

        //键盘事件
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_SPACE){
                    switch (state) {
                        case 0 ->
                                //未开始
                                state = 1;
                        case 1 -> {
                            //游戏中
                            state = 2;
                            repaint();
                        }
                        case 2 ->
                                //游戏暂停
                                state = 1;
                        case 3 ->
                                state =5;
                        case 4-> state =6;
                        default -> {
                        }
                    }
                }
            }
        });
        while (true){
            if (state ==1) {
                repaint();
            }
            //失败重启
            if (state==5){
                state=0;
                resetGame();
            }
            //通关下一关
            if (state==6 && GameUtils.level!=3){
                state=1;
                GameUtils.level++;
                resetGame();
            }
            //线程休眠时间
            Thread.sleep(200);
        }
    }

    @Override
    public void paint(Graphics g) {
        //初始化双缓存图片
        if (offScreenImage == null){
            offScreenImage = this.createImage(winWidth,winHeight);
        }
        //获取画笔对象
        Graphics gImage = offScreenImage.getGraphics();
        //灰色背景
        gImage.setColor(Color.gray);
        gImage.fillRect(0, 0, winWidth, winHeight);
        //网格线颜色
        gImage.setColor(Color.BLACK);
        //绘制横线竖线
        for (int i = 0; i <= 20; i++) {
            //横线
            gImage.drawLine(0, i * 30, 600, i * 30);
            //竖线
            gImage.drawLine(i * 30, 0, i * 30, 600);
        }
        //绘制蛇的身体
        for (int i = bodyObjList.size()-1; i >=0 ; i--){
            bodyObjList.get(i).painSelf(gImage);
        }
        //绘制蛇头
        headObj.painSelf(gImage);
        //绘制食物
        foodObj.painSelf(gImage);
        //关卡
        GameUtils.drawWord(gImage,"第"+GameUtils.level,Color.ORANGE,40,650,260);
        //绘制分数
        GameUtils.drawWord(gImage,score+"分",Color.BLUE,50,650,330);
        //绘制提示语
        gImage.setColor(Color.gray);
        prompt(gImage);
        //将双缓存的图片绘制到窗口中
        g.drawImage(offScreenImage,0,0,null);

    }

    //绘制提示语
    void prompt(Graphics g){
        //未开始
        if (state ==0){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格键开始游戏",Color.YELLOW,35,150,290);
        }
        //暂停
        if (state ==2){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"按下空格键继续游戏",Color.YELLOW,35,150,290);
        }
        //失败
        if (state ==3){
            g.fillRect(120,240,400,70);
            GameUtils.drawWord(g,"游戏失败，按空格重新开始",Color.RED,35,150,290);
        }
        //通关
        if (state ==4){
            g.fillRect(120,240,400,70);
            if (GameUtils.level ==3){
                GameUtils.drawWord(g,"游戏通关",Color.GREEN,35,150,290);
            }else {
                GameUtils.drawWord(g, "条件达成，空格进入下一关", Color.GREEN, 35, 150, 290);
            }
        }

    }

    //游戏重置
    void resetGame() throws InterruptedException {
        //关闭当前窗口
        this.dispose();
        //开启新窗口
        String[] args ={};
        main(args);
    }
    public static void main(String[] args) throws InterruptedException {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
