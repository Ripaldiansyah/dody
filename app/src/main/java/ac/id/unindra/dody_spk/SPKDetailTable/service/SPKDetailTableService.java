package ac.id.unindra.dody_spk.SPKDetailTable.service;

import java.util.List;

import ac.id.unindra.dody_spk.SPKDetailTable.model.SPKDetailModel;

public interface SPKDetailTableService {
    void setData(List<SPKDetailModel> spk);

    void removeData(int index);

    void clear();

    SPKDetailModel getSelectedIndex(int index);
}
