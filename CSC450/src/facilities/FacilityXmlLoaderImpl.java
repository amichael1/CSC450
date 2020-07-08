package facilities;

import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class FacilityXmlLoaderImpl implements FacilityLoader {
	private static final int DAYS_IN_SCHEDULE = 20;
	
	public List<Facility> getFacilities() throws Exception {
        List<Facility> facilityCollection = new ArrayList<Facility>();

		try {
            String fileName = "FacilitiesAndNetwork.xml";

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            File xml = new File(fileName);
            if (!xml.exists()) {
                System.err.println("**** XML File '" + fileName + "' cannot be found");
                System.exit(-1);
            }

            Document doc = db.parse(xml);
            doc.getDocumentElement().normalize();

            NodeList facilityEntries = doc.getDocumentElement().getChildNodes();

            for (int i = 0; i < facilityEntries.getLength(); i++) {

                List<Neighbor> neighborCollection = new ArrayList<Neighbor>();
            	
                if (facilityEntries.item(i).getNodeType() == Node.TEXT_NODE) {
                    continue;
                }
                
                String entryName = facilityEntries.item(i).getNodeName();
                if (!entryName.equals("Store")) {
                    System.err.println("Unexpected node found: " + entryName);
                    return facilityCollection;
                }
                
                //Do not need ID's
                //NamedNodeMap aMap = facilityEntries.item(i).getAttributes();
                //String facilityId = aMap.getNamedItem("Id").getNodeValue();


                Element elem = (Element) facilityEntries.item(i);
                String facilityName = elem.getElementsByTagName("Name").item(0).getTextContent();
                int facilityCap = Integer.parseInt(elem.getElementsByTagName("ItemCap").item(0).getTextContent());
                int facilityCost = Integer.parseInt(elem.getElementsByTagName("Cost").item(0).getTextContent());
                //schedule out 20 days
                List<Integer> schedule = new ArrayList<Integer>();
                schedule.add(0);
                for(int k=1; k<=DAYS_IN_SCHEDULE;k++) {
                    schedule.add(k,facilityCap);
                }

                Schedule newSchedule = new Schedule(schedule);
                //get neighbors
                Element elemTwo = (Element) facilityEntries.item(i);
                NodeList linkList = elemTwo.getElementsByTagName("Link");
                
               for (int j = 0; j < linkList.getLength(); j++) {
                	if (linkList.item(j).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = linkList.item(j).getNodeName();
                    if (!entryName.equals("Link")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return facilityCollection;
                   }


                    elemTwo = (Element) linkList.item(j);
                   String neighbor = elemTwo.getElementsByTagName("Facility").item(0).getTextContent();
                   int miles = Integer.parseInt(elemTwo.getElementsByTagName("Miles").item(0).getTextContent());
                    
                   if(neighbor==null) {
                	   throw new NullPointerException();
                   }
 
                   neighborCollection.add(new Neighbor(neighbor, miles));
               }
                
               //get inventory
                NodeList itemList = elem.getElementsByTagName("Item");

                List<String> items = new ArrayList<String>();
                List<Integer> quantity = new ArrayList<Integer>();

                for (int l = 0; l < itemList.getLength(); l++) {
                    if (itemList.item(l).getNodeType() == Node.TEXT_NODE) {
                        continue;
                    }

                    entryName = itemList.item(l).getNodeName();
                    if (!entryName.equals("Item")) {
                        System.err.println("Unexpected node found: " + entryName);
                        return facilityCollection;
                    }


                    elem = (Element) itemList.item(l);
                    String itemName = elem.getElementsByTagName("ItemName").item(0).getTextContent();
                    int quantityForItem = Integer.parseInt(elem.getElementsByTagName("ItemQuantity").item(0).getTextContent());

                    items.add(itemName);
                    quantity.add(quantityForItem);

                    //System.out.println("Item name " + itemName + " item quantity " + quantity);
                    if(itemName==null) {
                 	   throw new NullPointerException();
                    }
                }

                Inventory inventory = new Inventory(items, quantity);

                facilityCollection.add(FacilityFactory.createNewFacility(facilityName, facilityCost, facilityCap, neighborCollection, newSchedule, inventory));
            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }

		return facilityCollection;
	}

}
