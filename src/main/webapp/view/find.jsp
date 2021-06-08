<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Product By Name</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>
<c:forEach var="product" items="${product}">
<form class="form-inline my-2 my-lg-0" method="get">
    <input class="form-control mr-sm-2" type="text" name="search" value="${product.getName()}" aria-label="Search">
    <input class="btn btn-outline-success my-2 my-sm-0" type="submit">Search
</form>
<h3>Result</h3>
<table style="border-collapse: collapse; border: black solid 1px; text-align: center">
    <tr style="border: black solid 1px">
        <td style="border: black solid 1px">ID</td>
        <td style="border: black solid 1px">Name</td>
        <td style="border: black solid 1px">Price</td>
        <td style="border: black solid 1px">Quantity</td>
        <td style="border: black solid 1px">Color</td>
        <td style="border: black solid 1px">Description</td>
        <td style="border: black solid 1px">Category_ID</td>
    </tr>
    <tr style="border: black solid 1px">
        <td style="border: black solid 1px">${product.getId()}</td>
        <td style="border: black solid 1px">${product.getName()}</td>
        <td style="border: black solid 1px">${product.getPrice()}</td>
        <td style="border: black solid 1px">${product.getQuantity()}</td>
        <td style="border: black solid 1px">${product.getColor()}</td>
        <td style="border: black solid 1px">${product.getDescription()}</td>
        <td style="border: black solid 1px">${product.getCategory_id()}</td>
    </tr>
</table>
</body>
</html>
</c:forEach>
