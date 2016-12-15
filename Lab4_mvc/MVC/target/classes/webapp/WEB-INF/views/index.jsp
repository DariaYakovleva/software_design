<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<head>
    <title>My TODO List</title>
</head>
<body>

<h3>My TODO List</h3>

<table>
    <c:forEach var="TODO" items="${TODOs}">
    <tr>
        <td>
        <form:form modelAttribute="TODO" method="POST" action="/delete-TODO">
        <table>
            <tr>
                <td><b>${TODO.getName()}</b>
                </td>
                <td><button type="submit" name="id" value="${TODO.getId()}">delete TODO</button>
                </td>
            </tr>
        </table>
        </form:form>
        </td>
    </tr>
    <tr>
        <td>
            <table>
                <c:forEach var="TASK" items="${TODO.getTasks()}">
                <tr>
                    <form:form modelAttribute="TASK" method="POST" action="/delete-TASK?todoid=${TODO.getId()}&taskname=${TASK.getName()}">
                        <td>${TASK.getName()}
                        </td>
                        <td><button type="submit">delete task</button>
                        </td>
                    </form:form>
                </tr>
                </c:forEach>
                <tr>
                    <form:form modelAttribute="TASK" method="POST" action="/add-TASK">
                    <td><form:label path="name"></form:label><form:input path="name"/>
                    </td>
                    <td><button type="submit" name="TODOID" value="${TODO.getId()}">add task</button>
                    </td>
                    </form:form>
                </tr>
            </table>
        </td>
    </tr>
    </c:forEach>
</table>

<h3>Add new TODO</h3>
<form:form modelAttribute="TODO" method="POST" action="/add-TODO">
    <table>
        <tr>
            <td><form:label path="name">Name:</form:label>
            </td>
            <td><form:input path="name"/>
            </td>
        </tr>
    </table>
    <input type="submit" value="add TODO">
</form:form>

</body>
</html>
