package org.tnmk.practicespringlogging.pro00simpleapp.story;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleLogWriting {
    public static Logger logger = LoggerFactory.getLogger(SimpleLogWriting.class);

    public void writeHello() {
        logger.info("Hi there Info");
    }
}
