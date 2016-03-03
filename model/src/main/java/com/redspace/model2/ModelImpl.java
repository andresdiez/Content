package com.redspace.model2;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("SynchronizeOnNonFinalField")
public final class ModelImpl implements Model {
    private List<Message> messages=Collections.synchronizedList(new ArrayList<Message>());

    private boolean loaded;
    private final List<CallBack> clients = new ArrayList<>();

    public ModelImpl() {
        new UrlCall().loadMessages(new CallBack() {
            @Override public void updateView(List<Message> messages) {

                final List<CallBack> clientCopy;
                synchronized (clients) {
                    loaded = true;
                    clientCopy = new ArrayList<>(clients);
                }
                for (CallBack callBack : clientCopy)
                    callBack.updateView(messages);
            }
        }, this);
    }

    @Override
    public void whenModelLoaded(final CallBack call) {
        synchronized (clients) {
            if (loaded) call.updateView(messages);
            else clients.add(call);
        }
    }


    @Override
    public Message getMessage(int i) {

        synchronized (messages){ return messages.get(i); }
    }

    @Override
    public List<Message> getMessages() {
        synchronized (messages){ return Collections.unmodifiableList(messages); }
    }

    @Override
    public void deleteMessage(int i, CallBack call) {
        synchronized (messages){
            new UrlCall( messages ).deleteMessage( i , call );
        }
    }

    @Override
    public void addMessage(String t, String m, CallBack callBack) {
        synchronized (messages){
            new UrlCall( messages ).addMessage(callBack, t, m);
        }
    }


    @Override
    public void setData(final List<Message> data) {

        synchronized (messages){
            messages=data;
        }
    }

    @Override
    public void editMessage(int i, String t, String m) {
        synchronized (messages){ new UrlCall( messages).editMessage(i,t,m); }
    }



}
