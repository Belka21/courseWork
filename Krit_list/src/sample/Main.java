package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

        Journal j1 = new Journal();
        j1.addNote(new Note ( new Key(5,ValCiph.SUPER_VALUABLE, 12),5.56f));
        j1.addNote(new Note ( new Key(5,ValCiph.SUPER_VALUABLE, 12),5f));
        j1.addNote(new Note ( new Key(7,ValCiph.NON, 1),5));
        j1.addNote(new Note ( new Key(6,ValCiph.NORMAL, 6),7.5f));
        j1.addNote(new Note ( new Key(3,ValCiph.SUPER_VALUABLE, 7),12f));

        ObservableList<Note> test = FXCollections.observableArrayList(j1.getRegister());
        TableView<Note> table = new TableView<Note>(j1.getRegister());
       // System.out.println(test.get(1));
        System.out.println(j1.getRegister().get(0));
    }


    public static void main(String[] args)
    {
        launch(args);
        System.out.println("Hello world");

    }
}
