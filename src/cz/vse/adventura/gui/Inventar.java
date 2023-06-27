package cz.vse.adventura.gui;


import cz.vse.adventura.logika.Player;
import cz.vse.adventura.util.Observer;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import java.io.InputStream;
import java.util.Set;

public class Inventar implements Observer, Component {

    private VBox vbox = new VBox();
    private final Player player;
    private final FlowPane panelVeciFlowPane = new FlowPane();



    public Inventar(Player player){
        this.player = player;

        init();

        player.registerObserver(this);
    }

    private void init(){
        vbox.setPrefWidth(200.0);
        Label label = new Label("Věci v inventáři:");
        vbox.getChildren().addAll(label, panelVeciFlowPane);
    }

    @Override
    public Node getComponentNode() {
        return this.vbox;
    }

    @Override
    public void update() {
        nactiObrazkyVeci();
    }

    private void nactiObrazkyVeci() {
        panelVeciFlowPane.getChildren().clear();
        String nazevObrazku;
        Set<String> seznamVeci = player.getSeznamVeci();
        for (String nazevVeci : seznamVeci){
            nazevObrazku = "/zdroje/veci/" + nazevVeci + ".jpg";
            InputStream inputStream = Inventar.class.getResourceAsStream(nazevObrazku);
            Image image = new Image(inputStream,100.0, 100.0, false, true);
            ImageView imageView = new ImageView(image);
            panelVeciFlowPane.getChildren().add(imageView);
            }

    }
}
