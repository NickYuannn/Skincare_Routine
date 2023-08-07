package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.*;
import java.nio.file.FileSystemNotFoundException;
import java.util.ArrayList;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.Writer;
import java.io.*;
import java.util.Scanner;

public class wishlist {
    private String name;
    private String brand;
    private String type;
    private double price;
    public ArrayList<wishlist> wishListZ = new ArrayList<wishlist>();
    public ArrayList<Button> buttonz = new ArrayList<Button>();
    public ArrayList<wishlist> deleted = new ArrayList<wishlist>();

    public wishlist(String theName,String theBrand, String theType, double thePrice){
        this.name = theName;
        this.brand = theBrand;
        this.type = theType;
        this.price = thePrice;
    }
    public wishlist(Stage yuanViewStage){
        GridPane root = new GridPane();
        root.setHgap(70.00);

        //input the previous things as btns
        try {
            Scanner s = new Scanner(new File(login.wishesList));
            while(s.hasNext())
            {
                wishlist prod = new wishlist(s.nextLine(), s.nextLine(), s.nextLine(), Double.parseDouble(s.nextLine()));
                wishListZ.add(prod);
            }
            s.close();
        }catch(Exception wooo){System.out.println(wooo);}
        //end of the recovery

        Button back = new Button("Back");
        Scene scene = new Scene(root, 1000, 1000);
        VBox nickVBox = new VBox();
        root.add(nickVBox,0,0);

        nickVBox.getChildren().add(back);

        for (int i = 0; i < wishListZ.size();i++)
        {
            Button oldB = new Button(wishListZ.get(i).getName());
            oldB.setMinSize(50, 25);
            buttonz.add(oldB);
            nickVBox.getChildren().add(oldB);
            System.out.println(wishListZ.get(i).getName());
            System.out.println(wishListZ.get(i).getBrand());
            System.out.println(wishListZ.get(i).getType());
            System.out.println(wishListZ.get(i).getPrice());

            wishlist oldProd = new wishlist(wishListZ.get(i).getName(),wishListZ.get(i).getBrand(),wishListZ.get(i).getType(),wishListZ.get(i).getPrice());

            oldB.setOnAction(h -> {
                new wishlistDetails(oldB, wishListZ, yuanViewStage, oldProd);
            });
        }

        Label title = new Label("Wishlist");
        VBox nickCenter = new VBox();
        root.add(nickCenter, 2, 0);
        nickCenter.getChildren().add(title);


        back.setOnAction(event -> {
            new menu(yuanViewStage);
        });

        TextField nameT = new TextField("Name");
        TextField brandT = new TextField("Brand");
        TextField typeT = new TextField("Type");
        TextField priceT = new TextField("Price");
        Button add = new Button("Add something you wish for!");
        Label errorCheck = new Label("Enter a valid name, brand, type, and price with only numbers");
        errorCheck.setVisible(false);

        VBox buts = new VBox();
        root.add(buts,3, 0);

        buts.getChildren().add(nameT);
        buts.getChildren().add(brandT);
        buts.getChildren().add(typeT);
        buts.getChildren().add(priceT);
        buts.getChildren().add(add);
        buts.getChildren().add(errorCheck);

        add.setOnAction(event -> {
            errorCheck.setVisible(false);
            Boolean valid = true;
            for (int q = 0; q < priceT.getText().length(); q++)
            {
                if (!Character.isDigit(priceT.getText().charAt(q)) && priceT.getText().charAt(q) != '.')
                {
                    valid = false;
                    System.out.println("price is not valid");
                }
            }

            if(nameT.getText().isEmpty() || brandT.getText().isEmpty() || typeT.getText().isEmpty() || priceT.getText().isEmpty())
            {
                valid = false;
            }

            if (valid == true)
            {
                String nameName = nameT.getText();
                String name1 = nameName;
                int k = 0;
                for (int i =0; i < wishListZ.size(); i++)
                {
                    System.out.println(wishListZ.get(i).getName() + "is being compared");
                    if (name1.equals(wishListZ.get(i).getName()))
                    {
                        name1 = nameName + " (" + (i + 1) + ")";
                    }
                }
                nameName = name1;
                System.out.println(nameName + "is trying to be added");
                wishlist newProd = new wishlist(nameName,brandT.getText(),typeT.getText(),Double.parseDouble(priceT.getText()));
                wishListZ.add(newProd);
                Button newP = new Button(nameName);
                newP.setMinSize(50, 25);
                nickVBox.getChildren().add(newP);
                buttonz.add(newP);

                newP.setOnAction(h -> {
                    new wishlistDetails(newP, wishListZ, yuanViewStage, newProd);
                });
                try{

                    Writer w = new FileWriter(login.wishesList, true);
                    w.write(nameName + "\n");
                    w.write(brandT.getText() + "\n");
                    w.write(typeT.getText() + "\n");
                    w.write(priceT.getText() + "\n");
                    w.close();
                }catch (Exception e){System.out.println("error");}
            }
            else
            {
                errorCheck.setVisible(true);
            }
        });

        //search bar

        TextField searchT = new TextField("Search bar");
        Button searchB = new Button("Search");

        buts.getChildren().add(searchT);
        buts.getChildren().add(searchB);

        searchB.setOnAction(event -> {

            for (int i = 0; i < buttonz.size(); i++)
            {
                buttonz.get(i).setMinSize(50, 25);
            }
            
            for (int i = 0; i < buttonz.size();i++)
            {
                if (buttonz.get(i).getText().equals(searchT.getText()))
                {
                    buttonz.get(i).setMinSize(100, 100);
                }
            }
        });

        //end of search bar
        yuanViewStage.setScene(scene);
        yuanViewStage.show();
    }



    public String getName()
    {
        return name;
    }
    public String getBrand()
    {
        return brand;
    }
    public String getType()
    {
        return type;
    }
    public double getPrice()
    {
        return price;
    }



}
