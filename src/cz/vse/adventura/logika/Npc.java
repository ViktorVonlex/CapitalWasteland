package cz.vse.adventura.logika;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *  Třída Npc
 *
 *  Tato třída obsajuje konstruktor, pomocí kterého jsou vytvářeny npc v herním plánu.
 *  Také se zde vytváří hashMap s věcmi, které npc mají nebo chtějí.
 *
 */

public class Npc {

    private String name;
    private String konverzacePred;
    private String konverzacePo;
    private boolean nazivu;
    private Vec vecCoChce;
    private Vec vecCoMa;
    private boolean zabitelna;


    public Map<String, Vec> batoh;

    /**
     *  Konstruktor třídy
     *
     * @param name text představující jméno npc
     * @param konverzacePred textový řetězec, který se zobrazí pokud postava nedostala věc co chce, nebo žádnou nechce
     * @param konverzacePo textový řetězec, který se zobrazí pokud postava dostala věc co chce
     * @param nazivu určuje zda je npc naživu a tedy zda s ním lze mluvit a odevzdat věci
     * @param vecCoChce obsahuje instanci věci co npc chce
     * @param vecCoMa obsahuje instanci věci co npc má
     * @param zabitelna určuje zda lze postavu zabít příkazem zaútoč
     */
    public Npc(String name, String konverzacePred, String konverzacePo, boolean nazivu, Vec vecCoChce, Vec vecCoMa,boolean zabitelna) {
        this.name = name;
        batoh = new HashMap<>();
        this.konverzacePred = konverzacePred;
        this.konverzacePo = konverzacePo;
        this.nazivu = nazivu;
        this.vecCoChce = vecCoChce;
        this.vecCoMa = vecCoMa;
        this.zabitelna = zabitelna;
    }

    /**
     *  Umístní věc do batohu npc.
     */
    public void addBatohVec(Vec vec){
        batoh.put(vec.getNazev(), vec);
    }

    /**
     *  Vrátí jméno npc.
     */
    public String getName() {
        return name;
    }

    /**
     *  Vrátí textový řetezec konverzace před odevzdáním věci co npc chce.
     */
    public String getKonverzacePred() {
        return konverzacePred;
    }

    /**
     *  Vrátí textový řetezec konverzace přo odevzdání věci co npc chce.
     */
    public String getKonverzacePo() {
        return konverzacePo;
    }

    /**
     *  Vrátí true pokud je postava naživu.
     */
    public boolean isNazivu() {
        return nazivu;
    }

    /**
     *  Nastaví hodnotu @param nazivu.
     */
    public void setNazivu(boolean nazivu) {
        this.nazivu = nazivu;
    }

    /**
     *  Vrátí textový řetezec "V inventáři máš " + výpis obsahu batohu.
     */
    @Override
    public String toString() {
        return "V inventáři máš " + batoh;
    }

    /**
     *  Vrátí instanci třídy Vec.
     */
    public Vec getVecCoChce() {
        return vecCoChce;
    }

    /**
     *  Vrátí instanci třídy Vec.
     */
    public Vec getVecCoMa() {
        return vecCoMa;
    }

    /**
     *  Nastaví instanci třídy Vec pro parametr vecCoMa.
     */
    public void setVecCoMa(Vec vecCoMa) {
        this.vecCoMa = vecCoMa;
    }

    /**
     *  Vrátí true pokud je postava zabitelná.
     */
    public boolean isZabitelna() {
        return zabitelna;
    }

    /**
     *  Porovnává instance třídy Npc podle jejich parametrů.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Npc npc = (Npc) o;
        return nazivu == npc.nazivu && zabitelna == npc.zabitelna && name.equals(npc.name) && konverzacePred.equals(npc.konverzacePred) && konverzacePo.equals(npc.konverzacePo);
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
        return Objects.hash(name, konverzacePred, konverzacePo, nazivu, zabitelna);
    }
}

