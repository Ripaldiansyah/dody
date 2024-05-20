package ac.id.unindra.dody_spk.rank.service;

import ac.id.unindra.dody_spk.rank.model.RankTableModel;

public interface RankService {
    void saveSPK(RankTableModel model);

    void saveRankSPK(RankTableModel model);
}