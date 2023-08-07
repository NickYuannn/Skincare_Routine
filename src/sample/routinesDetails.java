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

public class routinesDetails {

    //take name of the button and see what product matches the same name
    public routinesDetails(Button button, ArrayList<routines> rou, Stage yuanViewStage, ArrayList<products> p, ArrayList<products> list)
    {
        BorderPane root = new BorderPane();
        Scene yuanInfoScene = new Scene(root, 400, 400);
        Button back = new Button("Back");
        Button delete = new Button("Delete Routine");
        VBox nickBox = new VBox();
        root.setTop(nickBox);

        nickBox.getChildren().add(back);

        back.setOnAction(event -> {
            new routines(yuanViewStage);
        });
        //the products in it
        for (int i = 0; i < p.size(); i++)
        {
            Button prods = new Button(p.get(i).getName());
            nickBox.getChildren().add(prods);
            products newProd = new products(p.get(i).getName(), p.get(i).getBrand(), p.get(i).getType(), p.get(i).getPrice(), p.get(i).getExpirationDate());
            prods.setOnAction(event -> {
                new productsDetails(prods, p, yuanViewStage, 1, newProd);
            });
        }
        //the delete button, write from file to arraylist and delete the name of what matches to the button
        nickBox.getChildren().add(delete);
        delete.setOnAction(event -> {
            ArrayList<routines> rous = new ArrayList<routines>();
            try {

                Scanner s = new Scanner(new File(login.inRoutineProductListNames));
                Scanner d = new Scanner(new File(login.inRoutineProductList));

                System.out.println(login.inRoutineProductListNames + "is what it will be deleted from");

                while (s.hasNext())
                {
                    ArrayList<products> inRousProd = new ArrayList<products>();
                    String name = s.nextLine();
                    String[] nameSplit = name.split("\\s");

                    for (int i = 0; i < Integer.parseInt(nameSplit[nameSplit.length - 1]); i++)
                    {
                        inRousProd.add(new products(d.nextLine(), d.nextLine(), d.nextLine(), Double.parseDouble(d.nextLine()), d.nextLine()));
                    }

                    rous.add(new routines(name, inRousProd));
                }
            }catch(Exception wooo){System.out.println(wooo);}

            for (int i = 0; i < rous.size(); i++)
            {
                String ori = "";
                String[] nameSplit = rous.get(i).getName().split("\\s");
                for (int y = 0; y < nameSplit.length - 1; y++)
                {
                    if (y == nameSplit.length -2)
                    {
                        ori += nameSplit[y];
                    }
                    else
                    {
                        ori += nameSplit[y] + " ";
                    }

                }
                System.out.println(ori + "original name");
                System.out.println((button.getText()) + "name of button");
                if (ori.equals(button.getText()))
                {
                    System.out.println(rous.get(i).getName() + "was deleted");
                    rous.remove(i);
                    i--;
                }
            }
            //empties the txt file so the new array without the deleted routine will be rewritten
            try{
                Writer d = new FileWriter(login.inRoutineProductListNames, false);
                d.close();
            }catch (Exception e){System.out.println("error");}
            try{
                Writer x = new FileWriter(login.inRoutineProductList, false);
                x.close();
            }catch (Exception e){System.out.println("error");}
            try{
                Writer z = new FileWriter(login.inRoutineProductListNames);
                Writer a = new FileWriter(login.inRoutineProductList);
                for (int b = 0; b < rous.size(); b++)
                {
                    z.write(rous.get(b).getName() + "\n");
                    System.out.println(rous.get(b).getName() + "is written into the name file");
                    for (int i = 0; i < rous.get(b).getProds().size(); i++)
                    {
                        a.write(rous.get(b).getProds().get(i).getName() + "\n");
                        a.write(rous.get(b).getProds().get(i).getBrand()+ "\n");
                        a.write(rous.get(b).getProds().get(i).getType()+ "\n");
                        a.write(rous.get(b).getProds().get(i).getPrice()+ "\n");
                        a.write(rous.get(b).getProds().get(i).getExpirationDate()+ "\n");
                    }
                }
                z.close();
                a.close();
            }catch (Exception e){System.out.println("error");}
            for (int b = 0; b < rous.size();b++)
            {
                System.out.println(rous.get(b).getName() + " is in the rous arr");
            }
            new  routines(yuanViewStage);
        });



        yuanViewStage.setScene(yuanInfoScene);
        yuanViewStage.show();
    }
}
