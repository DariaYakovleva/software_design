<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Plugin tester</title>
</head>

<body>
<h1>My Searcher</h1>
<form action = "servlet-parameters" method = "GET">
    <table border = "0">

        <tr>
            <td><b>QUERY</b></td>
            <td><input type = "text" name = "query"
                       value = "Russia" size = "70"/></td>
        </tr>
        <tr>
            <td colspan = "2"><input type = "submit" value = "SEARCH"/></td>
        </tr>
    </table>
</form>
</body>
</html>