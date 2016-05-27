package parsesample;
/*
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * - Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * - Redistribution in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in
 *   the documentation and/or other materials provided with the
 *   distribution.
 *
 * Neither the name of Sun Microsystems, Inc. or the names of
 * contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * This software is provided "AS IS," without a warranty of any
 * kind. ALL EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND
 * WARRANTIES, INCLUDING ANY IMPLIED WARRANTY OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE OR NON-INFRINGEMENT, ARE HEREBY
 * EXCLUDED. SUN AND ITS LICENSORS SHALL NOT BE LIABLE FOR ANY DAMAGES
 * SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 * DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES. IN NO EVENT WILL SUN
 * OR ITS LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR
 * FOR DIRECT, INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR
 * PUNITIVE DAMAGES, HOWEVER CAUSED AND REGARDLESS OF THE THEORY OF
 * LIABILITY, ARISING OUT OF THE USE OF OR INABILITY TO USE SOFTWARE,
 * EVEN IF SUN HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGES.
 *
 * You acknowledge that Software is not designed, licensed or intended
 * for use in the design, construction, operation or maintenance of
 * any nuclear facility.
 */
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

/**
 * This class construcs an XML document in memory using DOM.
 * It first creates the root Order element and subsequently creates components
 * of the order by inserting nodes to the root element.
 */
public class CreateOrderDOM {

  /**
   * DOM Document
   */
  private Document document = null;

  public CreateOrderDOM() {
    DocumentBuilder builder = null;
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    try{
      builder = factory.newDocumentBuilder();
      document = builder.newDocument();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }

    // Insert Root Order
    Element root = (Element) document.createElement("Order");
    // Insert child Manifest
    document.appendChild(root);
    Node manifestChild = document.createElement("Manifest");
    root.appendChild(manifestChild);
    // Insert Items
    insertItem(document, manifestChild, "101", "Name one", "$29.99");
    insertItem(document, manifestChild, "108", "Name two", "$19.99");
    insertItem(document, manifestChild, "125", "Name three", "$39.99");
    insertItem(document, manifestChild, "143", "Name four", "$59.99");
    insertItem(document, manifestChild, "118", "Name five", "$99.99");

    // Normalizing the DOM
    document.getDocumentElement().normalize();
  }

  /**
   * @return Document
   */
  public Document getDocument(){
    return document;
  }

  /**
   * Insert "Item" to Document
   * @param document - Order Document
   * @param parent - Node where to insert a "Item"
   * @param id - Item's ID
   * @param name - Item's Name
   * @param price - Item's Price
   */
  private void insertItem(Document document, Node parent, String id, String name, String price) {
    // Insert child Item
    Node itemChild = document.createElement("Item");
    parent.appendChild(itemChild);

    // Insert child ID
    Node item = document.createElement("ID");
    itemChild.appendChild(item);
    // Insert ID value
    Node value = document.createTextNode(id);
    item.appendChild(value);

    // Insert child NAME
    item = document.createElement("NAME");
    itemChild.appendChild(item);
    // Insert NAME value
    value = document.createTextNode(name);
    item.appendChild(value);

    // Insert child PRICE
    item = document.createElement("PRICE");
    itemChild.appendChild(item);
    // Insert PRICE value
    value = document.createTextNode(price);
    item.appendChild(value);
  }
}