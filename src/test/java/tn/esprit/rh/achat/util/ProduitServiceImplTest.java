package tn.esprit.rh.achat.util;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.services.IProduitService;
import tn.esprit.rh.achat.services.ProduitServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {
    @Autowired
    IProduitService produitService;
    private static final Logger l = LogManager.getLogger(ProduitServiceImpl.class);



    @Test
    public void testAddProduit() throws ParseException {
        List<Produit> Produits = produitService.retrieveAllProduits();
        Produit prod = new Produit();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date1 = dateFormat.parse("10/02/2020");
        Date date2 = dateFormat.parse("12/09/2022");
        prod.setCodeProduit("f-455112");
        prod.setLibelleProduit("Tele");
        prod.setDateCreation(date1);
        prod.setDateDerniereModification(date2);
        produitService.addProduit(prod);
        Produit savedProduit = produitService.addProduit(prod);
        l.info("Produit added : " + prod.getLibelleProduit());
        l.info("size produit : : " + Produits.size());
        assertNotNull(produitService.retrieveProduit(savedProduit.getIdProduit()));
    }
    @Test
    public void testRetrieveAllProduit() throws ParseException {
        try{
            List<Produit> Produits = produitService.retrieveAllProduits();
            //int expected = Produits.size();
            l.info("Produitssssssss " + Produits);
            l.info("Produit " +Produits.size());
            if(Produits.size()>0) {
                assertEquals(2, Produits.size());
                System.out.println("there is more then 0 Product");
                l.info("Produit " +Produits.size());
            }else{
                System.out.println("**************0 Product**************");
            }}catch (Exception e){
            l.info(e);
        }
    }

    @Test
    public void testDeleteProduit()  {
        try{
            List<Produit> Produits = produitService.retrieveAllProduits();
            Produit Prod =Produits.get(0);
            produitService.deleteProduit(Prod.getIdProduit());
        }catch (Exception e){
            l.info(e);}
    }
    @Test
    public void TestGetProduit(){
        try {
            Produit sa = new Produit();
            sa.setCodeProduit("f-5155");
            sa.setLibelleProduit("Telephone");
            // Produit savedProduit= produitService.addProduit(sa);
            Produit prod = produitService.retrieveProduit(sa.getIdProduit());
            l.info("****************Produit : " + prod.getLibelleProduit());
            System.out.println("****************Produit : "  + prod.getLibelleProduit());
            assertNotNull(produitService.retrieveProduit(sa.getIdProduit()));
        }catch (Exception e){
            l.info(e.getMessage());
        }
    }


}