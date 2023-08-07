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
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class track {
    private String feeling;
    private String date;
    ArrayList<track> tracking = new ArrayList<track>();
    public track(String feeling, String date)
    {
        this.feeling = feeling;
        this.date = date;
    }

    public track(Stage yuanViewStage)
    {
        GridPane root = new GridPane();
        root.setHgap(70.00);

        Button back = new Button("Back");
        Scene scene = new Scene(root, 1000, 1000);
        VBox nickVBox = new VBox();
        root.add(nickVBox, 0, 0);

        nickVBox.getChildren().add(back);

        Label title = new Label("Track your skin");
        VBox nickCenter = new VBox();
        root.add(nickCenter, 2,0);
        nickCenter.getChildren().add(title);

        back.setOnAction(event -> {
            new menu(yuanViewStage);
        });


        Label hru = new Label("How is your skin doing?");
        MenuItem veryGood = new MenuItem("Very Good");
        MenuItem good = new MenuItem("Good");
        MenuItem average = new MenuItem("Eh");
        MenuItem notGood = new MenuItem("Not Good");
        MenuItem bad = new MenuItem("Bad");
        MenuButton options = new MenuButton("options", null, veryGood, good, average, notGood, bad);
        Button addTrack = new Button("Add a track!");
        VBox nickBox = new VBox();

        nickBox.getChildren().add(hru);
        DatePicker pick = new DatePicker();
        nickBox.getChildren().add(pick);
        nickBox.getChildren().add(options);
        nickBox.getChildren().add(addTrack);
        root.add(nickBox, 3, 1);



        //recover the old ones
        VBox trackSide = new VBox();
        root.add(trackSide, 1, 1);
        try{
            Scanner s = new Scanner(new File(login.track));
            while (s.hasNext())
            {
                tracking.add(new track(s.nextLine(), s.nextLine()));
            }
        }catch(Exception wooo){System.out.println(wooo);}

        for (int i =0; i < tracking.size() - 1; i++)
        {
            for (int d = i + 1; d < tracking.size(); d++)
            {
                track tempTrack = new track(tracking.get(d).getFeeling(), tracking.get(d).getDate());
                String[] splitDate1 = tracking.get(i).getDate().split("-");
                String[] splitDate2 = tracking.get(d).getDate().split("-");

                int s1 = (Integer.parseInt(splitDate1[0])*365) + (Integer.parseInt(splitDate1[1]) * 31) + (Integer.parseInt(splitDate1[2]));
                int s2 = (Integer.parseInt(splitDate2[0])*365) + (Integer.parseInt(splitDate2[1]) * 31) + (Integer.parseInt(splitDate2[2]));

                if (s1 > s2)
                {
                    tracking.set(d, tracking.get(i));
                    tracking.set(i, tempTrack);
                }
            }
            System.out.println("Sorted iteration: " + i);
        }

        for (int i = 0; i < tracking.size(); i++)
        {
            String theTrack = "";
            theTrack = "Condition: " + tracking.get(i).getFeeling() + "\n" + "Date: " + tracking.get(i).getDate();
            System.out.println(theTrack + " " + i + " button made");
            Button dailyTracks = new Button(theTrack);
            dailyTracks.setMaxSize(200, 100);

            if (tracking.get(i).getFeeling().equals("Very Good"))
            {
                dailyTracks.setStyle("-fx-background-color: #00ff00;");
            }
            if (tracking.get(i).getFeeling().equals("Good"))
            {
                dailyTracks.setStyle("-fx-background-color: #90ee90;");
            }
            if (tracking.get(i).getFeeling().equals("Eh"))
            {
                dailyTracks.setStyle("-fx-background-color: #ffff00;");
            }
            if (tracking.get(i).getFeeling().equals("Not Good"))
            {
                dailyTracks.setStyle("-fx-background-color: #ffa500;");
            }
            if (tracking.get(i).getFeeling().equals("bad"))
            {
                dailyTracks.setStyle("-fx-background-color: #ff0000;");
            }
            trackSide.getChildren().add(dailyTracks);
        }
        //



        veryGood.setOnAction(event -> {
            feeling = "Very Good";
        });
        good.setOnAction(event -> {
            feeling = "Good";
        });
        average.setOnAction(event -> {
            feeling = "Eh";
        });
        notGood.setOnAction(event -> {
            feeling = "Not Good";
        });
        bad.setOnAction(event -> {
            feeling = "bad";
        });

        addTrack.setOnAction(event -> {
            tracking.add(new track(this.feeling, pick.getValue().toString()));
            try{
                Writer w = new FileWriter(login.track, true);
                w.write(feeling + "\n");
                w.write(pick.getValue().toString() + "\n");
                w.close();
            }catch(Exception wooo){System.out.println(wooo);}
            new track(yuanViewStage);
        });

        yuanViewStage.setScene(scene);
        yuanViewStage.show();
    }

    public String getFeeling()
    {
        return feeling;
    }

    public String getDate()
    {
        return date;
    }



}
