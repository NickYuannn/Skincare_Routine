package sample;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.swing.border.Border;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.util.ArrayList;
import java.io.File;
import java.io.Writer;
import java.io.*;
import java.util.Scanner;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;

public class login {
    private String password;
    private String username;
    public static String nameOfUser;

    public static String deletedProds;
    public static String inRoutineProductList;
    public static String inRoutineProductListNames;
    public static String productList;
    public static String setNightRou;
    public static String setRou;
    public static String track;
    public static String wishesList;
    ArrayList<login> logins = new ArrayList<login>();

    public login(String theUsername, String thePassword)
    {
        password = thePassword;
        username = theUsername;
        nameOfUser = theUsername;

        /*deletedProds = theUsername + "deletedProds.txt";
        inRoutineProductList = theUsername + "inRoutineProductList.txt";
        inRoutineProductListNames = theUsername + "inRoutineProductListNames.txt";
        productList = theUsername + "productList.txt";
        setRou = theUsername + "setRou.txt";
        setNightRou = theUsername + "setNightRou.txt";
        track = theUsername + "track.txt";
        wishesList = theUsername + "wishesList.txt";*/
    }

    public login(Stage yuanViewStage){
        GridPane root = new GridPane();
        Scene scene = new Scene(root, 1000, 1000);

        TextField nameText = new TextField();
        TextField passText = new TextField();
        Button submitB = new Button("Create Account");
        Button login = new Button("Login");
        Label user = new Label("Username: ");
        Label pw = new Label("Password: ");
        Label welcome = new Label("");
        Button back = new Button("back");
        Label failed = new Label("failed to login");
        Label exists = new Label("This username already exists or you need to enter password");
        //Image doggy = new Image("https://tinyurl.com/4n7pr5vs");
        //ImageView doggyImageView = new ImageView(doggy);
        exists.setVisible(false);
        failed.setVisible(false);


        //root.add(doggyImageView, 0 ,9);
        root.add(user, 0 , 1);
        root.add(nameText, 0, 2);
        root.add(pw, 0, 3);
        root.add(passText, 0, 4);
        root.add(submitB, 0, 5);
        root.add(login, 0 ,6);
        root.add(failed,0, 7);
        root.add(exists, 0, 8);

        BorderPane nickPane = new BorderPane();
        VBox vNick = new VBox();
        nickPane.setTop(vNick);
        vNick.getChildren().add(back);
        vNick.getChildren().add(welcome);


        ArrayList<login> str = new ArrayList<login>();
        try{
            Scanner s = new Scanner(new File("output.txt"));
            Scanner d = new Scanner(new File("outputPass.txt"));
            while (s.hasNext())
            {
                login cli = new login(s.next(), d.next());
                str.add(cli);
            }

            s.close();
        }catch(Exception wooo){System.out.println(wooo);}

        submitB.setOnAction(value ->
        {
            failed.setVisible(false);
            exists.setVisible(false);
            boolean check = true;
            if (passText.getText().isEmpty())
            {
                check = false;
            }
            for (int i = 0; i < str.size();i++)
            {
                if (str.get(i).getUsername().equals(nameText.getText()))
                {
                    check = false;
                }
            }
            if (!check)
            {
                exists.setVisible(true);
            }
            else if (check)
            {
                String password = passText.getText();
                String name = nameText.getText();
                String gradeInt= password;
                login stu = new login(name, gradeInt);
                str.add(stu);
                try{

                    Writer w = new FileWriter("output.txt", true);
                    w.write(nameText.getText() + "\n");
                    w.close();
                }catch (Exception e){System.out.println("error");}

                try{

                    Writer w = new FileWriter("outputPass.txt", true);
                    w.write(passText.getText() + "\n");
                    w.close();
                }catch (Exception e){System.out.println("error");}
                nameOfUser = nameText.getText();
                deletedProds = nameText.getText() + "deletedProds.txt";
                inRoutineProductList = nameText.getText() + "inRoutineProductList.txt";
                inRoutineProductListNames = nameText.getText() + "inRoutineProductListNames.txt";
                productList = nameText.getText() + "productList.txt";
                setRou = nameText.getText() + "setRou.txt";
                setNightRou = nameText.getText() + "setNightRou.txt";
                track = nameText.getText() + "track.txt";
                wishesList = nameText.getText() + "wishesList.txt";
                new menu(yuanViewStage);
            }
        });

        back.setOnAction(event -> {
            yuanViewStage.show();
            failed.setVisible(false);
        });

        login.setOnAction(event -> {
            failed.setVisible(false);
            exists.setVisible(false);
            nameOfUser = nameText.getText();
            deletedProds = nameText.getText() + "deletedProds.txt";
            inRoutineProductList = nameText.getText() + "inRoutineProductList.txt";
            inRoutineProductListNames = nameText.getText() + "inRoutineProductListNames.txt";
            productList = nameText.getText() + "productList.txt";
            setRou = nameText.getText() + "setRou.txt";
            setNightRou = nameText.getText() + "setNightRou.txt";
            track = nameText.getText() + "track.txt";
            wishesList = nameText.getText() + "wishesList.txt";

            failed.setVisible(true);
            for (int k = 0; k < str.size(); k++) {
                String info = (nameText.getText() + " " + passText.getText());
                if(nameText.getText().equals(str.get(k).getUsername()) && passText.getText().equals(str.get(k).getPassword()))
                {
                    welcome.setText("Welcome " + nameText.getText());
                    login.setVisible(true);
                    //primaryStage.hide();
                    failed.setVisible(true);
                    new menu(yuanViewStage);
                }
            }
            System.out.println("The name of the user is " + nameOfUser);
            System.out.println(deletedProds);
            System.out.println(inRoutineProductList);
            System.out.println(inRoutineProductListNames);
            System.out.println(productList);
            System.out.println(setNightRou);
            System.out.println(setRou);
            System.out.println(track);
            System.out.println(wishesList);

        });

        yuanViewStage.setScene(scene);
        yuanViewStage.show();
    }


    public String getUsername()
    {
        return username;
    }

    public String getPassword(){
        return password;
    }

    public String toString()
    {
        return username + " " + password;
    }


}
