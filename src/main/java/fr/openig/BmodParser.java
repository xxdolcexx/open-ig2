package fr.openig;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BmodParser {

	public static void main(String[] argv) throws IOException {
		BmodParser bmodParser = new BmodParser();
		bmodParser.parseAllFiles();
	}

	private void parseAllFiles() throws IOException {
		RandomAccessFile fichier = new BufferedReader(new FileReader("src/main/resources/bmod/a.bmod"));
		String str;
        str = fichier.read;
        while (str != null){
             System.out.println(str);
             str = fichier.readLine( );
        }
	}

}
