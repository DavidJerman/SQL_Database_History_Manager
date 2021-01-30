import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Grafični vmesnik aplikacije oz. front-end
 *
 * @author David Jerman
 * @version 2021.01.30
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
     * Spremeni lastnosti podanega Button (izgled)
     *
     * @param button Button, ki ga urejamo
     */
    static void setButtonProperties_GrayBold(Button button) {
        button.setStyle(Colors.LIGHTGRAY_BASE_COLOR);
        button.setFont(Font.font("Segoe", FontWeight.BOLD, 12));
    }

    /**
     * Spremeni lastnosti podanega Label (izgled)
     *
     * @param label Label, ki ga urejamo
     */
    static void setLabelProperties_InfoLabel(Label label) {
        label.setStyle(Colors.WHITE_BG_COLOR_LINEARGRADIENT +
                "-fx-padding: 4px;" +
                "-fx-background-radius: 3px;" +
                "-fx-border-radius: 3px;" +
                "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        label.setMinWidth(150);
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
        mainPane.setStyle(Colors.BLACK_BG_COLOR);

        // Zgornji GridPane
        GridPane upperPane = new GridPane();
        upperPane.setPadding(new Insets(10, 10, 10, 5));
        upperPane.setStyle(Colors.GRAY_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        //# GridPane povezava
        GridPane connectPane = new GridPane();
        connectPane.setVgap(11);
        connectPane.setHgap(11);
        connectPane.setPadding(new Insets(10));
        Button connectButton = new Button("Poveži se");
        setButtonProperties_GrayBold(connectButton);
        connectButton.setMinWidth(130);
        Button disconnectButton = new Button("Prekini povezavo");
        disconnectButton.setMinWidth(130);
        setButtonProperties_GrayBold(disconnectButton);
        Button customConnectButton = new Button("Povezava po meri");
        setButtonProperties_GrayBold(customConnectButton);
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
        connectInfoPane.setStyle(Colors.BLACK_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        //## Naslov
        Label connectionInfoTitleLabel = new Label("Informacije o strežniku:");
        connectionInfoTitleLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        connectionInfoTitleLabel.setPadding(new Insets(0, 0, 10, 0));
        //## Informacije o tabeli
        connectInfoPane.setVgap(10);
        connectInfoPane.setHgap(10);
        connectInfoPane.setPadding(new Insets(10));
        Label usernameLabel = new Label("Uporabnik:");
        usernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Label usernameValueLabel = new Label("N/A");
        setLabelProperties_InfoLabel(usernameValueLabel);
        Label serverIPLabel = new Label("IP strežnika:");
        serverIPLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Label serverIPValueLabel = new Label("N/A");
        setLabelProperties_InfoLabel(serverIPValueLabel);
        Label serverPortLabel = new Label("Vrata strežnika:");
        serverPortLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Label serverPortValueLabel = new Label("N/A");
        setLabelProperties_InfoLabel(serverPortValueLabel);
        Label databaseLabel = new Label("Podatk. baza:");
        databaseLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Label databaseValueLabel = new Label("N/A");
        setLabelProperties_InfoLabel(databaseValueLabel);
        //# GridPane konfiguracija
        GridPane configPane = new GridPane();
        configPane.setVgap(11);
        configPane.setHgap(11);
        configPane.setPadding(new Insets(10));
        Button loadConfigButton = new Button("Naloži konfiguracijo");
        loadConfigButton.setMinWidth(130);
        setButtonProperties_GrayBold(loadConfigButton);
        Button saveConfigButton = new Button("Shrani konfiguracijo");
        saveConfigButton.setMinWidth(130);
        setButtonProperties_GrayBold(saveConfigButton);
        Button helpButton = new Button("Pomoč");
        helpButton.setMinWidth(130);
        setButtonProperties_GrayBold(helpButton);
        Button clearButton = new Button("Počisti izbiro");
        clearButton.setMinWidth(130);
        setButtonProperties_GrayBold(clearButton);
        //# GridPane Tabela Info
        GridPane infoPane = new GridPane();
        infoPane.setStyle(Colors.BLACK_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        infoPane.setPadding(new Insets(0, 10, 0, 0));
        //## Naslov
        Label infoTitleLabel = new Label("Informacije o izbrani tabeli:");
        infoTitleLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        infoTitleLabel.setPadding(new Insets(10));
        //## Informacije o tabeli
        // Avtor Tabele
        Label authorLabel = new Label("Avtor:");
        authorLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        authorLabel.setPadding(new Insets(10));
        Label authorValueLabel = new Label("N/A");
        setLabelProperties_InfoLabel(authorValueLabel);
        // Datum nastanka
        Label creationDateLabel = new Label("Datum nastanka:");
        creationDateLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        creationDateLabel.setPadding(new Insets(10));
        Label creationDateValueLabel = new Label("N/A");
        setLabelProperties_InfoLabel(creationDateValueLabel);
        // Datum zaprtja
        Label deletionDateLabel = new Label("Datum izbrisa:");
        deletionDateLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        deletionDateLabel.setPadding(new Insets(10));
        Label deletionDateValueLabel = new Label("N/A");
        setLabelProperties_InfoLabel(deletionDateValueLabel);
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
        lowerPane.setStyle(Colors.GRAY_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        //## Maksimalna višina GridPane
        int MAXGRIDHEIGHT = 300;
        int MINGRIDWIDTH = 395;
        //# Vstavljanje GridPane
        GridPane insertionPane = new GridPane();
        insertionPane.setPadding(new Insets(10));
        Label insertionLabel = new Label("Vstavljanja:");
        Label insertionUsernameLabel = new Label("N/A");
        insertionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        insertionUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        insertionLabel.setPadding(new Insets(0, 0, 10, 0));
        insertionUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> insertionTableView = new TableView<>();
        insertionTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        insertionTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        insertionTableView.setMinWidth(MINGRIDWIDTH);
        insertionTableView.setMaxHeight(MAXGRIDHEIGHT);
        //# Ogledovanje GridPane
        GridPane viewPane = new GridPane();
        viewPane.setPadding(new Insets(10));
        Label viewLabel = new Label("Ogledovanja:");
        Label viewUsernameLabel = new Label("N/A");
        viewLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        viewUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        viewLabel.setPadding(new Insets(0, 0, 10, 0));
        viewUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> viewTableView = new TableView<>();
        viewTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        viewTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        viewTableView.setMinWidth(MINGRIDWIDTH);
        viewTableView.setMaxHeight(MAXGRIDHEIGHT);
        //# Ažuriranje GridPane
        GridPane updatePane = new GridPane();
        updatePane.setPadding(new Insets(10));
        Label updateLabel = new Label("Ažuriranja:");
        Label updateUsernameLabel = new Label("N/A");
        updateLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        updateUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        updateLabel.setPadding(new Insets(0, 0, 10, 0));
        updateUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> updateTableView = new TableView<>();
        updateTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        updateTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        updateTableView.setMinWidth(MINGRIDWIDTH);
        updateTableView.setMaxHeight(MAXGRIDHEIGHT);
        //# Brisanje GridPane
        GridPane deletionPane = new GridPane();
        deletionPane.setPadding(new Insets(10));
        Label deletionLabel = new Label("Brisanja:");
        Label deletionUsernameLabel = new Label("N/A");
        deletionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        deletionUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        deletionLabel.setPadding(new Insets(0, 0, 10, 0));
        deletionUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        TableView<String[]> deletionTableView = new TableView<>();
        deletionTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        deletionTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        deletionTableView.setMinWidth(MINGRIDWIDTH);
        deletionTableView.setMaxHeight(MAXGRIDHEIGHT);

        // Desni starševski GridPane
        GridPane rightSuperPane = new GridPane();
        rightSuperPane.setStyle(Colors.GRAY_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");

        // Desni Grid Pane
        GridPane rightPane = new GridPane();
        rightPane.setPadding(new Insets(20, 20, 20, 20));
        //# Seznam vseh tabel
        Label tablesLabel = new Label("Vse tabele:");
        tablesLabel.setPadding(new Insets(0, 0, 10, 0));
        tablesLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        ListView<String> tablesListView = new ListView<>();
        tablesListView.setMaxHeight(125);
        tablesListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        //# Seznam vseh uporabnikov (IP)
        Label usersIPLabel = new Label("Vsi uporabniki (IP):");
        usersIPLabel.setPadding(new Insets(10, 0, 10, 0));
        usersIPLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        ListView<String> usersIPListView = new ListView<>();
        usersIPListView.setMaxHeight(300);
        usersIPListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        //# Seznam vseh uporabnikov (username)
        Label usersUsernameLabel = new Label("Vsi uporabniki (uporabniško ime):");
        usersUsernameLabel.setPadding(new Insets(10, 0, 10, 0));
        usersUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        ListView<String> usersUsernameListView = new ListView<>();
        usersUsernameListView.setMaxHeight(300);
        usersUsernameListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
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
        tablesSelectionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        ListView<String> tablesSelectionListView = new ListView<>();
        tablesSelectionListView.setMaxHeight(125);
        tablesSelectionListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        //# Seznam uporabnikov (IP)
        Label usersIPSelectionLabel = new Label("Uporabniki izbrane tabele (IP):");
        usersIPSelectionLabel.setPadding(new Insets(10, 0, 10, 0));
        usersIPSelectionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        ListView<String> usersIPSelectionListView = new ListView<>();
        usersIPSelectionListView.setMaxHeight(300);
        usersIPSelectionListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        //# Seznam uporabnikov (username)
        Label usersUsernameSelectionLabel = new Label("Uporabniki izbrane tabele (uporab. ime):");
        usersUsernameSelectionLabel.setPadding(new Insets(10, 0, 10, 0));
        usersUsernameSelectionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        ListView<String> usersUsernameSelectionListView = new ListView<>();
        usersUsernameSelectionListView.setMaxHeight(300);
        usersUsernameSelectionListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);

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

    /**
     * Razred vsebuje barve uporabljene v aplikaciji
     */
    static class Colors {
        /**
         * Črno ozadje
         */
        static String BLACK_BG_COLOR = "-fx-background-color: rgb(43,43,45);";
        /**
         * Sivo ozadje
         */
        static String GRAY_BG_COLOR = "-fx-background-color: rgb(60,63,65);";
        /**
         * Svetlo siv gumb
         */
        static String LIGHTGRAY_BASE_COLOR = "-fx-base: rgb(180, 180, 180);";
        /**
         * Belo besedilo
         */
        static String WHITE_TEXT_COLOR = "-fx-text-fill: rgb(250,250,250)";
        /**
         * Belo ozadje z linearnim gradientom
         */
        static String WHITE_BG_COLOR_LINEARGRADIENT = "-fx-background-color: rgb(255,255,255)," +
                " linear-gradient(to bottom,#e3e3e3 0%,#cccccc 100%);";
        /**
         * Sivo ozadje
         */
        static String LIGHTGRAY_BG_COLOR = "-fx-background-color: rgb(195, 195, 195)";
    }
}
