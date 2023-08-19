import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class ParseXML {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("ticket_data.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList ticketList = doc.getElementsByTagName("ticket");

            System.out.println("<table border=\"1\">");
            System.out.println("<tr><th>Event</th><th>Quantity</th><th>Customer</th></tr>");

            for (int i = 0; i < ticketList.getLength(); i++) {
                Node ticketNode = ticketList.item(i);
                if (ticketNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element ticketElement = (Element) ticketNode;
                    String event = ticketElement.getElementsByTagName("event").item(0).getTextContent();
                    String quantity = ticketElement.getElementsByTagName("quantity").item(0).getTextContent();
                    String customer = ticketElement.getElementsByTagName("customer").item(0).getTextContent();

                    System.out.println(
                            "<tr><td>" + event + "</td><td>" + quantity + "</td><td>" + customer + "</td></tr>");
                }
            }

            System.out.println("</table>");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
