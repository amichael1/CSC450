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



public class FacilityXmlLoaderImpl implements FacilityLoader {
	
	private static final int DAYS_IN_SCHEDULE = 20;
	
	public ArrayList<Facility> getFacilities() throws Exception 
	{
		ArrayList<Facility> facilityCollection = new ArrayList<Facility>();

		
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
            	 
        		ArrayList<Neighbor> neighborCollection = new ArrayList<Neighbor>();
        		ArrayList<Inventory> inventoryCollection = new ArrayList<Inventory>();
        		ArrayList<Schedule> scheduleCollection = new ArrayList<Schedule>();
            	
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
                double facilityCost = Double.parseDouble(elem.getElementsByTagName("Cost").item(0).getTextContent());
                //schedule out 20 days
                for(int k=0; k<DAYS_IN_SCHEDULE;k++)
                {
                	scheduleCollection.add(new Schedule(k+1,facilityCap));
                }
                
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
                    
                   if(neighbor==null)
                   {
                	   throw new NullPointerException();
                   }
 
                   neighborCollection.add(new Neighbor(neighbor, miles));
               }
                
               //get inventory
                NodeList itemList = elem.getElementsByTagName("Item");

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
                    
                    int quantity = Integer.parseInt(elem.getElementsByTagName("ItemQuantity").item(0).getTextContent());
                    //System.out.println("Item name " + itemName + " item quantity " + quantity);
                    if(itemName==null)
                    {
                 	   throw new NullPointerException();
                    }

                    inventoryCollection.add(new Inventory(itemName, quantity));
                }
                
                facilityCollection.add(FacilityFactory.createNewFacility(facilityName, facilityCost, facilityCap, neighborCollection, scheduleCollection, inventoryCollection));


                
                
            }
        } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
            e.printStackTrace();
        }
		
		
		return facilityCollection;
	}

}
