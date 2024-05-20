package ac.id.unindra.dody_spk.SPKDetailTable.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ac.id.unindra.dody_spk.SPKDetailTable.model.SPKDetailModel;

public interface SPKDetailService {
    List<SPKDetailModel> getData();

    void deleteData(SPKDetailModel detail);

    List<SPKDetailModel> searchData(String key);

    List<Map<Object, Object>> rankDetail(SPKDetailModel model);

}
