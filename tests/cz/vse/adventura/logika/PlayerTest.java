package cz.vse.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Testovací třída PlayerTest slouží k otestování
 * třídy Player
 *
 */
public class PlayerTest {

    private Hra hra1;

    /***************************************************************************
     * Testuje omezení velikosti inventáře.
     *
     */
    @Test
    public void testOmezeniInvt(){
        Player player1 = new Player("nekdo");
        Vec vec1 = new Vec("1",true,"",true,false);
        Vec vec2 = new Vec("2",true,"",true,false);
        Vec vec3 = new Vec("3",true,"",true,false);
        Vec vec4 = new Vec("4",true,"",true,false);
        Vec vec5 = new Vec("5",true,"",true,false);
        Vec vec6 = new Vec("6",true,"",true,false);
        player1.addInvtVec(vec1);
        player1.addInvtVec(vec2);
        player1.addInvtVec(vec3);
        player1.addInvtVec(vec4);
        player1.addInvtVec(vec5);
        assertEquals(5,player1.getInvt().size());
        player1.addInvtVec(vec6);
        assertEquals(5,player1.getInvt().size());
    }

    /***************************************************************************
     * Testuje úspěšnost odstranění věcí z inventáře.
     *
     */
    @Test
    public void testOdtraneniPredmetuZInvt(){
        Player player1 = new Player("nekdo");
        Vec vec1 = new Vec("1",true,"",true,false);
        Vec vec2 = new Vec("2",true,"",true,false);
        player1.addInvtVec(vec1);
        player1.addInvtVec(vec2);
        player1.removeInvtVec("2");
        assertEquals(1,player1.getInvt().size());
    }

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
     * Testuje, že nelze sebrat nesebratelné věci do inventáře.
     */
    @Test
    public void testNesebratelnosti(){
        hra1.zpracujPrikaz("seber motorka");
        assertEquals(0,hra1.getHerniPlan().getPlayer().getInvt().size());
    }

}
