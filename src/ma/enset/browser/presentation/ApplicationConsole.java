package ma.enset.browser.presentation;

import ma.enset.browser.metier.IMetier;
import ma.enset.browser.metier.MetierImpl;
import ma.enset.browser.dao.entities.Page;

import java.util.ArrayList;
import java.util.List;

public class ApplicationConsole {
    public static void main(String[] args) {

        IMetier metier=new MetierImpl();
        List<Page> pages=new ArrayList<>();
        pages=metier.getAllPage();
        for (Page d:pages) {
            System.out.println(d);
        }

    }
}
