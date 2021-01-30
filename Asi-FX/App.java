import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
     * Spremeni temo aplikacije v temno
     */
    static void setToDarkTheme() {
        Resource.menuBar.setStyle(Colors.TRUEBLACK_BG_COLOR);
        Resource.title_img.setImage(new Image("images/database_logo_white.png"));
        Resource.menu.setStyle(Colors.TRUEBLACK_BG_COLOR);
        Resource.mainPane.setStyle(Colors.BLACK_BG_COLOR);
        Resource.upperPane.setStyle(Colors.GRAY_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectInfoPane.setStyle(Colors.BLACK_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectionInfoTitleLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.usernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.serverIPLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.serverPortLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.databaseLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.infoPane.setStyle(Colors.BLACK_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.infoTitleLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.authorLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.creationDateLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.deletionDateLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.lowerPane.setStyle(Colors.GRAY_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.insertionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.insertionUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.insertionTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.viewLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.viewUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.viewTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.updateLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.updateUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.updateTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.deletionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.deletionUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.deletionTableView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.rightSuperPane.setStyle(Colors.GRAY_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.tablesLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.tablesListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.usersIPLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.usersIPListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.usersUsernameLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.usersUsernameListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.tablesSelectionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.tablesSelectionListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.usersIPSelectionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.usersIPSelectionListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.usersUsernameSelectionLabel.setStyle(Colors.WHITE_TEXT_COLOR);
        Resource.usersUsernameSelectionListView.setStyle(Colors.LIGHTGRAY_BG_COLOR);
        Resource.menuMainContainer.setStyle(Colors.TRUEBLACK_BG_COLOR);
    }

    /**
     * Spremeni temo aplikacije v svetlo
     */
    static void setToLightTheme() {
        Resource.menuBar.setStyle(Colors.BEIGE_BG_COLOR);
        Resource.title_img.setImage(new Image("images/database_logo_black.png"));
        Resource.menu.setStyle(Colors.BEIGE_BG_COLOR);
        Resource.mainPane.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.upperPane.setStyle(Colors.BEIGE_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectInfoPane.setStyle(Colors.WHITE_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectionInfoTitleLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.usernameLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.serverIPLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.serverPortLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.databaseLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.infoPane.setStyle(Colors.WHITE_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.infoTitleLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.authorLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.creationDateLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.deletionDateLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.lowerPane.setStyle(Colors.BEIGE_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.insertionLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.insertionUsernameLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.insertionTableView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.viewLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.viewUsernameLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.viewTableView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.updateLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.updateUsernameLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.updateTableView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.deletionLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.deletionUsernameLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.deletionTableView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.rightSuperPane.setStyle(Colors.BEIGE_BG_COLOR +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.tablesLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.tablesListView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.usersIPLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.usersIPListView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.usersUsernameLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.usersUsernameListView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.tablesSelectionLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.tablesSelectionListView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.usersIPSelectionLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.usersIPSelectionListView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.usersUsernameSelectionLabel.setStyle(Colors.BLACK_TEXT_COLOR);
        Resource.usersUsernameSelectionListView.setStyle(Colors.TRUEWHITE_BG_COLOR);
        Resource.menuMainContainer.setStyle(Colors.BEIGE_BG_COLOR);
    }

    /**
     * Štartna funkcija aplikacije
     *
     * @param primaryStage Primary StageU
     */
    @Override
    public void start(Stage primaryStage) {
        // Meni
        Resource.menuBar = new MenuBar();
        // Izbira nastavitve
        Resource.title_img = new ImageView("images/database_logo_white.png");
        Resource.title_img.setFitHeight(25);
        Resource.title_img.setFitWidth(50);
        // TODO: Spremeni barvo teksta v menu
        Resource.menu = new Menu("", Resource.title_img);
        //# MenuItem Izhod
        Resource.exitMenuItem = new MenuItem(Texts.EXIT);
        Resource.exitMenuItem.setOnAction((event) -> Platform.exit());
        //# Menu Nastavitve
        Resource.settingsMenu = new Menu(Texts.SETTINGS);
        //## Tema aplikacije
        Resource.themeMenu = new Menu(Texts.APLICCATION_THEME);
        //### Teme aplikacije
        Resource.darkThemeMenuItem = new MenuItem(Texts.DARK_THEME);
        Resource.darkThemeMenuItem.setOnAction((event) -> setToDarkTheme());
        Resource.lightThemeMenuItem = new MenuItem(Texts.LIGHT_THEME);
        Resource.lightThemeMenuItem.setOnAction((event) -> setToLightTheme());
        // Dodajanje elementov v meni
        Resource.menu.getItems().addAll(Resource.exitMenuItem, Resource.settingsMenu);
        Resource.settingsMenu.getItems().addAll(Resource.themeMenu);
        Resource.themeMenu.getItems().addAll(Resource.darkThemeMenuItem, Resource.lightThemeMenuItem);
        // Dodaj vse elemente v meni
        Resource.menuBar.getMenus().addAll(Resource.menu);

        // Glavni GridPane
        Resource.mainPane = new GridPane();
        Resource.mainPane.setVgap(5);
        Resource.mainPane.setHgap(5);
        Resource.mainPane.setPadding(new Insets(20));

        // Zgornji GridPane
        Resource.upperPane = new GridPane();
        Resource.upperPane.setPadding(new Insets(10, 10, 10, 5));
        //# GridPane povezava
        Resource.connectPane = new GridPane();
        Resource.connectPane.setVgap(11);
        Resource.connectPane.setHgap(11);
        Resource.connectPane.setPadding(new Insets(10));
        //## Povezava
        Resource.connectButton = new Button(Texts.CONNECT);
        setButtonProperties_GrayBold(Resource.connectButton);
        Resource.connectButton.setMinWidth(130);
        //## Prekinitev povezave
        Resource.disconnectButton = new Button(Texts.DISCONNECT);
        Resource.disconnectButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.disconnectButton);
        //## Povezava po meri
        Resource.customConnectButton = new Button(Texts.CUSTOM_CONNECT);
        setButtonProperties_GrayBold(Resource.customConnectButton);
        Resource.customConnectButton.setMinWidth(130);
        //# Handler-ji za gumbe
        Resource.connectButton.setOnAction((event) -> {
            // Pošlje backend-u podatke o povezavi in zaprosi za povezavo z sql strežnikom
        });
        Resource.disconnectButton.setOnAction((event) -> {
            // Prekine povezavo z sql strežnikom
        });
        Resource.customConnectButton.setOnAction((event) -> {
            // Odpre JOption pane za vnos podatkov za povezavo po meri
        });
        //# GridPane povezava info
        Resource.connectInfoPane = new GridPane();
        //## Naslov
        Resource.connectionInfoTitleLabel = new Label(Texts.SERVER_INFO);
        Resource.connectionInfoTitleLabel.setPadding(new Insets(0, 0, 10, 0));
        //## Informacije o tabeli
        Resource.connectInfoPane.setVgap(10);
        Resource.connectInfoPane.setHgap(10);
        Resource.connectInfoPane.setPadding(new Insets(10));
        //### Uporabnik
        Resource.usernameLabel = new Label(Texts.SERVER_USER);
        Resource.usernameValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.usernameValueLabel);
        //### IP naslov strežnika
        Resource.serverIPLabel = new Label(Texts.SERVER_IP);
        Resource.serverIPValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.serverIPValueLabel);
        //### Vrata strežnika
        Resource.serverPortLabel = new Label(Texts.SERVER_PORT);
        Resource.serverPortValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.serverPortValueLabel);
        //### Podatkovna baza strežnika
        Resource.databaseLabel = new Label(Texts.SERVER_DATABASE);
        Resource.databaseValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.databaseValueLabel);
        //# GridPane konfiguracija
        Resource.configPane = new GridPane();
        Resource.configPane.setVgap(11);
        Resource.configPane.setHgap(11);
        Resource.configPane.setPadding(new Insets(10));
        //## Naloži konfiguracijo
        Resource.loadConfigButton = new Button(Texts.LOAD_CONFIG);
        Resource.loadConfigButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.loadConfigButton);
        //## Shrani konfiguracijo
        Resource.saveConfigButton = new Button(Texts.SAVE_CONFIG);
        Resource.saveConfigButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.saveConfigButton);
        //## Pomoč
        Resource.helpButton = new Button(Texts.HELP);
        Resource.helpButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.helpButton);
        //## Počisti izbiro
        Resource.clearButton = new Button(Texts.CLEAR_SELECTION);
        Resource.clearButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.clearButton);
        //# GridPane Tabela Info
        Resource.infoPane = new GridPane();
        Resource.infoPane.setPadding(new Insets(0, 10, 0, 0));
        //## Naslov
        Resource.infoTitleLabel = new Label(Texts.TABLE_INFO);
        Resource.infoTitleLabel.setPadding(new Insets(10));
        //## Informacije o tabeli
        // Avtor Tabele
        Resource.authorLabel = new Label(Texts.TABLE_AUTHOR);
        Resource.authorLabel.setPadding(new Insets(10));
        Resource.authorValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.authorValueLabel);
        // Datum nastanka
        Resource.creationDateLabel = new Label(Texts.TABLE_CREATION_DATE);
        Resource.creationDateLabel.setPadding(new Insets(10));
        Resource.creationDateValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.creationDateValueLabel);
        // Datum zaprtja
        Resource.deletionDateLabel = new Label(Texts.TABLE_DELETION_DATE);
        Resource.deletionDateLabel.setPadding(new Insets(10));
        Resource.deletionDateValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.deletionDateValueLabel);
        //# Handler-ji za gumbe
        Resource.loadConfigButton.setOnAction((event) -> {
            // Naloži podatke o povezavi iz datoteke
        });
        Resource.saveConfigButton.setOnAction((event) -> {
            // Shrani podatke o povezavi v datoteko
        });
        Resource.helpButton.setOnAction((event) -> {
            // Prikaže pomoč uporabniku
        });
        Resource.clearButton.setOnAction((event) -> {
            // Počisti izbiro
        });

        // Spodnji GridPane
        Resource.lowerPane = new GridPane();
        Resource.lowerPane.setPadding(new Insets(10));
        //## Maksimalna višina GridPane
        int MAXGRIDHEIGHT = 300;
        int MINGRIDWIDTH = 395;
        //# Vstavljanje GridPane
        Resource.insertionPane = new GridPane();
        Resource.insertionPane.setPadding(new Insets(10));
        Resource.insertionLabel = new Label(Texts.INSERTIONS);
        Resource.insertionUsernameLabel = new Label(Texts.NA);
        Resource.insertionLabel.setPadding(new Insets(0, 0, 10, 0));
        Resource.insertionUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        Resource.insertionTableView = new TableView<>();
        Resource.insertionTableView.setPlaceholder(new Label(Texts.NO_DATA_SELECTED));
        Resource.insertionTableView.setMinWidth(MINGRIDWIDTH);
        Resource.insertionTableView.setMaxHeight(MAXGRIDHEIGHT);
        //# Ogledovanje GridPane
        Resource.viewPane = new GridPane();
        Resource.viewPane.setPadding(new Insets(10));
        Resource.viewLabel = new Label(Texts.VIEWS);
        Resource.viewUsernameLabel = new Label(Texts.NA);
        Resource.viewLabel.setPadding(new Insets(0, 0, 10, 0));
        Resource.viewUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        Resource.viewTableView = new TableView<>();
        Resource.viewTableView.setPlaceholder(new Label(Texts.NO_DATA_SELECTED));
        Resource.viewTableView.setMinWidth(MINGRIDWIDTH);
        Resource.viewTableView.setMaxHeight(MAXGRIDHEIGHT);
        //# Ažuriranje GridPane
        Resource.updatePane = new GridPane();
        Resource.updatePane.setPadding(new Insets(10));
        Resource.updateLabel = new Label(Texts.UPDATES);
        Resource.updateUsernameLabel = new Label(Texts.NA);
        Resource.updateLabel.setPadding(new Insets(0, 0, 10, 0));
        Resource.updateUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        Resource.updateTableView = new TableView<>();
        Resource.updateTableView.setPlaceholder(new Label(Texts.NO_DATA_SELECTED));
        Resource.updateTableView.setMinWidth(MINGRIDWIDTH);
        Resource.updateTableView.setMaxHeight(MAXGRIDHEIGHT);
        //# Brisanje GridPane
        Resource.deletionPane = new GridPane();
        Resource.deletionPane.setPadding(new Insets(10));
        Resource.deletionLabel = new Label(Texts.DELETIONS);
        Resource.deletionUsernameLabel = new Label(Texts.NA);
        Resource.deletionLabel.setPadding(new Insets(0, 0, 10, 0));
        Resource.deletionUsernameLabel.setPadding(new Insets(0, 0, 10, 5));
        Resource.deletionTableView = new TableView<>();
        Resource.deletionTableView.setPlaceholder(new Label(Texts.NO_DATA_SELECTED));
        Resource.deletionTableView.setMinWidth(MINGRIDWIDTH);
        Resource.deletionTableView.setMaxHeight(MAXGRIDHEIGHT);

        // Desni starševski GridPane
        Resource.rightSuperPane = new GridPane();

        // Desni Grid Pane
        Resource.rightPane = new GridPane();
        Resource.rightPane.setPadding(new Insets(20, 20, 20, 20));
        //# Seznam vseh tabel
        Resource.tablesLabel = new Label(Texts.ALL_TABLES);
        Resource.tablesLabel.setPadding(new Insets(0, 0, 10, 0));
        Resource.tablesListView = new ListView<>();
        Resource.tablesListView.setMaxHeight(125);
        //# Seznam vseh uporabnikov (IP)
        Resource.usersIPLabel = new Label(Texts.ALL_USERS_IP);
        Resource.usersIPLabel.setPadding(new Insets(10, 0, 10, 0));
        Resource.usersIPListView = new ListView<>();
        Resource.usersIPListView.setMaxHeight(300);
        //# Seznam vseh uporabnikov (username)
        Resource.usersUsernameLabel = new Label(Texts.ALL_USERS_USERNAME);
        Resource.usersUsernameLabel.setPadding(new Insets(10, 0, 10, 0));
        Resource.usersUsernameListView = new ListView<>();
        Resource.usersUsernameListView.setMaxHeight(300);
        //# Handler-ji za ListView-je
        Resource.tablesListView.getSelectionModel().selectedItemProperty().addListener((event) -> {
            // Backend-u pošlje zahtevo za seznam uporabnikov tabele
        });
        Resource.usersIPListView.getSelectionModel().selectedItemProperty().addListener((event) -> {
            // Izpiše vse akcije izbranega uporabnika
        });
        Resource.usersUsernameListView.getSelectionModel().selectedItemProperty().addListener((event) -> {
            // Izpiše vse akcije izbranega uporabnika
        });

        // Desni izbirni GridPane
        Resource.rightSelectionPane = new GridPane();
        Resource.rightSelectionPane.setPadding(new Insets(20, 20, 20, 0));
        //# Seznam tabel
        Resource.tablesSelectionLabel = new Label(Texts.SELECTED_TABLES);
        Resource.tablesSelectionLabel.setPadding(new Insets(0, 0, 10, 0));
        Resource.tablesSelectionListView = new ListView<>();
        Resource.tablesSelectionListView.setMaxHeight(125);
        //# Seznam uporabnikov (IP)
        Resource.usersIPSelectionLabel = new Label(Texts.SELECTED_USERS_IP);
        Resource.usersIPSelectionLabel.setPadding(new Insets(10, 0, 10, 0));
        Resource.usersIPSelectionListView = new ListView<>();
        Resource.usersIPSelectionListView.setMaxHeight(300);
        //# Seznam uporabnikov (username)
        Resource.usersUsernameSelectionLabel = new Label(Texts.SELECTED_USERS_USERNAME);
        Resource.usersUsernameSelectionLabel.setPadding(new Insets(10, 0, 10, 0));
        Resource.usersUsernameSelectionListView = new ListView<>();
        Resource.usersUsernameSelectionListView.setMaxHeight(300);

        // Dodajanje elementov v glavni GridPane
        Resource.mainPane.add(Resource.upperPane, 0, 0, 1, 1);
        Resource.mainPane.add(Resource.lowerPane, 0, 1, 1, 1);
        Resource.mainPane.add(Resource.rightSuperPane, 1, 0, 1, 2);

        // Dodajanje elementov v zgornji GridPane
        Resource.upperPane.add(Resource.connectPane, 0, 0, 1, 1);
        Resource.upperPane.add(Resource.connectInfoPane, 1, 0, 1, 1);
        Resource.upperPane.add(Resource.configPane, 2, 0, 1, 1);
        Resource.upperPane.add(Resource.infoPane, 3, 0, 1, 1);
        //# Dodajanje elementov v povezava GridPane
        Resource.connectPane.add(Resource.connectButton, 0, 0, 1, 1);
        Resource.connectPane.add(Resource.disconnectButton, 0, 1, 1, 1);
        Resource.connectPane.add(Resource.customConnectButton, 0, 2, 1, 1);
        //# Dodajanje elementov v povezava info GridPane
        Resource.connectInfoPane.add(Resource.connectionInfoTitleLabel, 0, 0, 2, 1);
        Resource.connectInfoPane.add(Resource.usernameLabel, 0, 1, 1, 1);
        Resource.connectInfoPane.add(Resource.usernameValueLabel, 1, 1, 1, 1);
        Resource.connectInfoPane.add(Resource.serverIPLabel, 0, 2, 1, 1);
        Resource.connectInfoPane.add(Resource.serverIPValueLabel, 1, 2, 1, 1);
        Resource.connectInfoPane.add(Resource.serverPortLabel, 0, 3, 1, 1);
        Resource.connectInfoPane.add(Resource.serverPortValueLabel, 1, 3, 1, 1);
        Resource.connectInfoPane.add(Resource.databaseLabel, 0, 4, 1, 1);
        Resource.connectInfoPane.add(Resource.databaseValueLabel, 1, 4, 1, 1);
        //# Dodajanje elementov v konfiguracija GridPane
        Resource.configPane.add(Resource.loadConfigButton, 0, 0, 1, 1);
        Resource.configPane.add(Resource.saveConfigButton, 0, 1, 1, 1);
        Resource.configPane.add(Resource.helpButton, 0, 2, 1, 1);
        Resource.configPane.add(Resource.clearButton, 0, 3, 1, 1);
        //# Dodajanje elementov v table info GridPane
        Resource.infoPane.add(Resource.infoTitleLabel, 0, 0, 2, 1);
        Resource.infoPane.add(Resource.authorLabel, 0, 1, 1, 1);
        Resource.infoPane.add(Resource.authorValueLabel, 1, 1, 1, 1);
        Resource.infoPane.add(Resource.creationDateLabel, 0, 2, 1, 1);
        Resource.infoPane.add(Resource.creationDateValueLabel, 1, 2, 1, 1);
        Resource.infoPane.add(Resource.deletionDateLabel, 0, 3, 1, 1);
        Resource.infoPane.add(Resource.deletionDateValueLabel, 1, 3, 1, 1);

        // Dodajanje elementov v spodnji GridPane
        Resource.lowerPane.add(Resource.insertionPane, 0, 0, 1, 1);
        Resource.lowerPane.add(Resource.updatePane, 1, 0, 1, 1);
        Resource.lowerPane.add(Resource.viewPane, 0, 1, 1, 1);
        Resource.lowerPane.add(Resource.deletionPane, 1, 1, 1, 1);
        //# Dodajanje elementov v vstavljanje GridPane
        Resource.insertionPane.add(Resource.insertionLabel, 0, 0, 1, 1);
        Resource.insertionPane.add(Resource.insertionUsernameLabel, 1, 0, 1, 1);
        Resource.insertionPane.add(Resource.insertionTableView, 0, 1, 2, 1);
        //# Dodajanje elementov v ažuriranje GridPane
        Resource.updatePane.add(Resource.updateLabel, 0, 0, 1, 1);
        Resource.updatePane.add(Resource.updateUsernameLabel, 1, 0, 1, 1);
        Resource.updatePane.add(Resource.updateTableView, 0, 1, 2, 1);
        //# Dodajanje elementov v ogledovanje GridPane
        Resource.viewPane.add(Resource.viewLabel, 0, 0, 1, 1);
        Resource.viewPane.add(Resource.viewUsernameLabel, 1, 0, 1, 1);
        Resource.viewPane.add(Resource.viewTableView, 0, 1, 2, 1);
        //# Dodajanje elementov v brisanje GridPane
        Resource.deletionPane.add(Resource.deletionLabel, 0, 0, 1, 1);
        Resource.deletionPane.add(Resource.deletionUsernameLabel, 1, 0, 1, 1);
        Resource.deletionPane.add(Resource.deletionTableView, 0, 1, 2, 1);

        // Dodajanje elementov v desni starševski GridPane
        Resource.rightSuperPane.add(Resource.rightPane, 0, 0, 1, 1);
        Resource.rightSuperPane.add(Resource.rightSelectionPane, 1, 0, 1, 1);

        // Dodajanje elementov v desni GridPane
        Resource.rightPane.add(Resource.tablesLabel, 0, 0, 1, 1);
        Resource.rightPane.add(Resource.tablesListView, 0, 1, 1, 1);
        Resource.rightPane.add(Resource.usersIPLabel, 0, 2, 1, 1);
        Resource.rightPane.add(Resource.usersIPListView, 0, 3, 1, 1);
        Resource.rightPane.add(Resource.usersUsernameLabel, 0, 4, 1, 1);
        Resource.rightPane.add(Resource.usersUsernameListView, 0, 5, 1, 1);

        // Dodajanje elementov v desni izbirni GridPane
        Resource.rightSelectionPane.add(Resource.tablesSelectionLabel, 0, 0, 1, 1);
        Resource.rightSelectionPane.add(Resource.tablesSelectionListView, 0, 1, 1, 1);
        Resource.rightSelectionPane.add(Resource.usersIPSelectionLabel, 0, 2, 1, 1);
        Resource.rightSelectionPane.add(Resource.usersIPSelectionListView, 0, 3, 1, 1);
        Resource.rightSelectionPane.add(Resource.usersUsernameSelectionLabel, 0, 4, 1, 1);
        Resource.rightSelectionPane.add(Resource.usersUsernameSelectionListView, 0, 5, 1, 1);

        // Glavna scena
        // VBox For Menu + GridPane
        Resource.menuMainContainer = new VBox();
        GridPane menuContainer = new GridPane();
        menuContainer.add(Resource.menuBar, 0, 0);
        //# Premikanje okna aplikacije s handler-ji
        final Delta dragDelta = new Delta();
        menuContainer.setOnMousePressed((event) -> {
            dragDelta.x = primaryStage.getX() - event.getScreenX();
            dragDelta.y = primaryStage.getY() - event.getScreenY();
        });
        menuContainer.setOnMouseDragged((event) -> {
            primaryStage.setX(event.getScreenX() + dragDelta.x);
            primaryStage.setY(event.getScreenY() + dragDelta.y);
        });
        //# Ostalo
        menuContainer.setPadding(new Insets(10, 0, 10, 0));
        Resource.menuMainContainer.getChildren().addAll(menuContainer, Resource.mainPane);
        // Nastavitev aplikacije na temno kot privzeto
        setToDarkTheme();
        // Ostale nastavitve, okno po meri
        Scene mainScene = new Scene(Resource.menuMainContainer);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setMaxHeight(Resource.lowerPane.getHeight() + Resource.upperPane.getHeight());
        primaryStage.setMinHeight(Resource.lowerPane.getHeight() + Resource.upperPane.getHeight());
        primaryStage.setMaxWidth(Resource.lowerPane.getWidth() + Resource.rightSuperPane.getWidth());
        primaryStage.setMinWidth(Resource.lowerPane.getWidth() + Resource.rightSuperPane.getWidth());
    }
}

/**
 * Razred vsebuje barve uporabljene v aplikaciji
 */
class Colors {
    final static String TRUEBLACK_BG_COLOR = "-fx-background-color: rgb(10, 10, 10);";
    final static String BLACK_BG_COLOR = "-fx-background-color: rgb(43, 43, 45);";
    final static String GRAY_BG_COLOR = "-fx-background-color: rgb(60, 63, 65);";
    final static String LIGHTGRAY_BASE_COLOR = "-fx-base: rgb(180, 180, 180);";
    final static String WHITE_TEXT_COLOR = "-fx-text-fill: rgb(250, 250, 250);";
    final static String WHITE_BG_COLOR_LINEARGRADIENT = "-fx-background-color: rgb(255, 255, 255)," +
            " linear-gradient(to bottom, #e3e3e3 0%, #cccccc 100%);";
    final static String BEIGE_BG_COLOR = "-fx-background-color: rgb(253,242,211);";
    final static String WHITE_BG_COLOR = "-fx-background-color: rgb(250, 250, 250);";
    final static String TRUEWHITE_BG_COLOR = "-fx-background-color: rgb(255, 255, 255);";
    final static String LIGHTGRAY_BG_COLOR = "-fx-background-color: rgb(195, 195, 195);";
    final static String BLACK_TEXT_COLOR = "-fx-text-fill: rgb(10, 10, 10);";
}

/**
 * Razred vsebuje aplikacijska besedila
 */
class Texts {
    final static String NA = "N/A";
    final static String NO_DATA_SELECTED = "Podatki še niso bili izbrani.";
    final static String CONNECT = "Poveži se";
    final static String DISCONNECT = "Prekini povezavo";
    final static String CUSTOM_CONNECT = "Povezava po meri";
    final static String SERVER_INFO = "Informacije o strežniku:";
    final static String SERVER_USER = "Uporabnik:";
    final static String SERVER_IP = "IP strežnika:";
    final static String SERVER_PORT = "Vrata strežnika:";
    final static String SERVER_DATABASE = "Podatk. baza:";
    final static String LOAD_CONFIG = "Naloži konfiguracijo";
    final static String SAVE_CONFIG = "Shrani konfiguracijo";
    final static String HELP = "Pomoč";
    final static String CLEAR_SELECTION = "Počisti izbiro";
    final static String TABLE_INFO = "Informacije o izbrani tabeli:";
    final static String TABLE_AUTHOR = "Avtor:";
    final static String TABLE_CREATION_DATE = "Datum nastanka:";
    final static String TABLE_DELETION_DATE = "Datum izbrisa:";
    final static String INSERTIONS = "Vstavljanja:";
    final static String UPDATES = "Ažuriranja:";
    final static String VIEWS = "Ogledovanja:";
    final static String DELETIONS = "Brisanja:";
    final static String ALL_TABLES = "Vse tabele:";
    final static String ALL_USERS_IP = "Vsi uporabniki (IP):";
    final static String ALL_USERS_USERNAME = "Vsi uporabniki (uporabniško ime):";
    final static String SELECTED_TABLES = "Tabele izbranega uporabnika:";
    final static String SELECTED_USERS_IP = "Uporabniki izbrane tabele (IP):";
    final static String SELECTED_USERS_USERNAME = "Uporabniki izbrane tabele (uporab. ime):";
    final static String EXIT = "Izhod";
    final static String SETTINGS = "Nastavitve";
    final static String APLICCATION_THEME = "Tema aplikacije";
    final static String DARK_THEME = "Temno";
    final static String LIGHT_THEME = "Svetlo";
}

/**
 * Sprememba premika kazalca miške
 */
class Delta {
    double x, y;
}

/**
 * Resursi
 */
class Resource {
    static MenuBar menuBar;
    static ImageView title_img;
    static Menu menu;
    static MenuItem exitMenuItem;
    static Menu settingsMenu;
    static Menu themeMenu;
    static MenuItem darkThemeMenuItem;
    static MenuItem lightThemeMenuItem;
    static GridPane mainPane;
    static GridPane upperPane;
    static GridPane connectPane;
    static Button connectButton;
    static Button disconnectButton;
    static Button customConnectButton;
    static GridPane connectInfoPane;
    static Label connectionInfoTitleLabel;
    static Label usernameLabel;
    static Label usernameValueLabel;
    static Label serverIPLabel;
    static Label serverIPValueLabel;
    static Label serverPortLabel;
    static Label serverPortValueLabel;
    static Label databaseLabel;
    static Label databaseValueLabel;
    static GridPane configPane;
    static Button loadConfigButton;
    static Button saveConfigButton;
    static Button helpButton;
    static Button clearButton;
    static GridPane infoPane;
    static Label infoTitleLabel;
    static Label authorLabel;
    static Label authorValueLabel;
    static Label creationDateLabel;
    static Label creationDateValueLabel;
    static Label deletionDateLabel;
    static Label deletionDateValueLabel;
    static GridPane lowerPane;
    static GridPane insertionPane;
    static Label insertionLabel;
    static Label insertionUsernameLabel;
    static TableView<String[]> insertionTableView;
    static GridPane viewPane;
    static Label viewLabel;
    static Label viewUsernameLabel;
    static TableView<String[]> viewTableView;
    static GridPane updatePane;
    static Label updateLabel;
    static Label updateUsernameLabel;
    static TableView<String[]> updateTableView;
    static GridPane deletionPane;
    static Label deletionLabel;
    static Label deletionUsernameLabel;
    static TableView<String[]> deletionTableView;
    static GridPane rightSuperPane;
    static GridPane rightPane;
    static Label tablesLabel;
    static ListView<String> tablesListView;
    static Label usersIPLabel;
    static ListView<String> usersIPListView;
    static Label usersUsernameLabel;
    static ListView<String> usersUsernameListView;
    static GridPane rightSelectionPane;
    static Label tablesSelectionLabel;
    static ListView<String> tablesSelectionListView;
    static Label usersIPSelectionLabel;
    static ListView<String> usersIPSelectionListView;
    static Label usersUsernameSelectionLabel;
    static ListView<String> usersUsernameSelectionListView;
    static VBox menuMainContainer;
}
