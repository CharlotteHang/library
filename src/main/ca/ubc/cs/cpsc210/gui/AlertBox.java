package ca.ubc.cs.cpsc210.gui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

// The alert window
public class AlertBox {

    public static void displayAlert(String title, String alertMessage) {
        Stage alertWindow = new Stage();

// to make sure it will be taken care of
        alertWindow.initModality(Modality.APPLICATION_MODAL);
        alertWindow.setTitle(title);
        alertWindow.setMinWidth(200);

        Label label = new Label();
        label.setText(alertMessage);
        Button close = new Button("Close");
        close.setOnAction(e -> alertWindow.close());

        VBox vbox = new VBox(20);
        vbox.getChildren().addAll(label, close);
        vbox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vbox);
        alertWindow.setScene(scene);
        alertWindow.showAndWait();


    }
}
