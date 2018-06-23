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
        System.out.println("���ü������....");

        System.out.println("������...");

        connect();
        System.out.println("��½�ɹ�,��ǰ�û� Jid: " + client.getConnectedResource());

    }

    private void connect() throws XmppException {
        System.out.println(" - ׼������ʵ��,������:" + HOST);

        client = XmppClient.create(HOST);

        System.out.println(" - ʵ���������");
        boolean connected = false;
        while (!connected){
            try {
                System.out.println(" - ��������");
                client.connect();
                connected = true;
            } catch (Exception e) {
                System.out.println(" - ʧ��");
                System.out.println(e.getMessage());

            }
         }
        System.out.println(" - ���ӳɹ���");

        client.addInboundMessageListener(e ->{
            Message message = e.getMessage();
            System.err.println("------------------------------------");
            System.err.println("Message: " + message.getBody());
            System.err.println("------------------------------------");
            if(message.getFrom().toString().contains("directorbot")){
              observer.onMessageReceived(message);
            }
        });
        System.out.println(" - �������������");

        System.out.println(" - ���Ե�¼?");
        client.login(USERNAME,PASSWORD);
        System.out.println(" - ��¼�ɹ�");

    }

    public void run() throws InterruptedException {
        synchronized (this) {
            while (true) {
                wait();
                try {
                    client.close();
                } catch (XmppException e) {
                    e.printStackTrace();
                }
                try {
                    connect();
                } catch (XmppException e) {
                    e.printStackTrace();
                }
            }
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

    public boolean isConnected(){
        return client.isConnected();
    }

    public boolean isAuth(){
        return client.isAuthenticated();
    }




}
