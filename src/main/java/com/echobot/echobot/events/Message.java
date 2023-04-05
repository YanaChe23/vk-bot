package com.echobot.echobot.events;

import java.util.ArrayList;

public class Message {
    public int date;
    public boolean important;
    public int from_id;
    public ArrayList<Object> attachments;
    public boolean is_hidden;
    public ArrayList<Object> fwd_messages;
    public int id;
    public int random_id;
    public String text;
    public int out;
    public int conversation_message_id;
    public int peer_id;
}
