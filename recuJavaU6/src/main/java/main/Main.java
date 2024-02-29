package main;

import java.sql.SQLException;

import controller.Controller;
import java.io.IOException;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

public class Main {

	public static void main(String[] args) throws SQLException, ParserConfigurationException, IOException, TransformerException, JAXBException {
		Controller controller = Controller.getInstance();
		controller.init();
	}
}
