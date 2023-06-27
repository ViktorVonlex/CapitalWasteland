package cz.vse.adventura.main;

import cz.vse.adventura.gui.Inventar;
import cz.vse.adventura.gui.MapaHry;
import cz.vse.adventura.gui.PanelVychodu;
import cz.vse.adventura.logika.Hra;
import cz.vse.adventura.logika.IHra;
import cz.vse.adventura.uiText.TextoveRozhrani;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;

public class AdventuraZaklad extends Application {

    /***************************************************************************
     * Metoda, prostřednictvím níž se spouští celá aplikace.
     *
     * @param args Parametry příkazového řádku
     */
    public static void main(String[] args) {
        if (args.length > 0) {
            String param = args[0];
            if (param.equals("text")) {
                IHra hra = new Hra();
                TextoveRozhrani ui = new TextoveRozhrani(hra);
                ui.hraj();
            } else if (param.equals("graphic")) {
                launch(args);
            } else {
            throw new IllegalArgumentException("Argument musí být graphic nebo text");
        }
    } else {
        launch(args);
    }

    }


        @Override
        public void start (Stage primaryStage){
            IHra hra = new Hra();
            BorderPane borderPane = new BorderPane();

            VBox vBox =  new VBox();
            vBox.getChildren().add(pripravMenu(primaryStage));

            TextArea konzole = vytvorKonzoli(hra, borderPane);

            Label prikazovePoleLabel = new Label("Zadej příkaz");

            prikazovePoleLabel.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 16.00));

            TextField prikazovePole = vytvorPrikazovePole(konzole, hra);

            HBox spodniPanel = vytvorSpodniPanel(prikazovePoleLabel, prikazovePole);
            spodniPanel.setAlignment(Pos.CENTER);

            borderPane.setBottom(spodniPanel);

            vBox.getChildren().add(borderPane);
            Scene scene = new Scene(vBox, 1000.0, 800.0);
            primaryStage.setTitle("Adventura semestrálka");
            primaryStage.setScene(scene);
            prikazovePole.requestFocus();
            primaryStage.show();

            PanelVychodu panelVychodu = new PanelVychodu(hra.getHerniPlan());
            borderPane.setRight(panelVychodu.getListView());

            MapaHry mapaHry = new MapaHry(hra.getHerniPlan());
            borderPane.setTop(mapaHry.getComponentNode());

            Inventar inventar = new Inventar(hra.getHerniPlan().getPlayer());
            borderPane.setLeft(inventar.getComponentNode());

        }


    private MenuBar pripravMenu(Stage primaryStage){
        MenuBar menuBar = new MenuBar();
        Menu souborMenu = new Menu("Soubor");

        MenuItem newGame = new MenuItem("Nová hra");

        newGame.setOnAction(event -> {
            primaryStage.hide();
            this.start(new Stage());
        });

        newGame.setAccelerator(KeyCombination.keyCombination("CTRL+N"));

        MenuItem endGame = new MenuItem("Konec hry");

        endGame.setOnAction(event -> {
            primaryStage.close();
            System.exit(0);
        });

        souborMenu.getItems().add(newGame);
        souborMenu.getItems().add(new SeparatorMenuItem());
        souborMenu.getItems().add(endGame);

        Menu souborMenu1 = new Menu("Nápověda");

        MenuItem aboutApp = new MenuItem("O aplikaci");

        aboutApp.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Verze ZS 2021");
            alert.setTitle("Grafická adventura");
            alert.setHeaderText("Semestrální práce v JavaFX");
            alert.showAndWait();
        });

        souborMenu1.getItems().add(aboutApp);
        MenuItem napoveda = new MenuItem("Nápověda k aplikaci");
        souborMenu1.getItems().add(napoveda);

        napoveda.setOnAction(event -> {
            Stage stage1 = new Stage();
            URL url = this.getClass().getResource("/zdroje/napoveda.html");
            WebView webView = new WebView();
            webView.getEngine().load(url.toString());
            Scene scene = new Scene(webView, 600.0, 500.0);
            stage1.setScene(scene);
            stage1.show();
        });

        menuBar.getMenus().add(souborMenu);
        menuBar.getMenus().add(souborMenu1);

        return menuBar;
    }

    private TextArea vytvorKonzoli(IHra hra, BorderPane borderPane) {
        TextArea konzole = new TextArea();
        konzole.setText(hra.vratUvitani());
        borderPane.setCenter(konzole);
        konzole.setEditable(false);
        return konzole;
    }


    private TextField vytvorPrikazovePole(TextArea textArea, IHra hra) {
        TextField prikazovePole = new TextField();
        prikazovePole.setOnAction(event -> {
            String prikaz = prikazovePole.getText();
            String navratovaHodnota = hra.zpracujPrikaz(prikaz);
            prikazovePole.clear();
            if(hra.konecHry()){
                prikazovePole.setEditable(false);
            }
            textArea.appendText("\n" + navratovaHodnota + "\n");
        });
        return prikazovePole;
    }

    private HBox vytvorSpodniPanel(Label prikazovePoleLabel, TextField prikazovePole) {
        //vytvor spodni pane
        HBox spodniPanel = new HBox();
        spodniPanel.getChildren().add(prikazovePoleLabel);
        spodniPanel.getChildren().add(prikazovePole);
        return spodniPanel;
    }
}
