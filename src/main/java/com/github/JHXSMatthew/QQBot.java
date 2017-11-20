package com.github.JHXSMatthew;

import foolqq.BaseQQWindowContext;
import foolqq.ImgChkHelper;
import foolqq.model.QQMsg;
import org.jnativehook.NativeHookException;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by JHXSMatthew on 11/18/2017.
 */
public class QQBot {

    BaseQQWindowContext context;

    public QQBot(String picName) throws IOException, AWTException, NativeHookException {
        System.out.println("图片验证: "+  ImgChkHelper.validImage("ps.png","point.png") + " "
                +  ImgChkHelper.validImage("ps.png","target.png"));

        context = new BaseQQWindowContext(new File(picName)) {
            @Override
            public void onMessage(String s, QQMsg qqMsg) {
                System.out.println("QQ -> " + qqMsg.toString());
            }
        };
    }

    public void sendMessage(String message){
        context.writeQQMsg("target",message);
    }

}
