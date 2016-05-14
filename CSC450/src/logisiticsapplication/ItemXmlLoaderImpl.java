package logisiticsapplication;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ItemXmlLoaderImpl implements ItemLoader{
	
	public ArrayList<Item> getItems() 
			throws Exception
	{
		
		ArrayList<Item> itemCollection = new ArrayList<Item>(); 
		
		try {
            String fileName = "ItemCatalog.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList itemEntries = doc.getDocumentElement().getChildNodes();
            //Get each Item Node
            for (int i = 0; i < itemEntries.getLength(); i++) {
                if (itemEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = itemEntries.item(i).getNodeName();
                if (!entryName.equals("Item")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return itemCollection;
                }
                


                Element elem = (Element) itemEntries.item(i);
                String itemName = elem.getElementsByTagName("ItemName").item(0).getTextContent();
                double itemPrice = Double.parseDouble(elem.getElementsByTagName("ItemPrice").item(0).getTextContent());
        		if(itemName == null)
        		{
        			throw new NullPointerException();
        		}
                itemCollection.add(ItemFactory.createNewItem(itemName, itemPrice));


 
            }

        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
		
		return itemCollection;
	}

}
