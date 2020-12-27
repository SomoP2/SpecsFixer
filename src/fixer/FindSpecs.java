package fixer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindSpecs {

	private static LinkedHashSet<String> matchedspec = new LinkedHashSet<>(); // linkedhashset so if it's found multiple times															// its only saved once
	private static ArrayList<String> page = new ArrayList<>(); // full page for finding
	private static ArrayList<String> specs = new ArrayList<>();
	private static BufferedReader pagelist;
	private static BufferedReader speclist;

	public static LinkedHashSet<String> readPageFromTo(String keyword, int to, String spec) {
		try {

			pagelist = new BufferedReader(new FileReader("page.html"));
			speclist = new BufferedReader(new FileReader(spec));
			String pageline;
			while ((pageline = pagelist.readLine()) != null) {
				page.add(pageline);
			}
			String specline;
			while ((specline = speclist.readLine()) != null) {
				specs.add(specline);
			}

			int linefound = 0;
			for (int i = 0; i < page.size(); i++) {
				if (page.get(i).contains(keyword)) {
					linefound = i;
					break;
				}
			}
			System.out.println(keyword);
			System.out.println(linefound);

			for (int i = linefound - 200; i < linefound + to; i++) {
				for (int j = 0; j < specs.size(); j++) {
//				if (page.get(i).matches(specs.get(j))==true) { not good, finds anything that contains the string
//					matchedspec.add(specs.get(j));
//			         
//				}
					String specword = "\\b" + specs.get(j) + "\\b"; // finds the exact word
					Pattern p = Pattern.compile(specword); // pattern and matcher, it does stuff
					Matcher m = p.matcher(page.get(i));
					if (m.find() == true) {
						matchedspec.add(specs.get(j));
					}
				}
			}

			for (String asd : matchedspec) {
				System.out.println(asd);
			}

		} catch (IOException e) {
			System.out.println("Page not found");
		}
		return matchedspec;
	}

//	public static void main(String[] args) throws IOException {
////		ArrayList<String> asd= new ArrayList<>();
//
//		readPageFromTo("Specs", 10, "SearchParametersConf\\MemorySearchParameters");
//	
//	}

}
