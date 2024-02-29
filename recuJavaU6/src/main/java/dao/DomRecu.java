
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import model.Player;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class DomRecu {
    private Document doc;
    private Element el; 
    
    public DomRecu() throws ParserConfigurationException{
        //ESTE OBJETO CONTIENE LOS MÉTODOS DE CONFIGURACIÓN
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        //SE CREA O SE MODIFICA UN DOCUMENTO
        DocumentBuilder db = dbf.newDocumentBuilder();
        //CREAMOS EL DOCUMENTO
        doc = db.newDocument();
        //AGREGAMOS UNA NUEVA ETIQUETA QUE SERÁ LA ETIQUETA PADRE DE TODAS
        el = doc.createElement("Players");
        doc.appendChild(el);
    }
    
    //ME GENERA LA ESTRUCTURA XML DEL DOCUMENTO
    public void generateDocument(ArrayList<Player> jugadores){        

        for (Player p: jugadores){
            //CREAMOS LA ETIQUETA PARA JUGADOR
            Element player = doc.createElement("Player");
            //AÑADIMOS UN ATRIBUTO QUE CONTIENE EL ID DE CADA JUGADOR
            player.setAttribute("id",String.valueOf(p.getId()) );
            el.appendChild(player);
            //AQUI ESCRIBIMOS EL RESTO DE ETIQUETAS Y SUS VALORES
            Element name = doc.createElement("name");
            name.setTextContent(p.getName());
            player.appendChild(name);

            Element games = doc.createElement("games");
            games.setTextContent(String.valueOf(p.getGames()));
            player.appendChild(games);

            Element victories = doc.createElement("victories");
            victories.setTextContent(String.valueOf(p.getVictories()));
            player.appendChild(victories);
        }   
    }
    
    public void generateXml() throws IOException, TransformerConfigurationException, TransformerException{
   
        Source s = new DOMSource(doc);
        File f = new File("playersDOM.xml");

        try (FileWriter fw = new FileWriter(f)) {
            PrintWriter pw = new PrintWriter(fw);
            Result r = new StreamResult(pw);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer t = tf.newTransformer();
            t.transform(s, r);
        }

    }
}
