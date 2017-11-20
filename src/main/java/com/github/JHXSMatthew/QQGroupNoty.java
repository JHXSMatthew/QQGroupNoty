package com.github.JHXSMatthew;

import org.jnativehook.NativeHookException;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.stanza.model.Message;

import java.awt.*;
import java.io.IOException;

/**
 * Created by JHXSMatthew on 11/18/2017.
 */
public class QQGroupNoty implements Callback{

    private static String POINT_PICTURE = "point.png";
    private static QQGroupNoty controller;
    private QQBot bot;
    private XmppReceiver receiver;


    public QQGroupNoty() throws IOException, AWTException, XmppException, NativeHookException {
        bot = new QQBot(POINT_PICTURE);
        receiver = new XmppReceiver(this);

        try {
            receiver.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public QQGroupNoty getInstance(){
        return controller;
    }

    public static void main(String[] args) throws IOException, AWTException, XmppException, NativeHookException {
        controller = new QQGroupNoty();
    }

    @Override
    public void onMessageReceived(Message message) {
        bot.sendMessage(message.getBody());
    }
}
