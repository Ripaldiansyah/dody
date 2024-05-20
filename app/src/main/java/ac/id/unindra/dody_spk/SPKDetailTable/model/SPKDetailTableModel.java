package ac.id.unindra.dody_spk.SPKDetailTable.model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import ac.id.unindra.dody_spk.SPKDetailTable.controller.SPKDetailTableController;
import ac.id.unindra.dody_spk.SPKDetailTable.service.SPKDetailTableService;

public class SPKDetailTableModel extends AbstractTableModel implements SPKDetailTableService {

    SPKDetailTableController controller = new SPKDetailTableController();
    List<SPKDetailModel> spk = new ArrayList<>();

    @Override
    public int getRowCount() {
        return spk.size();
    }

    @Override
    public int getColumnCount() {
        return controller.tableHeader().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return spk.get(rowIndex).getSPKId();
            case 1:
                return spk.get(rowIndex).getSPKName();
            case 2:
                return spk.get(rowIndex).getUser().getFullname();
            case 3:
                return spk.get(rowIndex).getCreatedAt();
            default:
                return null;
        }

    }

    @Override
    public String getColumnName(int column) {
        return controller.tableHeader()[column];
    }

    @Override
    public void setData(List<SPKDetailModel> spk) {
        clear();
        this.spk.addAll(spk);
        fireTableDataChanged();
    }

    @Override
    public void clear() {
        spk.clear();
        fireTableDataChanged();
    }

    @Override
    public void removeData(int index) {
        spk.remove(index);
        fireTableRowsDeleted(index, index);

    }

    @Override
    public SPKDetailModel getSelectedIndex(int index) {
        return spk.get(index);
    }

}
