package cz.vse.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje jestli lze projít všechny místnosti.
     * 
     */
    @Test
    public void testPrubehVsechMistnosti() {
        assertEquals("parkovací_dům", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi centrální_ulice");
        assertEquals("centrální_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi východní_ulice");
        assertEquals("východní_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi stan");
        assertEquals("stan", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi východní_ulice");
        assertEquals("východní_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi centrální_ulice");
        assertEquals("centrální_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        assertEquals("obchodní_centrum", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi gunRunners");
        assertEquals("gunRunners", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        assertEquals("obchodní_centrum", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi samoobsluha");
        assertEquals("samoobsluha", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        assertEquals("obchodní_centrum", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi centrální_ulice");
        assertEquals("centrální_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi západní_ulice");
        assertEquals("západní_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi čerpací_stanice");
        assertEquals("čerpací_stanice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi západní_ulice");
        assertEquals("západní_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi park");
        assertEquals("park", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi západní_ulice");
        assertEquals("západní_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi centrální_ulice");
        assertEquals("centrální_ulice", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi parkovací_dům");
        assertEquals("parkovací_dům", hra1.getHerniPlan().getAktualniProstor().getNazev());
    }
    /**
     * Testuje kompletní průchod hrou a její konec..
     *
     */
    @Test
    public void testPrubehHrou(){
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi gunRunners");
        hra1.zpracujPrikaz("seber glock");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi samoobsluha");
        assertEquals("samoobsluha", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("seber džus");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi východní_ulice");
        hra1.zpracujPrikaz("jdi stan");
        hra1.zpracujPrikaz("zaútoč thorin");
        assertTrue(hra1.getHerniPlan().getPlayer().getInvt().containsKey("drobáky"));
        hra1.zpracujPrikaz("jdi východní_ulice");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi samoobsluha");
        assertEquals("samoobsluha", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("odevzdej drobáky automat_na_pití");
        assertTrue(hra1.getHerniPlan().getPlayer().getInvt().containsKey("pivíčko"));
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi západní_ulice");
        hra1.zpracujPrikaz("jdi čerpací_stanice");
        hra1.zpracujPrikaz("odevzdej džus franta");
        hra1.zpracujPrikaz("jdi západní_ulice");
        hra1.zpracujPrikaz("jdi park");
        hra1.zpracujPrikaz("odevzdej pivíčko bezdomovec");
        hra1.zpracujPrikaz("jdi západní_ulice");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi parkovací_dům");
        assertTrue(hra1.getHerniPlan().getPlayer().getInvt().containsKey("benzín"));
        assertTrue(hra1.getHerniPlan().getPlayer().getInvt().containsKey("klíčky"));
        hra1.zpracujPrikaz("polož klíčky");
        hra1.zpracujPrikaz("polož benzín");
        hra1.zpracujPrikaz("odjeď úkryt");
        assertEquals("úkryt", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertTrue(hra1.konecHry());
    }
}
