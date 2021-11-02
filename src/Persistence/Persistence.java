package Persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Controller.Controller;

public class Persistence {
	private static String fileName = "data.bin";

	public static Controller readFile() {
		Controller controller = null;

		try {
			ObjectInputStream input = new ObjectInputStream(new FileInputStream(fileName));
			controller = (Controller) input.readObject();
			input.close();
		} catch (FileNotFoundException e) {
			// Arquivo não encontrado: nenhum objeto serializado
		} catch (Exception e) {
			e.printStackTrace();
		}

		return controller;
	}

	public static void writeFile(Controller controller) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName));
			output.writeObject(controller);
			output.flush();
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
