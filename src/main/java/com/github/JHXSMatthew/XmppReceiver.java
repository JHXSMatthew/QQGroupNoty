package com.github.JHXSMatthew;

import rocks.xmpp.addr.Jid;
import rocks.xmpp.core.XmppException;
import rocks.xmpp.core.session.TcpConnectionConfiguration;
import rocks.xmpp.core.session.XmppClient;
import rocks.xmpp.core.stanza.model.Message;

import java.io.*;
import java.util.Arrays;

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


    public XmppReceiver(Callback observer) throws IOException, XmppException {
        this.observer = observer;
        loadConfig();
        setUp();
        System.out.println("配置加载完成....");

        System.out.println("连接中...");

        connect();
        System.out.println("登陆成功,当前用户 Jid: " + client.getConnectedResource());


    }

    private void connect() throws XmppException {
        client = XmppClient.create(HOST);
        client.connect();

        client.addInboundMessageListener(e ->{
            Message message = e.getMessage();
            System.err.println("------------------------------------");
            System.err.println("Message: " + message.getBody());
            System.err.println("------------------------------------");
            if(message.getFrom().toString().contains("directorbot")){
              observer.onMessageReceived(message);
            }
        });

        client.login(USERNAME,PASSWORD);
    }

    public void run() throws InterruptedException {
        synchronized (this) {
            wait();
        }
    }

    private void loadConfig() throws IOException {
        File f = new File("config");
        BufferedReader reader = new BufferedReader(new FileReader(f));
        String line = reader.readLine();
        String[] split = line.split("\\|");
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
