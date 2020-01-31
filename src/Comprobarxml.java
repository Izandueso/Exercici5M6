import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Node;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


public class Comprobarxml {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {

		Scanner teclado = new Scanner(System.in);		
		
		// per a carregar en memòria un arxiu xml
		File file = new File("rows.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		Scanner scan = new Scanner(file);

		//per obtenir el node arrel
		Element nodeArrel = doc.getDocumentElement();
		
		NodeList nodeList = doc.getDocumentElement().getChildNodes();
		
		//Menu
		System.out.println("1- Crear | 2- Modificar | 3- Eliminat | 4- Afegir atr | 5- Eliminar atr | 6- Modificar atr: ");
		int menu = teclado.nextInt();
		
		if (menu == 1){
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node temporal = nodeList.item(i);
				Element e = (Element) temporal;
				if (e.getNodeName().equals("row")) {
					Element alumne = doc.createElement("row");
					alumne.setAttribute("_adress", "https://analisi.transparenciacatalunya.cat/resource/yf2b-mjr6/row-efjc~t6wi.7y5k");
					alumne.setAttribute("_id", "row-efjc~t6wi.7y5k");
					alumne.setAttribute("_position", "_position");
					alumne.setAttribute("_uuid", "00000000-0000-0000-27CA-66ABEA857B6F");
					e.appendChild(alumne);
					
					Element nomFestiu = doc.createElement("nom_del_festiu");
					nomFestiu.setTextContent("Fiesta del izan");
					alumne.appendChild(nomFestiu);
					
					Element anyCalendari = doc.createElement("any_calendari");
					anyCalendari.setTextContent("2020");
					alumne.appendChild(anyCalendari);
					
					Element data = doc.createElement("data");
					data.setTextContent("2020-01-0600:00:00");
					alumne.appendChild(data);
					
					Element localitzacio = doc.createElement("localitzacio");
					localitzacio.setTextContent("Catalunya_España_Izan");
					alumne.appendChild(localitzacio);	
				}
			}	
			
		}else if(menu == 2){
			System.out.println("Introdueix element que vols modificar: ");
			String elementEliminar = teclado.next();
			Element eliminat = doc.getElementById(elementEliminar);
			
			System.out.println("Introdueix el element el qual sustituira: ");
			String element = teclado.next();
			Element p = doc.createElement(element);
			System.out.println(p);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node temporal = nodeList.item(i);
				Element e = (Element) temporal;
				if(e.getNodeName().equals(elementEliminar)){
					e.removeChild(eliminat);
					e.appendChild(p);
				}
			}
		}else if(menu == 3){
			System.out.println("Introdueix element que vols eliminar: ");
			String elementEliminar = teclado.next();
			Element eliminat = doc.getElementById(elementEliminar);
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node temporal = nodeList.item(i);
				Element e = (Element) temporal;
				if(e.getNodeName().equals(elementEliminar)){
					e.removeChild(eliminat);
				}
			}
			
		}else if (menu == 4){
			
			System.out.println("Introdueix element al que introduirli els nous atributs:");
			String element = teclado.next();
			Element p2 = doc.getElementById(element);
			
			System.out.println("Introdueix name: ");
			String name = teclado.next();
			
			System.out.println("Introdueix value: ");
			String value = teclado.next();
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node temporal = nodeList.item(i);
				Element e = (Element) temporal;
				if (e.getNodeName().equals(element)){
					e.setAttribute(name, value);
				}
			}
			
		}else if(menu == 5){
			System.out.println("Introdueix element al que introduirli els nous atributs:");
			String element = teclado.next();
			Element p2 = doc.getElementById(element);
			
			System.out.println("Introdueix name: ");
			String name = teclado.next();
			
			System.out.println("Introdueix value: ");
			String value = teclado.next();
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node temporal = nodeList.item(i);
				Element e = (Element) temporal;
				if (e.getNodeName().equals(element)){
					e.getAttributes().removeNamedItem(element);
				}
			}
		}else if(menu == 6){
			System.out.println("Introdueix element que vols modificar: ");
			String elementModificar = teclado.next();
			Element eliminat = doc.getElementById(elementModificar);
			
			System.out.println("Introdueix el element el qual sustituira: ");
			String nouValor = teclado.next();
			
			for (int i = 0; i < nodeList.getLength(); i++) {
				Node temporal = nodeList.item(i);
				Element e = (Element) temporal;
				if(e.getNodeName().equals(elementModificar)){
					e.setAttribute(elementModificar, nouValor);
					
				}
			}
		}else{
			System.out.println("error");
		}
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(doc);
        StreamResult streamResult = new StreamResult(new File("rows.xml"));
        
        transformer.transform(domSource, streamResult);

		while(scan.hasNextLine()){
			System.out.println(scan.nextLine());
		}

	}

	
}