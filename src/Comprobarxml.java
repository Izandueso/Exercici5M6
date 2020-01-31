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
				//if(temporal.equals("row")){
					Element alumne = doc.createElement("alumne");
					alumne.setAttribute("id", "4");
					e.appendChild(alumne);
					
					Element nom = doc.createElement("nom");
					nom.setTextContent("SaraxKevin");
					alumne.appendChild(nom);
					
					Element cognom1 = doc.createElement("cognom1");
					cognom1.setTextContent("Perez");
					alumne.appendChild(cognom1);
					
					Element cognom2 = doc.createElement("cognom2");
					cognom2.setTextContent("Garcia");
					alumne.appendChild(cognom2);
					
					Element notaFinal = doc.createElement("notaFinal");
					notaFinal.setTextContent("7");
					alumne.appendChild(notaFinal);	
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