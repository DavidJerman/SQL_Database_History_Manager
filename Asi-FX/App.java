import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Grafični vmesnik aplikacije oz. front-end
 *
 * @author David Jerman
 * @version 2021.01.28
 * @since 2021.01.25
 */
public class App extends Application {

    /**
     * Glavna metoda
     *
     * @param args Metoda ne obravnava argumentov
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Štartna funkcija aplikacije
     *
     * @param primaryStage Primary Stage
     */
    @Override
    public void start(Stage primaryStage) {
        // Lastnosti Primary Stage
        primaryStage.setTitle("SQL Upravljalnik");

        // Glavni GridPane
        GridPane mainPane = new GridPane();
        mainPane.setVgap(5);
        mainPane.setHgap(5);
        mainPane.setPadding(new Insets(20));
        mainPane.setStyle("-fx-background-color: rgb(43,43,45);");

        // Zgornji GridPane
        GridPane upperPane = new GridPane();
        upperPane.setPadding(new Insets(10, 10, 10, 5));
        upperPane.setStyle("-fx-background-color: rgb(60,63,65);" +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        //# GridPane povezava
        GridPane connectPane = new GridPane();
        connectPane.setVgap(11);
        connectPane.setHgap(11);
        connectPane.setPadding(new Insets(10));
        Button connectButton = new Button("Poveži se");
        connectButton.setMinWidth(130);
        Button disconnectButton = new Button("Prekini povezavo");
        disconnectButton.setMinWidth(130);
        Button customConnectButton = new Button("Povezava po meri");
        customConnectButton.setMinWidth(130);
        //# Handler-ji za gumbe
        connectButton.setOnAction((event) -> {
            // Pošlje backend-u podatke o povezavi in zaprosi za povezavo z sql strežnikom
        });
        disconnectButton.setOnAction((event) -> {
            // Prekine povezavo z sql strežnikom
        });
        customConnectButton.setOnAction((event) -> {
            // Odpre JOption pane za vnos podatkov za povezavo po meri
        });
        //# GridPane povezava info
        GridPane connectInfoPane = new GridPane();
        connectInfoPane.setStyle("-fx-background-color: rgb(43,43,44);" +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        //## Naslov
        Label connectionInfoTitleLabel = new Label("Informacije o strežniku:");
        connectionInfoTitleLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        connectionInfoTitleLabel.setPadding(new Insets(0, 0, 10, 0));
        //## Informacije o tabeli
        connectInfoPane.setVgap(10);
        connectInfoPane.setHgap(10);
        connectInfoPane.setPadding(new Insets(10));
        Label usernameLabel = new Label("Uporabnik:");
        usernameLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        Label usernameValueLabel = new Label("N/A");
        usernameValueLabel.setStyle("-fx-background-color: rgb(255,255,255)," +
                                    "linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);" +
                                    "-fx-padding: 4px;" +
                                    "-fx-background-radius: 3px;" +
                                    "-fx-border-radius: 3px;" +
                                    "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        usernameValueLabel.setMinWidth(150);
        Label serverIPLabel = new Label("IP strežnika:");
        serverIPLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        Label serverIPValueLabel = new Label("N/A");
        serverIPValueLabel.setStyle("-fx-background-color: rgb(255,255,255)," +
                                    "linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);" +
                                    "-fx-padding: 4px;" +
                                    "-fx-background-radius: 3px;" +
                                    "-fx-border-radius: 3px;" +
                                    "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        serverIPValueLabel.setMinWidth(150);
        Label serverPortLabel = new Label("Vrata strežnika:");
        serverPortLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        Label serverPortValueLabel = new Label("N/A");
        serverPortValueLabel.setStyle("-fx-background-color: rgb(255,255,255)," +
                "linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);" +
                "-fx-padding: 4px;" +
                "-fx-background-radius: 3px;" +
                "-fx-border-radius: 3px;" +
                "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        serverPortValueLabel.setMinWidth(150);
        Label databaseLabel = new Label("Podatk. baza:");
        databaseLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        Label databaseValueLabel = new Label("N/A");
        databaseValueLabel.setStyle("-fx-background-color: rgb(255,255,255)," +
                "linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);" +
                "-fx-padding: 4px;" +
                "-fx-background-radius: 3px;" +
                "-fx-border-radius: 3px;" +
                "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        databaseValueLabel.setMinWidth(150);
        //# GridPane konfiguracija
        GridPane configPane = new GridPane();
        configPane.setVgap(11);
        configPane.setHgap(11);
        configPane.setPadding(new Insets(10));
        Button loadConfigButton = new Button("Naloži konfiguracijo");
        loadConfigButton.setMinWidth(130);
        Button saveConfigButton = new Button("Shrani konfiguracijo");
        saveConfigButton.setMinWidth(130);
        Button helpButton = new Button("Pomoč");
        helpButton.setMinWidth(130);
        Button clearButton = new Button("Počisti izbiro");
        clearButton.setMinWidth(130);
        //# GridPane Tabela Info
        GridPane infoPane = new GridPane();
        infoPane.setStyle("-fx-background-color: rgb(43,43,44);" +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        infoPane.setPadding(new Insets(0, 10, 0, 0));
        //## Naslov
        Label infoTitleLabel = new Label("Informacije o izbrani tabeli:");
        infoTitleLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        infoTitleLabel.setPadding(new Insets(10));
        //## Informacije o tabeli
        // Avtor Tabele
        Label authorLabel = new Label("Avtor:");
        authorLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        authorLabel.setPadding(new Insets(10));
        Label authorValueLabel = new Label("N/A");
        authorValueLabel.setStyle("-fx-background-color: rgb(255,255,255)," +
                                  "linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);" +
                                  "-fx-padding: 4px;" +
                                  "-fx-background-radius: 3px;" +
                                  "-fx-border-radius: 3px;" +
                                  "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        authorValueLabel.setMinWidth(150);
        // Datum nastanka
        Label creationDateLabel = new Label("Datum nastanka:");
        creationDateLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        creationDateLabel.setPadding(new Insets(10));
        Label creationDateValueLabel = new Label("N/A");
        creationDateValueLabel.setStyle("-fx-background-color: rgb(255,255,255)," +
                                        "linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);" +
                                        "-fx-padding: 4px;" +
                                        "-fx-background-radius: 3px;" +
                                        "-fx-border-radius: 3px;" +
                                        "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        creationDateValueLabel.setMinWidth(150);
        // Datum zaprtja
        Label deletionDateLabel = new Label("Datum izbrisa:");
        deletionDateLabel.setStyle("-fx-text-fill: rgb(250,250,250)");
        deletionDateLabel.setPadding(new Insets(10));
        Label deletionDateValueLabel = new Label("N/A");
        deletionDateValueLabel.setStyle("-fx-background-color: rgb(255,255,255)," +
                                        "linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);" +
                                        "-fx-padding: 4px;" +
                                        "-fx-background-radius: 3px;" +
                                        "-fx-border-radius: 3px;" +
                                        "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        deletionDateValueLabel.setMinWidth(150);
        //# Handler-ji za gumbe
        loadConfigButton.setOnAction((event) -> {
            // Naloži podatke o povezavi iz datoteke
        });
        saveConfigButton.setOnAction((event) -> {
            // Shrani podatke o povezavi v datoteko
        });
        helpButton.setOnAction((event) -> {
            // Prikaže pomoč uporabniku
        });
        clearButton.setOnAction((event) -> {
            // Počisti izbiro
        });

        // Spodnji GridPane
        GridPane lowerPane = new GridPane();
        lowerPane.setPadding(new Insets(10));
        lowerPane.setStyle("-fx-background-color: rgb(60,63,65);" +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        //# Vstavljanje GridPane
        GridPane insertionPane = new GridPane();
        insertionPane.setPadding(new Insets(10));
        Label insertionLabel = new Label("Vstavljanja:");
        Label insertionUsernameLabel = new Label("N/A");
        insertionLabel.setStyle("-fx-text-fill: rgb(250, 250, 250);");
        insertionUsernameLabel.setStyle("-fx-text-fill: rgb(250, 250, 250)");
        insertionLabel.setPadding(new Insets(0, 0, 10, 0));
        insertionUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> insertionTableView = new TableView<>();
        insertionTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        insertionTableView.setMinWidth(395);
        insertionTableView.setMaxHeight(300);
        //# Ogledovanje GridPane
        GridPane viewPane = new GridPane();
        viewPane.setPadding(new Insets(10));
        Label viewLabel = new Label("Ogledovanja:");
        Label viewUsernameLabel = new Label("N/A");
        viewLabel.setStyle("-fx-text-fill: rgb(250, 250, 250);");
        viewUsernameLabel.setStyle("-fx-text-fill: rgb(250, 250, 250);");
        viewLabel.setPadding(new Insets(0, 0, 10, 0));
        viewUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> viewTableView = new TableView<>();
        viewTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        viewTableView.setMinWidth(395);
        viewTableView.setMaxHeight(300);
        //# Ažuriranje GridPane
        GridPane updatePane = new GridPane();
        updatePane.setPadding(new Insets(10));
        Label updateLabel = new Label("Ažuriranja:");
        Label updateUsernameLabel = new Label("N/A");
        updateLabel.setStyle("-fx-text-fill: rgb(250, 250, 250);");
        updateUsernameLabel.setStyle("-fx-text-fill: rgb(250, 250, 250);");
        updateLabel.setPadding(new Insets(0, 0, 10, 0));
        updateUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> updateTableView = new TableView<>();
        updateTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        updateTableView.setMinWidth(395);
        updateTableView.setMaxHeight(300);
        //# Brisanje GridPane
        GridPane deletionPane = new GridPane();
        deletionPane.setPadding(new Insets(10));
        Label deletionLabel = new Label("Brisanja:");
        Label deletionUsernameLabel = new Label("N/A");
        deletionLabel.setStyle("-fx-text-fill: rgb(250, 250, 250);");
        deletionUsernameLabel.setStyle("-fx-text-fill: rgb(250, 250, 250);");
        deletionLabel.setPadding(new Insets(0, 0, 10, 0));
        deletionUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> deletionTableView = new TableView<>();
        deletionTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        deletionTableView.setMinWidth(395);
        deletionTableView.setMaxHeight(300);

        // Desni starševski GridPane
        GridPane rightSuperPane = new GridPane();
        rightSuperPane.setStyle("-fx-background-color: rgb(60,63,65);" +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");

        // Desni Grid Pane
        GridPane rightPane = new GridPane();
        rightPane.setPadding(new Insets(20, 20, 20, 20));
        //# Seznam vseh tabel
        Label tablesLabel = new Label("Vse tabele:");
        tablesLabel.setPadding(new Insets(0, 0, 10, 0));
        tablesLabel.setStyle("-fx-text-fill: rgb(250, 250, 250)");
        ListView<String> tablesListView = new ListView<>();
        tablesListView.setMaxHeight(125);
        //# Seznam vseh uporabnikov (IP)
        Label usersIPLabel = new Label("Vsi uporabniki (IP):");
        usersIPLabel.setPadding(new Insets(10, 0, 10, 0));
        usersIPLabel.setStyle("-fx-text-fill: rgb(250, 250, 250)");
        ListView<String> usersIPListView = new ListView<>();
        usersIPListView.setMaxHeight(300);
        //# Seznam vseh uporabnikov (username)
        Label usersUsernameLabel = new Label("Vsi uporabniki (uporabniško ime):");
        usersUsernameLabel.setPadding(new Insets(10, 0, 10, 0));
        usersUsernameLabel.setStyle("-fx-text-fill: rgb(250, 250, 250)");
        ListView<String> usersUsernameListView = new ListView<>();
        usersUsernameListView.setMaxHeight(300);
        //# Handler-ji za ListView-je
        tablesListView.getSelectionModel().selectedItemProperty().addListener((event) -> {
            // Backend-u pošlje zahtevo za seznam uporabnikov tabele
        });
        usersIPListView.getSelectionModel().selectedItemProperty().addListener((event) -> {
            // Izpiše vse akcije izbranega uporabnika
        });
        usersUsernameListView.getSelectionModel().selectedItemProperty().addListener((event) -> {
            // Izpiše vse akcije izbranega uporabnika
        });

        // Desni izbirni GridPane
        GridPane rightSelectionPane = new GridPane();
        rightSelectionPane.setPadding(new Insets(20, 20, 20, 0));
        //# Seznam tabel
        Label tablesSelectionLabel = new Label("Tabele izbranega uporabnika:");
        tablesSelectionLabel.setPadding(new Insets(0, 0, 10, 0));
        tablesSelectionLabel.setStyle("-fx-text-fill: rgb(250, 250, 250)");
        ListView<String> tablesSelectionListView = new ListView<>();
        tablesSelectionListView.setMaxHeight(125);
        //# Seznam uporabnikov (IP)
        Label usersIPSelectionLabel = new Label("Uporabniki izbrane tabele (IP):");
        usersIPSelectionLabel.setPadding(new Insets(10, 0, 10, 0));
        usersIPSelectionLabel.setStyle("-fx-text-fill: rgb(250, 250, 250)");
        ListView<String> usersIPSelectionListView = new ListView<>();
        usersIPSelectionListView.setMaxHeight(300);
        //# Seznam uporabnikov (username)
        Label usersUsernameSelectionLabel = new Label("Uporabniki izbrane tabele (uporab. ime):");
        usersUsernameSelectionLabel.setPadding(new Insets(10, 0, 10, 0));
        usersUsernameSelectionLabel.setStyle("-fx-text-fill: rgb(250, 250, 250)");
        ListView<String> usersUsernameSelectionListView = new ListView<>();
        usersUsernameSelectionListView.setMaxHeight(300);

        // Dodajanje elementov v glavni GridPane
        mainPane.add(upperPane, 0, 0, 1, 1);
        mainPane.add(lowerPane, 0, 1, 1, 1);
        mainPane.add(rightSuperPane, 1, 0, 1, 2);

        // Dodajanje elementov v zgornji GridPane
        upperPane.add(connectPane, 0, 0, 1, 1);
        upperPane.add(connectInfoPane, 1, 0, 1, 1);
        upperPane.add(configPane, 2, 0, 1, 1);
        upperPane.add(infoPane, 3, 0, 1, 1);
        //# Dodajanje elementov v povezava GridPane
        connectPane.add(connectButton, 0, 0, 1, 1);
        connectPane.add(disconnectButton, 0, 1, 1, 1);
        connectPane.add(customConnectButton, 0, 2, 1, 1);
        //# Dodajanje elementov v povezava info GridPane
        connectInfoPane.add(connectionInfoTitleLabel, 0, 0, 2, 1);
        connectInfoPane.add(usernameLabel, 0, 1, 1, 1);
        connectInfoPane.add(usernameValueLabel, 1, 1, 1, 1);
        connectInfoPane.add(serverIPLabel, 0, 2, 1, 1);
        connectInfoPane.add(serverIPValueLabel, 1, 2, 1, 1);
        connectInfoPane.add(serverPortLabel, 0, 3, 1, 1);
        connectInfoPane.add(serverPortValueLabel, 1, 3, 1, 1);
        connectInfoPane.add(databaseLabel, 0, 4, 1, 1);
        connectInfoPane.add(databaseValueLabel, 1, 4, 1, 1);
        //# Dodajanje elementov v konfiguracija GridPane
        configPane.add(loadConfigButton, 0, 0, 1, 1);
        configPane.add(saveConfigButton, 0, 1, 1, 1);
        configPane.add(helpButton, 0, 2, 1, 1);
        configPane.add(clearButton, 0, 3, 1, 1);
        //# Dodajanje elementov v table info GridPane
        infoPane.add(infoTitleLabel, 0, 0, 2, 1);
        infoPane.add(authorLabel, 0, 1, 1, 1);
        infoPane.add(authorValueLabel, 1, 1, 1, 1);
        infoPane.add(creationDateLabel, 0, 2, 1, 1);
        infoPane.add(creationDateValueLabel, 1, 2, 1, 1);
        infoPane.add(deletionDateLabel, 0, 3, 1, 1);
        infoPane.add(deletionDateValueLabel, 1, 3, 1, 1);

        // Dodajanje elementov v spodnji GridPane
        lowerPane.add(insertionPane, 0, 0, 1, 1);
        lowerPane.add(updatePane, 1, 0, 1, 1);
        lowerPane.add(viewPane, 0, 1, 1, 1);
        lowerPane.add(deletionPane, 1, 1, 1, 1);
        //# Dodajanje elementov v vstavljanje GridPane
        insertionPane.add(insertionLabel, 0, 0, 1, 1);
        insertionPane.add(insertionUsernameLabel, 1, 0, 1, 1);
        insertionPane.add(insertionTableView, 0, 1, 2, 1);
        //# Dodajanje elementov v ažuriranje GridPane
        updatePane.add(updateLabel, 0, 0, 1, 1);
        updatePane.add(updateUsernameLabel, 1, 0, 1, 1);
        updatePane.add(updateTableView, 0, 1, 2, 1);
        //# Dodajanje elementov v ogledovanje GridPane
        viewPane.add(viewLabel, 0, 0, 1, 1);
        viewPane.add(viewUsernameLabel, 1, 0, 1, 1);
        viewPane.add(viewTableView, 0, 1, 2, 1);
        //# Dodajanje elementov v brisanje GridPane
        deletionPane.add(deletionLabel, 0, 0, 1, 1);
        deletionPane.add(deletionUsernameLabel, 1, 0, 1, 1);
        deletionPane.add(deletionTableView, 0, 1, 2, 1);

        // Dodajanje elementov v desni starševski GridPane
        rightSuperPane.add(rightPane, 0, 0, 1, 1);
        rightSuperPane.add(rightSelectionPane, 1, 0, 1, 1);

        // Dodajanje elementov v desni GridPane
        rightPane.add(tablesLabel, 0, 0, 1, 1);
        rightPane.add(tablesListView, 0, 1, 1, 1);
        rightPane.add(usersIPLabel, 0, 2, 1, 1);
        rightPane.add(usersIPListView, 0, 3, 1, 1);
        rightPane.add(usersUsernameLabel, 0, 4, 1, 1);
        rightPane.add(usersUsernameListView, 0, 5, 1, 1);

        // Dodajanje elementov v desni izbirni GridPane
        rightSelectionPane.add(tablesSelectionLabel, 0, 0, 1, 1);
        rightSelectionPane.add(tablesSelectionListView, 0, 1, 1, 1);
        rightSelectionPane.add(usersIPSelectionLabel, 0, 2, 1, 1);
        rightSelectionPane.add(usersIPSelectionListView, 0, 3, 1, 1);
        rightSelectionPane.add(usersUsernameSelectionLabel, 0, 4, 1, 1);
        rightSelectionPane.add(usersUsernameSelectionListView, 0, 5, 1, 1);

        // Glavna scena
        Scene mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setMaxHeight(lowerPane.getHeight() + upperPane.getHeight());
        primaryStage.setMinHeight(lowerPane.getHeight() + upperPane.getHeight());
        primaryStage.setMaxWidth(lowerPane.getWidth() + rightSuperPane.getWidth());
        primaryStage.setMinWidth(lowerPane.getWidth() + rightSuperPane.getWidth());
    }
}
