/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login.view;

import account.controller.AccountController;
import account.dao.MysqlAccountDao;
import static account.dao.MysqlAccountDao.RESULT_ACCOUNT_INCORRECT;
import account.model.Account;
import admin.controller.AdminController;
import admin.dao.MysqlAdminDao;
import carowner.controller.CarOwnerController;
import carowner.view.CarOwnerForm;
import javax.swing.JOptionPane;
import admin.view.AdminForm;
import admin.view.HandRequestUpdateCar;
import car.controller.CarController;
import car.model.CarUpdate;
import guest.view.HomeGuestForm;
import java.util.List;

/**
 *
 * @author Linh
 */
public class LoginForm extends javax.swing.JFrame {

    private final AdminController ac;
    private final CarOwnerController coc;
    private final CarController carController;
    private final AccountController accountController;

    /**
     * Creates new form LoginForm
     */
    public LoginForm() {
        initComponents();
        ac = new AdminController();
        coc = new CarOwnerController();
        carController = new CarController();
        accountController = new AccountController();
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
        jDialog_forgotPass = new javax.swing.JDialog();
        jLabel5 = new javax.swing.JLabel();
        jTextField_username = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jTextField_email = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jRadioButton_admin1 = new javax.swing.JRadioButton();
        jRadioButton_carOwner1 = new javax.swing.JRadioButton();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        userNameField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        loginButton = new javax.swing.JButton();
        jLabelTitle = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jRadioButton_admin = new javax.swing.JRadioButton();
        jRadioButton_carOwner = new javax.swing.JRadioButton();

        jDialog_forgotPass.setTitle("Quên mật khẩu");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Tài khoản :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Email đăng ký :");

        jButton1.setText("Cấp mật khẩu mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel8.setText("Loại tài khoản : ");

        buttonGroup2.add(jRadioButton_admin1);
        jRadioButton_admin1.setText("Admin");

        buttonGroup2.add(jRadioButton_carOwner1);
        jRadioButton_carOwner1.setSelected(true);
        jRadioButton_carOwner1.setText("Chủ xe");

        javax.swing.GroupLayout jDialog_forgotPassLayout = new javax.swing.GroupLayout(jDialog_forgotPass.getContentPane());
        jDialog_forgotPass.getContentPane().setLayout(jDialog_forgotPassLayout);
        jDialog_forgotPassLayout.setHorizontalGroup(
            jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_forgotPassLayout.createSequentialGroup()
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jDialog_forgotPassLayout.createSequentialGroup()
                        .addComponent(jRadioButton_admin1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jRadioButton_carOwner1))
                    .addGroup(jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton1)
                        .addGroup(jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField_email, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField_username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jDialog_forgotPassLayout.setVerticalGroup(
            jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog_forgotPassLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_username, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jTextField_email, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(28, 28, 28)
                .addGroup(jDialog_forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_carOwner1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_admin1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Trang đăng nhập ");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Tài khoản :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Mật khẩu :");

        jLabel1.setText("<html><u>Quên mật khẩu?<u><html>");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        loginButton.setText("Đăng nhập");
        loginButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                doLogin(evt);
            }
        });

        jLabelTitle.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitle.setText("Phần mềm quản lý bến xe khách Hà Nội");

        jLabel2.setText("Đăng nhập với quyền :");

        buttonGroup1.add(jRadioButton_admin);
        jRadioButton_admin.setText("Admin");

        buttonGroup1.add(jRadioButton_carOwner);
        jRadioButton_carOwner.setSelected(true);
        jRadioButton_carOwner.setText("Chủ xe");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(loginButton)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jRadioButton_admin)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jRadioButton_carOwner))
                                .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 23, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jRadioButton_admin)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jRadioButton_carOwner))
                .addGap(18, 18, 18)
                .addComponent(loginButton)
                .addGap(20, 20, 20))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel4, jLabel6, passField, userNameField});

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void doLogin(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_doLogin
        // TODO add your handling code here:
        if (jRadioButton_admin.isSelected()) {
            String pass = new String(passField.getPassword());
            int result = ac.loginAdmin(userNameField.getText(), pass);
            switch (result) {
                case MysqlAdminDao.RESULT_EMPTY:
                    JOptionPane.showMessageDialog(this, "Các trường dữ liệu không được để trống.Vui lòng nhập lại");
                    break;
                case MysqlAdminDao.RESULT_LOGIN_SUCCESS:
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                    List<CarUpdate> listCarUpdates = carController.getAllRequest();
                    if (listCarUpdates.size() > 0) {
                        if (JOptionPane.showConfirmDialog(null, "Hiện tại có chủ xe gửi yêu cầu cập nhật thông tin xe.\nBạn có muốn chuyển sang giao diện xem và xác nhận yêu cầu không ??", "Thông báo ",
                                JOptionPane.YES_OPTION) == JOptionPane.YES_NO_OPTION) {
                            this.dispose();
                            HandRequestUpdateCar handRequestUpdateCar = new HandRequestUpdateCar(userNameField.getText());
                            handRequestUpdateCar.setVisible(true);
                        } else {
                            this.dispose();
                            AdminForm adminForm = new AdminForm(userNameField.getText());
                            adminForm.setVisible(true);
                        }
                    } else {
                        this.dispose();
                        AdminForm adminForm = new AdminForm(userNameField.getText());
                        adminForm.setVisible(true);
                    }
                    break;
                case MysqlAdminDao.RESULT_ACCOUNT_INCORECT:
                    JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác");
                    break;
                case MysqlAdminDao.RESULT_ERROR_SQL:
                    JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                    break;
            }
        } else {
            String pass = new String(passField.getPassword());
            int result = coc.loginCarOwner(userNameField.getText(), pass);
            switch (result) {
                case MysqlAdminDao.RESULT_EMPTY:
                    JOptionPane.showMessageDialog(this, "Các trường dữ liệu không được để trống.Vui lòng nhập lại");
                    break;
                case MysqlAdminDao.RESULT_LOGIN_SUCCESS:
                    JOptionPane.showMessageDialog(this, "Đăng nhập thành công");
                    this.dispose();
                    new CarOwnerForm(userNameField.getText()).setVisible(true);
                    break;
                case MysqlAdminDao.RESULT_ACCOUNT_INCORECT:
                    JOptionPane.showMessageDialog(this, "Tài khoản hoặc mật khẩu không chính xác");
                    break;
                case MysqlAdminDao.RESULT_ERROR_SQL:
                    JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                    break;
            }
        }
    }//GEN-LAST:event_doLogin

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        jDialog_forgotPass.setVisible(true);
        jDialog_forgotPass.setSize(467, 320);
        jDialog_forgotPass.setLocationRelativeTo(null);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String username = jTextField_username.getText().trim();
        String email = jTextField_email.getText().trim();
        int result = 0;
        if (jRadioButton_admin1.isSelected()) {
            result = accountController.forgotPassAdmin(username, email);
        } else {
            result = accountController.forgotPassCarOwner(username, email);
        }
        switch (result) {
            case MysqlAccountDao.RESULT_EMPTY:
                JOptionPane.showMessageDialog(jDialog_forgotPass, "Các trường dữ liệu không được để trống.Vui lòng nhập lại");
                break;
            case MysqlAccountDao.RESULT_CMT_INCORRECT:
                JOptionPane.showMessageDialog(jDialog_forgotPass, "Định dạng chứng minh thư không đúng.Vui lòng nhập lại");
                break;
            case MysqlAccountDao.RESULT_EMAIL_INCORRECT:
                JOptionPane.showMessageDialog(jDialog_forgotPass, "Định dạng email không đúng.Vui lòng nhập lại");
                break;
            case MysqlAccountDao.RESULT_ACCOUNT_INCORRECT:
                JOptionPane.showMessageDialog(jDialog_forgotPass, "Tài khoản hoặc email xác nhận không đúng");
                break;
            case MysqlAccountDao.RESULT_SQL_ERROR:
                JOptionPane.showMessageDialog(jDialog_forgotPass, "Đã có lỗi xảy ra.Vui lòng thử lại sau");
                break;
            case MysqlAccountDao.RESULT_SUCCESS:
                JOptionPane.showMessageDialog(jDialog_forgotPass, "Mật khẩu đã được cấp lại và gửi vào email của bạn.Vui lòng kiểm tra lại email");
                break;
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        this.dispose();
        HomeGuestForm homeGuestForm = new HomeGuestForm();
        homeGuestForm.setVisible(true);
    }//GEN-LAST:event_formWindowClosing


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JDialog jDialog_forgotPass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabelTitle;
    private javax.swing.JRadioButton jRadioButton_admin;
    private javax.swing.JRadioButton jRadioButton_admin1;
    private javax.swing.JRadioButton jRadioButton_carOwner;
    private javax.swing.JRadioButton jRadioButton_carOwner1;
    private javax.swing.JTextField jTextField_email;
    private javax.swing.JTextField jTextField_username;
    private javax.swing.JButton loginButton;
    private javax.swing.JPasswordField passField;
    private javax.swing.JTextField userNameField;
    // End of variables declaration//GEN-END:variables
}
