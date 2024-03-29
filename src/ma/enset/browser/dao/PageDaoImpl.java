package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Favori;
import ma.enset.browser.dao.entities.Historique;
import ma.enset.browser.dao.entities.Page;
import ma.enset.browser.dao.entities.Telechargement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PageDaoImpl implements PageDao {

    @Override
    public void addPage(Page page) {
       Connection connection= SingletonConnexionDB.getConnection();

        try {
            PreparedStatement pstm=connection.prepareStatement("insert into PAGE(IDHISTORIQUE,IDFAVORI,NAMEPAGE,URLPAGE,PHOTO,DATEPAGE) values(?,?,?,?,?,?)");
            pstm.setDate(1, Date.valueOf(page.getHistorique().getIdHistorique()));
            pstm.setInt(2,page.getFavori().getIdFavori());
            pstm.setString(3,page.getNamePage());
            pstm.setString(4,page.getUrlPage());
            pstm.setString(5,page.getPhoto());
            pstm.setTime(6, Time.valueOf(page.getDateTime()));
            pstm.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Page> getAllPage() {
        Connection conn= SingletonConnexionDB.getConnection();
        List<Page> pages=new ArrayList<>();
        try {
            PreparedStatement pstm=conn.prepareStatement("select * from PAGE");
            ResultSet rs= pstm.executeQuery();
            while (rs.next()){
                Page p=new Page(rs.getInt("IDPAGE"),rs.getString("NAMEPAGE"), rs.getString("URLPAGE"),rs.getString("PHOTO"),rs.getTime("DATEPAGE").toLocalTime());
                p.setHistorique(new Historique(rs.getDate("IDHISTORIQUE").toLocalDate()));
                p.setFavori(new Favori(rs.getInt("IDFAVORI")));
                //Page p=new Page();
                //System.out.println(rs.getDate("DATEPAGE").toLocalDate().atTime(rs.getTime("DATEPAGE").toLocalTime()));
                pages.add(p);
                ////////////interet de 'historique/////////////////
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  pages;
    }

    @Override
    public List<Page> searchPage(String cle) {
        Connection connection= SingletonConnexionDB.getConnection();

        List<Page> pages=new ArrayList<>();
        try {
            PreparedStatement pstm=connection.prepareStatement("select * from PAGE where NAMEPAGE like ?");
            pstm.setString(1,"%"+cle+"%");
            pstm.executeQuery();
            ResultSet rs= pstm.getResultSet();
            while (rs.next()){
                Page p=new Page();
                p.setId(rs.getInt("IDPAGE"));
                p.setHistorique(new Historique(rs.getDate("IDHISTORIQUE").toLocalDate()));
                p.setFavori(new Favori(rs.getInt("IDFAVORI")));
                p.setNamePage(rs.getString("NAMEPAGE"));
                p.setUrlPage(rs.getString("URLPAGE"));
                p.setPhoto(rs.getString("PHOTO"));
                p.setDateTime(rs.getTime("DATEPAGE").toLocalTime());
                pages.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pages;
    }


    @Override
    public void deletePage(Page page) {
        Connection conn= SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("DELETE FROM PAGE WHERE IDPAGE=?");
            pstm.setInt(1,page.getId());
            pstm.executeUpdate();
            System.out.println("La page a été supprimer avec succès...");
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public List<Page> getPageByIdHistorique(LocalDate date) {
        Connection conn= SingletonConnexionDB.getConnection();
        List<Page> pages=new ArrayList<>();
        try {
            PreparedStatement pstm=conn.prepareStatement("select * from PAGE WHERE IDHISTORIQUE=?");
            pstm.setDate(1,Date.valueOf(date));
            ResultSet rs= pstm.executeQuery();
            while (rs.next()){
                Page p=new Page(rs.getInt("IDPAGE"),rs.getString("NAMEPAGE"), rs.getString("URLPAGE"),rs.getString("PHOTO"),rs.getTime("DATEPAGE").toLocalTime());
                p.setHistorique(new Historique(rs.getDate("IDHISTORIQUE").toLocalDate()));
                p.setFavori(new Favori(rs.getInt("IDFAVORI")));
                pages.add(p);
                ////////////interet de 'historique/////////////////
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  pages;
    }
    @Override
    public void affectPageToDownload(Telechargement telechargement, Page page) throws SQLException {
        Connection conn= SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDTELECHARGEMENT=? WHERE IDPAGE=?");
            pstm.setInt(1,telechargement.getIdTelechargement());
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void affectPageToHistorical(Historique historique, Page page) throws SQLException {
        page.setHistorique(historique);
        Connection conn= SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDHISTORIQUE=? WHERE IDPAGE=?");
            pstm.setDate(1,Date.valueOf(historique.getIdHistorique()));
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void affectPageTofavorite(Favori favori, Page page) throws SQLException {
        page.setFavori(favori);
        Connection conn= SingletonConnexionDB.getConnection();
        try {
            PreparedStatement pstm=conn.prepareStatement("update PAGE SET IDFAVORI=? WHERE IDPAGE=?");
            pstm.setInt(1,favori.getIdFavori());
            pstm.setInt(2,page.getId());
            pstm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
