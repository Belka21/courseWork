package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
       /* Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show(); */

        Journal j1 = new Journal();
  /*      j1.addNote(new Note (5,ValCiph.SUPER_VALUABLE, 12,5.56f));
        j1.addNote(new Note (5,ValCiph.SUPER_VALUABLE, 12,5f));
        j1.addNote(new Note (7,ValCiph.NON, 1,5));
        j1.addNote(new Note (6,ValCiph.NORMAL, 6,7.5f));
        j1.addNote(new Note (3,ValCiph.SUPER_VALUABLE, 7,12f));*/

        DBIO.cout(j1, "DB1.txt");

        TableView<Note> table = new TableView<Note>(j1.getRegister()); // определяем таблицу и устанавливаем данные
        table.setEditable(true);
        table.setPrefWidth(250);
        table.setPrefHeight(200);

        // столбец для вывода имени
        TableColumn<Note, Integer> nameColumn = new TableColumn<Note, Integer>("Граничный вес");
        // определяем фабрику для столбца с привязкой к свойству name
        nameColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("weight"));
        nameColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        // Set Sort type for userName column
        nameColumn.setSortType(TableColumn.SortType.DESCENDING);
        nameColumn.setSortable(false);

        // добавляем столбец
        table.getColumns().add(nameColumn);

        StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        primaryStage.setTitle(j1.getName());

        Scene scene = new Scene(root, 450, 300);
        primaryStage.setScene(scene);
        primaryStage.show();

        //DBIO.cin(j1,"DB1.txt");

    }


    public static void main(String[] args)
    {
        launch(args);
        System.out.println("Hello world");

    }
}
