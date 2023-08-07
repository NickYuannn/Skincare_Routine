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

public class wishlistDetails {
    public wishlistDetails(Button button, ArrayList<wishlist> prodList, Stage yuanViewStage, wishlist prod)
    {
        BorderPane root = new BorderPane();
        Scene yuanInfoScene = new Scene(root, 400, 400);

        javafx.scene.control.TextField nameD = new javafx.scene.control.TextField("name");
        javafx.scene.control.TextField typeD = new javafx.scene.control.TextField("type");
        javafx.scene.control.TextField brandD = new javafx.scene.control.TextField("brand");
        javafx.scene.control.TextField priceD = new javafx.scene.control.TextField("price");
        javafx.scene.control.Button back = new javafx.scene.control.Button("Back");
        Button delete = new Button("Delete");

        VBox nickVBox = new VBox();
        root.setLeft(nickVBox);

        nickVBox.getChildren().add(nameD);
        nickVBox.getChildren().add(typeD);
        nickVBox.getChildren().add(brandD);
        nickVBox.getChildren().add(priceD);
        nickVBox.getChildren().add(back);
        nickVBox.getChildren().add(delete);

        delete.setOnAction(event -> {
            ArrayList<String> del = new ArrayList<String>();
            try{
                Scanner s = new Scanner(new File(login.wishesList));
                while(s.hasNext())
                {
                    del.add(s.nextLine());
                }
                s.close();
            }catch (Exception e){System.out.println("error");}

            for (int i = 0; i < del.size(); i++)
            {
                if (del.get(i).equals(prod.getName()))
                {
                    del.remove(i);
                    del.remove(i);
                    del.remove(i);
                    del.remove(i);
                }
            }
            try{
                Writer d = new FileWriter(login.wishesList, false);
                d.close();
            }catch (Exception e){System.out.println("error");}
            try{
                Writer l = new FileWriter(login.wishesList, true);
                for (int i = 0; i < del.size(); i++)
                {
                    l.write(del.get(i) + "\n");
                }
                l.close();
            }catch (Exception e){System.out.println("error");}
            new wishlist(yuanViewStage);
        });

        back.setOnAction(event -> {
            new wishlist(yuanViewStage);
        });
        nameD.setText(button.getText());
        brandD.setText(prod.getBrand());
        typeD.setText(prod.getType());
        priceD.setText(Double.toString(prod.getPrice()));

        yuanViewStage.setScene(yuanInfoScene);
        yuanViewStage.show();
    }

}



