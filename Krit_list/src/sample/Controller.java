package sample;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.stage.FileChooser;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Window;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.File;


public class Controller {
    @FXML
    private TableView<Note> table;
        @FXML
         private TableColumn<Note, Integer> weightColumn;
        @FXML
        private TableColumn<Note, ValCiph> valColumn;
        @FXML
        private TableColumn<Note, Integer>  timeColumn;
        @FXML
        private TableColumn<Note, Float> costColumn;

        @FXML
        private Journal l1;
        Window stage;

    FileChooser fileChooser=new FileChooser();

    public void loadIn(Journal journal, Window st)
    {
        stage = st;
        fileChooser.setInitialDirectory(new File("C:\\temp"));

        table.setItems(journal.getRegister());
        l1=journal;
        // определяем фабрику для столбца с привязкой к свойству
        //вес
        weightColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("weight"));
        weightColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //время доставки
        timeColumn.setCellValueFactory(new PropertyValueFactory<Note, Integer>("deliveTime"));
        timeColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //цена доставки
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

        //ивент обновления колонки шифра
        valColumn.setOnEditCommit((TableColumn.CellEditEvent<Note, ValCiph> event) ->
        {
            TablePosition<Note, ValCiph> pos = event.getTablePosition();
            ValCiph newVal = event.getNewValue();

            Note note = event.getTableView().getItems().get(pos.getRow());

            journal.updateNote(pos.getRow(), new Note(note.getWeight(), newVal, note.getDeliveTime(), note.getCost()));
        });

        //ивент обновления колонки веса
        weightColumn.setOnEditCommit((TableColumn.CellEditEvent<Note, Integer> event) ->
        {
            TablePosition<Note, Integer> pos = event.getTablePosition();
            Integer newWeight = event.getNewValue();

            Note note = event.getTableView().getItems().get(pos.getRow());

            journal.updateNote(pos.getRow(), new Note(newWeight, note.getValuable(), note.getDeliveTime(), note.getCost()));
        });

        //ивент обновления колонки времени
        timeColumn.setOnEditCommit((TableColumn.CellEditEvent<Note, Integer> event) ->
        {
            TablePosition<Note, Integer> pos = event.getTablePosition();
            Integer newTime = event.getNewValue();

            Note note = event.getTableView().getItems().get(pos.getRow());

            journal.updateNote(pos.getRow(), new Note(note.getWeight(), note.getValuable(), newTime, note.getCost()));
        });

        //ивент обновления колонки стоимости
        costColumn.setOnEditCommit((TableColumn.CellEditEvent<Note, Float> event) ->
        {
            TablePosition<Note, Float> pos = event.getTablePosition();
            Float newCost = event.getNewValue();

            Note note = event.getTableView().getItems().get(pos.getRow());

            journal.updateNote(pos.getRow(), new Note(note.getWeight(), note.getValuable(), note.getDeliveTime(), newCost));
        });

        valColumn.setCellFactory(ComboBoxTableCell.forTableColumn(valList));
    }//loadIn



    @FXML
    public void addRow(ActionEvent actionEvent)
    {
        Note nt = new Note();
        l1.getRegister().add(nt);
    }

    public void deleteRow(ActionEvent actionEvent)
    {
        TableView.TableViewSelectionModel<Note> selectionModel = table.getSelectionModel();
        l1.getRegister().remove(selectionModel.getSelectedIndex());
    }

    public void saveFile(ActionEvent actionEvent) throws Exception {
        DBIO.cin(l1,l1.getName());
    }

    public void saveAs(ActionEvent actionEvent)
    {
        //Window stage = n;
        fileChooser.setTitle("Save As");
        fileChooser.setInitialFileName("DB");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("text file", "*.txt"));

        try {
            DBIO.cin(l1,fileChooser.showSaveDialog(stage).getAbsolutePath());
        }
        catch (Exception ex)
        {

        }
    }
}
