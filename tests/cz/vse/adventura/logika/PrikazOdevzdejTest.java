package cz.vse.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída PrikazOdevzdejTest slouží k otestování
 * třídy PrikazOdevzdej
 *
 */
public class PrikazOdevzdejTest {
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
     * Testuje, že lze odevzdat věc, kterou npc chce, a že hráč obratem dostane věc od npc.
     */
    @Test
    public void testFunkcniOdevzdat(){
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi samoobsluha");
        hra1.zpracujPrikaz("seber džus");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi západní_ulice");
        hra1.zpracujPrikaz("jdi čerpací_stanice");
        hra1.zpracujPrikaz("odevzdej džus franta");
        assertTrue(hra1.getHerniPlan().getPlayer().getInvt().containsKey("benzín"));
        assertFalse(hra1.getHerniPlan().getPlayer().getInvt().containsKey("džus"));
    }

    /***************************************************************************
     * Testuje, že nelze odevzdat věc, kterou npc nechce.
     */
    @Test
    public void testNelzeOdevzdatPredmetCoNechce(){
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi gunRunners");
        hra1.zpracujPrikaz("seber glock");
        hra1.zpracujPrikaz("jdi obchodní_centrum");
        hra1.zpracujPrikaz("jdi centrální_ulice");
        hra1.zpracujPrikaz("jdi západní_ulice");
        hra1.zpracujPrikaz("jdi čerpací_stanice");
        hra1.zpracujPrikaz("odevzdej glock franta");
        assertFalse(hra1.getHerniPlan().getPlayer().getInvt().containsKey("benzín"));
    }
}
