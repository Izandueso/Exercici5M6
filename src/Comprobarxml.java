import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.lang.model.element.Element;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Comprobarxml {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		
		Scanner teclado = new Scanner(System.in);		
		
		// per a carregar en memòria un arxiu xml
		File file = new File("alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);

		//per obtenir el node arrel
		org.w3c.dom.Element nodeArrel = doc.getDocumentElement();
		NodeList nodeList = doc.getDocumentElement().getChildNodes();
		
		System.out.println("1- Crear | 2- Modificar | 3- Eliminat | 4- Afegir atr | 5- Eliminar atr | 6- Modificar atr: ");
		int menu = teclado.nextInt();
		
		if (menu == 1){
			
			//Creem elements
			System.out.println("Introdueix el element que vols crear: ");
			String element = teclado.next();
			org.w3c.dom.Element p = doc.createElement(element);
			System.out.println(p);
			
		}else if(menu == 2){
			
		}else if(menu == 3){
			System.out.println("Introdueix element que vols eliminar: ");
			String elementEliminar = teclado.next();
			org.w3c.dom.Element eliminat = doc.createElement(elementEliminar);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				org.w3c.dom.Node temporal = nodeList.item(i);
				if(temporal.equals(eliminat)){
					temporal.getParentNode().removeChild(eliminat);
				}
			}
			
		}else if (menu == 4){
			
		}else if(menu == 5){
			
		}else if(menu == 6){
			
		}else{
			
		}
		
		//Busquem node
		System.out.println("Introdueix un node per cercar: ");
		String node = teclado.next();
		org.w3c.dom.Element c = doc.getElementById(node);
		System.out.println(c);
		
		//Creem un node de text
		System.out.println("Introdueix el element que vols crear: ");
		String elementText = teclado.next();
		Text text = doc.createTextNode(elementText);
		System.out.println(text);
		
		//Introduim elnode que volem adquirir els atributs
		System.out.println("Introduex element amb el qual vols treballar: ");
		String elementNode = teclado.next();
		org.w3c.dom.Element elementNodeVar = doc.createElement(elementNode);
		
		System.out.println("Introdueix node per obtenir atributs: ");
		String nodeAtribut = teclado.next();
		
		String atribut = elementNodeVar.getAttribute(nodeAtribut);
		System.out.println(atribut);
		
		
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			imprimirDadesXml((org.w3c.dom.Node)nodeList.item(i));
			
		}	
		
		//Per obtenir els nodes fill d’un node useu el mètode getChildNodes()
		//Per obtenir els atributs d’un node, useu el mètode getAttributes()
		//Per obtenir el nom, el tipus i el valor d’un node, cerqueu els mètodes apropiats.
		
	}
	
	public static void imprimirDadesXml(org.w3c.dom.Node nodos){
			System.out.println(nodos.getNodeName() + " " + nodos.getTextContent());
			NodeList nodoListo = nodos.getChildNodes();
			for (int j = 0; j < nodoListo.getLength(); j++){
				imprimirDadesXml(nodoListo.item(j));	
			}
	}

}