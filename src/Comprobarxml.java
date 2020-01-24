import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Node;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Comprobarxml {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		
		Scanner teclado = new Scanner(System.in);		
		
		// per a carregar en mem�ria un arxiu xml
		File file = new File("alumnes.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		
		

		//per obtenir el node arrel
		org.w3c.dom.Element nodeArrel = doc.getDocumentElement();
		NodeList nodeList = doc.getChildNodes();
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node polla = nodeList.item(i);
			Element caca = (Element) nodeList.item(i);
			System.out.println(caca);
			if(nodeList.item(i).equals()){
//				Element p40000 = doc.createElement("alumne");
//				p40000.setIdAttribute("id", true);
				System.out.println("si");
			}
		}
		
		//Menu
		System.out.println("1- Crear | 2- Modificar | 3- Eliminat | 4- Afegir atr | 5- Eliminar atr | 6- Modificar atr: ");
		int menu = teclado.nextInt();
		
		if (menu == 1){
			
			//Busquem node
			System.out.println("Introdueix una id: ");
			String node = teclado.next();
			Element c = doc.getElementById(node);
			System.out.println(c);
			
			//Creem elements
			System.out.println("Introdueix el element que vols crear: ");
			String element = teclado.next();
			c = doc.createElement(element);
			System.out.println(c);
			
		}else if(menu == 2){
			System.out.println("Introdueix element que vols modificar: ");
			String elementEliminar = teclado.next();
			Element eliminat = doc.createElement(elementEliminar);
			
			System.out.println("Introdueix el element el qual sustituira: ");
			String element = teclado.next();
			Element p = doc.createElement(element);
			System.out.println(p);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				org.w3c.dom.Node temporal = nodeList.item(i);
				if(temporal.equals(eliminat)){
					temporal.getParentNode().removeChild(eliminat);
					temporal.getParentNode().setNodeValue(element);
				}
			}
		}else if(menu == 3){
			System.out.println("Introdueix element que vols eliminar: ");
			String elementEliminar = teclado.next();
			Element eliminat = doc.createElement(elementEliminar);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				org.w3c.dom.Node temporal = nodeList.item(i);
				if(temporal.equals(eliminat)){
					temporal.getParentNode().removeChild(eliminat);
				}
			}
			
		}else if (menu == 4){
			
			System.out.println("Introdueix element al que introduirli els nous atributs:");
			String element2 = teclado.next();
			Element p2 = doc.createElement(element2);
			
			System.out.println("Introdueix name: ");
			String name = teclado.next();
			
			System.out.println("Introdueix value: ");
			String value = teclado.next();
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				org.w3c.dom.Node temporal2 = nodeList.item(i);
				if (temporal2.equals(p2)){
					p2.setAttribute(name, value);
				}
			}
			
			
		}else if(menu == 5){
			
		}else if(menu == 6){
			
		}else{
			
		}
		
	
		
		//Creem un node de text
		System.out.println("Introdueix el element que vols crear: ");
		String elementText = teclado.next();
		Text text = doc.createTextNode(elementText);
		System.out.println(text);
		
		
		
		
		
		for (int i = 0; i < nodeList.getLength(); i++) {
			//imprimirDadesXml((org.w3c.dom.Node)nodeList.item(i));
			
		}	
		
		//Per obtenir els nodes fill d�un node useu el m�tode getChildNodes()
		//Per obtenir els atributs d�un node, useu el m�tode getAttributes()
		//Per obtenir el nom, el tipus i el valor d�un node, cerqueu els m�todes apropiats.
		
	}
	
	public static void imprimirDadesXml(org.w3c.dom.Node nodos){
			System.out.println(nodos.getNodeName() + " " + nodos.getTextContent());
			NodeList nodoListo = nodos.getChildNodes();
			for (int j = 0; j < nodoListo.getLength(); j++){
				imprimirDadesXml(nodoListo.item(j));	
			}
	}

}