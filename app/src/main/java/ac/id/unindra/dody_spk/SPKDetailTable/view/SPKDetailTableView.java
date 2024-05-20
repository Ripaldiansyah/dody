package ac.id.unindra.dody_spk.SPKDetailTable.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatClientProperties;

import ac.id.unindra.dody_spk.SPKDetailTable.controller.SPKDetailTableController;
import ac.id.unindra.dody_spk.SPKDetailTable.model.SPKDetailModel;
import ac.id.unindra.dody_spk.SPKDetailTable.model.SPKDetailTableModel;
import ac.id.unindra.dody_spk.alternativeInput.model.AlternativeInputModel;
import ac.id.unindra.dody_spk.menu.view.Menu;
import ac.id.unindra.dody_spk.rank.model.RankTableModel;
import ac.id.unindra.dody_spk.rank.view.RankView;
import net.miginfocom.swing.MigLayout;

public class SPKDetailTableView extends JPanel {
        SPKDetailTableController controller = new SPKDetailTableController();
        private SPKDetailTableModel tableModel = new SPKDetailTableModel();
        SPKDetailModel model = new SPKDetailModel();

        public SPKDetailTableView() {
                initComponents();
                init();

        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // <editor-fold defaultstate="collapsed" desc="Generated
        // Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                titleLabel = new javax.swing.JLabel();
                jScrollPane1 = new javax.swing.JScrollPane();
                spkTable = new javax.swing.JTable();
                btnDelete = new javax.swing.JButton();
                btnDetail = new javax.swing.JButton();
                txtSearch = new javax.swing.JTextField();

                titleLabel.setText("Daftar Perhitungan");

                spkTable.setModel(new javax.swing.table.DefaultTableModel(
                                new Object[][] {
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null },
                                                { null, null, null, null }
                                },
                                new String[] {
                                                "Title 1", "Title 2", "Title 3", "Title 4"
                                }));
                spkTable.setRowHeight(30);
                spkTable.getTableHeader().setResizingAllowed(false);
                spkTable.getTableHeader().setReorderingAllowed(false);
                spkTable.addMouseListener(new java.awt.event.MouseAdapter() {
                        public void mouseClicked(java.awt.event.MouseEvent evt) {
                                criteriaTableMouseClicked(evt);
                        }
                });
                jScrollPane1.setViewportView(spkTable);

                btnDelete.setText("Hapus SPK");
                btnDelete.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDeleteActionPerformed(evt);
                        }
                });

                btnDetail.setText("Detail SPK");
                btnDetail.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                btnDetailActionPerformed(evt);
                        }
                });

                txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
                        public void keyReleased(java.awt.event.KeyEvent evt) {
                                txtSearchKeyReleased(evt);
                        }
                });

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                layout.createSequentialGroup()
                                                                                                                .addGap(755, 755,
                                                                                                                                755)
                                                                                                                .addComponent(btnDetail)
                                                                                                                .addPreferredGap(
                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                .addComponent(btnDelete))
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addContainerGap()
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                                                .addComponent(titleLabel,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                                                238,
                                                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                                                .addPreferredGap(
                                                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                                                .addComponent(txtSearch))
                                                                                                                .addComponent(jScrollPane1))))
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(titleLabel,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                48,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(txtSearch,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                35,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane1,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                377, Short.MAX_VALUE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(btnDetail,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(btnDelete,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                41,
                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                                                .addGap(28, 28, 28)));
        }// </editor-fold>//GEN-END:initComponents

        private void btnDetailActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDetailActionPerformed
                int rowIndex = spkTable.getSelectedRow();
                String SPKid = (String) spkTable.getValueAt(rowIndex, 0);
                model.setSPKId(SPKid);
                List<Map<Object, Object>> rankListMap = new ArrayList<>(controller.rankListMap(model));
                setTable(rankListMap);

                RankView.spkID = Integer.parseInt(SPKid);
                removeAll();
                RankView.isSaved = true;
                setLayout(new MigLayout("fill,wrap", "[center, fill]", "[center, fill]"));
                add(new RankView());
                repaint();
                revalidate();

        }// GEN-LAST:event_btnDetailActionPerformed

        private void criteriaTableMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_usersTableMouseClicked
                enableButtons();
        }// GEN-LAST:event_usersTableMouseClicked

        private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteActionPerformed
                int index = spkTable.getSelectedRow();
                if (index != -1) {
                        SPKDetailModel criteria = tableModel
                                        .getSelectedIndex(spkTable.convertRowIndexToModel(index));
                        int response = JOptionPane.showConfirmDialog(
                                        null,
                                        "Apakah Anda yakin ingin menghapus Perhitungan ini ?",
                                        "Konfirmasi Hapus",
                                        JOptionPane.YES_NO_OPTION,
                                        JOptionPane.QUESTION_MESSAGE);

                        if (response == JOptionPane.YES_OPTION) {
                                controller.deleteData(criteria);
                                refreshUI();
                        }
                }
        }// GEN-LAST:event_btnDeleteActionPerformed

        private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txtSearchKeyReleased
                String key = txtSearch.getText();
                tableModel.setData(controller.searchData(key));
        }// GEN-LAST:event_txtSearchKeyReleased

        private void init() {
                txtSearch.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Cari SPK");
                titleLabel.putClientProperty(FlatClientProperties.STYLE, ""
                                + "font:+11");
                spkTable.setModel(tableModel);
                tableModel.setData(controller.getData());
                disableButtons();
        }

        void disableButtons() {
                btnDetail.setEnabled(false);
                btnDelete.setEnabled(false);
        }

        void enableButtons() {
                btnDetail.setEnabled(true);
                btnDelete.setEnabled(true);
        }

        void refreshUI() {
                tableModel.setData(controller.getData());
                disableButtons();
        }

        void setTable(List<Map<Object, Object>> rankListMap) {
                AlternativeInputModel detailModel = new AlternativeInputModel();
                RankTableModel.model = detailModel;
                detailModel.setRankListMap(rankListMap);
                detailModel.setRowCount(rankListMap.size());
                RankView.rankListMap = rankListMap;

        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton btnDelete;
        private javax.swing.JButton btnDetail;
        private javax.swing.JTable spkTable;
        private javax.swing.JScrollPane jScrollPane1;
        private javax.swing.JLabel titleLabel;
        private javax.swing.JTextField txtSearch;
        // End of variables declaration//GEN-END:variables
}
