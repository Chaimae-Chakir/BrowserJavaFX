package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Page;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class FavoriDaoImpl implements FavoriDao{
    @Override
    public void supprimerPageInFavoris(Page page){
        Connection conn= SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDFAVORI=? WHERE IDPAGE=?");
            pstm.setInt(1,1);
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
            System.out.println("Favori a été supprimer avec succès...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void ajouterPagetoFavoris(Page page){
        Connection conn= SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDFAVORI=? WHERE IDPAGE=?");
            pstm.setInt(1,2);
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
            System.out.println("La page a été ajouter au favori avec succès...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
