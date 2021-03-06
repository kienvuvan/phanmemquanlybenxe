/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package admin.view;

import car.controller.CarController;
import carowner.controller.CarOwnerController;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.Utils;

/**
 *
 * @author kienanh2903
 */
public class HandRequestUpdateCar extends javax.swing.JFrame {

    private final String idAdmin;
    private final CarController carController;
    private final CarOwnerController carOwnerController;

    /**
     * Creates new form HandRequestUpdateCar
     *
     * @param idAdmin
     */
    public HandRequestUpdateCar(String idAdmin) {
        initComponents();
        this.idAdmin = idAdmin;
        carController = new CarController();
        carOwnerController = new CarOwnerController();
        getAllIdCarOwnerRequest();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog_parkCar = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        jTextField_thoGianDo = new javax.swing.JTextField();
        jComboBox_viTriDoXe = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jComboBox_cmt = new javax.swing.JComboBox<>();
        jLabel41 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jTextField_ten = new javax.swing.JTextField();
        jLabel46 = new javax.swing.JLabel();
        jComboBox_maXe = new javax.swing.JComboBox<>();
        jLabel47 = new javax.swing.JLabel();
        jTextField_ngayYeuCau = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable_inforCarNow = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTable_inforCarUpdate = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Vị trí đỗ xe : ");

        jTextField_thoGianDo.setEditable(false);

        jLabel2.setText("Thời gian đỗ : ");

        jButton3.setText("Chấp nhận");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jDialog_parkCarLayout = new javax.swing.GroupLayout(jDialog_parkCar.getContentPane());
        jDialog_parkCar.getContentPane().setLayout(jDialog_parkCarLayout);
        jDialog_parkCarLayout.setHorizontalGroup(
            jDialog_parkCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_parkCarLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jDialog_parkCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDialog_parkCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton3)
                    .addComponent(jComboBox_viTriDoXe, 0, 175, Short.MAX_VALUE)
                    .addComponent(jTextField_thoGianDo))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jDialog_parkCarLayout.setVerticalGroup(
            jDialog_parkCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_parkCarLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jDialog_parkCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_viTriDoXe, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jDialog_parkCarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField_thoGianDo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Trang xử lý yêu cầu cập nhật xe của chủ xe");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jComboBox_cmt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_cmtActionPerformed(evt);
            }
        });

        jLabel41.setText("Chứng minh thư chủ xe :");

        jLabel45.setText("Họ tên chủ xe :");

        jTextField_ten.setEditable(false);

        jLabel46.setText("Yêu cầu cập nhật mã xe :");

        jComboBox_maXe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_maXeActionPerformed(evt);
            }
        });

        jLabel47.setText("Ngày yêu cầu :");

        jTextField_ngayYeuCau.setEditable(false);

        jPanel14.setLayout(new java.awt.GridLayout(1, 0));

        jPanel15.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin xe hiện tại", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTable_inforCarNow.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã xe", "Biển số xe", "Giá vé", "Lịch trình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane10.setViewportView(jTable_inforCarNow);

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel15);

        jPanel16.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin xe cần cập nhật", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14))); // NOI18N

        jTable_inforCarUpdate.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã xe", "Biển số xe", "Giá vé", "Lịch trình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane11.setViewportView(jTable_inforCarUpdate);

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
        );

        jPanel14.add(jPanel16);

        jButton1.setText("Chấp nhận");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Không đồng ý");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox_cmt, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox_maXe, 0, 207, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel45, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_ten, javax.swing.GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
                    .addComponent(jTextField_ngayYeuCau))
                .addGap(22, 22, 22))
            .addComponent(jPanel14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel41, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_cmt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel45, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_ten, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel46, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox_maXe, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel47, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_ngayYeuCau, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.dispose();
        AdminForm adminForm = new AdminForm(idAdmin);
        adminForm.setVisible(true);
    }//GEN-LAST:event_formWindowClosing

    private void jComboBox_cmtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_cmtActionPerformed
        // TODO add your handling code here:
        setIdCarOwner();
    }//GEN-LAST:event_jComboBox_cmtActionPerformed

    private void jComboBox_maXeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_maXeActionPerformed
        // TODO add your handling code here:
        if (jComboBox_maXe.getSelectedItem() == null) {
            jTextField_ngayYeuCau.setText("");
        } else {
            String maXe = jComboBox_maXe.getSelectedItem().toString();
            SimpleDateFormat simple = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            jTextField_ngayYeuCau.setText(simple.format(carController.getTimeSendRequest(maXe)));
            carController.displayInforCarForUpdate(jTable_inforCarNow, carController.getCarByIdCar(maXe));
            carController.displayInforCarUpdate(jTable_inforCarUpdate, carController.getCarUpdateByIdCar(maXe));
        }
    }//GEN-LAST:event_jComboBox_maXeActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có đồng ý cập nhật xe này không ??", "Thông báo ",
                JOptionPane.YES_OPTION) == JOptionPane.YES_NO_OPTION) {
            double giaVe = Double.parseDouble(jTable_inforCarUpdate.getValueAt(0, 2).toString());
            String maXe = jTable_inforCarUpdate.getValueAt(0, 0).toString();
            String lichTrinh = jTable_inforCarUpdate.getValueAt(0, 3).toString();
            if (checkChangeRoute() == false) {
                boolean result = carController.updateCar(giaVe, maXe);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    getAllIdCarOwnerRequest();
                } else {
                    JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                }
            } else {
                String thoiGianDo = Utils.cutRoute(jTable_inforCarNow.getValueAt(0, 3).toString()).get(1) + "-" + Utils.cutRoute(jTable_inforCarNow.getValueAt(1, 3).toString()).get(0);
                if (checkTimeParkNew(lichTrinh, maXe)) {
                    String viTriDoXe = carController.getParkLocation(maXe);
                    String bsx = jTable_inforCarUpdate.getValueAt(0, 1).toString();
                    String lichTrinhVe = Utils.generateCarReturn(lichTrinh);
                    String thoiGianDoMoi = Utils.cutRoute(lichTrinh).get(1) + "-" + Utils.cutRoute(lichTrinhVe).get(0);
                    boolean kqua = carController.updateParkLocaction(viTriDoXe, bsx, thoiGianDo, thoiGianDoMoi);
                    boolean kqua1 = carController.updateCar(giaVe, lichTrinh, maXe);
                    if (kqua && kqua1) {
                        JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                        getAllIdCarOwnerRequest();
                    } else {
                        JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                    }
                } else {
                    if (JOptionPane.showConfirmDialog(null, "Hiện tại với thời gian đỗ xe mới.Vị trí đỗ xe đã bị trùng.Bạn có muốn chọn vị trí đỗ xe khác không ??", "Thông báo ",
                            JOptionPane.YES_OPTION) == JOptionPane.YES_NO_OPTION) {
                        jDialog_parkCar.setVisible(true);
                        jDialog_parkCar.setSize(322, 225);
                        jDialog_parkCar.setLocationRelativeTo(null);
                        carController.addListItemToJCombobox(jComboBox_viTriDoXe, carController.getAllParkLocation());
                        jTextField_thoGianDo.setText(thoiGianDo);
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(null, "Bạn có muốn hủy yêu cầu cập nhật xe này không ??", "Thông báo ",
                JOptionPane.YES_OPTION) == JOptionPane.YES_NO_OPTION) {
            boolean result = carController.updateStatusNoAgree(jTable_inforCarUpdate.getValueAt(0, 0).toString());
            if (result) {
                JOptionPane.showMessageDialog(this, "Bạn đã không đồng ý với yêu cầu chủ xe. Thông báo này sẽ được gửi cho chủ xe");
                getAllIdCarOwnerRequest();
            } else {
                JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jComboBox_viTriDoXe.getSelectedItem() == null) {
            JOptionPane.showMessageDialog(this, "Hiện tại không có vị trí đỗ xe.");
        } else {
            String viTriDo = jComboBox_viTriDoXe.getSelectedItem().toString();
            String thoiGianDo = Utils.cutRoute(jTable_inforCarNow.getValueAt(0, 3).toString()).get(1) + "-" + Utils.cutRoute(jTable_inforCarNow.getValueAt(1, 3).toString()).get(0);
            if (carController.checkParkLocation(viTriDo, thoiGianDo) == false) {
                JOptionPane.showMessageDialog(jDialog_parkCar, "Vị trí đỗ với khoảng thời gian này đã có xe đỗ.Vui lòng chọn vị trí khác");
            } else {
                double giaVe = Double.parseDouble(jTable_inforCarUpdate.getValueAt(0, 2).toString());
                String maXe = jTable_inforCarUpdate.getValueAt(0, 0).toString();
                String lichTrinh = jTable_inforCarUpdate.getValueAt(0, 3).toString();
                String viTriDoXe = carController.getParkLocation(maXe);
                String bsx = jTable_inforCarUpdate.getValueAt(0, 1).toString();
                String lichTrinhVe = Utils.generateCarReturn(lichTrinh);
                String thoiGianDoMoi = Utils.cutRoute(lichTrinh).get(1) + "-" + Utils.cutRoute(lichTrinhVe).get(0);
                boolean kqua = carController.updateParkLocaction(viTriDoXe, bsx, thoiGianDo, thoiGianDoMoi);
                boolean kqua1 = carController.updateCar(giaVe, lichTrinh, maXe);
                if (kqua && kqua1) {
                    JOptionPane.showMessageDialog(jDialog_parkCar, "Cập nhật thành công");
                    getAllIdCarOwnerRequest();
                } else {
                    JOptionPane.showMessageDialog(jDialog_parkCar, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                }
            }
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void getAllIdCarOwnerRequest() {
        carController.addListItemToJCombobox(jComboBox_cmt, carController.getAllIdCarOwnerRequest());
    }

    private boolean checkChangeRoute() {
        String routeNow = jTable_inforCarNow.getValueAt(0, 3).toString();
        String routeUpdate = jTable_inforCarUpdate.getValueAt(0, 3).toString();
        if (routeNow.equalsIgnoreCase(routeUpdate)) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkTimeParkNew(String lichTrinh, String maXe) {
        String lichTrinhVe = Utils.generateCarReturn(lichTrinh);
        String thoiGianDo = Utils.cutRoute(lichTrinh).get(1) + "-" + Utils.cutRoute(lichTrinhVe).get(0);
        String viTriDoXe = carController.getParkLocation(maXe);
        if (carController.checkParkLocation(viTriDoXe, thoiGianDo)) {
            //Thời gian này và vị trí cũ có thể sắp xếp vào bãi
            return true;
        } else {
            return false;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox<String> jComboBox_cmt;
    private javax.swing.JComboBox<String> jComboBox_maXe;
    private javax.swing.JComboBox<String> jComboBox_viTriDoXe;
    private javax.swing.JDialog jDialog_parkCar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JTable jTable_inforCarNow;
    private javax.swing.JTable jTable_inforCarUpdate;
    private javax.swing.JTextField jTextField_ngayYeuCau;
    private javax.swing.JTextField jTextField_ten;
    private javax.swing.JTextField jTextField_thoGianDo;
    // End of variables declaration//GEN-END:variables

    private void setIdCarOwner() {
        if (jComboBox_cmt.getSelectedItem() == null) {
            jTextField_ten.setText("");
            jComboBox_maXe.removeAllItems();
            jTextField_ngayYeuCau.setText("");
            DefaultTableModel model = (DefaultTableModel) jTable_inforCarNow.getModel();
            DefaultTableModel model1 = (DefaultTableModel) jTable_inforCarUpdate.getModel();
            model.setRowCount(0);
            model1.setRowCount(0);
        } else {
            String cmtChuXe = jComboBox_cmt.getSelectedItem().toString();
            jTextField_ten.setText(carOwnerController.getNameByIdCarOwner(cmtChuXe));
            carController.addListItemToJCombobox(jComboBox_maXe, carController.getAllIdCarRequestByCarOwner(cmtChuXe));
        }
    }
}
