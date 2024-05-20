package ac.id.unindra.dody_spk.SPKDetailTable.controller;

import java.util.List;
import java.util.Map;

import ac.id.unindra.dody_spk.SPKDetailTable.dao.SPKDetailTableDAO;
import ac.id.unindra.dody_spk.SPKDetailTable.model.SPKDetailModel;
import ac.id.unindra.dody_spk.SPKDetailTable.view.SPKDetailTableView;

public class SPKDetailTableController {

    private SPKDetailTableDAO spk;
    private SPKDetailModel model = new SPKDetailModel();

    public SPKDetailTableController() {
        this.spk = new SPKDetailTableDAO();
    }

    public String[] tableHeader() {
        return model.getColumnHeader();
    }

    public List<SPKDetailModel> getData() {
        return spk.getData();
    }

    public List<SPKDetailModel> searchData(String key) {
        return spk.searchData(key);
    }

    public void deleteData(SPKDetailModel model) {
        spk.deleteData(model);
    }

    public List<Map<Object, Object>> rankListMap(SPKDetailModel model) {
        return spk.rankDetail(model);
    }

}
