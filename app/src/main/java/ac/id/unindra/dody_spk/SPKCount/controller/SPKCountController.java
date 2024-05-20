package ac.id.unindra.dody_spk.SPKCount.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import ac.id.unindra.dody_spk.SPKCount.dao.SPKCountDAO;
import ac.id.unindra.dody_spk.SPKCount.model.SPKCountModel;
import ac.id.unindra.dody_spk.SPKCount.view.SPKCountView;

public class SPKCountController {

    public SPKCountController() {
        this.spk = new SPKCountDAO();
    }

    // public boolean isNotRegistered(SPKCountModel model) {
    // return spk.isNotRegistered(model);
    // }

    // public void addCriteria(SPKCountModel model) {
    // spk.addCriteria(model);
    // }

    // public List<String> getCriteria() {
    // return spk.getCriteria();
    // }

    // public String getCriteriaType(SPKCountModel model) {
    // return spk.getCriteriaType(model);
    // }

    // public List<String> getAlternative() {
    // return spk.getAlternative();
    // }

    public List<Double> getCriteriaWeight() {
        List<Double> criteriaWeight = new ArrayList<>(List.of(
                0.24,
                0.22,
                0.16,
                0.16,
                0.07,
                0.15));
        return criteriaWeight;
    }

    private SPKCountDAO spk;
}
