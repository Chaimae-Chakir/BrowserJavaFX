package ma.enset.browser.dao;

import ma.enset.browser.dao.entities.Favori;
import ma.enset.browser.dao.entities.Page;

import java.sql.SQLException;

public interface FavoriDao extends Dao<Favori>{
    public void supprimerPageInFavoris(Page page);
    public void ajouterPagetoFavoris(Page page);

}
