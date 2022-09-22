package cz.vse.adventura.logika;

/**
 *  Třída PrikazInventar implementuje pro hru příkaz inventář.
 *
 */

class PrikazPromluv implements IPrikaz{

    private static final String NAZEV = "promluv";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazPromluv(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "promluv".
     *  Metoda nejprve kontroluje zda-li se zadané npc nachází v současném prostoru.
     *  Dále zjišťuje jestli je dané npc naživu.
     *  Nakonec vrací textové řetězce konverzace podle toho jestli už npc dostalo věc co chce nebo ne.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím, s kým si promluvit.";
        }

        String jmenoPostavy = parametry[0];

        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Npc npc = aktualniProstor.vratNpc(jmenoPostavy);

        try  {
            if (!npc.isNazivu()){
                return "Mrtvola ti toho moc neřekne...";
            }

            if (npc.getVecCoMa() == null) {
                return npc.getKonverzacePo();
            }
            else {
                return npc.getKonverzacePred();
            }

        }
        catch(NullPointerException e)
        {
            System.out.print("Nikdo takový tady není.");
        }

        return "";
    }

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
