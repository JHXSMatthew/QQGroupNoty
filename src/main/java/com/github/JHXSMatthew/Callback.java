package com.github.JHXSMatthew;

import rocks.xmpp.core.stanza.model.Message;

/**
 * Created by JHXSMatthew on 11/18/2017.
 */
public interface Callback {
    void onMessageReceived(Message message);
}
