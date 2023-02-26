package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Historique;
import ma.enset.browser.dao.entities.Page;

import java.sql.SQLException;

public interface HistoriqueDao extends Dao<Historique>{
    public void supprimerPageInHistorique(Page page);
    void affectPageToHistorical(Historique historique, Page page) throws SQLException;

}
