package cz.vse.adventura.logika;


import cz.vse.adventura.logika.Prostor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/*******************************************************************************
 * Testovací třída ProstorTest slouží ke komplexnímu otestování
 * třídy Prostor
 *
 * @author    Jarmila Pavlíčková
 * @version   pro skolní rok 2016/2017
 */
public class ProstorTest
{
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
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /**
     * Testuje, zda jsou správně nastaveny průchody mezi prostory hry. Prostory
     * nemusí odpovídat vlastní hře, 
     */
    @Test
    public  void testLzeProjit() {		
        Prostor prostor1 = new Prostor("hala", "vstupní hala budovy VŠE na Jižním městě");
        Prostor prostor2 = new Prostor("bufet", "bufet, kam si můžete zajít na svačinku");
        prostor1.setVychod(prostor2);
        prostor2.setVychod(prostor1);
        assertEquals(prostor2, prostor1.vratSousedniProstor("bufet"));
        assertEquals(null, prostor2.vratSousedniProstor("pokoj"));
    }

    /**
     * Testuje, zda je možné do prostoru přidat vícero věcí a npc.
     */
    @Test
    public void testObsahujeVeciANpc(){
        Prostor prostor1 = new Prostor("obejvák","nejlepší místo, kde se může člověk natáhnout");
        Vec vec1 = new Vec("ovladač",true,"ovladač od televize",false,false);
        Vec vec2 = new Vec ("nevim",true,"gsdfgs", false,false);
        Npc npc1 = new Npc ("já","zzzzz","zzzz", true,vec1,null,true);
        Npc npc2 = new Npc ("fasdf","fgfa","asdad",true,null,null,false);
        prostor1.addNpc(npc1);
        prostor1.addVec(vec1);
        prostor1.addVec(vec2);
        prostor1.addNpc(npc2);
        assertEquals(2, prostor1.getPostavy().size());
        assertEquals(2, prostor1.getVeci().size());
    }

}
