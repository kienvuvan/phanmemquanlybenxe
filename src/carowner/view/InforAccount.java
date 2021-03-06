/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package carowner.view;

import account.controller.AccountController;
import account.dao.MysqlAccountDao;
import account.model.Account;
import admin.controller.AdminController;
import admin.dao.MysqlAdminDao;
import admin.model.Admin;
import carowner.controller.CarOwnerController;
import carowner.dao.MysqlCarOwnerDao;
import carowner.model.CarOwner;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author kienanh2903
 */
public class InforAccount extends javax.swing.JFrame {

    AccountController ac;
    AdminController adminController;
    CarOwnerController coc;

    /**
     * Creates new form InforAccount
     */
    public InforAccount() {
        initComponents();
        setLocationRelativeTo(null);
        setVisiableInforAccountPanel();
        ac = new AccountController();
        adminController = new AdminController();
        coc = new CarOwnerController();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jPanel_InforAccount = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_sdt = new javax.swing.JLabel();
        jLabel_email = new javax.swing.JLabel();
        jLabel_gioiTinh = new javax.swing.JLabel();
        jLabel_ngaySinh = new javax.swing.JLabel();
        jTextField_cmt = new javax.swing.JTextField();
        jTextField_ten = new javax.swing.JTextField();
        jTextField_sdt = new javax.swing.JTextField();
        jTextField_email = new javax.swing.JTextField();
        jLabel_nhaXe = new javax.swing.JLabel();
        jTextField_nhaXe = new javax.swing.JTextField();
        jLabel_diaChi = new javax.swing.JLabel();
        jTextField_diaChi = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jRadioButton_nam = new javax.swing.JRadioButton();
        jRadioButton_nu = new javax.swing.JRadioButton();
        jXDatePicker_ngaySinh = new org.jdesktop.swingx.JXDatePicker();
        jPanel_changePass = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jPasswordField_PassOld = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jPasswordField_PassNew = new javax.swing.JPasswordField();
        jPasswordField_PassAgain = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu_inforAccount = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông tin tài khoản");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin tài khoản", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(255, 51, 51))); // NOI18N

        jLabel1.setText("Chứng minh thư :");

        jLabel2.setText("Họ tên : ");

        jLabel_sdt.setText("Số điện thoại :");

        jLabel_email.setText("Email : ");

        jLabel_gioiTinh.setText("Giới tính :");

        jLabel_ngaySinh.setText("Ngày sinh :");

        jTextField_cmt.setEditable(false);

        jLabel_nhaXe.setText("Nhà xe :");

        jLabel_diaChi.setText("Địa chỉ :");

        jButton3.setText("Cập nhật");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        buttonGroup1.add(jRadioButton_nam);
        jRadioButton_nam.setSelected(true);
        jRadioButton_nam.setText("Nam");

        buttonGroup1.add(jRadioButton_nu);
        jRadioButton_nu.setText("Nữ");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_sdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(jLabel_email, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_gioiTinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_ngaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_nhaXe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_diaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jRadioButton_nam)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton_nu))
                    .addComponent(jTextField_email)
                    .addComponent(jTextField_sdt)
                    .addComponent(jTextField_ten)
                    .addComponent(jTextField_cmt, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_nhaXe, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField_diaChi, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jXDatePicker_ngaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(20, 20, 20))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jButton3)
                .addContainerGap(183, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField_cmt)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_ten))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_nhaXe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_nhaXe, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_sdt, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_email, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_gioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_nam)
                    .addComponent(jRadioButton_nu))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jXDatePicker_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel_diaChi, jLabel_email, jLabel_gioiTinh, jLabel_ngaySinh, jLabel_sdt, jRadioButton_nam, jRadioButton_nu, jTextField_email, jTextField_sdt, jXDatePicker_ngaySinh});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2, jTextField_cmt});

        javax.swing.GroupLayout jPanel_InforAccountLayout = new javax.swing.GroupLayout(jPanel_InforAccount);
        jPanel_InforAccount.setLayout(jPanel_InforAccountLayout);
        jPanel_InforAccountLayout.setHorizontalGroup(
            jPanel_InforAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel_InforAccountLayout.setVerticalGroup(
            jPanel_InforAccountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(234, 19, 89));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Bạn vui lòng điền mật khẩu mới vào ô dưới đây");

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(239, 34, 127));
        jLabel16.setText("Mật khẩu cũ :");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(239, 34, 127));
        jLabel17.setText("Mật khẩu mới :");

        jLabel18.setFont(new java.awt.Font("SansSerif", 1, 15)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(239, 34, 127));
        jLabel18.setText("Nhập lại :");

        jButton1.setText("Đổi mật khẩu");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel_changePassLayout = new javax.swing.GroupLayout(jPanel_changePass);
        jPanel_changePass.setLayout(jPanel_changePassLayout);
        jPanel_changePassLayout.setHorizontalGroup(
            jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_changePassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_changePassLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 426, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_changePassLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jPasswordField_PassNew)
                                .addComponent(jPasswordField_PassOld)
                                .addComponent(jPasswordField_PassAgain, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)))
                        .addGap(40, 40, 40))))
        );

        jPanel_changePassLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel16, jLabel17, jLabel18});

        jPanel_changePassLayout.setVerticalGroup(
            jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel_changePassLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(85, 85, 85)
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jPasswordField_PassOld, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jPasswordField_PassNew))
                .addGap(18, 18, 18)
                .addGroup(jPanel_changePassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordField_PassAgain, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(37, 37, 37)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jPanel_changePassLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel16, jLabel17, jLabel18});

        jLayeredPane1.setLayer(jPanel_InforAccount, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jLayeredPane1.setLayer(jPanel_changePass, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_InforAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_changePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel_InforAccount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel_changePass, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu_inforAccount.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/applications_system.png"))); // NOI18N
        jMenu_inforAccount.setText("Hệ thống");

        jMenuItem1.setText("Thông tin tài khoản");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu_inforAccount.add(jMenuItem1);

        jMenuItem2.setText("Đổi mật khẩu");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu_inforAccount.add(jMenuItem2);

        jMenuBar1.add(jMenu_inforAccount);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        setVisiableInforAccountPanel();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        // TODO add your handling code here:
        setVisiableChangePass();
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (jTextField_diaChi.isVisible() == false) {
            changePassWithAdmin();
        } else {
            changePassWithCarOwner();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (jTextField_diaChi.isVisible() == false) {
            updateInforAdmin();
        } else {
            updateInforCarOwner();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    public void setInforAccount(CarOwner carOwner) {
        jTextField_cmt.setText(carOwner.getCmt());
        jTextField_ten.setText(carOwner.getTen());
        jTextField_nhaXe.setText(carOwner.getNhaXe());
        jTextField_sdt.setText(carOwner.getSdt());
        jTextField_email.setText(carOwner.getEmail());
        if (carOwner.getGioiTinh().equalsIgnoreCase("Nam")) {
            jRadioButton_nam.setSelected(true);
        } else {
            jRadioButton_nu.setSelected(true);
        }
        jXDatePicker_ngaySinh.setDate(carOwner.getNgaySinh());
        jTextField_diaChi.setText(carOwner.getDiaChi());
    }

    public void setInforAccountWithAdmin(Admin admin) {
        jTextField_cmt.setText(admin.getCmt());
        jTextField_ten.setText(admin.getTen());
        jTextField_nhaXe.setText(admin.getSdt());
        jTextField_sdt.setText(admin.getEmail());
        jTextField_email.setText(admin.getDiaChi());
        jXDatePicker_ngaySinh.setDate(admin.getNgaySinh());
        if (admin.getGioiTinh().equalsIgnoreCase("Nam")) {
            jRadioButton_nam.setSelected(true);
        } else {
            jRadioButton_nu.setSelected(true);
        }

    }

    private void setVisiableInforAccountPanel() {
        jPanel_InforAccount.setVisible(true);
        jPanel_changePass.setVisible(false);
    }

    public void setVisiableChangePass() {
        jPanel_InforAccount.setVisible(false);
        jPanel_changePass.setVisible(true);
    }

    public void setVisiableWithAdmin() {
        jLabel_diaChi.setVisible(false);
        jTextField_diaChi.setVisible(false);
        jLabel_nhaXe.setText("Số điện thoại :");
        jLabel_sdt.setText("Email :");
        jLabel_email.setText("Địa chỉ :");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InforAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InforAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InforAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InforAccount.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InforAccount().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel_diaChi;
    private javax.swing.JLabel jLabel_email;
    private javax.swing.JLabel jLabel_gioiTinh;
    private javax.swing.JLabel jLabel_ngaySinh;
    private javax.swing.JLabel jLabel_nhaXe;
    private javax.swing.JLabel jLabel_sdt;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenu jMenu_inforAccount;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel_InforAccount;
    private javax.swing.JPanel jPanel_changePass;
    private javax.swing.JPasswordField jPasswordField_PassAgain;
    private javax.swing.JPasswordField jPasswordField_PassNew;
    private javax.swing.JPasswordField jPasswordField_PassOld;
    private javax.swing.JRadioButton jRadioButton_nam;
    private javax.swing.JRadioButton jRadioButton_nu;
    private javax.swing.JTextField jTextField_cmt;
    private javax.swing.JTextField jTextField_diaChi;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_nhaXe;
    private javax.swing.JTextField jTextField_sdt;
    private javax.swing.JTextField jTextField_ten;
    private org.jdesktop.swingx.JXDatePicker jXDatePicker_ngaySinh;
    // End of variables declaration//GEN-END:variables

    private void changePassWithCarOwner() {
        String cmt = jTextField_cmt.getText();
        String passOld = new String(jPasswordField_PassOld.getPassword());
        String passNew = new String(jPasswordField_PassNew.getPassword());
        String passAgain = new String(jPasswordField_PassAgain.getPassword());
        Account acc = new Account(cmt, passOld, "Chủ xe");
        int result = ac.setOwnerPass(acc, passNew, passAgain);
        switch (result) {
            case MysqlAccountDao.RESULT_EMPTY:
                JOptionPane.showMessageDialog(this, "Bạn phải điền đầy đủ thông tin");
                break;
            case MysqlAccountDao.RESULT_ACCOUNT_INCORRECT:
                JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác.Vui lòng thử lại!!");
                break;
            case MysqlAccountDao.RESULT_PASSAGAIN_NOT_SAME:
                JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp.Vui lòng nhập lại!!");
                break;
            case MysqlAccountDao.RESULT_SUCCESS:
                JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công!!");
                break;
            case MysqlAccountDao.RESULT_SQL_ERROR:
                JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại!!");
                break;
        }
    }

    private void changePassWithAdmin() {
        String cmt = jTextField_cmt.getText();
        String passOld = new String(jPasswordField_PassOld.getPassword());
        String passNew = new String(jPasswordField_PassNew.getPassword());
        String passAgain = new String(jPasswordField_PassAgain.getPassword());
        Account acc = new Account(cmt, passOld, "Admin");
        int result = ac.setAdminPass(acc, passNew, passAgain);
        switch (result) {
            case MysqlAccountDao.RESULT_EMPTY:
                JOptionPane.showMessageDialog(this, "Bạn phải điền đầy đủ thông tin");
                break;
            case MysqlAccountDao.RESULT_ACCOUNT_INCORRECT:
                JOptionPane.showMessageDialog(this, "Mật khẩu không chính xác.Vui lòng thử lại!!");
                break;
            case MysqlAccountDao.RESULT_PASSAGAIN_NOT_SAME:
                JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp.Vui lòng nhập lại!!");
                break;
            case MysqlAccountDao.RESULT_SUCCESS:
                JOptionPane.showMessageDialog(this, "Thay đổi mật khẩu thành công!!");
                break;
            case MysqlAccountDao.RESULT_SQL_ERROR:
                JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại!!");
                break;
        }
    }

    private void updateInforAdmin() {
        String cmt = jTextField_cmt.getText();
        String ten = jTextField_ten.getText();
        String sdt = jTextField_nhaXe.getText();
        String email = jTextField_sdt.getText();
        String diaChi = jTextField_email.getText();
        String gioiTinh = "";
        if (jRadioButton_nam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        if (jXDatePicker_ngaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh");
        } else {
            Date ngaySinh = new Date(jXDatePicker_ngaySinh.getDate().getTime());
            Admin adminUpdate = new Admin(cmt, ten, gioiTinh, ngaySinh, sdt, email, diaChi);
            int result = adminController.updateInforAdmin(adminUpdate);
            switch (result) {
                case MysqlAdminDao.RESULT_EMPTY:
                    JOptionPane.showMessageDialog(this, "Bạn phải điền đầy đủ thông tin");
                    break;
                case MysqlAdminDao.RESULT_ERROR_SDT:
                    JOptionPane.showMessageDialog(this, "Định dạng số điện thoại không đúng.Vui lòng nhập lại");
                    break;
                case MysqlAdminDao.RESULT_ERROR_EMAIL:
                    JOptionPane.showMessageDialog(this, "Định dạng email không đúng.Vui lòng nhập lại");
                    break;
                case MysqlAdminDao.RESULT_SUCCESS:
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    break;
                case MysqlAdminDao.RESULT_ERROR_SQL:
                    JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                    break;
            }
        }
    }

    private void updateInforCarOwner() {
        String cmt = jTextField_cmt.getText();
        String ten = jTextField_ten.getText();
        String nhaXe = jTextField_nhaXe.getText();
        String sdt = jTextField_sdt.getText();
        String email = jTextField_email.getText();
        String gioiTinh = "";
        if (jRadioButton_nam.isSelected()) {
            gioiTinh = "Nam";
        } else {
            gioiTinh = "Nữ";
        }
        if (jXDatePicker_ngaySinh.getDate() == null) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày sinh");
        } else {
            Date ngaySinh = new Date(jXDatePicker_ngaySinh.getDate().getTime());
            String diaChi = jTextField_diaChi.getText();
            CarOwner carOwnerUpdate = new CarOwner(cmt, ten, nhaXe, sdt, email, gioiTinh, ngaySinh, diaChi);
            int result = coc.updateInforCarOwner(carOwnerUpdate);
            switch (result) {
                case MysqlCarOwnerDao.RESULT_EMPTY:
                    JOptionPane.showMessageDialog(this, "Các trường dữ liệu không được để trống.Vui lòng nhập đầy đủ thông tin");
                    break;
                case MysqlCarOwnerDao.RESULT_ERROR_CMT:
                    JOptionPane.showMessageDialog(this, "Định dạng chứng minh thư không đúng.Vui lòng nhập lại!");
                    break;
                case MysqlCarOwnerDao.RESULT_ERROR_SDT:
                    JOptionPane.showMessageDialog(this, "Định dạng số điện thoại không đúng.Vui lòng nhập lại!");
                    break;
                case MysqlCarOwnerDao.RESULT_ERROR_EMAIL:
                    JOptionPane.showMessageDialog(this, "Định dạng email không đúng.Vui lòng nhập lại!");
                    break;
                case MysqlCarOwnerDao.RESULT_SUCCESS:
                    JOptionPane.showMessageDialog(this, "Cập nhật thông tin thành công");
                    break;
                case MysqlCarOwnerDao.RESULT_ERROR_SQL:
                    JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                    break;
            }
        }
    }
}
