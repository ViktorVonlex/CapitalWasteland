package cz.vse.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PrikazZautocTest slouží k otestování
 * třídy PrikazZautoc
 *
 */
public class PrikazZautocTest {

    private Hra hra1;

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

    /***************************************************************************
     * Testuje, že příkaz "zaútoč" dané npc zabije, pokud má hráč věc "glock".
     */
    @Test
    public void testZautocSeZbrani(){
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi gunRunners");
        hra1.zpracujPrikaz("seber glock");
        assertEquals(1,hra1.getHerniPlan().getPlayer().getInvt().size());
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi východní_ulice");
        hra1.zpracujPrikaz("jdi stan");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().vratNpc("thorin").isNazivu());
        hra1.zpracujPrikaz("zaútoč thorin");
        assertFalse(hra1.getHerniPlan().getAktualniProstor().vratNpc("thorin").isNazivu());
    }

    /***************************************************************************
     * Testuje, že příkaz "zaútoč" dané npc nezabije, když hráč nemá u sebe věc "glock".
     */
    @Test
    public void testZautocBezeZbrane(){
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi východní_ulice");
        hra1.zpracujPrikaz("jdi stan");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().vratNpc("thorin").isNazivu());
        hra1.zpracujPrikaz("zaútoč thorin");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().vratNpc("thorin").isNazivu());
    }

    /***************************************************************************
     * Testuje, že příkaz "zaútoč" dané npc nezabije, pokud je npc nastaveno jako nezabitelné.
     */
    @Test
    public void testZautocNaNezabitelnouPostavu(){
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi gunRunners");
        hra1.zpracujPrikaz("seber glock");
        assertEquals(1,hra1.getHerniPlan().getPlayer().getInvt().size());
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi západní_ulice");
        hra1.zpracujPrikaz("jdi čerpací_stanice");
        hra1.zpracujPrikaz("zaútoč franta");
        assertTrue(hra1.getHerniPlan().getAktualniProstor().vratNpc("franta").isNazivu());
    }
}
