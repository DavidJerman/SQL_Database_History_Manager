import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

/**
 * Grafični vmesnik aplikacije oz. front-end
 *
 * @author David JermanU
 * @version 2021.01.30
 * @since 2021.01.25
 */
public class App extends Application {

    /**
     * Backend
     */
    static backend backend;
    static String theme = Texts.DARK;
    static String language = Texts.SL;

    /**
     * Glavna metoda
     *
     * @param args Metoda ne obravnava argumentov
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Nastavi jezik in temo aplikacije
     */
    static void initApp() {
        Properties prop = new Properties();
        String fileName = Texts.ASI_FX_DIRECTORY + Texts.CONFIG_FILE_NAME;
        InputStream is;
        try {
            is = new FileInputStream(fileName);
            prop.load(is);
            theme = prop.getProperty("theme");
            language = prop.getProperty("language");
            initBackend();
            loadLanguage();
        } catch (IOException ignored) {
            JOptionPane.showMessageDialog(Resource.jFrame, "Prišlo je do napake.\nThe program encountered an error.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            Platform.exit();
        }
    }

    /**
     * Inicializira backend
     */
    static void initBackend() {
        try {
            switch (language) {
                case Texts.SL:
                    backend = new backend(Texts.ASI_FX_DIRECTORY + Texts.SL_LANGUAGE_PACKB_PATH);
                    break;
                case Texts.DE:
                    backend = new backend(Texts.ASI_FX_DIRECTORY + Texts.DE_LANGUAGE_PACKB_PATH);
                    break;
                case Texts.JP:
                    backend = new backend(Texts.ASI_FX_DIRECTORY + Texts.JP_LNAGUAGE_PACKB_PATH);
                    break;
                default:
                    backend = new backend(Texts.ASI_FX_DIRECTORY + Texts.EN_LANGUAGE_PACKB_PATH);
                    break;
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Naloži jezikovni paket
     *
     * @throws IOException Branje neuspešno
     */
    static void loadLanguage() throws IOException {
        Properties prop = new Properties();
        String fileName;
        switch (language) {
            case Texts.SL:
                fileName = Texts.ASI_FX_DIRECTORY + Texts.SL_LANGUAGE_PACK_PATH;
                break;
            case Texts.DE:
                fileName = Texts.ASI_FX_DIRECTORY + Texts.DE_LANGUAGE_PACK_PATH;
                break;
            case Texts.JP:
                fileName = Texts.ASI_FX_DIRECTORY + Texts.JP_LNAGUAGE_PACK_PATH;
                break;
            default:
                fileName = Texts.ASI_FX_DIRECTORY + Texts.EN_LANGUAGE_PACK_PATH;
                break;
        }
        InputStream is;
        is = new FileInputStream(fileName);
        prop.load(new InputStreamReader(is, StandardCharsets.UTF_8));
        Texts.NA = prop.getProperty("NA");
        Texts.NO_DATA_SELECTED = prop.getProperty("NO_DATA_SELECTED");
        Texts.CONNECT = prop.getProperty("CONNECT");
        Texts.DISCONNECT = prop.getProperty("DISCONNECT");
        Texts.CUSTOM_CONNECT = prop.getProperty("CUSTOM_CONNECT");
        Texts.SERVER_INFO = prop.getProperty("SERVER_INFO");
        Texts.SERVER_USER = prop.getProperty("SERVER_USER");
        Texts.SERVER_IP = prop.getProperty("SERVER_IP");
        Texts.SERVER_PORT = prop.getProperty("SERVER_PORT");
        Texts.SERVER_DATABASE = prop.getProperty("SERVER_DATABASE");
        Texts.LOAD_CONFIG = prop.getProperty("LOAD_CONFIG");
        Texts.SAVE_CONFIG = prop.getProperty("SAVE_CONFIG");
        Texts.HELP = prop.getProperty("HELP");
        Texts.CLEAR_SELECTION = prop.getProperty("CLEAR_SELECTION");
        Texts.TABLE_INFO = prop.getProperty("TABLE_INFO");
        Texts.TABLE_CREATOR = prop.getProperty("TABLE_CREATOR");
        Texts.TABLE_DELETOR = prop.getProperty("TABLE_DELETOR");
        Texts.TABLE_CREATION_DATE = prop.getProperty("TABLE_CREATION_DATE");
        Texts.TABLE_DELETION_DATE = prop.getProperty("TABLE_DELETION_DATE");
        Texts.INSERTIONS = prop.getProperty("INSERTIONS");
        Texts.UPDATES = prop.getProperty("UPDATES");
        Texts.VIEWS = prop.getProperty("VIEWS");
        Texts.DELETIONS = prop.getProperty("DELETIONS");
        Texts.ALL_TABLES = prop.getProperty("ALL_TABLES");
        Texts.ALL_USERS_IP = prop.getProperty("ALL_USERS_IP");
        Texts.ALL_USERS_USERNAME = prop.getProperty("ALL_USERS_USERNAME");
        Texts.SELECTED_TABLES = prop.getProperty("SELECTED_TABLES");
        Texts.SELECTED_USERS_IP = prop.getProperty("SELECTED_USERS_IP");
        Texts.SELECTED_USERS_USERNAME = prop.getProperty("SELECTED_USERS_USERNAME");
        Texts.EXIT = prop.getProperty("EXIT");
        Texts.SETTINGS = prop.getProperty("SETTINGS");
        Texts.APLICCATION_THEME = prop.getProperty("APLICCATION_THEME");
        Texts.DARK_THEME = prop.getProperty("DARK_THEME");
        Texts.LIGHT_THEME = prop.getProperty("LIGHT_THEME");
        Texts.APPLICATION_LANGUAGE = prop.getProperty("APPLICATION_LANGUAGE");
        Texts.EN_LANGUAGE = prop.getProperty("EN_LANGUAGE");
        Texts.SL_LANGUAGE = prop.getProperty("SL_LANGUAGE");
        Texts.DE_LANGUAGE = prop.getProperty("DE_LANGUAGE");
        Texts.JP_LANGUAGE = prop.getProperty("JP_LANGUAGE");
        Texts.CUSTOM_CONNECTION_PROMPT_TITLE = prop.getProperty("CUSTOM_CONNECTION_PROMPT_TITLE");
        Texts.USERNAME_PROMPT = prop.getProperty("USERNAME_PROMPT");
        Texts.PASSWORD_PROMPT = prop.getProperty("PASSWORD_PROMPT");
        Texts.IP_PROMOT = prop.getProperty("IP_PROMOT");
        Texts.PORT_PROMPT = prop.getProperty("PORT_PROMPT");
        Texts.DATABASE_PROMPT = prop.getProperty("DATABASE_PROMPT");
        Texts.CONNECTION_STATUS = prop.getProperty("CONNECTION_STATUS");
        Texts.INSERTION_TOOLTIP = prop.getProperty("INSERTION_TOOLTIP");
        Texts.DELETION_TOOLTIP = prop.getProperty("DELETION_TOOLTIP");
        Texts.UPDATE_TOOLTIP = prop.getProperty("UPDATE_TOOLTIP");
        Texts.VIEW_TOOLTIP = prop.getProperty("VIEW_TOOLTIP");
        Texts.CONNECT_TOOLTIP = prop.getProperty("CONNECT_TOOLTIP");
        Texts.DISCONNECT_TOOLTIP = prop.getProperty("DISCONNECT_TOOLTIP");
        Texts.CUSTOM_CONNECT_TOOLTIP = prop.getProperty("CUSTOM_CONNECT_TOOLTIP");
        Texts.LOAD_CONFIG_TOOLTIP = prop.getProperty("LOAD_CONFIG_TOOLTIP");
        Texts.SAVE_CONFIG_TOOLTIP = prop.getProperty("SAVE_CONFIG_TOOLTIP");
        Texts.HELP_TOOLTIP = prop.getProperty("HELP_TOOLTIP");
        Texts.CLEAR_TOOLTIP = prop.getProperty("CLEAR_TOOLTIP");
        Texts.SAVE_CONFIG_PROMPT = prop.getProperty("SAVE_CONFIG_PROMPT");
        Texts.LOAD_CONFIG_PROMPT = prop.getProperty("LOAD_CONFIG_PROMPT");
        Texts.FILE_ERROR = prop.getProperty("FILE_ERROR");
        Texts.CONNECTION_ERROR = prop.getProperty("CONNECTION_ERROR");
        Texts.CONNECTION_SUCCESSFUL = prop.getProperty("CONNECTION_SUCCESSFUL");
        Texts.HELP_URL = prop.getProperty("HELP_URL");
        Texts.DATE_AND_TIME = prop.getProperty("DATE_AND_TIME");
        Texts.CONTENT = prop.getProperty("CONTENT");
        Texts.FILE_TYPE_NAME = prop.getProperty("FILE_TYPE_NAME");
        Texts.FILE_TYPE_EXTENSION = prop.getProperty("FILE_TYPE_EXTENSION");
        Texts.EMPTY = "";
    }

    /**
     * Posodobi konfiguracijsko datoteko
     */
    static void updateConfig() {
        try {
            Properties prop = new Properties();
            String fileName = Texts.ASI_FX_DIRECTORY + Texts.CONFIG_FILE_NAME;
            InputStream is;
            is = new FileInputStream(fileName);
            prop.load(is);
            is.close();

            String oldLanguage = prop.getProperty("language");

            PrintWriter fileWriter = new PrintWriter(Texts.ASI_FX_DIRECTORY + Texts.CONFIG_FILE_NAME);
            fileWriter.println("language = " + language);
            fileWriter.println("theme = " + theme);
            fileWriter.close();

            if (!oldLanguage.equals(language)) {
                if (language.equals(Texts.SL))
                    JOptionPane.showMessageDialog(Resource.jFrame, "Spremembe bodo uveljavljene ob ponovnem zagonu",
                            null, JOptionPane.INFORMATION_MESSAGE);
                else if (language.equals(Texts.EN))
                    JOptionPane.showMessageDialog(Resource.jFrame, "Changes will be applied after restarting the app",
                            null, JOptionPane.INFORMATION_MESSAGE);
                else if (language.equals(Texts.DE))
                    JOptionPane.showMessageDialog(Resource.jFrame, "Änderungen werden nach dem Neustart der App übernommen",
                            null, JOptionPane.INFORMATION_MESSAGE);
                else if (language.equals(Texts.JP))
                    JOptionPane.showMessageDialog(Resource.jFrame, "アプリの再起動後に変更が適用されます",
                            null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ignored) {
        }
    }

    /**
     * Spremeni lastnosti podanega Button (izgled)
     *
     * @param button Button, ki ga urejamo
     */
    static void setButtonProperties_GrayBold(Button button) {
        button.setStyle(Colors.LIGHTGRAY_BASE);
        button.setFont(Font.font("Segoe", FontWeight.BOLD, 12));
    }

    /**
     * Spremeni lastnosti podanega Label (izgled)
     *
     * @param label Label, ki ga urejamo
     */
    static void setLabelProperties_InfoLabel(Label label) {
        label.setStyle(Colors.WHITE_BG_LINEARGRADIENT +
                "-fx-padding: 4px;" +
                "-fx-background-radius: 3px;" +
                "-fx-border-radius: 3px;" +
                "-fx-effect: innershadow(three-pass-box, rgba(0,0,0,0.75),1,0,1,1);");
        label.setMinWidth(150);
        label.setMaxWidth(150);
    }

    /**
     * Napolni ListView s podanimi vrednostmi
     *
     * @param listView ListView
     * @param values   Vrednosti, ki jih dodajamo v ListView
     */
    static void populateListViewWith(ListView<String> listView, String[] values) {
        ObservableList<String> items = FXCollections.observableArrayList();
        items.addAll(Arrays.asList(values));
        listView.setItems(items);
    }

    /**
     * Napolni TableView s podanimi vrednostmi
     *
     * @param tableView TableView
     * @param hashMap   Vrednosti, ki jih dodajamo v TableView
     */
    static void populateTableViewWith(TableView<String[]> tableView, HashMap<String, String> hashMap) {
        tableView.getColumns().clear();
        tableView.getItems().clear();
        TableColumn<String[], String> column1 = new TableColumn<>(Texts.DATE_AND_TIME);
        TableColumn<String[], String> column2 = new TableColumn<>(Texts.CONTENT);
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        column1.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 0 ? x[0] : Texts.NA);
        });
        column2.setCellValueFactory((p) -> {
            String[] x = p.getValue();
            return new SimpleStringProperty(x != null && x.length > 0 ? x[1] : Texts.NA);
        });
        String[][] data = new String[hashMap.size()][2];
        int i = 0;
        for (String key : hashMap.keySet()) {
            data[i] = new String[]{key, hashMap.get(key)};
            i++;
        }
        tableView.getItems().addAll(Arrays.asList(data));
    }

    /**
     * Vpiše informacije o tabeli
     *
     * @param table Tabela
     */
    static void setTableInfo(String table) {
        String[] data = backend.getTableInfo(table);
        Resource.creationAuthorValueLabel.setText(data[0] == null ? Texts.NA : data[0]);
        Resource.creationDateValueLabel.setText(data[1] == null ? Texts.NA : data[1]);
        Resource.deletionAuthorValueLabel.setText(data[2] == null ? Texts.NA : data[2]);
        Resource.deletionDateValueLabel.setText(data[3] == null ? Texts.NA : data[3]);
    }

    /**
     * Ponastavi informacije o tabeli
     */
    static void resetTableInfo() {
        Resource.creationAuthorValueLabel.setText(null);
        Resource.creationDateValueLabel.setText(null);
        Resource.deletionAuthorValueLabel.setText(null);
        Resource.deletionDateValueLabel.setText(null);
    }

    /**
     * Shrani podatke o konfiguraciji v datoteko
     *
     * @param file Datoteko, kamor shranjujemo
     */
    static void saveConfig(File file) {
        try {
            PrintWriter printWriter = new PrintWriter(file);
            printWriter.println("username:" + Resource.username);
            printWriter.println("password:" + Resource.password);
            printWriter.println("ip:" + Resource.serverIp);
            printWriter.println("port:" + Resource.serverPort);
            printWriter.println("database:" + Resource.database);
            printWriter.close();
            JOptionPane.showMessageDialog(Resource.jFrame, Texts.SAVE_CONFIG_PROMPT, null, JOptionPane.INFORMATION_MESSAGE);
        } catch (FileNotFoundException ignored) {
            JOptionPane.showMessageDialog(Resource.jFrame, Texts.FILE_ERROR, null, JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Spremeni stanje indikatorja povezanosti:
     * 0: Ni povezave
     * 1: Povezovanje
     * 2: Je povezava
     *
     * @param state Stanje
     */
    static void changeIndicatorState(int state) {
        switch (state) {
            case 0:
                Resource.connectionIndicatorCircle.setFill(Color.valueOf(Colors.RED));
                break;
            case 1:
                Resource.connectionIndicatorCircle.setFill(Color.valueOf(Colors.YELLOW));
                break;
            case 2:
                Resource.connectionIndicatorCircle.setFill(Color.valueOf(Colors.GREEN));
        }
    }

    /**
     * Pobriše trenutno izbrane tabele in uporabnike
     */
    static void clearSelection() {
        Resource.tablesSelectionListView.setItems(null);
        Resource.usersIPSelectionListView.setItems(null);
        Resource.usersUsernameSelectionListView.setItems(null);
        Resource.viewTableView.getColumns().clear();
        Resource.viewTableView.getItems().clear();
        Resource.updateTableView.getColumns().clear();
        Resource.updateTableView.getItems().clear();
        Resource.insertionTableView.getColumns().clear();
        Resource.insertionTableView.getItems().clear();
        Resource.deletionTableView.getColumns().clear();
        Resource.deletionTableView.getItems().clear();
        resetLabels();
        resetTableInfo();
    }

    /**
     * Ponastavi vsak fokus na ListView
     */
    static void resetAllFocus() {
        Resource.tablesSelectionListView.getSelectionModel().clearSelection();
        Resource.usersIPSelectionListView.getSelectionModel().clearSelection();
        Resource.usersUsernameSelectionListView.getSelectionModel().clearSelection();
        Resource.tablesListView.getSelectionModel().clearSelection();
        Resource.usersIPListView.getSelectionModel().clearSelection();
        Resource.usersUsernameListView.getSelectionModel().clearSelection();
    }

    /**
     * Nastavi avtorja in tabelo vsem Label-om
     *
     * @param author Avtor
     * @param table  Tabela
     */
    static void setTableAuthorToLabels(String author, String table) {
        Resource.deletionUsernameLabel.setText(table + " > " + author);
        Resource.updateUsernameLabel.setText(table + " > " + author);
        Resource.insertionUsernameLabel.setText(table + " > " + author);
        Resource.viewUsernameLabel.setText(table + " > " + author);
    }

    /**
     * Ponastavi vsebino vsem Label-om
     */
    static void resetLabels() {
        Resource.deletionUsernameLabel.setText(Texts.NA);
        Resource.updateUsernameLabel.setText(Texts.NA);
        Resource.insertionUsernameLabel.setText(Texts.NA);
        Resource.viewUsernameLabel.setText(Texts.NA);
    }

    /**
     * Naloži konfiguracijo iz datoteke
     *
     * @param file Datoteka, iz katere nalagamo
     */
    static void loadConfig(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String username = null;
            String password = null;
            String ip = null;
            String port = null;
            String database = null;
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] split = line.split(":");
                if (split.length != 2) throw new IndexOutOfBoundsException();
                String key = split[0];
                String value = split[1];
                switch (key) {
                    case "username":
                        username = value;
                        break;
                    case "password":
                        password = value;
                        break;
                    case "ip":
                        ip = value;
                        break;
                    case "port":
                        port = value;
                        break;
                    case "database":
                        database = value;
                }
            }
            if (username != null && password != null && ip != null && port != null && database != null) {
                Resource.username = username;
                Resource.password = password;
                Resource.serverIp = ip;
                Resource.serverPort = port;
                Resource.database = database;
                Resource.usernameValueLabel.setText(Resource.username);
                Resource.serverIPValueLabel.setText(Resource.serverIp);
                Resource.serverPortValueLabel.setText(Resource.serverPort);
                Resource.databaseValueLabel.setText(Resource.database);
                JOptionPane.showMessageDialog(Resource.jFrame, Texts.LOAD_CONFIG_PROMPT, null, JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (FileNotFoundException | IndexOutOfBoundsException ignored) {
            JOptionPane.showMessageDialog(Resource.jFrame, Texts.FILE_ERROR, null, JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Spremeni temo aplikacije v temno
     */
    static void setToDarkTheme() {
        Resource.menuBar.setStyle(Colors.TRUEBLACK_BG);
        Resource.title_img.setImage(new Image(Texts.WHITE_LOGO_URL));
        Resource.menu.setStyle(Colors.TRUEBLACK_BG);
        Resource.mainPane.setStyle(Colors.BLACK_BG);
        Resource.upperPane.setStyle(Colors.GRAY_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectInfoPane.setStyle(Colors.BLACK_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectionInfoTitleLabel.setStyle(Colors.WHITE_TEXT);
        Resource.usernameLabel.setStyle(Colors.WHITE_TEXT);
        Resource.serverIPLabel.setStyle(Colors.WHITE_TEXT);
        Resource.serverPortLabel.setStyle(Colors.WHITE_TEXT);
        Resource.databaseLabel.setStyle(Colors.WHITE_TEXT);
        Resource.infoPane.setStyle(Colors.BLACK_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.infoTitleLabel.setStyle(Colors.WHITE_TEXT);
        Resource.creationAuthorLabel.setStyle(Colors.WHITE_TEXT);
        Resource.deletionAuthorLabel.setStyle(Colors.WHITE_TEXT);
        Resource.creationDateLabel.setStyle(Colors.WHITE_TEXT);
        Resource.deletionDateLabel.setStyle(Colors.WHITE_TEXT);
        Resource.lowerPane.setStyle(Colors.GRAY_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.insertionLabel.setStyle(Colors.WHITE_TEXT);
        Resource.insertionUsernameLabel.setStyle(Colors.WHITE_TEXT);
        Resource.insertionTableView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.viewLabel.setStyle(Colors.WHITE_TEXT);
        Resource.viewUsernameLabel.setStyle(Colors.WHITE_TEXT);
        Resource.viewTableView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.updateLabel.setStyle(Colors.WHITE_TEXT);
        Resource.updateUsernameLabel.setStyle(Colors.WHITE_TEXT);
        Resource.updateTableView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.deletionLabel.setStyle(Colors.WHITE_TEXT);
        Resource.deletionUsernameLabel.setStyle(Colors.WHITE_TEXT);
        Resource.deletionTableView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.rightSuperPane.setStyle(Colors.GRAY_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.tablesLabel.setStyle(Colors.WHITE_TEXT);
        Resource.tablesListView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.usersIPLabel.setStyle(Colors.WHITE_TEXT);
        Resource.usersIPListView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.usersUsernameLabel.setStyle(Colors.WHITE_TEXT);
        Resource.usersUsernameListView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.tablesSelectionLabel.setStyle(Colors.WHITE_TEXT);
        Resource.tablesSelectionListView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.usersIPSelectionLabel.setStyle(Colors.WHITE_TEXT);
        Resource.usersIPSelectionListView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.usersUsernameSelectionLabel.setStyle(Colors.WHITE_TEXT);
        Resource.usersUsernameSelectionListView.setStyle(Colors.LIGHTGRAY_BG);
        Resource.menuMainContainer.setStyle(Colors.TRUEBLACK_BG);
    }

    /**
     * Spremeni temo aplikacije v svetlo
     */
    static void setToLightTheme() {
        Resource.menuBar.setStyle(Colors.BEIGE_BG);
        Resource.title_img.setImage(new Image(Texts.BLACK_LOGO_URL));
        Resource.menu.setStyle(Colors.BEIGE_BG);
        Resource.mainPane.setStyle(Colors.TRUEWHITE_BG);
        Resource.upperPane.setStyle(Colors.BEIGE_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectInfoPane.setStyle(Colors.WHITE_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.connectionInfoTitleLabel.setStyle(Colors.BLACK_TEXT);
        Resource.usernameLabel.setStyle(Colors.BLACK_TEXT);
        Resource.serverIPLabel.setStyle(Colors.BLACK_TEXT);
        Resource.serverPortLabel.setStyle(Colors.BLACK_TEXT);
        Resource.databaseLabel.setStyle(Colors.BLACK_TEXT);
        Resource.infoPane.setStyle(Colors.WHITE_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.infoTitleLabel.setStyle(Colors.BLACK_TEXT);
        Resource.creationAuthorLabel.setStyle(Colors.BLACK_TEXT);
        Resource.deletionAuthorLabel.setStyle(Colors.BLACK_TEXT);
        Resource.creationDateLabel.setStyle(Colors.BLACK_TEXT);
        Resource.deletionDateLabel.setStyle(Colors.BLACK_TEXT);
        Resource.lowerPane.setStyle(Colors.BEIGE_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.insertionLabel.setStyle(Colors.BLACK_TEXT);
        Resource.insertionUsernameLabel.setStyle(Colors.BLACK_TEXT);
        Resource.insertionTableView.setStyle(Colors.TRUEWHITE_BG);
        Resource.viewLabel.setStyle(Colors.BLACK_TEXT);
        Resource.viewUsernameLabel.setStyle(Colors.BLACK_TEXT);
        Resource.viewTableView.setStyle(Colors.TRUEWHITE_BG);
        Resource.updateLabel.setStyle(Colors.BLACK_TEXT);
        Resource.updateUsernameLabel.setStyle(Colors.BLACK_TEXT);
        Resource.updateTableView.setStyle(Colors.TRUEWHITE_BG);
        Resource.deletionLabel.setStyle(Colors.BLACK_TEXT);
        Resource.deletionUsernameLabel.setStyle(Colors.BLACK_TEXT);
        Resource.deletionTableView.setStyle(Colors.TRUEWHITE_BG);
        Resource.rightSuperPane.setStyle(Colors.BEIGE_BG +
                "-fx-background-radius: 5px;" +
                "-fx-background-insets: 2px;");
        Resource.tablesLabel.setStyle(Colors.BLACK_TEXT);
        Resource.tablesListView.setStyle(Colors.TRUEWHITE_BG);
        Resource.usersIPLabel.setStyle(Colors.BLACK_TEXT);
        Resource.usersIPListView.setStyle(Colors.TRUEWHITE_BG);
        Resource.usersUsernameLabel.setStyle(Colors.BLACK_TEXT);
        Resource.usersUsernameListView.setStyle(Colors.TRUEWHITE_BG);
        Resource.tablesSelectionLabel.setStyle(Colors.BLACK_TEXT);
        Resource.tablesSelectionListView.setStyle(Colors.TRUEWHITE_BG);
        Resource.usersIPSelectionLabel.setStyle(Colors.BLACK_TEXT);
        Resource.usersIPSelectionListView.setStyle(Colors.TRUEWHITE_BG);
        Resource.usersUsernameSelectionLabel.setStyle(Colors.BLACK_TEXT);
        Resource.usersUsernameSelectionListView.setStyle(Colors.TRUEWHITE_BG);
        Resource.menuMainContainer.setStyle(Colors.BEIGE_BG);
    }

    /**
     * Štartna funkcija aplikacije
     *
     * @param primaryStage Primary StageU
     */
    @Override
    public void start(Stage primaryStage) {
        // Inicializacija
        initApp();
        // Meni
        Resource.menuBar = new MenuBar();
        // Izbira nastavitve
        Resource.title_img = new ImageView(Texts.WHITE_LOGO_URL);
        Resource.title_img.setFitHeight(25);
        Resource.title_img.setFitWidth(50);
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
        Resource.darkThemeMenuItem.setOnAction((event) -> {
            setToDarkTheme();
            theme = Texts.DARK;
            updateConfig();
        });
        Resource.lightThemeMenuItem = new MenuItem(Texts.LIGHT_THEME);
        Resource.lightThemeMenuItem.setOnAction((event) -> {
            setToLightTheme();
            theme = Texts.LIGHT;
            updateConfig();
        });
        //## Jezik aplikacije
        Resource.languageMenu = new Menu(Texts.APPLICATION_LANGUAGE);
        //### Jeziki aplikacije
        Resource.enLanguageMenuItem = new MenuItem(Texts.EN_LANGUAGE);
        Resource.enLanguageMenuItem.setOnAction((event) -> {
            language = Texts.EN;
            updateConfig();
        });
        Resource.slLanguageMenuItem = new MenuItem(Texts.SL_LANGUAGE);
        Resource.slLanguageMenuItem.setOnAction((event) -> {
            language = Texts.SL;
            updateConfig();
        });
        Resource.deLanguageMenuItem = new MenuItem(Texts.DE_LANGUAGE);
        Resource.deLanguageMenuItem.setOnAction((event) -> {
            language = Texts.DE;
            updateConfig();
        });
        Resource.jpLanguageMenuItem = new MenuItem(Texts.JP_LANGUAGE);
        Resource.jpLanguageMenuItem.setOnAction((event) -> {
            language = Texts.JP;
            updateConfig();
        });
        // Dodajanje elementov v meni
        Resource.menu.getItems().addAll(Resource.exitMenuItem, Resource.settingsMenu);
        Resource.settingsMenu.getItems().addAll(Resource.themeMenu, Resource.languageMenu);
        Resource.themeMenu.getItems().addAll(Resource.darkThemeMenuItem, Resource.lightThemeMenuItem);
        Resource.languageMenu.getItems().addAll(Resource.enLanguageMenuItem, Resource.slLanguageMenuItem,
                Resource.deLanguageMenuItem, Resource.jpLanguageMenuItem);
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
        Tooltip.install(Resource.connectButton, new Tooltip(Texts.CONNECT_TOOLTIP));
        //## Prekinitev povezave
        Resource.disconnectButton = new Button(Texts.DISCONNECT);
        Resource.disconnectButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.disconnectButton);
        Tooltip.install(Resource.disconnectButton, new Tooltip(Texts.DISCONNECT_TOOLTIP));
        //## Povezava po meri
        Resource.customConnectButton = new Button(Texts.CUSTOM_CONNECT);
        setButtonProperties_GrayBold(Resource.customConnectButton);
        Resource.customConnectButton.setMinWidth(130);
        Tooltip.install(Resource.customConnectButton, new Tooltip(Texts.CUSTOM_CONNECT_TOOLTIP));
        //# Handler-ji za gumbe
        Resource.connectButton.setOnAction((event) -> {
            // Vzpostavi povezavo z sql strežnikom
            Resource.connected = backend.connect(Resource.username, Resource.password, Resource.serverIp,
                    Resource.serverPort, Resource.database);
            // Če je povezava vzpostavljena, sprejema podatke
            if (Resource.connected) {
                Thread thread = new Thread(() -> {
                    // Spreminjanje stanja indikatorja
                    Platform.runLater(() -> changeIndicatorState(1));
                    // Polnjenje tabel
                    final String[][] tables = new String[1][];
                    final String[][] usersIP = new String[1][];
                    final String[][] usersUsername = new String[1][];
                    Thread work = new Thread(() -> {
                        tables[0] = backend.getAllTablesCurrentDatabase();
                        usersIP[0] = backend.getAllUsersIP();
                        usersUsername[0] = backend.getAllUsersName();
                    });
                    work.start();
                    try {
                        // Počakamo, da se delo konča
                        work.join();
                        Platform.runLater(() -> {
                            // Polnjenje tabel
                            populateListViewWith(Resource.tablesListView, tables[0]);
                            populateListViewWith(Resource.usersIPListView, usersIP[0]);
                            populateListViewWith(Resource.usersUsernameListView, usersUsername[0]);
                            // Spreminjanje stanja indikatorja
                            changeIndicatorState(2);
                        });
                        JOptionPane.showMessageDialog(Resource.jFrame, Texts.CONNECTION_SUCCESSFUL, null,
                                JOptionPane.INFORMATION_MESSAGE);
                    } catch (InterruptedException ignored) {
                    }
                });
                thread.start();
            } else {
                JOptionPane.showMessageDialog(Resource.jFrame, Texts.CONNECTION_ERROR, null,
                        JOptionPane.ERROR_MESSAGE);
            }
        });
        Resource.disconnectButton.setOnAction((event) -> {
            // Prekine povezavo z sql strežnikom
            Resource.connected = !backend.disconnect();
            // Spreminjanje stanja gumba
            if (!Resource.connected) {
                Platform.runLater(() -> {
                    Resource.tablesListView.setItems(null);
                    Resource.usersIPListView.setItems(null);
                    Resource.usersUsernameListView.setItems(null);
                    resetAllFocus();
                    clearSelection();
                    changeIndicatorState(0);
                });
            }
        });
        Resource.customConnectButton.setOnAction((event) -> {
            String username = Texts.EMPTY;
            String password = Texts.EMPTY;
            String ip = Texts.EMPTY;
            String port = Texts.EMPTY;
            String database = Texts.EMPTY;
            while (username.equals(Texts.EMPTY)) {
                username = JOptionPane.showInputDialog(Resource.jFrame, Texts.USERNAME_PROMPT,
                        Texts.CUSTOM_CONNECTION_PROMPT_TITLE, JOptionPane.PLAIN_MESSAGE);
                if (username == null) return;
            }
            while (password.equals(Texts.EMPTY)) {
                password = JOptionPane.showInputDialog(Resource.jFrame, Texts.PASSWORD_PROMPT,
                        Texts.CUSTOM_CONNECTION_PROMPT_TITLE, JOptionPane.PLAIN_MESSAGE);
                if (password == null) return;
            }
            while (ip.equals(Texts.EMPTY)) {
                ip = JOptionPane.showInputDialog(Resource.jFrame, Texts.IP_PROMOT,
                        Texts.CUSTOM_CONNECTION_PROMPT_TITLE, JOptionPane.PLAIN_MESSAGE);
                if (ip == null) return;
            }
            while (port.equals(Texts.EMPTY)) {
                port = JOptionPane.showInputDialog(Resource.jFrame, Texts.PORT_PROMPT,
                        Texts.CUSTOM_CONNECTION_PROMPT_TITLE, JOptionPane.PLAIN_MESSAGE);
                if (port == null) return;
            }
            while (database.equals(Texts.EMPTY)) {
                database = JOptionPane.showInputDialog(Resource.jFrame, Texts.DATABASE_PROMPT,
                        Texts.CUSTOM_CONNECTION_PROMPT_TITLE, JOptionPane.PLAIN_MESSAGE);
                if (database == null) return;
            }
            Resource.usernameValueLabel.setText(username);
            Resource.serverIPValueLabel.setText(ip);
            Resource.serverPortValueLabel.setText(port);
            Resource.databaseValueLabel.setText(database);
            Resource.username = username;
            Resource.password = password;
            Resource.serverIp = ip;
            Resource.serverPort = port;
            Resource.database = database;
        });
        //# GridPane povezava info
        Resource.connectInfoPane = new GridPane();
        //## Naslov
        Resource.connectionInfoTitleLabel = new Label(Texts.SERVER_INFO);
        Resource.connectionInfoTitleLabel.setPadding(new Insets(0, 10, 10, 0));
        Resource.connectionIndicatorCircle = new Circle(10);
        changeIndicatorState(0);
        Tooltip.install(Resource.connectionIndicatorCircle, new Tooltip(Texts.CONNECTION_STATUS));
        Resource.connectionInfoContainer = new HBox();
        Resource.connectionInfoContainer.getChildren().addAll(Resource.connectionInfoTitleLabel,
                Resource.connectionIndicatorCircle);
        //## Informacije o tabeli
        Resource.connectInfoPane.setVgap(10);
        Resource.connectInfoPane.setHgap(10);
        Resource.connectInfoPane.setPadding(new Insets(10));
        //### Uporabnik
        Resource.usernameLabel = new Label(Texts.SERVER_USER);
        Resource.usernameValueLabel = new Label(Texts.DEFAULT_USERNAME);
        setLabelProperties_InfoLabel(Resource.usernameValueLabel);
        //### IP naslov strežnika
        Resource.serverIPLabel = new Label(Texts.SERVER_IP);
        Resource.serverIPValueLabel = new Label(Texts.DEFAULT_IP);
        setLabelProperties_InfoLabel(Resource.serverIPValueLabel);
        //### Vrata strežnika
        Resource.serverPortLabel = new Label(Texts.SERVER_PORT);
        Resource.serverPortValueLabel = new Label(Texts.DEFAULT_PORT);
        setLabelProperties_InfoLabel(Resource.serverPortValueLabel);
        //### Podatkovna baza strežnika
        Resource.databaseLabel = new Label(Texts.SERVER_DATABASE);
        Resource.databaseValueLabel = new Label(Texts.DEFAULT_DATABASE);
        setLabelProperties_InfoLabel(Resource.databaseValueLabel);
        // Setting up the default values
        Resource.username = Texts.DEFAULT_USERNAME;
        Resource.password = Texts.DEFAULT_PASSWORD;
        Resource.serverIp = Texts.DEFAULT_IP;
        Resource.serverPort = Texts.DEFAULT_PORT;
        Resource.database = Texts.DEFAULT_DATABASE;
        //# GridPane konfiguracija
        Resource.configPane = new GridPane();
        Resource.configPane.setVgap(11);
        Resource.configPane.setHgap(11);
        Resource.configPane.setPadding(new Insets(10));
        //## Naloži konfiguracijo
        Resource.loadConfigButton = new Button(Texts.LOAD_CONFIG);
        Resource.loadConfigButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.loadConfigButton);
        Tooltip.install(Resource.loadConfigButton, new Tooltip(Texts.LOAD_CONFIG_TOOLTIP));
        //## Shrani konfiguracijo
        Resource.saveConfigButton = new Button(Texts.SAVE_CONFIG);
        Resource.saveConfigButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.saveConfigButton);
        Tooltip.install(Resource.saveConfigButton, new Tooltip(Texts.SAVE_CONFIG_TOOLTIP));
        //## Pomoč
        Resource.helpButton = new Button(Texts.HELP);
        Resource.helpButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.helpButton);
        Tooltip.install(Resource.helpButton, new Tooltip(Texts.HELP_TOOLTIP));
        //## Počisti izbiro
        Resource.clearButton = new Button(Texts.CLEAR_SELECTION);
        Resource.clearButton.setMinWidth(130);
        setButtonProperties_GrayBold(Resource.clearButton);
        Tooltip.install(Resource.clearButton, new Tooltip(Texts.CLEAR_TOOLTIP));
        //# GridPane Tabela Info
        Resource.infoPane = new GridPane();
        Resource.infoPane.setPadding(new Insets(0, 10, 0, 0));
        //## Naslov
        Resource.infoTitleLabel = new Label(Texts.TABLE_INFO);
        Resource.infoTitleLabel.setPadding(new Insets(10));
        //## Informacije o tabeli
        // Avtor Tabele
        Resource.creationAuthorLabel = new Label(Texts.TABLE_CREATOR);
        Resource.creationAuthorLabel.setPadding(new Insets(10));
        Resource.creationAuthorValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.creationAuthorValueLabel);
        // Datum nastanka
        Resource.creationDateLabel = new Label(Texts.TABLE_CREATION_DATE);
        Resource.creationDateLabel.setPadding(new Insets(10));
        Resource.creationDateValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.creationDateValueLabel);
        // Avtor zaprtja
        Resource.deletionAuthorLabel = new Label(Texts.TABLE_DELETOR);
        Resource.deletionAuthorLabel.setPadding(new Insets(10));
        Resource.deletionAuthorValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.deletionAuthorValueLabel);
        // Datum zaprtja
        Resource.deletionDateLabel = new Label(Texts.TABLE_DELETION_DATE);
        Resource.deletionDateLabel.setPadding(new Insets(10));
        Resource.deletionDateValueLabel = new Label(Texts.NA);
        setLabelProperties_InfoLabel(Resource.deletionDateValueLabel);
        //# Handler-ji za gumbe
        Resource.loadConfigButton.setOnAction((event) -> {
            // Naloži podatke o povezavi iz datoteke
            // Prikaže okno za odpiranje datoteke
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(Texts.FILE_TYPE_NAME, Texts.FILE_TYPE_EXTENSION);
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new File(Texts.ASI_FX_DIRECTORY + Texts.CONFIG_FOLDER_NAME));
            File file = fileChooser.showOpenDialog(primaryStage);
            // Naloži datoteko
            if (file != null) loadConfig(file);
        });
        Resource.saveConfigButton.setOnAction((event) -> {
            // Shrani podatke o povezavi v datoteko
            // Prikaže okno za shranjevanje datoteke
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(Texts.FILE_TYPE_NAME, Texts.FILE_TYPE_EXTENSION);
            fileChooser.getExtensionFilters().add(extFilter);
            fileChooser.setInitialDirectory(new File(Texts.ASI_FX_DIRECTORY + Texts.CONFIG_FOLDER_NAME));
            File file = fileChooser.showSaveDialog(primaryStage);
            // Shrani datoteko
            if (file != null) saveConfig(file);
        });
        Resource.helpButton.setOnAction((event) -> {
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                try {
                    Desktop.getDesktop().browse(new URI(Texts.HELP_URL));
                } catch (IOException | URISyntaxException ignored) {
                }
        });
        Resource.clearButton.setOnAction((event) -> {
            resetAllFocus();
            clearSelection();
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
        Tooltip.install(Resource.insertionLabel, new Tooltip(Texts.INSERTION_TOOLTIP));
        Tooltip.install(Resource.insertionUsernameLabel, new Tooltip(Texts.INSERTION_TOOLTIP));
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
        Tooltip.install(Resource.viewLabel, new Tooltip(Texts.VIEW_TOOLTIP));
        Tooltip.install(Resource.viewUsernameLabel, new Tooltip(Texts.VIEW_TOOLTIP));
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
        Tooltip.install(Resource.updateLabel, new Tooltip(Texts.UPDATE_TOOLTIP));
        Tooltip.install(Resource.updateUsernameLabel, new Tooltip(Texts.UPDATE_TOOLTIP));
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
        Tooltip.install(Resource.deletionLabel, new Tooltip(Texts.DELETION_TOOLTIP));
        Tooltip.install(Resource.deletionUsernameLabel, new Tooltip(Texts.DELETION_TOOLTIP));
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
        Resource.tablesListView.getSelectionModel().selectedItemProperty().addListener((ignored) -> {
            // Izpiše vse uporabnike izbrane tabele
            String selectedTable = Resource.tablesListView.getSelectionModel().getSelectedItem();
            Resource.selectedTable = selectedTable;
            clearSelection();
            if (selectedTable != null)
                Platform.runLater(() -> {
                    setTableInfo(Resource.selectedTable);
                    populateListViewWith(Resource.usersUsernameSelectionListView, backend.getAllUsersName(Resource.selectedTable));
                    populateListViewWith(Resource.usersIPSelectionListView, backend.getAllUsersIP(Resource.selectedTable));
                });
        });
        Resource.usersIPListView.getSelectionModel().selectedItemProperty().addListener((ignored) -> {
            // Izpiše vse akcije izbranega uporabnika
            String selectedUserIP = Resource.usersIPListView.getSelectionModel().getSelectedItem();
            Resource.selectedIp = selectedUserIP;
            clearSelection();
            if (Resource.selectedIp != null)
                Platform.runLater(() -> {
                    // TODO: Napolni tabelo tabel izbranega uporabnika (IP)
                });
        });
        Resource.usersUsernameListView.getSelectionModel().selectedItemProperty().addListener((ignored) -> {
            // Izpiše vse akcije izbranega uporabnika
            String selectedUserUsername = Resource.usersUsernameListView.getSelectionModel().getSelectedItem();
            // TODO: Uporaba prave metode
            if (selectedUserUsername != null) {
                Resource.selectedIp = backend.nameToIP(selectedUserUsername);
                clearSelection();
                if (Resource.selectedIp != null)
                    Platform.runLater(() -> {
                        // TODO: Napolni tabelo tabel izbranega uporabnika (Username)
                    });
            }
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
        //# Handler-ji za ListView-je
        Resource.tablesSelectionListView.getSelectionModel().selectedItemProperty().addListener((ignored) -> {
            if (Resource.selectedIp != null) {
                String selectedTable = Resource.tablesSelectionListView.getSelectionModel().getSelectedItem();
                if (selectedTable != null) {
                    setTableInfo(selectedTable);
                    setTableAuthorToLabels(Resource.selectedIp, selectedTable);
                    populateTableViewWith(Resource.insertionTableView,
                            backend.getDataInsert(Resource.selectedIp, selectedTable));
                    populateTableViewWith(Resource.deletionTableView,
                            backend.getDataDelete(Resource.selectedIp, selectedTable));
                    populateTableViewWith(Resource.updateTableView,
                            backend.getDataUpdate(Resource.selectedIp, selectedTable));
                    populateTableViewWith(Resource.viewTableView,
                            backend.getDataSelect(Resource.selectedIp, selectedTable));
                }
            }
        });
        Resource.usersIPSelectionListView.getSelectionModel().selectedItemProperty().addListener((ignored) -> {
            if (Resource.selectedTable != null) {
                String selectedIP = Resource.usersIPSelectionListView.getSelectionModel().getSelectedItem();
                if (selectedIP != null) {
                    setTableAuthorToLabels(selectedIP, Resource.selectedTable);
                    populateTableViewWith(Resource.insertionTableView,
                            backend.getDataInsert(selectedIP, Resource.selectedTable));
                    populateTableViewWith(Resource.deletionTableView,
                            backend.getDataDelete(selectedIP, Resource.selectedTable));
                    populateTableViewWith(Resource.updateTableView,
                            backend.getDataUpdate(selectedIP, Resource.selectedTable));
                    populateTableViewWith(Resource.viewTableView,
                            backend.getDataSelect(selectedIP, Resource.selectedTable));
                }
            }
        });
        Resource.usersUsernameSelectionListView.getSelectionModel().selectedItemProperty().addListener((ignored) -> {
            if (Resource.selectedTable != null) {
                String selectedUsername = Resource.usersUsernameSelectionListView.getSelectionModel().getSelectedItem();
                if (selectedUsername != null) {
                    String selectedIP = backend.nameToIP(selectedUsername);
                    if (selectedIP != null) {
                        setTableAuthorToLabels(selectedUsername, Resource.selectedTable);
                        populateTableViewWith(Resource.insertionTableView,
                                backend.getDataInsert(selectedIP, Resource.selectedTable));
                        populateTableViewWith(Resource.deletionTableView,
                                backend.getDataDelete(selectedIP, Resource.selectedTable));
                        populateTableViewWith(Resource.updateTableView,
                                backend.getDataUpdate(selectedIP, Resource.selectedTable));
                        populateTableViewWith(Resource.viewTableView,
                                backend.getDataSelect(selectedIP, Resource.selectedTable));
                    }
                }
            }
        });

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
        Resource.connectInfoPane.add(Resource.connectionInfoContainer, 0, 0, 2, 1);
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
        Resource.infoPane.add(Resource.creationAuthorLabel, 0, 1, 1, 1);
        Resource.infoPane.add(Resource.creationAuthorValueLabel, 1, 1, 1, 1);
        Resource.infoPane.add(Resource.creationDateLabel, 0, 2, 1, 1);
        Resource.infoPane.add(Resource.creationDateValueLabel, 1, 2, 1, 1);
        Resource.infoPane.add(Resource.deletionAuthorLabel, 0, 3, 1, 1);
        Resource.infoPane.add(Resource.deletionAuthorValueLabel, 1, 3, 1, 1);
        Resource.infoPane.add(Resource.deletionDateLabel, 0, 4, 1, 1);
        Resource.infoPane.add(Resource.deletionDateValueLabel, 1, 4, 1, 1);

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
        if (theme.equals(Texts.LIGHT)) setToLightTheme();
        else setToDarkTheme();
        // Ostale nastavitve, okno po meri
        Scene mainScene = new Scene(Resource.menuMainContainer);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setMaxHeight(Resource.lowerPane.getHeight() + Resource.upperPane.getHeight());
        primaryStage.setMinHeight(Resource.lowerPane.getHeight() + Resource.upperPane.getHeight());
        primaryStage.setMaxWidth(Resource.lowerPane.getWidth() + Resource.rightSuperPane.getWidth());
        primaryStage.setMinWidth(Resource.lowerPane.getWidth() + Resource.rightSuperPane.getWidth());

        // Spremenljivke
        Resource.connected = false;
    }
}

/**
 * Razred vsebuje barve uporabljene v aplikaciji
 */
class Colors {
    final static String TRUEBLACK_BG = "-fx-background-color: rgb(10, 10, 10);";
    final static String BLACK_BG = "-fx-background-color: rgb(43, 43, 45);";
    final static String GRAY_BG = "-fx-background-color: rgb(60, 63, 65);";
    final static String LIGHTGRAY_BASE = "-fx-base: rgb(180, 180, 180);";
    final static String WHITE_TEXT = "-fx-text-fill: rgb(250, 250, 250);";
    final static String WHITE_BG_LINEARGRADIENT = "-fx-background-color: rgb(255, 255, 255)," +
            " linear-gradient(to bottom, #e3e3e3 0%, #cccccc 100%);";
    final static String BEIGE_BG = "-fx-background-color: rgb(253,242,211);";
    final static String WHITE_BG = "-fx-background-color: rgb(250, 250, 250);";
    final static String TRUEWHITE_BG = "-fx-background-color: rgb(255, 255, 255);";
    final static String LIGHTGRAY_BG = "-fx-background-color: rgb(195, 195, 195);";
    final static String BLACK_TEXT = "-fx-text-fill: rgb(10, 10, 10);";
    final static String RED = "#d23c28";
    final static String YELLOW = "#cdbb44";
    final static String GREEN = "#419941";
}

/**
 * Razred vsebuje aplikacijska besedila
 */
class Texts {
    static String NA;
    static String NO_DATA_SELECTED;
    static String CONNECT;
    static String DISCONNECT;
    static String CUSTOM_CONNECT;
    static String SERVER_INFO;
    static String SERVER_USER;
    static String SERVER_IP;
    static String SERVER_PORT;
    static String SERVER_DATABASE;
    static String LOAD_CONFIG;
    static String SAVE_CONFIG;
    static String HELP;
    static String CLEAR_SELECTION;
    static String TABLE_INFO;
    static String TABLE_CREATOR;
    static String TABLE_DELETOR;
    static String TABLE_CREATION_DATE;
    static String TABLE_DELETION_DATE;
    static String INSERTIONS;
    static String UPDATES;
    static String VIEWS;
    static String DELETIONS;
    static String ALL_TABLES;
    static String ALL_USERS_IP;
    static String ALL_USERS_USERNAME;
    static String SELECTED_TABLES;
    static String SELECTED_USERS_IP;
    static String SELECTED_USERS_USERNAME;
    static String EXIT;
    static String SETTINGS;
    static String APLICCATION_THEME;
    static String DARK_THEME;
    static String LIGHT_THEME;
    static String APPLICATION_LANGUAGE;
    static String SL_LANGUAGE;
    static String EN_LANGUAGE;
    final static String DE = "de";
    final static String JP = "jp";
    static String CUSTOM_CONNECTION_PROMPT_TITLE;
    static String USERNAME_PROMPT;
    static String PASSWORD_PROMPT;
    static String IP_PROMOT;
    static String PORT_PROMPT;
    static String DATABASE_PROMPT;
    static String CONNECTION_STATUS;
    static String INSERTION_TOOLTIP;
    static String DELETION_TOOLTIP;
    static String UPDATE_TOOLTIP;
    static String VIEW_TOOLTIP;
    static String CONNECT_TOOLTIP;
    static String DISCONNECT_TOOLTIP;
    static String CUSTOM_CONNECT_TOOLTIP;
    static String LOAD_CONFIG_TOOLTIP;
    static String SAVE_CONFIG_TOOLTIP;
    static String HELP_TOOLTIP;
    static String CLEAR_TOOLTIP;
    static String SAVE_CONFIG_PROMPT;
    static String LOAD_CONFIG_PROMPT;
    static String FILE_ERROR;
    static String CONNECTION_ERROR;
    static String CONNECTION_SUCCESSFUL;
    static String HELP_URL;
    static String DATE_AND_TIME;
    static String CONTENT;
    // Default server values
    final static String EN = "en";
    final static String SL = "sl";
    final static String CONFIG_FILE_NAME = "app.config";
    final static String SL_LANGUAGE_PACK_PATH = "language_packs/sl_app.config";
    final static String DARK = "dark";
    final static String LIGHT = "light";
    final static String DEFAULT_USERNAME = "remote";
    final static String DEFAULT_PASSWORD = "remote";
    final static String DEFAULT_IP = "193.2.190.23";
    final static String DEFAULT_PORT = "3306";
    final static String DEFAULT_DATABASE = "remote11";
    final static String ASI_FX_DIRECTORY = "Asi-FX/";
    final static String WHITE_LOGO_URL = "images/database_logo_white.png";
    final static String BLACK_LOGO_URL = "images/database_logo_black.png";
    final static String CONFIG_FOLDER_NAME = "config";
    final static String EN_LANGUAGE_PACK_PATH = "language_packs/en_app.config";
    final static String DE_LANGUAGE_PACK_PATH = "language_packs/de_app.config";
    final static String JP_LNAGUAGE_PACK_PATH = "language_packs/jp_app.config";
    final static String SL_LANGUAGE_PACKB_PATH = "language_packs/sl_backend.cfg";
    final static String EN_LANGUAGE_PACKB_PATH = "language_packs/en_backend.cfg";
    final static String DE_LANGUAGE_PACKB_PATH = "language_packs/de_backend.cfg";
    final static String JP_LNAGUAGE_PACKB_PATH = "language_packs/jp_backend.cfg";
    static String DE_LANGUAGE;
    static String JP_LANGUAGE;
    static String FILE_TYPE_NAME;
    static String FILE_TYPE_EXTENSION;
    static String EMPTY;
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
    // Values
    static String username;
    static String password;
    static String serverIp;
    static String serverPort;
    static String database;
    static boolean connected;
    static String selectedIp;
    static String selectedTable;
    // Components
    static MenuBar menuBar;
    static ImageView title_img;
    static Menu menu;
    static MenuItem exitMenuItem;
    static Menu settingsMenu;
    static Menu themeMenu;
    static MenuItem darkThemeMenuItem;
    static MenuItem lightThemeMenuItem;
    static Menu languageMenu;
    static MenuItem slLanguageMenuItem;
    static MenuItem enLanguageMenuItem;
    static MenuItem deLanguageMenuItem;
    static MenuItem jpLanguageMenuItem;
    static GridPane mainPane;
    static GridPane upperPane;
    static GridPane connectPane;
    static Button connectButton;
    static Button disconnectButton;
    static Button customConnectButton;
    static GridPane connectInfoPane;
    static Label connectionInfoTitleLabel;
    static Circle connectionIndicatorCircle;
    static HBox connectionInfoContainer;
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
    static Label creationAuthorLabel;
    static Label creationAuthorValueLabel;
    static Label creationDateLabel;
    static Label creationDateValueLabel;
    static Label deletionAuthorLabel;
    static Label deletionAuthorValueLabel;
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
    static JFrame jFrame;
}
