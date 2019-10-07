
package com.java.online;

import com.java.online.fx.Launch;
import com.java.online.fx.TextAreaAppender;
import com.java.online.util.PortUtil;
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
import org.springframework.context.ApplicationContext;

import java.net.InetAddress;
import java.util.Collections;

@SpringBootApplication
public class Server extends Application{

    @Autowired
    private ApplicationContext context;
    private int port = 80;
    private String http = "http://%s:%d/";

    public static void main(String[] args) {
        launch(Launch.args = args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        initView(stage);
        SpringApplication instance = new SpringApplication(Server.class);
        instance.setDefaultProperties(Collections.singletonMap("server.port", port));
        Launch.springboot = instance.run(Launch.args);
    }

    @Override
    public void init() throws Exception {
        while (PortUtil.available(port)){
            if(port == 80){
                port = 8080;
                continue;
            }
            port++;
        }
    }

    public void initView(Stage stage) throws Exception{
        Launch.stage = stage;
        String ip = InetAddress.getLocalHost().getHostAddress();
        BorderPane root = FXMLLoader.load(getClass()
                .getResource("/fx/xml/server.fxml"));
        Scene scene = new Scene(root,750,350);
        Label label = (Label) scene.lookup("#host");
        label.setText(String.format(http, ip, port));
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

}