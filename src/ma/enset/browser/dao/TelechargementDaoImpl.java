package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Page;
import ma.enset.browser.dao.entities.Telechargement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TelechargementDaoImpl  implements TelechargementDao {
    @Override
    public void affectPageToDownload(Telechargement telechargement, Page page) throws SQLException {
        Connection conn= SignletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDTELECHARGEMENT=? WHERE IDPAGE=?");
            pstm.setInt(1,telechargement.getIdTelechargement());
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
