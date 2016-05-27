<%-- 
    Document   : ToyTest
    Created on : Sep 21, 2008, 12:29:43 PM
    Author     : Rakesh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% sample.Person person = new sample.Person();
           person.setName("Rakesh");
           sample.Dog dog = new sample.Dog();
           dog.setName("Jerry");
           sample.Toy toy = new sample.Toy();
           toy.setName("Rat Doll");
           sample.Toy[] toys = new sample.Toy[2];
           toys[0] = toy;
           toy = new sample.Toy();
           toy.setName("Bone");
           toys[1] = toy;
           dog.setToys(toys);
           person.setDog(dog);
           request.setAttribute("person", person); %>
        <% request.getRequestDispatcher("ToyTester.jsp").forward(request, response); %>
    </body>
</html>
