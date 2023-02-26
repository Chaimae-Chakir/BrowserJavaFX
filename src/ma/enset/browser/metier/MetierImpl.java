package ma.enset.browser.metier;

import ma.enset.browser.dao.*;
import ma.enset.browser.dao.entities.Favori;
import ma.enset.browser.dao.entities.Historique;
import ma.enset.browser.dao.entities.Page;
import ma.enset.browser.dao.entities.Telechargement;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MetierImpl implements IMetier {
    PageDao pageDao;
    FavoriDao favoriDao;
    HistoriqueDao historiqueDao;
    TelechargementDao telechargementDao;



    public MetierImpl(PageDao pageDao, FavoriDao favoriDao, HistoriqueDao historiqueDao, TelechargementDao telechargementDao) {
        this.pageDao = pageDao;
        this.favoriDao = favoriDao;
        this.historiqueDao = historiqueDao;
        this.telechargementDao = telechargementDao;
    }

    public MetierImpl() {
        Connection connection= SignletonConnexionDB.getConnection();
    }


    @Override
    public void addPage(Page page) {
       pageDao.addPage(page);
    }


    @Override
    public List<Page> searchPage(String cle) {
      return pageDao.searchPage(cle);
    }


    @Override
    public void deletePage(Page page) {
       pageDao.deletePage(page);
    }

    @Override
    public void affectPageTofavorite(Favori favori, Page page) throws SQLException {
    pageDao.affectPageTofavorite(favori,page);
    }

    @Override
    public void affectPageToHistorical(Historique historique, Page page) throws SQLException {
        pageDao.affectPageToHistorical(historique,page);
    }

    @Override
    public void affectPageToDownload(Telechargement telechargement, Page page) throws SQLException {
    pageDao.affectPageToDownload(telechargement,page);
    }

    @Override
    public List<Page> getPageByIdHistorique(LocalDate date) {
        return pageDao.getPageByIdHistorique(date);
    }


    @Override
    public void supprimerPageInHistorique(Page page) {
       historiqueDao.supprimerPageInHistorique(page);
    }
    @Override
    public void supprimerPageInFavoris(Page page){
        favoriDao.supprimerPageInFavoris(page);
    }

    @Override
    public void ajouterPagetoFavoris(Page page){
    favoriDao.ajouterPagetoFavoris(page);
    }
    @Override
    public List<Page> getAllPage() {
        Connection conn=SignletonConnexionDB.getConnection();
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

}