package GUIWindows;

import Database.PartOrder;
import Database.WorkOrder;
import UpdateTables.UpdatePartOrder;
import UpdateTables.UpdateReport;
import UpdateTables.UpdateWorkOrder;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;

/**
 * Created by colto on 2017-11-26.
 */
public class Report {
    /**
     * This is the window that deals with the generation of reports.
     */
    public static void Report(String name, int privilege)
    {
        Stage stage = new Stage();

        TableView<PartOrder> parts = new TableView();
        TableColumn partNumber = new TableColumn("Part Order Number");
        TableColumn cost = new TableColumn("Cost");

        TableView<WorkOrder> works = new TableView();
        TableColumn workNumber = new TableColumn("Work Order Number");

        Tab partOrder = new Tab("Part Orders");
        Tab workOrder = new Tab("Work Orders");

        Button saveSelectedPart = new Button("Save File");
        Button saveSelectedWork = new Button("Save File");

        HBox hBox = new HBox(10, saveSelectedPart);
        HBox hBox1 = new HBox(10, saveSelectedWork);

        hBox.setPadding(new Insets(10));
        hBox.setAlignment(Pos.BOTTOM_CENTER);

        hBox1.setPadding(new Insets(10));
        hBox1.setAlignment(Pos.BOTTOM_CENTER);

        BorderPane borderPart = new BorderPane(parts);
        borderPart.setBottom(hBox);

        BorderPane borderWork = new BorderPane(works);
        borderWork.setBottom(hBox1);

        parts.getColumns().addAll(partNumber, cost);
        partOrder.setContent(borderPart);

        works.getColumns().addAll(workNumber);
        workOrder.setContent(borderWork);

        UpdateReport.updatePartOrderReport(parts);
        UpdateReport.updateWorkOrderReport(works);



        TabPane tabPane = new TabPane(partOrder, workOrder);

        BorderPane borderPane = new BorderPane(tabPane);

        Scene scene = new Scene(borderPane);

        saveSelectedPart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = parts.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                PartOrder selected = parts.getItems().get(row);

                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extensionFilter);

                File file = fileChooser.showSaveDialog(stage);

                if(file != null)
                {
                    SavePart(selected, parts, file);
                }
            }
        });

        saveSelectedWork.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TablePosition pos = works.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                WorkOrder selected = works.getItems().get(row);

                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fileChooser.getExtensionFilters().add(extensionFilter);

                File file = fileChooser.showSaveDialog(stage);

                if(file != null)
                {
                    SaveWork(selected, works, file);
                }


            }
        });

        stage.setTitle("Report");
        stage.setScene(scene);
        stage.show();
    }

    private static void SavePart(PartOrder order, TableView tableView, File file)
    {
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(order.toString());
            fileWriter.close();

            UpdatePartOrder.removeOrderFinalized(order);
            UpdateReport.updatePartOrderReport(tableView);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private static void SaveWork(WorkOrder order, TableView tableView, File file)
    {
        try {
            FileWriter fileWriter = new FileWriter(file);

            fileWriter.write(order.toString());
            fileWriter.close();

            UpdateWorkOrder.removeFinalized(order);
            UpdateReport.updateWorkOrderReport(tableView);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}
