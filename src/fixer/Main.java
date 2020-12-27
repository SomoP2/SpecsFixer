package fixer;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
public class Main extends Application implements Lists{

	static Text bottomText = new Text("Empty");
	static Label label = new Label("Set url below:");
	static TextField urlin = new TextField();
	static Menu newItem = new Menu("New");
	static Menu selectItem = new Menu("Select");
	static RadioMenuItem newParam = new RadioMenuItem("New Param"); // make new parameters, eg, vga,cpu,motherboard,ram
	static RadioMenuItem newSearchRange = new RadioMenuItem("New SearchRange"); // make new range, range for specific																			// page,
	static RadioMenuItem newSearchParameters = new RadioMenuItem("New SearchParameters"); // make new parameters, for eg
	// vga,
	// between 2
	static Menu selectParam = new Menu("Select Param");
	static Menu selectSearchRange = new Menu("Select SearchRange");
	static Menu selectSearchParameters = new Menu("Select SearchParameters");

	static MenuBar menubar = new MenuBar();
	
	static HBox top = new HBox();
	static HBox bottom = new HBox();
	static HBox buttonBox = new HBox();

	static Button next = new Button("Next");
	static Button copy = new Button("Copy");
	static Button reset = new Button("reload select");

	static VBox vBox = new VBox();
	static Scene main = new Scene(vBox);

	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage primaryStage) throws Exception {
//checkcircles
		paramCheck.setFill(Color.RED);
		rangeCheck.setFill(Color.RED);
		searchCheck.setFill(Color.RED);
//top label
		label.setAlignment(Pos.CENTER_LEFT);
//url to get
		urlin.setPromptText("Set Url:");
		urlin.setFocusTraversable(false);
//selectparam menu
		MenuMethods.makeMenu(selectParam, selectParamMenu, selectParamFile, "paramconf");
		newParam.setOnAction(e -> {
			String param = "Param";
			NewConfig.newConfig(param);
		});
//searchrange menu
		MenuMethods.makeMenu(selectSearchRange, selectSearchRangeMenu, selectSearchRangeFile, "SearchRangeConf");
		newSearchRange.setOnAction(e -> {
			String param = "SearchRange";
			NewConfig.newConfig(param);
		}); // lines
//searchparameters menu
		MenuMethods.makeMenu(selectSearchParameters, selectSearchSpecMenu, selectSpecFile, "SearchParametersConf");
		newSearchParameters.setOnAction(e -> {
			String param = "SearchParameters";
			NewConfig.newConfig(param);
		}); // nvidia-amd, 4gb-8gb-11gb,
//dropdown menus
		newItem.getItems().addAll(newParam, newSearchRange, newSearchParameters);
		selectItem.getItems().addAll(selectParam, selectSearchRange, selectSearchParameters);
//menubar
		menubar.getMenus().addAll(newItem, selectItem);
		menubar.autosize();

//topbox, contains selected parameters
		top.getChildren().add(topText);
		top.autosize();
		top.setPrefSize(240, 320);
		top.setStyle(/* "-fx-background-color: #9E909A;"+ */"-fx-border-color: #B77B82;" + "-fx-border-insets:5;"
				+ "-fx-border-width:3;");
//bottombox, contains read parameters from page
		bottom.getChildren().add(bottomText);
		bottom.autosize();
		bottom.setPrefSize(240, 320);
		bottom.setStyle(/* "-fx-background-color: #9E909A;"+ */"-fx-border-color: #8FB1CC;" + "-fx-border-insets:5;"
				+ "-fx-border-width:3;");

//buttons
		copy.setVisible(false);
		// copies parameters and the found parameters to an arraylist; and copies it to
		// a clipboard
		next.setOnAction(e -> {
			ButtonProps.next(urlin);
		});
		next.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				ButtonProps.next(urlin);
			}
		});
		copy.setOnAction(e -> {
			ButtonProps.copy();
		});
		copy.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				ButtonProps.copy();
			}
		});
		reset.setOnAction(e -> {
			ButtonProps.reset();
		});
		reset.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				ButtonProps.reset();
			}
			copy.setVisible(false);
		});
//box that contains the buttons
		buttonBox.getChildren().setAll(next, copy, reset, paramCheck, rangeCheck, searchCheck);
		buttonBox.setSpacing(50);
		buttonBox.setAlignment(Pos.CENTER);
//main vbox that contains everything
		vBox.getChildren().addAll(menubar, label, urlin, top, bottom, buttonBox);
		vBox.setSpacing(1);
//primary stage
		primaryStage.setResizable(true);
		primaryStage.setWidth(540);
		primaryStage.setHeight(570);
		primaryStage.setTitle("SpecsFixer");
		primaryStage.setScene(main);
		primaryStage.show();
		primaryStage.getIcons().add(new Image("icon.bmp"));

	}
}
