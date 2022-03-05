<%-- 
    Document   : Search.jsp
    Created on : Feb 7, 2022, 2:32:22 PM
    Author     : BK
--%>

<%@page import="Entity.Patient"%>
<%@page import="Entity.Doctor"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            ArrayList<Doctor> docs = (ArrayList<Doctor>) request.getAttribute("docs");
            ArrayList<Patient> pts = (ArrayList<Patient>) request.getAttribute("pts");
            int did = (Integer) request.getAttribute("did");
        %>
    </head>
    <body>
        <form action="search" method="GET">
            Departments: <select name="did">
                <option value="-1"> All </option>
                <% for (Doctor d : docs) {%>
                <option
                    <%=(d.getDid() == did) ? "selected=\"selected\"" : ""%>
                    value="<%=d.getDid()%>"> <%=d.getDname()%></option>
                <%}%>
            </select>
            <input type="submit" value="Search"/>
        </form>
        <br>
        <%if (pts.size() > 0) {%>
        <table border="1px">
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Gender</td>
                <td>Dob</td>
                <td>Doctor</td>
            </tr>
            <%for (Patient P : pts) {
            %>
            <tr>
                <td><%=P.getPid()%></td>
                <td><%=P.getPname()%></td>
                <td><%=P.getQender()%></td>
                <td><%=P.getDob()%></td>
                <td><%=P.getD().getDname()%></td>
            </tr>
            <%}%>
        </table> 
        <%} else {%>
        No record to display.
        <%}%>
    </body>
</html>
