package fixer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.paint.Color;

public class MenuMethods implements Lists {

	public static void cleanMenu() {

		for (int i = 0; i < selectParamMenu.size() + 1; i++) {
			selectParamMenu.removeAll(selectParamMenu);
		}
		for (int i = 0; i < selectSearchRangeMenu.size(); i++) {
			selectSearchRangeMenu.removeAll(selectSearchRangeMenu);
		}
		for (int i = 0; i < selectSearchSpecMenu.size(); i++) {
			selectSearchSpecMenu.removeAll(selectSearchSpecMenu);
		}
		for (int i = 0; i < selectParamFile.size(); i++) {
			selectParamFile.removeAll(selectParamFile);
		}
		for (int i = 0; i < selectSearchRangeFile.size(); i++) {
			selectSearchRangeFile.removeAll(selectSearchRangeFile);
		}
		for (int i = 0; i < selectSpecFile.size(); i++) {
			selectSpecFile.removeAll(selectSpecFile);
		}
	}

	public static void makeMenu(Menu mainMenu, ArrayList<RadioMenuItem> selectMenu, ArrayList<String> selectFile,
			String conf) {
		for (int i = 0; i < MenuMethods.setselectlist(conf).size(); i++) {
			selectMenu.add(MenuMethods.setselectlist(conf).get(i));
		}
		for (int i = 0; i < MenuMethods.setselectlistStringName(conf).size(); i++) {
			selectFile.add(MenuMethods.setselectlistStringName(conf).get(i));
			System.out.println(selectFile.get(i));
		}
		for (int i = 0; i < selectMenu.size(); i++) {
			mainMenu.getItems().add(selectMenu.get(i));
		}
		if (conf.equals("SearchRangeConf")) {
			for (int i = 0; i < selectMenu.size(); i++) {
				selectMenu.get(i).setOnAction(e -> {
					keyword[0] = MenuMethods.selectRange(selectFile, selectMenu, 0);
					to[0] = Integer.parseInt(MenuMethods.selectRange(selectFile, selectMenu, 1));
					rangeCheck.setFill(Color.GREEN);
					System.out.println("from" + keyword[0]);
					System.out.println("to" + to[0]);

				});
			}
		} else if ((conf.equals("paramconf"))) {
			for (int i = 0; i < selectMenu.size(); i++) {
				int it = i;
				selectMenu.get(i).setOnAction(e -> {
					topText.setText(MenuMethods.toptext(selectFile, selectMenu, it));
					paramCheck.setFill(Color.GREEN);
					System.out.println(topText.getText());
				});
			}
		} else {
			for (int i = 0; i < selectSearchSpecMenu.size(); i++) {
				int it = i;
				selectMenu.get(i).setOnAction(e -> {
					selectedSpec[0] = selectFile.get(it);
					searchCheck.setFill(Color.GREEN);
					System.out.println(selectedSpec[0]);
				});
			}
		}

	}

	public static String toptext(ArrayList<String> inlist, ArrayList<RadioMenuItem> inmenulist, int it) {

		String textout = "";

		// br= new BufferedReader(new FileReader(selectParamList.get(i)));
		try (BufferedReader br = new BufferedReader(
				new InputStreamReader(new FileInputStream("ParamConf\\" + inlist.get(it)), StandardCharsets.UTF_8))) {

			// toptext

			String text = "";
			String line;
			while ((line = br.readLine()) != null) {
				text += line + System.lineSeparator();
				copyTop.add(line);
			}
			textout = text;
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		return textout;
	}

	public static String selectSpec(ArrayList<String> inlist, ArrayList<RadioMenuItem> inmenulist, int init) {
		return inlist.get(init);
	}

	public static String selectRange(ArrayList<String> inlist, ArrayList<RadioMenuItem> inmenulist, int fromTo) {
		String outint = "";

		for (int i = 0; i < inlist.size(); i++) {
			int index = i;
			try (BufferedReader br = new BufferedReader(new FileReader("SearchRangeConf\\" + inlist.get(index)))) {
				// toptext

				ArrayList<String> text = new ArrayList<>();
				String line;
				while ((line = br.readLine()) != null) {
					text.add(line);
				}
				keyword[0] = text.get(0);
				to[0] = Integer.parseInt(text.get(1));

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (fromTo == 0) {
				outint = keyword[0];
			} else if (fromTo == 1) {
				outint = Integer.toString(to[0]);
			}
		}
		return outint;
	}

	public static ArrayList<RadioMenuItem> setselectlist(String conf) {

		ArrayList<RadioMenuItem> asd = new ArrayList<>();
		File file = new File(conf);
		String[] pathnames;
		pathnames = file.list();

		for (String pathname : pathnames) {
			asd.add(new RadioMenuItem(pathname));
		}
		return asd;
	}

	public static ArrayList<String> setselectlistStringName(String conf) {
		ArrayList<String> asd = new ArrayList<>();
		File file = new File(conf);
		String[] pathnames;
		pathnames = file.list();
		for (String pathname : pathnames) {
			asd.add(pathname);
		}
		return asd;
	}
}
