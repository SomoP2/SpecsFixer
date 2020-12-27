package fixer;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import javax.net.ssl.HttpsURLConnection;

public class PageGetter {

	public static void download(String urlString){
//		SSLTool.disableCertificateValidation();
		String httpsURL = urlString;
		String fileName = "page.html";
		try {
		BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
		URL myurl = new URL(httpsURL);
		HttpsURLConnection con = (HttpsURLConnection) myurl.openConnection();
		con.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0");
		InputStream ins = con.getInputStream();
		InputStreamReader isr = new InputStreamReader(ins, StandardCharsets.UTF_8);
		BufferedReader in = new BufferedReader(isr);
		String inputLine;

		// Write each line into the file
		while ((inputLine = in.readLine()) != null) {
		//	System.out.println(inputLine);
			bw.write(inputLine);
			bw.newLine();
		}
		in.close();
		bw.close();
		}catch (IOException e) {
		System.out.println("Cant download nothing");
		}
	
	}

}
