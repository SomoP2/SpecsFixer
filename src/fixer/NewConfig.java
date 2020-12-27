package fixer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class NewConfig {
	//private static int index = 0;
	
	private static ArrayList<TextField> tflist = new ArrayList<>();
	private static TextField giveName = new TextField();
	private static TextField tf = new TextField();
	private static Button add = new Button("+");
	private static Button ok = new Button("Ok");
	private static Button cancel = new Button("Cancel");
	private static HBox hb = new HBox();
	private static HBox buttons = new HBox();
	private static VBox vb = new VBox();
	private static HBox top = new HBox();
	private static HBox bottom = new HBox();
	private static HBox box = new HBox();
	private static Scene sc = new Scene(box);
	private static String watconfig;
	private static Stage st = new Stage(StageStyle.UTILITY);

	private static void okbutton() {
		// TODO Auto-generated method stub
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(watconfig + "Conf\\" + giveName.getText() + watconfig), StandardCharsets.UTF_8))) {
			for (int i = 0; i < tflist.size(); i++) {
				bw.write(tflist.get(i).getText());
				bw.newLine();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
		st.close();

	}

	private static void cancelbutton() {
		// TODO Auto-generated method stub
		st.close();
	}

	private static void addbutton() {
		// TODO Auto-generated method stub
		TextField tf2 = new TextField();
		tf2.setPromptText("Add parameter:");
		tf2.setFocusTraversable(false);
		HBox hb2 = new HBox();
		hb2.getChildren().addAll(tf2, add);
		vb.getChildren().add(hb2);
		tflist.add(tf2);
	}

	public static void newConfig(String configselect) {
		watconfig = configselect;
		giveName.setPromptText("Give name:");
		giveName.setFocusTraversable(false);

		tf.setPromptText("Add parameter:");
		tf.setFocusTraversable(false);
		tflist.add(tf);
		hb.getChildren().addAll(tf, add);

		buttons.setSpacing(30);
		buttons.setAlignment(Pos.BOTTOM_CENTER);
		buttons.getChildren().addAll(ok, cancel);

		vb.getChildren().addAll(giveName, hb);
		top.getChildren().addAll(vb);
		top.setAlignment(Pos.TOP_LEFT);

		bottom.getChildren().add(buttons);
//		bottom.setAlignment(Pos.BOTTOM_CENTER);

		box.getChildren().addAll(top, bottom);

		st.setScene(sc);
		st.setResizable(true);
		st.setWidth(320);
		st.setHeight(240);
		st.setTitle("SpecsFixer");
		st.isAlwaysOnTop();
		st.show();

		add.setOnAction(e -> {
			addbutton();
		});
		add.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				addbutton();
			}
		});

		cancel.setOnAction(e -> {
			cancelbutton();
		});
		cancel.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				cancelbutton();
			}
		});

		ok.setOnAction(e -> {
			okbutton();
		});
		ok.setOnKeyPressed(e -> {
			KeyCode keyCode = e.getCode();
			if (keyCode.equals(KeyCode.ENTER)) {
				okbutton();
			}
		});

	}

}
