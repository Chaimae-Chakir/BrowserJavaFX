package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Page;
import ma.enset.browser.dao.entities.Telechargement;

import java.sql.SQLException;

public interface TelechargementDao extends Dao<Telechargement> {
    void affectPageToDownload(Telechargement telechargement, Page page) throws SQLException;

}
