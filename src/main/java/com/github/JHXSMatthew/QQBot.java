package com.github.JHXSMatthew;

import foolqq.BaseQQWindowContext;
import foolqq.ImgChkHelper;
import foolqq.model.QQMsg;
import org.jnativehook.NativeHookException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.util.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by JHXSMatthew on 11/18/2017.
 */
public class QQBot {

    private BaseQQWindowContext context;
    private List<String> lastMessages;
    private XmppReceiver receiver;

    private String[] instance =new String[]{
            "target", "target2"
    };

    public QQBot(String picName, XmppReceiver receiver) throws IOException, AWTException, NativeHookException {
        Robot r = new Robot();
        BufferedImage bf = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        ImageIO.write(bf,"png",new File("ps.png"));
        for(int i = 0 ; i < instance.length ; i ++){
            System.out.println("图片验证I: "+  ImgChkHelper.validImage("ps.png","point.png") + " "
                    +  ImgChkHelper.validImage("ps.png",instance[i] + ".png"));
        }

        lastMessages = new ArrayList<>();
        this.receiver = receiver;
        context = new BaseQQWindowContext(new File(picName)) {
            @Override
            public void onMessage(String s, QQMsg qqMsg) {
                System.out.println("QQ -> " + qqMsg.toString());
                if(qqMsg.getContent().startsWith("!") && qqMsg.getContent().length() > 1){
                    String content = qqMsg.getContent().substring(1).trim();
                    switch (content){
                        case "status":
                            sendMessage(s,"监听: " + Arrays.toString(context.getListening().toArray(new String[context.getListening().size()])) + "\n" +
                                    "状态: 连接->" +  receiver.isConnected() + " 认证->" + receiver.isAuth() );
                            if(!receiver.isConnected()){
                                synchronized (receiver){
                                    receiver.notify();
                                }
                            }
                            break;
                        case "hehe":
                            sendMessage(s,"呵呵");
                            break;
                        case "restart":
                            sendMessage(s,"收到，重启。");
                            synchronized (receiver){
                                receiver.notify();
                            }
                            sendMessage(s,"收到，重启完毕。");

                            break;
                        case "toall":
                            sendMessageAll("测试全体信息");
                            break;
                        default:
                            sendMessage(s,content);
                    }
                }

            }
        };
    }

    public void sendMessage(String to, String message){
        System.out.println("send to: " + to + " message: " + message);
        try {
            context.writeQQMsg(to, message);
        } catch (Exception e) {
            System.err.println("Fail to send message to " + to);
            e.printStackTrace();
        }
    }

    public void sendMessageAll(String message){
        for(String str : instance) {
            try {
                context.writeQQMsg(str, message);
                Thread.sleep(500);
            } catch (Exception e) {
                System.err.println("Fail to send message to " + str);
                e.printStackTrace();
            }
        }
    }

}
