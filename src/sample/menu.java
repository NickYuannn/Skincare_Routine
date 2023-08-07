package sample;
import javafx.event.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.event.EventHandler.*;
import javafx.event.ActionEvent.*;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class menu {
    public menu(Stage yuanViewStage) {
        BorderPane yuanPaneForView = new BorderPane();
        Scene menuScene = new Scene(yuanPaneForView, 1000, 1000);
        String nOU = login.nameOfUser;

        Button skincare = new Button("Skincare routines");
        Button products = new Button("Products");
        Button wishlist = new Button("wishlist");
        Button skinDiary = new Button("Track your skin");
        Button back = new Button("Back to login screen");
        Label welcome = new Label("Welcome " +login.nameOfUser + " to the skincare app created by Nick Yuan");
        Button deleteAcc = new Button("delete account");

        VBox nickBox = new VBox();
        yuanPaneForView.setTop(nickBox);
        nickBox.getChildren().add(skincare);
        nickBox.getChildren().add(products);
        nickBox.getChildren().add(wishlist);
        nickBox.getChildren().add(skinDiary);
        nickBox.getChildren().add(back);
        nickBox.getChildren().add(welcome);
        nickBox.getChildren().add(deleteAcc);

        ArrayList<String> theFile = new ArrayList<String>();
        theFile.add(login.deletedProds);
        theFile.add(login.inRoutineProductListNames);
        theFile.add(login.inRoutineProductList);
        theFile.add(login.wishesList);
        theFile.add(login.productList);
        theFile.add(login.setNightRou);
        theFile.add(login.setRou);
        theFile.add(login.track);

        deleteAcc.setOnAction(event -> {
            ArrayList<login> logs = new ArrayList<login>();
            try{
                try{
                    Writer x = new FileWriter(login.deletedProds, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer x = new FileWriter(login.inRoutineProductListNames, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer x = new FileWriter(login.inRoutineProductList, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer x = new FileWriter(login.wishesList, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer x = new FileWriter(login.productList, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer x = new FileWriter(login.setNightRou, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer x = new FileWriter(login.setRou, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer x = new FileWriter(login.track, false);
                    x.close();
                }catch (Exception e){System.out.println("error");}

                new File(login.deletedProds).delete();
                new File(login.inRoutineProductListNames).delete();
                new File(login.inRoutineProductList).delete();
                new File(login.wishesList).delete();
                new File(login.productList).delete();
                new File(login.setNightRou).delete();
                new File(login.setRou).delete();
                new File(login.track).delete();
            }catch(Exception wooo){System.out.println(wooo);}
            try {
                Scanner s = new Scanner(new File("output.txt"));
                Scanner n = new Scanner(new File("outputPass.txt"));
                while (s.hasNext())
                {
                    logs.add(new login(s.nextLine(), n.nextLine()));
                }
            }catch(Exception wooo){System.out.println(wooo);}
            try {
                Writer w = new FileWriter("output.txt", false);
                w.close();
            }catch(Exception wooo){System.out.println(wooo);}
            try {
                Writer w = new FileWriter("outputPass.txt", false);
                w.close();
            }catch(Exception wooo){System.out.println(wooo);}



            try {
                Writer w = new FileWriter("outputPass.txt", true);
                Writer d = new FileWriter("output.txt", true);
                for (int i = 0; i < logs.size(); i++)
                {
                    if (logs.get(i).getUsername().equals(nOU))
                    {
                        System.out.println("Removing " + logs.get(i).getUsername() + " because it is the same as " + nOU);
                        logs.remove(i);
                        i--;
                    }
                }

                for (int i = 0; i < logs.size();i++)
                {
                    w.write(logs.get(i).getPassword() + "\n");
                    d.write(logs.get(i).getUsername() + "\n");
                    System.out.println("User: " + logs.get(i).getUsername() + " Pass: " + logs.get(i).getPassword());
                }

                d.close();
                w.close();
            }catch(Exception wooo){System.out.println(wooo);}

            new login(yuanViewStage);
        });



        skincare.setOnAction(event -> {
            new routines(yuanViewStage);
        });

        products.setOnAction(event -> {
            new products(yuanViewStage);
        });

        wishlist.setOnAction(event -> {
            new wishlist(yuanViewStage);
        });

        skinDiary.setOnAction(event -> {
            new track(yuanViewStage);
        });
        back.setOnAction(event -> {
            new login(yuanViewStage);
        });

        yuanViewStage.setScene(menuScene);
        yuanViewStage.show();
    }



}
