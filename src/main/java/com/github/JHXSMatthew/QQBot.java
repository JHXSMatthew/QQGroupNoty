package com.github.JHXSMatthew;

import foolqq.BaseQQWindowContext;
import foolqq.model.QQMsg;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by JHXSMatthew on 11/18/2017.
 */
public class QQBot {

    BaseQQWindowContext context;

    public QQBot(String picName) throws IOException, AWTException {
        context = new BaseQQWindowContext(new File(picName)) {
            @Override
            public void onMessage(String s, QQMsg qqMsg) {
                //do nothing
            }
        };
    }

    public void sendMessage(String message){
        context.writeQQMsg("gj",message);
    }

}
