package ac.id.unindra.dody_spk.rank.controller;

import ac.id.unindra.dody_spk.rank.dao.RankDAO;
import ac.id.unindra.dody_spk.rank.model.RankTableModel;

public class RankController {

    private RankDAO dao;

    public RankController() {
        this.dao = new RankDAO();
    }

    public void saveSPK(RankTableModel model) {
        dao.saveSPK(model);
    }
}
