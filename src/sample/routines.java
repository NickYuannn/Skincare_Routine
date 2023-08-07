package sample;
import com.sun.org.apache.xml.internal.dtm.ref.CoroutineManager;
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
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class routines{
    ArrayList<products> prodList = new ArrayList<products>();
    ArrayList<Button> buttonz = new ArrayList<Button>();
    ArrayList<routines> theRoutines = new ArrayList<routines>();
    ArrayList<products> inRoutine = new ArrayList<products>();
    ArrayList<products> deletedProds = new ArrayList<products>();
    ArrayList<String> theRNames = new ArrayList<>();
    ArrayList<Button> routineButtons = new ArrayList<Button>();
    String nameOfRoutine;
    String setter;
    String nightSetter;
    int count = 0;

    public routines(String theName, ArrayList<products> theRoutinez)
    {
        nameOfRoutine = theName;
        for (int i = 0; i < theRoutinez.size(); i++)
        {
            inRoutine.add(theRoutinez.get(i));
        }
    }
    public routines(Stage yuanViewStage)
    {

        //recovery start prods
        try {
            Scanner s = new Scanner(new File(login.deletedProds));
            while(s.hasNext())
            {
                deletedProds.add(new products(s.nextLine(), s.nextLine(), s.nextLine(), Double.parseDouble(s.nextLine()), s.nextLine()));
            }
        }catch(Exception wooo){System.out.println(wooo);}
        try {
            Scanner s = new Scanner(new File(login.productList));
            while(s.hasNext())
            {
                products prod = new products(s.nextLine(), s.nextLine(), s.nextLine(), Double.parseDouble(s.nextLine()), s.nextLine());
                prodList.add(prod);
            }
            s.close();
        }catch(Exception wooo){System.out.println(wooo);}
        for (int i = 0; i < prodList.size(); i++)
        {
            for (int k = 0; k < deletedProds.size(); k++)
            {
                if (prodList.get(i).getName().equals(deletedProds.get(k).getName()))
                {
                    prodList.remove(i);
                    deletedProds.remove(k);
                    k--;
                }
            }
        }
        //end of recovery
        GridPane root = new GridPane();
        root.setHgap(70.00);
        VBox butBox = new VBox();
        root.add(butBox, 3, 1);
        VBox routineSide = new VBox();
        VBox setSide = new VBox();
        root.add(setSide, 1, 1);
        root.add(routineSide, 0, 1);

        Button setBtn = new Button("set day routine");
        TextField setTxtF = new TextField();
        Label needR = new Label("You need to put in a name that exists");
        needR.setVisible(false);

        Button setNightBtn = new Button("set night routine");
        TextField setNightTxtF = new TextField();
        Label needNightR = new Label("You need to put in a name that exists");
        needNightR.setVisible(false);

        setSide.getChildren().add(setBtn);
        setSide.getChildren().add(setTxtF);
        setSide.getChildren().add(needR);
        setSide.getChildren().add(setNightBtn);
        setSide.getChildren().add(setNightTxtF);
        setSide.getChildren().add(needNightR);
        try{
            ArrayList<String> set = new ArrayList<String>();
            Scanner s = new Scanner(new File(login.setRou));

            while(s.hasNext())
            {
                set.add(s.nextLine());
            }

            setter = set.get(set.size() - 1);
            //("will set " + setter + "to be routine");
            s.close();
        }catch(Exception wooo){System.out.println(wooo);}

        try{
            ArrayList<String> set = new ArrayList<String>();
            Scanner s = new Scanner(new File(login.setNightRou));

            while(s.hasNext())
            {
                set.add(s.nextLine());
            }

            nightSetter = set.get(set.size() - 1);
            //System.out.println("will set " + nightSetter + "to be night routine");
            s.close();
        }catch(Exception wooo){System.out.println(wooo);}

        //recover start for routs
        try {
            Scanner s = new Scanner(new File(login.inRoutineProductList));
            Scanner d = new Scanner(new File(login.inRoutineProductListNames));
            while (d.hasNext())
            {
                ArrayList<products> collected = new ArrayList<products>();
                String name = d.nextLine();
                String[] nameSplit = name.split("\\s");
                String original = "";
                for (int i =0 ; i < nameSplit.length -1; i++)
                {
                    if (i == nameSplit.length-2)
                    {
                        original += nameSplit[i];
                    }
                    else{
                        original += nameSplit[i] + " ";
                    }
                }
                //System.out.println(name.length());
                for (int j = 0; j < Integer.parseInt(nameSplit[nameSplit.length -1]); j++)
                {
                    collected.add(new products(s.nextLine(), s.nextLine(), s.nextLine(), Double.parseDouble(s.nextLine()), s.nextLine()));
                }
                Button oldR = new Button(original);
                if (original.equals(setter))
                {
                    oldR.setStyle("-fx-background-color:  #FFFF00;");
                }
                if (original.equals(nightSetter))
                {
                    oldR.setStyle("-fx-background-color:  #0000FF;");
                }
                routineButtons.add(oldR);
                theRNames.add(original);
                routineSide.getChildren().add(oldR);
                oldR.setOnAction(event -> {
                    new routinesDetails(oldR, theRoutines, yuanViewStage, collected, prodList);
                });
            }
            s.close();
        }catch(Exception wooo){System.out.println(wooo);}
        for (int i = 0; i < theRNames.size(); i++)
        {
            //System.out.println(theRNames.get(i) + "is one of the preexisting names");
        }
        //buttons
        Button back = new Button("Back");
        TextField name = new TextField("Name");
        Button create = new Button("create");
        Label noName = new Label("There is no name");
        noName.setVisible(false);
        create.setStyle("-fx-background-color: #CBC3E3;");
        Scene scene = new Scene(root, 1000, 1000);
        VBox nickVBox = new VBox();
        root.add(nickVBox, 0, 0);

        Label title = new Label("Routines");
        VBox nickCenter = new VBox();
        root.add(nickCenter, 2, 0);
        nickCenter.getChildren().add(title);

        nickVBox.getChildren().add(back);
        butBox.getChildren().add(create);
        butBox.getChildren().add(name);
        butBox.getChildren().add(noName);

        //show buttons on the side
        for (int i = 0; i < prodList.size();i++)
        {
            Button oldB = new Button(prodList.get(i).getName());
            buttonz.add(oldB);
            butBox.getChildren().add(oldB);

            oldB.setOnAction(event -> {
                count = 0;
                for (int k = 0; k < prodList.size(); k++) {
                    if (prodList.get(k).getName().equals(oldB.getText()))
                    {
                        inRoutine.add(prodList.get(k));
                    }
                }
                oldB.setMinSize(100, 50);
            });
        }
        //show button on the side


        create.setOnAction(event -> {
            noName.setVisible(false);
            if (!name.getText().isEmpty())
            {
                //all the buttons selected are inside of the arraylist when pressed
                //when create is pressed the buttons get added into the routine by name
                ArrayList<products> collected = new ArrayList<products>();
                //make unique names
                String nameName = name.getText();
                String name1 = nameName;

                for (int i = 0; i < theRNames.size(); i++)
                {
                    if ((name1).equals(theRNames.get(i)))
                    {
                        name1= nameName + "(" + (i+1) + ")";
                    }
                }
                nameName = name1;
                theRNames.add(nameName);

                Button newR = new Button(nameName + " ");
                routineButtons.add(newR);
                routineSide.getChildren().add(newR);
                try{
                    Writer d = new FileWriter(login.inRoutineProductListNames, true);
                    d.write((nameName + " " + (inRoutine.size()) + "\n"));
                    d.close();
                }catch (Exception e){System.out.println("error");}
                //write all of the details of the products seletcted to be in the routine
                try{
                    Writer w = new FileWriter(login.inRoutineProductList, true);
                    for (int i = 0; i < inRoutine.size(); i++)
                    {
                        w.write(inRoutine.get(i).getName() + "\n");
                        w.write(inRoutine.get(i).getBrand()+ "\n");
                        w.write(inRoutine.get(i).getType()+ "\n");
                        w.write(inRoutine.get(i).getPrice()+ "\n");
                        w.write(inRoutine.get(i).getExpirationDate()+ "\n");
                    }
                    w.close();
                }catch (Exception e){System.out.println("error");}
                for (int i = 0; i < inRoutine.size(); i++)
                {
                    collected.add(inRoutine.get(i));
                }
                newR.setOnAction(event1 -> {

                    new routinesDetails(newR, theRoutines, yuanViewStage, collected, prodList);

                });
                inRoutine.clear();
                new routines(yuanViewStage);
            }
            else{
                noName.setVisible(true);
            }


        });
        setBtn.setOnAction(event -> {


            Boolean check = false;
            try {
                Scanner s = new Scanner(new File(login.setNightRou));
                if (setTxtF.getText().equals(s.nextLine()))
                {
                    try{
                        Writer w = new FileWriter(login.setNightRou, false);
                        w.close();
                    }catch (Exception e){System.out.println("error");}
                }
            }catch(Exception wooo){System.out.println(wooo);}
            for (int i = 0; i < theRNames.size();i++)
            {
                if (setTxtF.getText().equals(theRNames.get(i)))
                {
                    check = true;
                }
            }

            if (check == false)
            {
                needR.setVisible(true);
            }
            else {
                try{
                Writer w = new FileWriter(login.setRou, false);
                w.close();
            }catch (Exception e){System.out.println("error");}
                try{
                    Writer w = new FileWriter(login.setRou, true);
                    w.write(setTxtF.getText() + "\n");
                    w.close();
                }catch (Exception e){System.out.println("error");}
                new routines(yuanViewStage);
            }
        });


        setNightBtn.setOnAction(event -> {
            Boolean check = false;

            try {
                Scanner s = new Scanner(new File(login.setRou));
                if (setNightTxtF.getText().equals(s.nextLine()))
                {
                    try{
                        Writer w = new FileWriter(login.setRou, false);
                        w.close();
                    }catch (Exception e){System.out.println("error");}
                }
            }catch(Exception wooo){System.out.println(wooo);}

            for (int i = 0; i < theRNames.size();i++)
            {
                if (setNightTxtF.getText().equals(theRNames.get(i)))
                {
                    check = true;
                }
            }

            if (check == false)
            {
                needNightR.setVisible(true);
            }
            else {
                try{
                    Writer w = new FileWriter(login.setNightRou, false);
                    w.close();
                }catch (Exception e){System.out.println("error");}
                try{
                    Writer w = new FileWriter(login.setNightRou, true);
                    w.write(setNightTxtF.getText() + "\n");
                    w.close();
                }catch (Exception e){System.out.println("error");}
                new routines(yuanViewStage);
            }


        });
        Button removeDay = new Button("Remove Day Routine");
        Button removeNight = new Button("Remove Night Routine");
        setSide.getChildren().add(removeDay);
        setSide.getChildren().add(removeNight);

        removeDay.setOnAction(event -> {
            try{
                Writer w = new FileWriter(login.setRou, false);
                w.close();
            }catch (Exception e){System.out.println("error");}
            new routines(yuanViewStage);
        });
        removeNight.setOnAction(event -> {
            try{
                Writer w = new FileWriter(login.setNightRou, false);
                w.close();
            }catch (Exception e){System.out.println("error");}
            new routines(yuanViewStage);
        });

        back.setOnAction(event1 -> {
            new menu(yuanViewStage);
        });

        yuanViewStage.setScene(scene);
        yuanViewStage.show();
    }

    public ArrayList<products> getProds()
    {
        return inRoutine;
    }
    public String getName()
    {
        return nameOfRoutine;
    }
}
