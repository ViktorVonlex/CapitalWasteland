package cz.vse.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/*******************************************************************************
 * Testovací třída PrikazPolozTest slouží k otestování
 * třídy PrikazPoloz
 *
 */
public class PrikazPolozTest {

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
     * Testuje, že lze přenášet přenositelné věci mezi prostory.
     */
    @Test
    public void testPrenositelnosti(){
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi gunRunners");
        hra1.zpracujPrikaz("seber glock");
        assertEquals(1, hra1.getHerniPlan().getPlayer().getInvt().size());
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("polož glock");
        assertEquals(0, hra1.getHerniPlan().getPlayer().getInvt().size());
        assertTrue(hra1.getHerniPlan().getAktualniProstor().getVeci().containsKey("glock"));
    }
}
