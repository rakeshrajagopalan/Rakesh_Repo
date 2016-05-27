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

import org.w3c.dom.Document;

/**
 * Main class for DOM Sample
 */
public class DOMSample {

  /**
   * File name of Order
   */
  static String orderFileName = "Order.xml";

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {

    Document document = null;
    Order order = null;

    out("\nConstruct document using DOM in memory, save to file, and print it");
    // Build new DOM Document
    CreateOrderDOM cDOM = new CreateOrderDOM();
    document = cDOM.getDocument();
    out("New Order created");

    // Print XML content
    DOMUtil.printDOM(document);

    // Write to a file
    DOMUtil.writeXmlToFile("newOrder.xml",document);
    out("\n\nThe New Order stored to NewOrder.xml");

    // Create Order object based on Document
    order = new Order(document);
    out("\n\nPrint the Order's summary");
    order.print();

    out("\n\nConstruct document using existing XML Order," +
                      " apply discount, insert Tax and Total info to the" +
                      " Order, save to file, and print it");
    // Read XML from file to DOM
    document = DOMUtil.parse("Order.xml");

    if (document != null){
      // Print XML content
      out("\nPrint XML from file");
      DOMUtil.printDOM(document);

      // Get Order Price
      order = new Order(document);
      out("\nPrint the Order's summary");
      order.print();

      //Calculate total Items in Order
      int count = DOMUtil.countByTagName("Item",document);
      out("\nNumber Items in Order: " + count);

      out("Check for Discount");
      // Check for Discount
      if (order.isDiscount()){
        out("Apply Discount");
        order.applyDiscount();
      }

      out("\nResult Order");
      order.print();

      // Add Tax and Total to the Order XML
      order.AddTaxAndTotal();
      // Print XML content
      out("\nPrint XML that include Tax and Total");
      DOMUtil.printDOM(document);

      // Write to a file
      DOMUtil.writeXmlToFile("DiscountOrder.xml",document);
      out("\n\nThe Order modified and stored to DiscountOrder.xml");
    }
  }

  /**
   * Prints to the System output a message
   * @param message String
   */
  private static void out(String message) {
    System.out.println(message);
  }

}
