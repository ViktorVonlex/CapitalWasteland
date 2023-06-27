package cz.vse.adventura.logika;

/**
 *  Třída PrikazOdevzdej implementuje pro hru příkaz odevzdej.
 *
 */

class PrikazOdevzdej implements IPrikaz{

    private static final String NAZEV = "odevzdej";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazOdevzdej(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "odevzdej".
     *  Zkontroluje zda-li je jako první parametr zadáno jméno věci, kterou má hráč v inventáři a že ji lze odevzdat.
     *  Dále kontroluje zda-li je jako druhý parametr zadáno jméno npc, které se nachází v současné místnosti a zda-li se hráč
     *  pokouší odevzdat věc, kterou dané npc chce.
     *  Pokud jsou parametry v pořádku, metoda odebere hráči věc, kterou odevzdává a přidá věc, kterou má u sebe npc.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevím, jaký předmět mám odevzdat.";
        }

        if (parametry.length == 1) {
            return "Nevím, komu mám předmět odevzdat";
        }

        String predmet = parametry[0]; //první parametr příkazu je jméno věci kterou chceme odevzdat

        Player hrac = herniPlan.getPlayer();
        Vec vec = hrac.vratInvtVec(predmet);

        String jmenoNpc = parametry[1]; //druhý parametr je jméno npc kterému chceme věc odevzdat


        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Npc npc = aktualniProstor.vratNpc(jmenoNpc);


        try {

            if (vec == null) {
                return "Nevím, jaký předmět mám odevzdat.";
            }
            if (npc == null) {
                return "Nevím, komu mám předmět odevzdat.";
            }

            if (!vec.isOdevzdatelna()) {
                return "Tuhle věc nemůžeš odevzdat";
            }

            if (vec.equals(npc.getVecCoChce())) {
                hrac.removeInvtVec(predmet);
                Vec nova = npc.getVecCoMa();
                hrac.addInvtVec(nova);

                npc.addBatohVec(vec);
                npc.setVecCoMa(null);


                return "Odevzdal jsi " + vec.toString() + " postavě " + jmenoNpc + " a dostal jsi " + nova.toString() + ".";
            }
            }
        catch(NullPointerException e)
            {
                System.out.print("Takový předmět se zde nenachází, ani ho nemám v inventáři.");
            }

        return "Tuhle věc tahle postava nechce...";
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
