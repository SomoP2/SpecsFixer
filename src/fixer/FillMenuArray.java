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

import javafx.scene.control.RadioMenuItem;

public class FillMenuArray implements Lists {

	private static int from;
	private static int to;

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

	public static int selectRange(ArrayList<String> inlist, ArrayList<RadioMenuItem> inmenulist, int fromTo) {
		int outint = 0;

		for (int i = 0; i < inlist.size(); i++) {
			int index = i;
			try (BufferedReader br = new BufferedReader(new FileReader("SearchRangeConf\\" + inlist.get(index)))) {
				// toptext

				ArrayList<String> text = new ArrayList<>();
				String line;
				while ((line = br.readLine()) != null) {
					text.add(line);
				}
				from = Integer.parseInt(text.get(0));
				to = Integer.parseInt(text.get(1));

			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if (fromTo == 0) {
				outint = from;
			} else if (fromTo == 1) {
				outint = to;
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
