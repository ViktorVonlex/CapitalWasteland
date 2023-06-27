package cz.vse.adventura.logika;

import java.util.Objects;

/**
 *  Třída Vec
 *
 *  Tato třída obsajuje konstruktor, pomocí kterého jsou vytvářeny předměty v herním plánu.
 *
 */

public class Vec {

    private final String nazev;
    private final boolean sebratelna;
    private final String popisPredmetu;
    private final boolean odevzdatelna;
    private final boolean potrebaProOdjezd;

    /**
     *  Konstruktor třídy
     *
     * @param nazev představuje název věci.
     * @param sebratelna určuje zda se dá věc sebrat.
     * @param popisPredmetu text popisující věc.
     * @param odevzdatelna určuje zda se věc dá odevzdat nějakému npc.
     * @param potrebaProOdjezd určuje zda je nezbytná pro odjezd do závěrečné místnosti.
     */
    public Vec(String nazev, boolean sebratelna, String popisPredmetu, boolean odevzdatelna, boolean potrebaProOdjezd) {
        this.nazev = nazev;
        this.sebratelna = sebratelna;
        this.popisPredmetu = popisPredmetu;
        this.odevzdatelna = odevzdatelna;
        this.potrebaProOdjezd = potrebaProOdjezd;
    }

    /**
     *  Vrátí název předmětu.
     */
    public String getNazev() {
        return nazev;
    }

    /**
     *  Vrátí true pokud je věc sebratelná.
     */
    public boolean isSebratelna() {
        return sebratelna;
    }

    @Override
    public String toString() {
        return nazev;
    }

    /**
     *  Vrátí popis věci.
     */
    public String getPopisPredmetu() {
        return popisPredmetu;
    }

    /**
     *  Vrátí true pokud je věc odevzdatelná.
     */
    public boolean isOdevzdatelna() {
        return odevzdatelna;
    }

    /**
     *  Vrátí true pokud je věc potřeba pro odjezd do závěrečné místnosti.
     */
    public boolean isPotrebaProOdjezd() {
        return potrebaProOdjezd;
    }

    /**
     *  Metoda porovnává instance věcí na základě jejich parametrů.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vec vec = (Vec) o;
        return sebratelna == vec.sebratelna && odevzdatelna == vec.odevzdatelna && potrebaProOdjezd == vec.potrebaProOdjezd && nazev.equals(vec.nazev) && popisPredmetu.equals(vec.popisPredmetu);
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
        return Objects.hash(nazev, sebratelna, popisPredmetu, odevzdatelna, potrebaProOdjezd);
    }
}
