import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;

public class GenerateHTMLTable {
    public static void main(String[] args) {
        try {
            File xmlFile = new File("ticket_data.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(xmlFile);

            NodeList ticketList = doc.getElementsByTagName("ticket");

            StringBuilder tableHtml = new StringBuilder();
            tableHtml.append("<!DOCTYPE html>\n<html>\n<head>\n<title>Ticket Data</title>\n</head>\n<body>\n");
            tableHtml.append("<h1>Ticket Data</h1>\n<table border=\"1\">");
            tableHtml.append("<tr><th>Event</th><th>Quantity</th><th>Customer</th></tr>");

            for (int i = 0; i < ticketList.getLength(); i++) {
                Node ticketNode = ticketList.item(i);
                if (ticketNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element ticketElement = (Element) ticketNode;
                    String event = ticketElement.getElementsByTagName("event").item(0).getTextContent();
                    String quantity = ticketElement.getElementsByTagName("quantity").item(0).getTextContent();
                    String customer = ticketElement.getElementsByTagName("customer").item(0).getTextContent();

                    tableHtml.append(
                            "<tr><td>" + event + "</td><td>" + quantity + "</td><td>" + customer + "</td></tr>");
                }
            }

            tableHtml.append("</table>\n</body>\n</html>");
            saveHTMLPage(tableHtml.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void saveHTMLPage(String htmlContent) {
        try {
            FileWriter writer = new FileWriter("ticket_data_table.html");
            writer.write(htmlContent);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
