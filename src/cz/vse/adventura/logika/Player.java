package cz.vse.adventura.logika;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *  Třída Player
 *
 *  Tato třída obsajuje konstruktor, pomocí kterého je vytvořena postava hráče.
 *
 */

public class Player {

    public String name;
    public int mistoInvt;
    public Map<String, Vec> invt;

    /**
     *  Konstruktor třídy
     *
     * @param name představuje jméno postavy
     * Rovněž vytváří hashMapu invt, do které se ukládají věci, které hráč sebere.
     */
    public Player(String name) {
        this.name = name;
        invt = new HashMap<>();
        mistoInvt = 5;

    }

    /**
     *  Přidá věc do inventáře a zmenší místo v inventáři o 1.
     */
    public void addInvtVec(Vec vec){
        if (mistoInvt != 0){
            mistoInvt--;
            invt.put(vec.getNazev(), vec);
        }
    }

    /**
     *  Vrátí instanci třídy Vec.
     */
    public Vec vratInvtVec(String jmenoVeci){
        return invt.get(jmenoVeci);
    }

    /**
     *  Odebere věc z inventáře a zvětší místo v inventáři o 1.
     */
    public void removeInvtVec(String jmenoVeci){
        mistoInvt++;
        invt.remove(jmenoVeci);
    }

    /**
     *  Vrátí obsah mapy inventáře.
     */
    public Map<String, Vec> getInvt() {
        return invt;
    }

    /**
     *  Vrátí textový řetezec "V inventáři máš " + výpis obsahu inventáře.
     */
    @Override
    public String toString() {
        return "V inventáři máš " + invt.keySet();
    }

    /**
     *  Porovnává instance třídy Player podle parametru name.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    /**
     * Metoda hashCode vraci ciselny identifikator instance, ktery se pouziva
     * pro optimalizaci ukladani v dynamickych datovych strukturach. Pri
     * prekryti metody equals je potreba prekryt i metodu hashCode. Podrobny
     * popis pravidel pro vytvareni metody hashCode je u metody hashCode ve
     * tride Object
     */
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
