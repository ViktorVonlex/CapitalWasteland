package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.util.Observer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ListView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.*;

public class PanelVychodu implements Observer {

    HerniPlan herniPlan;

    ObservableList<String> seznamVychodu = FXCollections.observableArrayList();
    ListView<String> listView = new ListView<>();
    HashMap<String, Prostor> prostorHashMap = new HashMap<>();

    public PanelVychodu(HerniPlan herniPlan){
        this.herniPlan = herniPlan;

        init();

        herniPlan.registerObserver(this);
    }

    public void init() {
        nactiSeznamVychodu();

        listView.setItems(seznamVychodu);
        listView.setPrefWidth(150.0);
        listView.setOnMouseClicked(me -> {
            try {
                String nazevProstoru = listView.getSelectionModel().getSelectedItem();
                Prostor prostor = prostorHashMap.get(nazevProstoru);
                if (!prostor.getNazev().equals("úkryt")) {
                    herniPlan.setAktualniProstor(prostor);
                }
                System.out.println("Odjeď pomocí konzole debílku!");
            } catch (NullPointerException e) {
                System.out.println("Neklikej do prádzna debílku!");
            }
        });
    }


    public Node getListView(){
        return listView;
    }

    @Override
    public void update() {
        nactiSeznamVychodu();
    }


    private void nactiSeznamVychodu(){
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        this.seznamVychodu.clear();
        this.prostorHashMap.clear();
        for (Prostor prostor : aktualniProstor.getVychody()){
            seznamVychodu.add(prostor.getNazev());
            prostorHashMap.put(prostor.getNazev(), prostor);
        }
    }
}
