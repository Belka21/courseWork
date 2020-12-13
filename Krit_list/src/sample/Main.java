package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.ComboBoxTableCell;
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
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.FormatStringConverter;
import javafx.util.converter.IntegerStringConverter;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));

        Parent root = loader.load();
        Controller controller = loader.getController();

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();

        Journal j1 = new Journal();
  /*      j1.addNote(new Note (5,ValCiph.SUPER_VALUABLE, 12,5.56f));
        j1.addNote(new Note (5,ValCiph.SUPER_VALUABLE, 12,5f));
        j1.addNote(new Note (7,ValCiph.NON, 1,5));
        j1.addNote(new Note (6,ValCiph.NORMAL, 6,7.5f));
        j1.addNote(new Note (3,ValCiph.SUPER_VALUABLE, 7,12f));*/

        DBIO.cout(j1, "DB1.txt");
        j1.setName("DB1.txt");
        controller.loadIn(j1, primaryStage);

        TableView<Note> table = new TableView<Note>(j1.getRegister()); // определяем таблицу и устанавливаем данные
        table.setEditable(true);
        table.setPrefWidth(250);
        table.setPrefHeight(200);

        //объявление колонок
        //колонка для веса
        TableColumn<Note, Integer> weightColumn =
                    new TableColumn<Note, Integer>("Граничный вес");

        //колонка для шифра
        TableColumn<Note, ValCiph> valColumn =
                new TableColumn<Note, ValCiph>("Шифр ценности");

        //колонка для временидоставки
        TableColumn<Note, Integer>  deliveryColumn=
                new TableColumn<Note, Integer>("Время доставки");

        //колонка для cost
        TableColumn<Note, Float> costColumn =
                new TableColumn<Note, Float>("Стоимость доставки");

        // определяем фабрику для столбца с привязкой к свойству
        //вес
        weightColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("weight"));
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //время доставки
        deliveryColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("deliveTime"));
        deliveryColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        //cost доставки
        costColumn.setCellValueFactory(new PropertyValueFactory<Note, Float>("cost"));
        costColumn.setCellFactory(TextFieldTableCell.forTableColumn(new FloatStringConverter()));


        //шифр ценности
        // ==== ШИФР (COMBO BOX) ===

        ObservableList<ValCiph> valList = FXCollections.observableArrayList(//
                ValCiph.values());

        valColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Note, ValCiph>, ObservableValue<ValCiph>>() {

            @Override
            public ObservableValue<ValCiph> call(TableColumn.CellDataFeatures<Note, ValCiph> param) {
                Note nt = param.getValue();
                // F,M
                ValCiph val = nt.getValuable();
                return new SimpleObjectProperty<ValCiph>(val);
            }
        });

        valColumn.setCellFactory(ComboBoxTableCell.forTableColumn(valList));

        valColumn.setOnEditCommit((TableColumn.CellEditEvent<Note, ValCiph> event) -> {
            TablePosition<Note, ValCiph> pos = event.getTablePosition();

            ValCiph newVal = event.getNewValue();

            int row = pos.getRow();
            Note note = event.getTableView().getItems().get(row);

            note.setValuable(newVal);
        });

        valColumn.setMinWidth(120);



        // Set Sort type for userName column
        weightColumn.setSortType(TableColumn.SortType.DESCENDING);
        //nameColumn.setSortable(false);

        // добавляем столбцы
        table.getColumns().addAll(weightColumn, valColumn, deliveryColumn, costColumn);

        /*StackPane root = new StackPane();
        root.setPadding(new Insets(5));
        root.getChildren().add(table);

        primaryStage.setTitle(j1.getName());

        Scene scene = new Scene(root, 480, 720);
        primaryStage.setScene(scene);
        primaryStage.show();*/

        //DBIO.cin(j1,"DB1.txt");

    }




    public static void main(String[] args)
    {
        launch(args);
        System.out.println("Hello world");

    }
}
