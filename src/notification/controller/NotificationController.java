/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notification.controller;

import admin.controller.AdminController;
import notification.model.Notification;
import notification.view.NotificationPanel;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author kienanh2903
 */
public class NotificationController {

    private Notification information;

    public NotificationController() {
        information = new Notification();
    }

    public List<Notification> getAllInfor() {
        return information.getAllInfor();
    }

    public void displayInformation(JPanel jPanel, JPanel jPanel1, List<Notification> list, int stt) {
        if (list.isEmpty()) {
            JLabel jLabel = new JLabel("Hiện tại hệ thống không có tin tức cập nhật!!!");
            jLabel.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
            jLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            jPanel.add(jLabel);
        } else if (list.size() < stt) {
            for (int i = stt - 1; i < stt; i++) {
                if (i < list.size()) {
                    Notification infor = list.get(i);
                    NotificationPanel inforPanel = new NotificationPanel(infor.getId() + "", infor.getTieuDe(), infor.getNgayDang(), infor.getCmtAdmin(), infor.getNoiDung());
                    jPanel.add(inforPanel);
                } else {
                    NotificationPanel inforPanel = new NotificationPanel("", "", null, "", "");
                    jPanel.add(inforPanel);
                }
            }
        } else {
            for (int j = stt - 1; j < stt; j++) {
                Notification infor = list.get(j);
                NotificationPanel inforPanel = new NotificationPanel(infor.getId() + "", infor.getTieuDe(), infor.getNgayDang(), infor.getCmtAdmin(), infor.getNoiDung());
                jPanel.add(inforPanel);
            }
        }
    }

    public void displayInformationToTable(JTable jtb) {
        List<Notification> list = getAllInfor();
        DefaultTableModel model = (DefaultTableModel) jtb.getModel();
        AdminController adminController = new AdminController();
        model.setRowCount(0);
        for (int i = 0; i < list.size(); i++) {
            Notification infor = list.get(i);
            model.addRow(new Object[]{infor.getId(), infor.getTieuDe(), infor.getNoiDung(), infor.getNgayDang(), adminController.getNameAdmin(infor.getCmtAdmin())});
        }
       jtb.setModel(model);
    }
    
    public int postNew(Notification infor) {
        return information.postNew(infor);
    }
    
    public int updateNew(Notification infor) {
        return information.updateNew(infor);
    }
    
    public boolean deleteNew(int id) {
        return information.deleteNew(id);
    }
}
