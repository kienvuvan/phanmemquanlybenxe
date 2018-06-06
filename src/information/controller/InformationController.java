/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information.controller;

import admin.controller.AdminController;
import information.model.Information;
import information.view.InforPanel;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kienanh2903
 */
public class InformationController {

    private Information information;

    public InformationController() {
        information = new Information();
    }

    public List<Information> getAllInfor() {
        return information.getAllInfor();
    }

    public void displayInformation(JPanel jPanel, JPanel jPanel1, List<Information> list, int stt) {
        if (list.isEmpty()) {
            JLabel jLabel = new JLabel("Hiện tại hệ thống không có tin tức cập nhật!!!");
            jLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
            jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jPanel.add(jLabel);
        } else if (list.size() < stt) {
            for (int i = stt - 1; i < stt; i++) {
                if (i < list.size()) {
                    Information infor = list.get(i);
                    InforPanel inforPanel = new InforPanel(infor.getId() + "", infor.getTieuDe(), infor.getNgayDang(), infor.getCmtAdmin(), infor.getNoiDung());
                    jPanel.add(inforPanel);
                } else {
                    InforPanel inforPanel = new InforPanel("", "", null, "", "");
                    jPanel.add(inforPanel);
                }
            }
        } else {
            for (int j = stt - 1; j < stt; j++) {
                Information infor = list.get(j);
                InforPanel inforPanel = new InforPanel(infor.getId() + "", infor.getTieuDe(), infor.getNgayDang(), infor.getCmtAdmin(), infor.getNoiDung());
                jPanel.add(inforPanel);
            }
        }
    }

    public void displayInformationToTable(JTable jtb) {
        List<Information> list = getAllInfor();
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        AdminController adminController = new AdminController();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            Information infor = list.get(i);
            model.addRow(new Object[]{infor.getId(), infor.getTieuDe(), infor.getNoiDung(), infor.getNgayDang(), adminController.getNameAdmin(infor.getCmtAdmin())});
        }
       jtb.setModel(model);
    }
    
    public int postNew(Information infor) {
        return information.postNew(infor);
    }
    
    public int updateNew(Information infor) {
        return information.updateNew(infor);
    }
    
    public boolean deleteNew(int id) {
        return information.deleteNew(id);
    }
}
