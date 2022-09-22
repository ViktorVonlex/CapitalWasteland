package cz.vse.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/*******************************************************************************
 * Testovací třída PrikazSeberTest slouží k otestování
 * třídy PrikazSeber
 *
 */
public class PrikazSeberTest {
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
     * Testuje, že lze sebrat pouze existující sebratelné věci.
     */
    @Test
    public void testSebratelnosti(){
        hra1.zpracujPrikaz("seber motorka");
        assertFalse(hra1.getHerniPlan().getPlayer().getInvt().containsKey("motorka"));
        hra1.zpracujPrikaz("seber sdfgsg");
        assertEquals(0, hra1.getHerniPlan().getPlayer().getInvt().size());
        hra1.zpracujPrikaz("seber ");
        assertEquals(0, hra1.getHerniPlan().getPlayer().getInvt().size());
        hra1.zpracujPrikaz("seber gfds gssg");
        assertEquals(0, hra1.getHerniPlan().getPlayer().getInvt().size());
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi gunRunners");
        hra1.zpracujPrikaz("seber glock");
        assertEquals(1, hra1.getHerniPlan().getPlayer().getInvt().size());
    }
}
