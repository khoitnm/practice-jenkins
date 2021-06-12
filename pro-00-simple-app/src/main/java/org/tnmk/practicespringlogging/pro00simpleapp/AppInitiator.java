package org.tnmk.practicespringlogging.pro00simpleapp;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.tnmk.practicespringlogging.pro00simpleapp.story.SimpleLogWriting;

@Component
public class AppInitiator {
    private SimpleLogWriting simpleLogWriting = new SimpleLogWriting();

    @EventListener(ApplicationReadyEvent.class)
    public void ready() {
        simpleLogWriting.writeHello();
    }
}
