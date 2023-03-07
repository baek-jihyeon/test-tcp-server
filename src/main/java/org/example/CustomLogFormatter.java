package org.example;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CustomLogFormatter extends Formatter {
    public String getHead(Handler h) {
        return "";
    }
    public String format(LogRecord rec) {
        String log =  rec.getMessage()+"\n";
        return log;
    }
    public String getTail(Handler h) {
        return "";
    }
}
