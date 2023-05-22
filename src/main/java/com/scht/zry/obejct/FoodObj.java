package com.scht.zry.obejct;

import com.scht.zry.GameWin;
import com.scht.zry.utils.GameUtils;

import java.awt.*;
import java.util.Random;

/**
 * @PACKAGE:com.scht.zry.obejct
 * @NAME:詹瑞炀
 * @TIME:12:59
 * @PROJECT:snack1
 */
public class FoodObj extends GameObj{

    //随机
    Random r = new Random();
    public FoodObj(){

    }
    public FoodObj(Image img, int x, int y, GameWin frame) {
        super(img, x, y, frame);
    }

    //获取食物
    public FoodObj gerFood(){
        return new FoodObj(GameUtils.foodImg,r.nextInt(20)*30, (r.nextInt(19)+1)*30,this.frame );
    }
    @Override
    public void painSelf(Graphics g) {
        super.painSelf(g);
    }
}
