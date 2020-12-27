package fixer;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PopupScene {
	private Button close = new Button();
	private VBox closebox = new VBox();
	private Scene popupscene = new Scene(closebox);
	private Stage popupwindow = new Stage(StageStyle.UNDECORATED);

	public PopupScene(String error) {
		// TODO Auto-generated constructor stub
		close.setText(error);

		close.setStyle("-fx-font-size:16; -fx-font-weight:bold;-fx-background-color: #ff0000; ");
		close.setPrefSize(650, 60);

		closebox.getChildren().add(close);
		closebox.setAlignment(Pos.CENTER);

		popupwindow.isAlwaysOnTop();
		popupwindow.centerOnScreen();
		popupwindow.setScene(popupscene);
		popupwindow.setHeight(60);
		popupwindow.setWidth(320);
		popupwindow.show();
		close.setOnAction(i -> {
			popupwindow.close();
			popupwindow.setAlwaysOnTop(true);
		});
		close.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				popupwindow.close();
			}
		});

	}

}
