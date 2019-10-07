package com.java.online.fx;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

public class TextAreaAppender extends WriterAppender {

    private static volatile TextArea textArea;

    public static void setTextArea(final TextArea textArea) {
        TextAreaAppender.textArea = textArea;
    }

    @Override
    public void append(final LoggingEvent event) {
        final String message = this.layout.format(event);
        try {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        if (textArea != null) {
                            if (textArea.getText().length() == 0) {
                                textArea.setText(message);
                            } else {
                                textArea.selectEnd();
                                textArea.insertText(textArea.getText().length(),
                                        message);
                            }
                        }
                    } catch (final Throwable t) {
                        t.printStackTrace();
                    }
                }
            });
        } catch (final IllegalStateException e) {
            e.printStackTrace();
        }
    }
}