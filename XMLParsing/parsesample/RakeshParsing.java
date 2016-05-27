package parsesample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class RakeshParsing {

	public RakeshParsing() {
		try {
			FileWriter writer = writeToFile(parse("D:\\Sample.xml"), new FileWriter(new File("D:\\ParsedFile.txt")));
			writer.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private Document parse(String fileName) {
		Document document = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(true);
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(new File(fileName));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return document;
	}

	private FileWriter writeToFile(Node node, FileWriter writer) {
		try {
			int type = node.getNodeType();
			switch (type) {
			case Node.DOCUMENT_NODE: {
				System.out.println("Inside DOCUMENT_NODE");
				writeToFile(((Document) node).getDocumentElement(),writer);
				break;
			}

				// print element with attributes
			case Node.ELEMENT_NODE: {
				System.out.println("Inside ELEMENT_NODE");
				writer.write("<");
				writer.write(node.getNodeName());
				NamedNodeMap attrs = node.getAttributes();
				for (int i = 0; i < attrs.getLength(); i++) {
					Node attr = attrs.item(i);
					writer.write(" " + attr.getNodeName().trim() + "=\""
							+ attr.getNodeValue().trim() + "\"");
				}
				writer.write(">");

				NodeList children = node.getChildNodes();
				if (children != null) {
					int len = children.getLength();
					for (int i = 0; i < len; i++)
						writeToFile(children.item(i),writer);
				}
				break;
			}

			case Node.ENTITY_REFERENCE_NODE: {
				System.out.println("Inside ENTITY_REFERENCE_NODE");
				writer.write("&");
				writer.write(node.getNodeName().trim());
				writer.write(";");
				break;
			}

			case Node.CDATA_SECTION_NODE: {
				System.out.println("Inside CDATA_SECTION_NODE");
				writer.write("<![CDATA[");
				writer.write(node.getNodeValue().trim());
				writer.write("]]>");
				break;
			}

			case Node.TEXT_NODE: {
				System.out.println("Inside TEXT_NODE");
				writer.write(node.getNodeValue().trim());
				break;
			}

			case Node.PROCESSING_INSTRUCTION_NODE: {
				System.out.println("Inside PROCESSING_INSTRUCTION_NODE");
				writer.write("<?");
				writer.write(node.getNodeName().trim());
				String data = node.getNodeValue().trim();
				{
					writer.write(" ");
					writer.write(data);
				}
				writer.write("?>");
				break;
			}

			}
			if (type == Node.ELEMENT_NODE) {
				System.out.println();
				writer.write("</");
				writer.write(node.getNodeName().trim());
				writer.write('>');
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return writer;
	}

	public static void main(String[] args) {
		new RakeshParsing();
	}
}
