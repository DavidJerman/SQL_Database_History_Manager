import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
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

        // Zgornji GridPane
        GridPane upperPane = new GridPane();
        upperPane.setPadding(new Insets(10));
        //# GridPane povezava
        GridPane connectPane = new GridPane();
        // TODO: Gumb za povezavo, prekinitev povezave, povezavo po meri
        //# GridPane povezava info
        // TODO: Informacije o trenutni povezavi uporabnika
        //# GridPane konfiguracija
        GridPane configPane = new GridPane();
        configPane.setVgap(10);
        configPane.setHgap(10);
        configPane.setPadding(new Insets(10));
        Button loadConfigButton = new Button("Naloži konfiguracijo");
        loadConfigButton.setMinWidth(130);
        Button saveConfigButton = new Button("Shrani konfiguracijo");
        saveConfigButton.setMinWidth(130);
        Button helpButton = new Button("Pomoč");
        helpButton.setMinWidth(130);
        //# GridPane Tabela Info
        GridPane infoPane = new GridPane();
        //## Informacije o tabeli
        // Avtor Tabele
        Label authorLabel = new Label("Avtor:");
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

        });
        saveConfigButton.setOnAction((event) -> {

        });
        helpButton.setOnAction((event) -> {

        });

        // Spodnji GridPane
        GridPane lowerPane = new GridPane();
        lowerPane.setPadding(new Insets(10, 0, 10, 10));
        //# Vstavljanje GridPane
        GridPane insertionPane = new GridPane();
        insertionPane.setPadding(new Insets(10));
        Text insertionText = new Text("Vstavljanja:");
        VBox insertionTextBox = new VBox(insertionText);
        insertionTextBox.setPadding(new Insets(0, 0, 10, 0));
        TableView<String[]> insertionTableView = new TableView<>();
        insertionTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        insertionTableView.setMinWidth(350);
        insertionTableView.setMaxHeight(300);
        //# Ogledovanje GridPane
        GridPane viewPane = new GridPane();
        viewPane.setPadding(new Insets(10));
        Text viewText = new Text("Ogledovanja:");
        VBox viewTextBox = new VBox(viewText);
        viewTextBox.setPadding(new Insets(0, 0, 10, 0));
        TableView<String[]> viewTableView = new TableView<>();
        viewTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        viewTableView.setMinWidth(350);
        viewTableView.setMaxHeight(300);
        //# Ažuriranje GridPane
        GridPane updatePane = new GridPane();
        updatePane.setPadding(new Insets(10));
        Text updateText = new Text("Ažuriranja:");
        VBox updateTextBox = new VBox(updateText);
        updateTextBox.setPadding(new Insets(0, 0, 10, 0));
        TableView<String[]> updateTableView = new TableView<>();
        updateTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        updateTableView.setMinWidth(350);
        updateTableView.setMaxHeight(300);
        //# Brisanje GridPane
        GridPane deletionPane = new GridPane();
        deletionPane.setPadding(new Insets(10));
        Text deletionText = new Text("Brisanja:");
        VBox deletionTextBox = new VBox(deletionText);
        deletionTextBox.setPadding(new Insets(0, 0, 10, 0));
        TableView<String[]> deletionTableView = new TableView<>();
        deletionTableView.setPlaceholder(new Label("Podatki še niso bili izbrani."));
        deletionTableView.setMinWidth(350);
        deletionTableView.setMaxHeight(300);

        // Desni Grid Pane
        GridPane rightPane = new GridPane();
        rightPane.setPadding(new Insets(20, 20, 20, 10));
        //# Seznam tabel
        Text tablesText = new Text("Tabele:");
        VBox tablesTextBox = new VBox(tablesText);
        tablesTextBox.setPadding(new Insets(0, 0, 10, 0));
        ListView<String> tablesListView = new ListView<>();
        tablesListView.setMaxHeight(80);
        //# Seznam uporabnikov (IP)
        Text usersIPText = new Text("Uporabniki (IP):");
        VBox usersIPTextBox = new VBox(usersIPText);
        usersIPTextBox.setPadding(new Insets(10, 0, 10, 0));
        ListView<String> usersIPListView = new ListView<>();
        usersIPListView.setMaxHeight(300);
        //# Seznam uporabnikov (username)
        Text usersUsernameText = new Text("Uporabniki (uporabniško ime):");
        VBox usersUsernameTextBox = new VBox(usersUsernameText);
        usersUsernameTextBox.setPadding(new Insets(10, 0, 10, 0));
        ListView<String> usersUsernameListView = new ListView<>();
        usersUsernameListView.setMaxHeight(300);
        //# Handler-ji za ListView-je
        tablesListView.getSelectionModel().selectedItemProperty().addListener((event) -> {

        });
        usersIPListView.getSelectionModel().selectedItemProperty().addListener((event) -> {

        });
        usersUsernameListView.getSelectionModel().selectedItemProperty().addListener((event) -> {

        });

        // Dodajanje elementov v glavni GridPane
        mainPane.add(upperPane, 0, 0, 1, 1);
        mainPane.add(lowerPane, 0, 1, 1, 1);
        mainPane.add(rightPane, 1, 0, 1, 2);

        // Dodajanje elementov v zgornji GridPane
        upperPane.add(connectPane, 0, 0, 1, 1);
        upperPane.add(configPane, 1, 0, 1, 1);
        upperPane.add(infoPane, 2, 0, 1, 1);
        //# Dodajanje elementov v povezava GridPane
        // TODO: Dodajanje bodočih elementov
        //# Dodajanje elementov v povezava info GridPane
        // TODO: Dodajanje bodočih elementov
        //# Dodajanje elementov v konfiguracija GridPane
        configPane.add(loadConfigButton, 0, 0, 1, 1);
        configPane.add(saveConfigButton, 0, 1, 1, 1);
        configPane.add(helpButton, 0, 2, 1, 1);
        //# Dodajanje elementov v table info GridPane
        infoPane.add(authorLabel, 0, 0, 1, 1);
        infoPane.add(authorValueLabel, 1, 0, 1, 1);
        infoPane.add(creationDateLabel, 0, 1, 1, 1);
        infoPane.add(creationDateValueLabel, 1, 1, 1, 1);
        infoPane.add(deletionDateLabel, 0, 2, 1, 1);
        infoPane.add(deletionDateValueLabel, 1, 2, 1, 1);

        // Dodajanje elementov v spodnji GridPane
        lowerPane.add(insertionPane, 0, 0, 1, 1);
        lowerPane.add(updatePane, 1, 0, 1, 1);
        lowerPane.add(viewPane, 0, 1, 1, 1);
        lowerPane.add(deletionPane, 1, 1, 1, 1);
        //# Dodajanje elementov v vstavljanje GridPane
        insertionPane.add(insertionTextBox, 0, 0, 1, 1);
        insertionPane.add(insertionTableView, 0, 1, 1, 1);
        //# Dodajanje elementov v ažuriranje GridPane
        updatePane.add(updateTextBox, 0, 0, 1, 1);
        updatePane.add(updateTableView, 0, 1, 1, 1);
        //# Dodajanje elementov v ogledovanje GridPane
        viewPane.add(viewTextBox, 0, 0, 1, 1);
        viewPane.add(viewTableView, 0, 1, 1, 1);
        //# Dodajanje elementov v brisanje GridPane
        deletionPane.add(deletionTextBox, 0, 0, 1, 1);
        deletionPane.add(deletionTableView, 0, 1, 1, 1);

        // Dodajanje elementov v desni GridPane
        rightPane.add(tablesTextBox, 0, 0, 1, 1);
        rightPane.add(tablesListView, 0, 1, 1, 1);
        rightPane.add(usersIPTextBox, 0, 2, 1, 1);
        rightPane.add(usersIPListView, 0, 3, 1, 1);
        rightPane.add(usersUsernameTextBox, 0, 4, 1, 1);
        rightPane.add(usersUsernameListView, 0, 5, 1, 1);

        // Glavna scena
        Scene mainScene = new Scene(mainPane);
        primaryStage.setScene(mainScene);
        primaryStage.show();
        primaryStage.setMaxHeight(lowerPane.getHeight() + upperPane.getHeight());
        primaryStage.setMinHeight(lowerPane.getHeight() + upperPane.getHeight());
        primaryStage.setMaxWidth(lowerPane.getWidth() + rightPane.getWidth());
        primaryStage.setMinWidth(lowerPane.getWidth() + rightPane.getWidth());
    }
}
