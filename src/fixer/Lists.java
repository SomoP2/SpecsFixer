package fixer;

import java.util.ArrayList;

import javafx.scene.control.RadioMenuItem;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public interface Lists {
	static ArrayList<String> copyTop = new ArrayList<>();
	static ArrayList<String> copyBottom = new ArrayList<>();
	static ArrayList<RadioMenuItem> selectParamMenu = new ArrayList<>();
	static ArrayList<RadioMenuItem> selectSearchRangeMenu = new ArrayList<>();
	static ArrayList<RadioMenuItem> selectSearchSpecMenu = new ArrayList<>();
	static ArrayList<String> selectParamFile = new ArrayList<>();
	static ArrayList<String> selectSearchRangeFile = new ArrayList<>();
	static ArrayList<String> selectSpecFile = new ArrayList<>();
	Text topText = new Text("Please select Parameters");
	static String[] selectedSpec = new String[1];
	static String[] keyword = new String[1];
	int to[] = new int[1];
	Circle paramCheck = new Circle(5);
	Circle rangeCheck = new Circle(5);
	Circle searchCheck = new Circle(5);

}
