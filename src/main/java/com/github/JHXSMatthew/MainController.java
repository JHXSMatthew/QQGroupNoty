package com.github.JHXSMatthew;

import java.awt.*;
import java.io.IOException;

/**
 * Created by JHXSMatthew on 11/18/2017.
 */
public class MainController implements Callback{

    private static String POINT_PICTURE = "point.png";
    private static MainController controller;
    private QQBot bot;
    private XmppReceiver receiver;


    public MainController() throws IOException, AWTException {
        receiver = new XmppReceiver(this);
        bot = new QQBot(POINT_PICTURE);
    }

    public MainController getInstance(){
        return controller;
    }

    public static void main(String[] args) throws IOException, AWTException {
        controller = new MainController();
    }
}
