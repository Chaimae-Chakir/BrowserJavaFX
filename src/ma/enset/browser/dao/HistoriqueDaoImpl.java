package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Historique;
import ma.enset.browser.dao.entities.Page;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HistoriqueDaoImpl implements HistoriqueDao{

    @Override
    public void supprimerPageInHistorique(Page page) {
        Connection conn= SignletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDHISTORIQUE=? WHERE IDPAGE=?");
            pstm.setDate(1, Date.valueOf("0001-01-01"));
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
            System.out.println("l'historique a été supprimer avec succès...");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void affectPageToHistorical(Historique historique, Page page) throws SQLException {
        page.setHistorique(historique);
        Connection conn=SignletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDHISTORIQUE=? WHERE IDPAGE=?");
            pstm.setDate(1,Date.valueOf(historique.getIdHistorique()));
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
