package cz.vse.adventura.logika;


/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 */
public class HerniPlan {

    private Prostor aktualniProstor;
    public Player hrdina;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {

        Prostor parkovaciDum = new Prostor("parkovaci_dum", "Je tu jen par rozpadlych aut a funkcni motorka.");
        Prostor ukryt = new Prostor("ukryt", "Bezpecne utociste plne vybaveni. Tady jsi doma.\nDokazal jsi prezit strastiplnou cestu. Nyni muzes hru ukoncit prikazem 'konec'");
        Prostor centralniUlice = new Prostor("centralni_ulice", "Opustena hlavni trida, ktera vede na spoustu mist.");
        Prostor zapadniUlice = new Prostor("zapadni_ulice", "Je odtud videt park a cerpací stanice.");
        Prostor vychodniUlice = new Prostor("vychodni_ulice", "Na chodniku se vali mrtvola v louzi krve.");
        Prostor stan = new Prostor("stan", "Majitel tohoto obydli zrejme rad sbira mince.");
        Prostor obchodniCentrum = new Prostor("obchodni_centrum", "Snadno pruchodne ruiny stareho obchodniho domu.");
        Prostor zbrane = new Prostor("gunRunners", "Vyrabovany obchod se zbranemi.\nMozna tu neco zustalo.");
        Prostor potraviny = new Prostor("samoobsluha", "Nic cerstveho uz tu asi nenajdu.");
        Prostor benzinka = new Prostor("cerpaci_stanice", "Budova je znacne opevnena, zrejme proti nahodnym utokum.");
        Prostor park = new Prostor("park", "Kdysi tento park zaril krasou.\nNyni vyzařuje pouze radiaci.");

        //vychody k mym prostorum
        parkovaciDum.setVychod(ukryt);
        parkovaciDum.setVychod(centralniUlice);
        centralniUlice.setVychod(parkovaciDum);
        centralniUlice.setVychod(zapadniUlice);
        centralniUlice.setVychod(vychodniUlice);
        centralniUlice.setVychod(obchodniCentrum);
        vychodniUlice.setVychod(centralniUlice);
        vychodniUlice.setVychod(stan);
        stan.setVychod(vychodniUlice);
        obchodniCentrum.setVychod(centralniUlice);
        obchodniCentrum.setVychod(zbrane);
        obchodniCentrum.setVychod(potraviny);
        zbrane.setVychod(obchodniCentrum);
        potraviny.setVychod(obchodniCentrum);
        zapadniUlice.setVychod(centralniUlice);
        zapadniUlice.setVychod(benzinka);
        zapadniUlice.setVychod(park);
        benzinka.setVychod(zapadniUlice);
        park.setVychod(zapadniUlice);

        Player hrac = new Player("hrac");
        hrdina = hrac;

        //Nastaveni zacatecniho prostoru
        aktualniProstor = parkovaciDum;

        //Vytvareni mych veci
        Vec benzin = new Vec("benzin", true, "Plny kanistr benzinu.", false, true);
        Vec motorka = new Vec("motorka", false, "Tvoje jedina nadeje na navrat do ukrytu.", false, true);
        Vec klicky = new Vec("klicky", true, "Klicky od motorky v parkovacim dome.", false, true);
        Vec glock = new Vec("glock", true, "Ma sobe vyryty nazev: MoneyMaker", false, false);
        Vec dzus = new Vec("dzus", true, "Vyborny proshly dzus...", true, false);
        Vec drobaky = new Vec("drobaky", true, "Par drobnych minci.", true, false);
        Vec pivko = new Vec("pivicko", true, "Vychlazena dvanactka.\nJine pivo neexistuje...", true, false);
        Vec mrtvola = new Vec("mrtvola", false, "Toto telo ma kudlu v zadech a lezi pred stanem.", false, false);
        Vec socha = new Vec("socha", false, "Znicena socha diktatora, ktery rozpoutal jaderny holokaust.", false, false);
        Vec billboard = new Vec("billboard", false, "Billboard s napisem: Aladin zvolen cisarem uz po seste v rade.\nPres tento napis je nasprejovane graffiti: VRAH", false, false);
        Vec hromadaOdpadku = new Vec("hromada_odpadku", false, "Nekonecna hromada odpadku zrejme slouzi mistnimu tulakovi jako bydliste.", false, false);

        //Vytvareni npccek
        Npc bezdomovec = new Npc("bezdomovec", "Heeeej ty musis mi pomoct.\nOd te doby co mi dosel benzin v moji milovane motorce jsem porad v lihu a bojim se, ze vystrizlivim...\nRychle mi sezej nejakej alkacek.", "Eeeaeheha aaaa na svete je prece krasne lalalal!!", true, pivko, klicky, true);
        Npc franta = new Npc("franta", "Vypadas, ze tam venku dokazes prezit...\nJestli mi prineses trochu dzusu, tak se ti odmenim.", "Diky za dzus kamaraade, snad ti ten benzin hilfne.", true, dzus, benzin, false);
        Npc automat = new Npc("automat_na_piti", "Tak co to bude sefe? Mame tu posledni pivko.", "Tak co to bude sefe?", true, drobaky, pivko, false);
        Npc thorin = new Npc("thorin", "Jsem kral pod stanem a tohle je moje kralovstvi.\nVsechno zlato je moje a nikdo nic nedostane!!\n(Mam pocit, ze tohohle typka budu muset sejmout...)", null, true, null, drobaky, true);


        //Vkládání npc do prostorů
        park.addNpc(bezdomovec);
        benzinka.addNpc(franta);
        vychodniUlice.addVec(mrtvola);
        potraviny.addNpc(automat);
        stan.addNpc(thorin);


        //Předmety pro npc
        bezdomovec.addBatohVec(klicky);
        franta.addBatohVec(benzin);
        automat.addBatohVec(pivko);
        thorin.addBatohVec(drobaky);


        //Vkládání mých věcí do prostorů
        parkovaciDum.addVec(motorka);
        zbrane.addVec(glock);
        potraviny.addVec(dzus);
        obchodniCentrum.addVec(socha);
        zapadniUlice.addVec(billboard);
        park.addVec(hromadaOdpadku);
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
    }

    /**
     * Vrací instanci třídy Player.
     */
    public Player getPlayer() {
        return hrdina;
    }

}
