package org.example.l28;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class Model implements IModel {

    private IController iController;
    private Image preloadedImage;
    private Random random = new Random();

    public Model() {
        preloadRandomImage();
    }

    @Override
    public void setiController(IController iController) {
        this.iController = iController;
    }

    @Override
    public void doIt() {
        int a = random.nextInt(1, 100);
        iController.removeAll();
        if (a > 20) {
            iController.setText(getRandomChuckJoke());
        } else {
            showRandomImage();
        }
    }


    public void preloadRandomImage() {
        String url = "https://picsum.photos/800/600";
        preloadedImage = new Image(url, true); // true = background loading
    }


    public void showRandomImage() {
        if (preloadedImage.getProgress() >= 1.0 && !preloadedImage.isError()) {
            iController.setImage(preloadedImage);
            preloadRandomImage();
        } else {
            preloadedImage.progressProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal.doubleValue() >= 1.0 && !preloadedImage.isError()) {
                    Platform.runLater(() -> {
                        iController.setImage(preloadedImage);
                        preloadRandomImage();
                    });
                }
            });
        }
    }


    public static String getRandomChuckJoke() {
        String apiUrl = "https://api.chucknorris.io/jokes/random";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = in.readLine()) != null) {
                response.append(line);
            }

            in.close();
            JSONObject json = new JSONObject(response.toString());
            return json.getString("value");

        } catch (Exception e) {
            e.printStackTrace();
            return "Помилка отримання жарту.";
        }
    }

    public static void main(String[] args) {
        System.out.println(getRandomChuckJoke());
    }
}
