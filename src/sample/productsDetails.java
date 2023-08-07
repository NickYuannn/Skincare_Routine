package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.Writer;
import java.io.*;
import java.util.Scanner;


import java.util.ArrayList;

public class productsDetails {
    public productsDetails(Button button, ArrayList<products> prodList, Stage yuanViewStage, int d, products prod)
    {
        BorderPane root = new BorderPane();
        Scene yuanInfoScene = new Scene(root, 400, 400);

        javafx.scene.control.TextField nameD = new javafx.scene.control.TextField("name");
        javafx.scene.control.TextField typeD = new javafx.scene.control.TextField("type");
        javafx.scene.control.TextField brandD = new javafx.scene.control.TextField("brand");
        javafx.scene.control.TextField priceD = new javafx.scene.control.TextField("price");
        javafx.scene.control.TextField expD = new javafx.scene.control.TextField("expiration date");
        javafx.scene.control.Button back = new javafx.scene.control.Button("Back");
        Button delete = new Button("Delete");

        VBox nickVBox = new VBox();
        root.setLeft(nickVBox);

        nickVBox.getChildren().add(nameD);
        nickVBox.getChildren().add(typeD);
        nickVBox.getChildren().add(brandD);
        nickVBox.getChildren().add(priceD);
        nickVBox.getChildren().add(expD);
        nickVBox.getChildren().add(back);
        nickVBox.getChildren().add(delete);

        delete.setOnAction(event -> {
            try {
                Writer w = new FileWriter(login.deletedProds, true);
                w.write(prod.getName() + "\n");
                w.write(prod.getBrand() + "\n");
                w.write(prod.getType() + "\n");
                w.write(prod.getPrice() + "\n");
                w.write(prod.getExpirationDate() + "\n");
                w.close();
            }catch (Exception e){System.out.println("error");}

            if (d == 0)
            {
                new products(yuanViewStage);
            }
            else if (d == 1)
            {
                new routines(yuanViewStage);
            }
        });

        back.setOnAction(event -> {
            if (d == 0)
            {
                new products(yuanViewStage);
            }
            else if (d == 1)
            {
                new routines(yuanViewStage);
            }

        });
        nameD.setText(button.getText());
        brandD.setText(prod.getBrand());
        typeD.setText(prod.getType());
        priceD.setText(Double.toString(prod.getPrice()));
        expD.setText(prod.getExpirationDate());

        yuanViewStage.setScene(yuanInfoScene);
        yuanViewStage.show();
    }

}
