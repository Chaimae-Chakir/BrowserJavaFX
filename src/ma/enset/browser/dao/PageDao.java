package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Favori;
import ma.enset.browser.dao.entities.Historique;
import ma.enset.browser.dao.entities.Page;
import ma.enset.browser.dao.entities.Telechargement;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface PageDao extends Dao<Page>{
    void addPage(Page page);
    void deletePage(Page page);
    List<Page> getAllPage();
    List<Page> searchPage(String cle);
    List<Page> getPageByIdHistorique(LocalDate date);
    void affectPageTofavorite(Favori favori, Page page) throws SQLException;
    void affectPageToHistorical(Historique historique, Page page) throws SQLException;
    void affectPageToDownload(Telechargement telechargement, Page page) throws SQLException;

}
