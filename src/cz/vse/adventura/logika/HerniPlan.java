package cz.vse.adventura.logika;


import cz.vse.adventura.util.Observer;
import cz.vse.adventura.util.SubjectOfChange;

import java.util.HashSet;
import java.util.Set;

/**
 *  Class HerniPlan - třída představující mapu a stav adventury.
 * 
 *  Tato třída inicializuje prvky ze kterých se hra skládá:
 *  vytváří všechny prostory,
 *  propojuje je vzájemně pomocí východů 
 *  a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */
public class HerniPlan implements SubjectOfChange {
    
    private Prostor aktualniProstor;
    public Player hrdina;

    private Set<Observer> seznamPozorovatelu = new HashSet<>();
    
     /**
     *  Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();

    }
    /**
     *  Vytváří jednotlivé prostory a propojuje je pomocí východů.
     *  Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {

        //moje prostory
        Prostor parkovaciDum = new Prostor("parkovací_dům", "Je tu jen pár rozpadlých aut a funkční motorka.", 467.0, 330.0);
        Prostor centralniUlice = new Prostor("centrální_ulice","Opuštěná hlavní třída, která vede na spoustu míst.", 470.0, 200.0);
        Prostor obchodniCentrum = new Prostor("obchodní_centrum","Snadno průchodné ruiny starého obchodního domu.", 471.0, 93.0);
        Prostor zbrane = new Prostor("gunRunners","Vyrabovaný obchod se zbraněmi.\nMožná tu něco zůstalo.", 305.0, 52.0);
        Prostor potraviny = new Prostor("samoobsluha", "Nic čerstvého už tu asi nenajdu.", 645.0, 31.0);
        Prostor zapadniUlice = new Prostor("západní_ulice","Je odtud vidět park a čerpací stanice.", 220.0, 200.0);
        Prostor benzinka = new Prostor("čerpací_stanice", "Budova je značně opevněná, zřejmě proti náhodným útokům.", 83.0, 310.0);
        Prostor park = new Prostor("park", "Kdysi tento park zářil krásou.\nNyní vyzařuje pouze radiaci.", 98.0, 55.0);
        Prostor vychodniUlice = new Prostor("východní_ulice","Na chodníku se válí mrtvola v louži krve.", 737.0, 180.0);
        Prostor stan = new Prostor("stan","Majitel tohoto obydlí zřejmě rád sbírá mince.", 875.0, 65.0);
        Prostor ukryt = new Prostor("úkryt", "Bezpečné útočistě plné vybavení. Tady jsi doma.\nDokázal jsi přežít strastiplnou cestu.\n Nyní můžeš hru ukončit příkazem 'konec'", 737.0, 330.0);


        //východy k mým prostorům
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

        //Nastavení začátečního prostoru
        aktualniProstor = parkovaciDum;
        //aktualniProstor = potraviny;


        //Vytváření mých věcí
        Vec benzin = new Vec ("benzín", true,"Plný kanistr benzínu.",false,true);
        Vec klicky = new Vec("klíčky", true,"Klíčky od motorky v parkovacím domě.",false,true);
        Vec glock = new Vec("glock",true, "Má sobe vyrytý název: MoneyMaker",false,false);
        Vec dzus = new Vec ("džus", true,"Výborný prošlý džuš...",true,false);
        Vec drobaky = new Vec ("drobáky",true, "Pár drobných mincí.",true,false);
        Vec pivko = new Vec ("pivíčko",true,"Vychlazená dvanáctka.\nJiné pivo neexistuje...",true,false);

        Vec mrtvola = new Vec("mrtvola", false, "Toto tělo má kudlu v zádech a leží před stanem.",false,false);
        Vec socha = new Vec("socha", false,"Zničená socha diktárora, který rozpoutal jaderný holokaust.",false,false);
        Vec billboard = new Vec ("billboard",false,"Billboard s nápisem: Aladin zvolen císařem už po šesté v řadě.\nPřes tento nápis je nasprejované graffiti: VRAH",false,false);
        Vec hromadaOdpadku = new Vec("hromada_odpadků",false,"Nekonečná hromada odpadků zřejmě slouží místnímu tulákovi jako bydlistě.",false,false);
        Vec motorka = new Vec("motorka",false,"Tvoje jediná naděje na návrat do úkrytu.",false, true);


        //Vytváření npcček
        Npc bezdomovec = new Npc("bezdomovec", "Heeeej ty musíš mi pomoct.\nOd té doby co mi došel benzín v mojí milovaný motorce jsem pořád v lihu a bojím se, že vystřízlivím...\nRychle mi sežeň nějakej alkáček.", "Eeeaeheha aaaa na světě je přece krásně lalalal!!",true, pivko,klicky,true);
        Npc franta = new Npc ("franta", "Vypadáš, že tam venku dokážeš přežít...\nJestli mi přineseš trochu džusu, tak se ti odměním.", "Díky za džus kamaráde, snad ti ten benzín hilfne.",true,dzus,benzin,false);
        Npc automat = new Npc("automat_na_pití","Tak co to bude šéfe? Máme tu poslední pivko.","Tak co to bude šéfe?",true,drobaky,pivko,false);
        Npc thorin = new Npc("thorin", "Já jsem král pod stanem a tohle je moje králoství.\nVšechno zlato je moje a nikdo nic nedostane!!\n(Mám pocit, že tohohle týpka budu muset sejmout...)",null, true, null,drobaky,true);


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

//        parkovaciDum.addVec(klicky);
//        parkovaciDum.addVec(benzin);
}
    
    /**
     *  Metoda vrací odkaz na aktuální prostor, ve kterém se hráč právě nachází.
     *
     *@return     aktuální prostor
     */
    
    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }
    
    /**
     *  Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     *@param  prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    /**
     *  Vrací instanci třídy Player.
     */
    public Player getPlayer(){
        return hrdina;
    }

    @Override
    public void registerObserver(Observer observer) {
        seznamPozorovatelu.add(observer);
    }

    @Override
    public void unregisterObserver(Observer observer) {
        seznamPozorovatelu.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : seznamPozorovatelu){
            o.update();
        }
    }
}
