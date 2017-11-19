package com.github.JHXSMatthew;

import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;

import java.io.*;

/**
 * Created by JHXSMatthew on 11/18/2017.
 */
public class XmppReceiver {

    private Callback observer;
    private XmppClient client;

    private static String HOST;
    private static String PORT;
    private static String USERNAME;
    private static String PASSWORD;

    private TcpConnectionConfiguration tcpConnectionConfiguration;


    public XmppReceiver(Callback observer) throws IOException {
        this.observer = observer;
        loadConfig();
        setUp();
        client = XmppClient.create(HOST);

    }






    private void loadConfig() throws IOException {
        File f = new File("config");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();
        String[] split = line.split("|");
        HOST = split[0];
        PORT = split[1];
        USERNAME = split[2];
        PASSWORD = split[3];
    }

    private void setUp(){
        tcpConnectionConfiguration = TcpConnectionConfiguration.builder()
            .hostname(HOST)
            .port(Integer.parseInt(PORT))
            .build();
    }


}
