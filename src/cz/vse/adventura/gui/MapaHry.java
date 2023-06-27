package cz.vse.adventura.gui;

import cz.vse.adventura.logika.HerniPlan;
import cz.vse.adventura.logika.Prostor;
import cz.vse.adventura.util.Observer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class MapaHry implements Component, Observer {
    private AnchorPane anchorPane = new AnchorPane();
    private Circle teckaNaPozici = new Circle(10.0, Paint.valueOf("darkred"));
    private final HerniPlan herniPlan;

    public MapaHry(HerniPlan herniPlan){
        this.herniPlan = herniPlan;

        init();
        herniPlan.registerObserver(this);
    }

    private void init(){
        ImageView imageView = new ImageView(new Image(MapaHry.class.getResourceAsStream("/zdroje/herniPlan.png"), 1000.0, 400.0, false, true));
        anchorPane.getChildren().add(imageView);
        anchorPane.getChildren().add(teckaNaPozici);
        vykresliTecku();
    }

    private void vykresliTecku(){
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        AnchorPane.setLeftAnchor(teckaNaPozici, aktualniProstor.getPosLeft());
        AnchorPane.setTopAnchor(teckaNaPozici, aktualniProstor.getPosTop());
    }

    @Override
    public Node getComponentNode() {
        return anchorPane;
    }

    @Override
    public void update() {
        vykresliTecku();
    }
}
