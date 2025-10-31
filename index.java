package app;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class WeatherApp extends Application {
    private Label tempLabel = new Label();
    private Label descLabel = new Label();
    private Label humidityLabel = new Label();
    private Label windLabel = new Label();
    private ImageView iconView = new ImageView();

    @Override
    public void start(Stage stage) {
        TextField cityInput = new TextField();
        cityInput.setPromptText("Enter city name");

        Button searchBtn = new Button("Get Weather");
        searchBtn.setOnAction(e -> {
            String city = cityInput.getText().trim();
            if (!city.isEmpty()) {
                WeatherService.updateWeather(city, this);
            } else {
                showAlert("Please enter a city name!");
            }
        });

        VBox layout = new VBox(10, cityInput, searchBtn, iconView, tempLabel, descLabel, humidityLabel, windLabel);
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-padding: 20; -fx-background-color: linear-gradient(to bottom, #d4f1f9, #ffffff);");

        tempLabel.setFont(Font.font(18));
        descLabel.setFont(Font.font(16));

        Scene scene = new Scene(layout, 320, 450);
        stage.setTitle("Weather App");
        stage.setScene(scene);
        stage.show();
    }

    public void setWeather(String temp, String desc, String humidity, String wind, ImageView icon) {
        tempLabel.setText("Temperature: " + temp);
        descLabel.setText("Condition: " + desc);
        humidityLabel.setText("Humidity: " + humidity);
        windLabel.setText("Wind Speed: " + wind);
        iconView.setImage(icon.getImage());
    }

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Input Required");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch();
    }
}
