package UpdateTables;

import Database.Part;
import Database.PartOrder;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

/**
 * Created by colto on 2017-12-01.
 */
public class UpdateReport
{
    public static void updatePartOrderReport(TableView part)
    {
        List<TableColumn> list = part.getColumns();

        TableColumn partNumber = list.get(0);
        TableColumn cost = list.get(1);

        partNumber.setCellValueFactory(new PropertyValueFactory<>("number"));
        cost.setCellValueFactory(new PropertyValueFactory<>("cost"));

        part.getItems().setAll(UpdatePartOrder.getFinalized());
    }

    public static void updateWorkOrderReport(TableView work)
    {
        List<TableColumn> list = work.getColumns();

        TableColumn workNumber = list.get(0);

        workNumber.setCellValueFactory(new PropertyValueFactory<>("unitNumber"));

        work.getItems().setAll(UpdateWorkOrder.getFinalized());
    }
}
