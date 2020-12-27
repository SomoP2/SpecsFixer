package fixer;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class ButtonProps extends Main implements Lists {

	public static void next(TextField urlin) {
		// TODO Auto-generated method stub
		if (urlin.getText().isEmpty()) {
			new PopupScene("Set link to download page");

		} else if (!urlin.getText().contains("https://")) {
			new PopupScene("Set full link including: \"https://www.\" ");
		} else {
			PageGetter.download(urlin.getText());
			String txt = "";
			for (String str : FindSpecs.readPageFromTo(keyword[0], to[0], "SearchParametersConf\\" + selectedSpec[0])) {
				txt += str + System.lineSeparator();
				copyBottom.add(str);
			}
			bottomText.setText(txt);
			if (bottomText.getText().equals("")) {
				bottomText.setText("no matches found");
			}
			copy.setVisible(true);
		}
		// changes text of bottom box text, according to parameters
	}

	public static void copy() {
		String myString = "";

		for (int i = 0; i < copyTop.size(); i++) {
			myString += copyTop.get(i) + " " + copyBottom.get(i) + System.lineSeparator();
		}

		StringSelection stringSelection = new StringSelection(myString);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, null);
	}

	public static void reset() {
		MenuMethods.cleanMenu();
		topText.setText("Please select Parameters");
		bottomText.setText("empty");
		selectParam.getItems().clear();
		selectSearchRange.getItems().clear();
		selectSearchParameters.getItems().clear();
		MenuMethods.makeMenu(selectParam, selectParamMenu, selectParamFile, "paramconf");
		MenuMethods.makeMenu(selectSearchRange, selectSearchRangeMenu, selectSearchRangeFile, "SearchRangeConf");
		MenuMethods.makeMenu(selectSearchParameters, selectSearchSpecMenu, selectSpecFile, "SearchParametersConf");
		paramCheck.setFill(Color.RED);
		rangeCheck.setFill(Color.RED);
		searchCheck.setFill(Color.RED);
	}

}
