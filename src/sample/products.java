package sample;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

public class products {
    private String name;
    private String brand;
    private String type;
    private double price;
    private String expirationDate;
    public ArrayList<products> prodList = new ArrayList<products>();
    public ArrayList<Button> buttonz = new ArrayList<Button>();
    public ArrayList<products> deleted = new ArrayList<products>();
    public ArrayList<String> alphabet = new ArrayList<String>();



    public products(String theName,String theBrand, String theType, double thePrice, String theExpirationDate){
        this.name = theName;
        this.brand = theBrand;
        this.type = theType;
        this.price = thePrice;
        this.expirationDate = theExpirationDate;
    }
    public products(Stage yuanViewStage){
        GridPane root = new GridPane();
        root.setHgap(70.00);

        //input the previous things as btns
        try {
            Scanner s = new Scanner(new File(login.deletedProds));
            while(s.hasNext())
            {
                deleted.add(new products(s.nextLine(), s.nextLine(), s.nextLine(), Double.parseDouble(s.nextLine()), s.nextLine()));;
            }
        }catch(Exception wooo){System.out.println(wooo);}
        for (int i = 0; i < deleted.size(); i++)
        {
            System.out.println(deleted.get(i).getName() + "will be delelted");
        }
        try {
            Scanner s = new Scanner(new File(login.productList));
            while(s.hasNext())
            {
                products prod = new products(s.nextLine(), s.nextLine(), s.nextLine(), Double.parseDouble(s.nextLine()), s.nextLine());
                System.out.println(prod.getName() + "is pre existing");
                //(prod.getBrand());
                //System.out.println(prod.getType());
                //System.out.println(prod.getPrice());
                //System.out.println(prod.getExpirationDate());
                prodList.add(prod);
            }
            s.close();
        }catch(Exception wooo){System.out.println(wooo);}
        for (int i = 0; i < prodList.size(); i++)
        {
            for (int k = 0; k < deleted.size(); k++)
            {
                if (prodList.get(i).getName().equals(deleted.get(k).getName()))
                {
                    prodList.remove(i);
                    deleted.remove(k);
                    k--;
                }
            }
        }
        //end of the recovery

        Button back = new Button("Back");
        Scene scene = new Scene(root, 1000, 1000);
        VBox nickVBox = new VBox();
        root.add(nickVBox,0,0);

        nickVBox.getChildren().add(back);

        for (int i = 0; i < prodList.size();i++)
        {
            Button oldB = new Button(prodList.get(i).getName());
            oldB.setMinSize(50, 25);
            buttonz.add(oldB);
            nickVBox.getChildren().add(oldB);
            products oldProd = new products(prodList.get(i).getName(), prodList.get(i).getBrand(),prodList.get(i).getType(),prodList.get(i).getPrice(),prodList.get(i).getExpirationDate());

            oldB.setOnAction(h -> {
                new productsDetails(oldB, prodList, yuanViewStage, 0, oldProd);
            });
        }

        Label title = new Label("Products");
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
        Label exLabel = new Label("Expiration date: ");
        DatePicker expT = new DatePicker();
        Button add = new Button("Add Product");
        Label errorCheck = new Label("submit a name, a brand, a type,  price with only numbers, and an exp date");
        errorCheck.setVisible(false);

        VBox buts = new VBox();
        root.add(buts,3, 0);

        buts.getChildren().add(nameT);
        buts.getChildren().add(brandT);
        buts.getChildren().add(typeT);
        buts.getChildren().add(priceT);
        buts.getChildren().add(exLabel);
        buts.getChildren().add(expT);
        buts.getChildren().add(add);
        buts.getChildren().add(errorCheck);


        add.setOnAction(event -> {
            // change it so it can take a product parameter
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

            if(nameT.getText().isEmpty() || brandT.getText().isEmpty() || typeT.getText().isEmpty() || priceT.getText().isEmpty() || expT.getValue() == null)
            {
                valid = false;
            }

            if (valid == true)
            {
                String nameName = nameT.getText();
                String name1 = nameName;
                int k = 0;
                for (int i =0; i < prodList.size(); i++)
                {
                    //System.out.println(prodList.get(i).getName() + "is being compared");
                    if (name1.equals(prodList.get(i).getName()))
                    {
                        name1 = nameName + " (" + (i + 1) + ")";
                    }
                }
                nameName = name1;
                //System.out.println(nameName + "is trying to be added");
                products newProd = new products(nameName,brandT.getText(),typeT.getText(),Double.parseDouble(priceT.getText()),expT.getValue().toString());
                prodList.add(newProd);
                Button newP = new Button(nameName);
                newP.setMinSize(50, 25);
                nickVBox.getChildren().add(newP);
                buttonz.add(newP);

                newP.setOnAction(h -> {
                    new productsDetails(newP, prodList, yuanViewStage, 0, newProd);
                });
                try{

                    Writer w = new FileWriter(login.productList, true);
                    w.write(nameName + "\n");
                    w.write(brandT.getText() + "\n");
                    w.write(typeT.getText() + "\n");
                    w.write(priceT.getText() + "\n");
                    w.write(expT.getValue().toString() + "\n");
                    w.close();
                }catch (Exception e){System.out.println("error");}
            }
            else
            {
                System.out.println("price no work");
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
    public String  getExpirationDate()
    {
        return expirationDate;
    }



}
