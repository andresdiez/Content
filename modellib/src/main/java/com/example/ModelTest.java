package com.example;

import java.util.List;

public class ModelTest extends CallBack {

    public ModelTest() {
        final Model model = new ModelImpl();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                int size = model.getMessages().size();
                System.out.println("Size: " + size);

            }
        });
        t.start();
        model.whenModelLoaded(this);
    }


    @Override
    public void updateView(List<Message> messages) {
        super.updateView(messages);
        int size=messages.size();
        System.out.println("Size: " + size);
    }
}
