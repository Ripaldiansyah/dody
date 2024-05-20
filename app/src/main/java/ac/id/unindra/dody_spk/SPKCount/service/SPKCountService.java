package ac.id.unindra.dody_spk.SPKCount.service;

import java.util.List;

import ac.id.unindra.dody_spk.SPKCount.model.SPKCountModel;

public interface SPKCountService {

    boolean isNotRegistered(SPKCountModel criteria);

    void addCriteria(SPKCountModel criteria);

    List<String> getCriteria();

    List<String> getAlternative();

    String getCriteriaType(SPKCountModel criteria);

    int getCriteriaWeight(SPKCountModel criteria);

}
