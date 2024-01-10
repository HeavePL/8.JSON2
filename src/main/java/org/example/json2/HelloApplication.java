package org.example.json2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.*;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException, JSONException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        URL url = new URL("http://zasob.itmargen.com/4TD/");
        Text text = new Text();
        Text info = new Text();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            String responseString = response.toString();
            JSONObject jOAll = new JSONObject(responseString);
            JSONObject jOInfo = jOAll.getJSONObject("info");
            JSONObject jOGrupa2 = jOAll.getJSONObject("Grupa2");
            String returned = jOInfo.getString("przedmiot");
            returned = returned +"\n" + jOInfo.getString("prowadzacy");
            returned = returned +"\n" + jOInfo.getString("szkola");
            returned = returned +"\n" + jOInfo.getString("miasto");
            returned = returned +"\n" + jOInfo.getString("dataczas");
            String inf = returned;
            info.setText(inf);
            info.setY(30);
            info.setX(20);
            String imie = jOGrupa2.getString("11");
            text.setText(imie);
            text.setY(20);
            text.setX(20);

        } else {
            System.out.println("GET request did not work.");
        }

        Group root = new Group(text,info);
        Scene scene = new Scene(root, 200, 200);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
        con.disconnect();
    }

    public static void main(String[] args) {
        launch();
    }
}