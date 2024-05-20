package ac.id.unindra.dody_spk.alternativeInput.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

import ac.id.unindra.dody_spk.alternativeInput.controller.AlternativeInputController;

public class TableModelSaham extends AbstractTableModel {

    AlternativeInputController controller = new AlternativeInputController();
    List<String> alternativeList = new ArrayList<>();

    public static AlternativeInputModel model;

    @Override
    public int getRowCount() {
        if (controller.getRowCount(model) == 0) {
            return 1;
        } else {
            return controller.getRowCount(model);
        }

    }

    @Override
    public int getColumnCount() {
        return 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        if (controller.getRowCount(model) == 0) {
            return "Tidak ada data";
        } else {
            return alternativeList.get(rowIndex);
        }

    }

    @Override
    public String getColumnName(int column) {
        String[] header = {
                "Nama Saham",
        };
        return header[column];
    }

    public void setData() {
        alternativeList.addAll(model.getAlternativeInput());
        fireTableDataChanged();
    }

}
