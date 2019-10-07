package com.java.online;

import com.java.online.fx.Launch;
import com.java.online.fx.TextAreaAppender;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class Server extends Application implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ApplicationContext context;

    public static void main(String[] args) {
        launch(Launch.args = args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initView(stage);
        Thread.sleep(5000);
        Launch.springboot =
                SpringApplication.run(Server.class, Launch.args);
    }

    public void initView(Stage stage) throws Exception{
        Launch.stage = stage;
        BorderPane root = FXMLLoader.load(getClass()
                .getResource("/fx/xml/server.fxml"));
        Scene scene = new Scene(root,750,350);
        TextAreaAppender.setTextArea((TextArea) scene.lookup("#log"));
        scene.setFill(Color.TRANSPARENT);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void stop() {
       Launch.springboot.close();
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            String http = "http://%s:%d/";
            String ip = InetAddress.getLocalHost().getHostAddress();
            int port = context.getBean(Environment.class)
                    .getProperty("server.port", Integer.class, 8080);
            Label label = (Label) Launch.stage.getScene().lookup("#host");
            label.setText(String.format(http, ip ,port));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


}
