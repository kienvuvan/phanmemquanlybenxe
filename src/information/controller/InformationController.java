/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package information.controller;

import information.model.Information;
import information.view.InforPanel;
import java.util.List;
import javax.swing.JPanel;

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
        if (list.size() < stt*4) {
            for (int i = stt*4-4; i < stt*4; i++) {
                if (i < list.size()) {
                    Information infor = list.get(i);
                    InforPanel inforPanel = new InforPanel(infor.getId()+"", infor.getTieuDe(), infor.getNgayDang(), infor.getCmtAdmin(), infor.getNoiDung());
                    jPanel.add(inforPanel);
                } else {
                    InforPanel inforPanel = new InforPanel("", "", null, "", "");
                    jPanel.add(inforPanel);
                }
            }
        } else {
            for (int j = stt*4-4; j < stt*4; j++) {
                Information infor = list.get(j);
                InforPanel inforPanel = new InforPanel(infor.getId()+"", infor.getTieuDe(), infor.getNgayDang(), infor.getCmtAdmin(), infor.getNoiDung());
                jPanel.add(inforPanel);
            }
        }
    }
}
