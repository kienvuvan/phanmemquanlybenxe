/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information.view;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 *
 * @author kienanh2903
 */
public class InforPanel extends javax.swing.JPanel {
    
    /**
     * Creates new form Information
     */
    public InforPanel(String stt, String tieuDe, Timestamp ngayDang, String nguoiDang, String noiDung) {
        initComponents();
        jLabel_stt.setText(stt);
        jLabel_tieuDe.setText(tieuDe);
        SimpleDateFormat simple = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        jLabel_ngayDang.setText(tieuDe);
        if(ngayDang == null){
            jLabel_ngayDang.setText("");
        }else{
            jLabel_ngayDang.setText(simple.format(ngayDang));
        }
        jLabel_nguoiDang.setText(nguoiDang);
        jTextArea_noiDung.setText(noiDung);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_tieuDe = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel_ngayDang = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel_nguoiDang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea_noiDung = new javax.swing.JTextArea();
        jLabel_stt = new javax.swing.JLabel();

        jLabel_tieuDe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel_tieuDe.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel2.setText("Ngày đăng : ");

        jLabel4.setText("Người đăng :");

        jTextArea_noiDung.setEditable(false);
        jTextArea_noiDung.setColumns(20);
        jTextArea_noiDung.setRows(5);
        jScrollPane1.setViewportView(jTextArea_noiDung);

        jLabel_stt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel_ngayDang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel_nguoiDang, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(94, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel_stt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel_tieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel_tieuDe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel_stt, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 21, Short.MAX_VALUE)
                    .addComponent(jLabel_ngayDang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel_nguoiDang, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel4, jLabel_ngayDang, jLabel_nguoiDang});

    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel_ngayDang;
    private javax.swing.JLabel jLabel_nguoiDang;
    private javax.swing.JLabel jLabel_stt;
    private javax.swing.JLabel jLabel_tieuDe;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea_noiDung;
    // End of variables declaration//GEN-END:variables
}
