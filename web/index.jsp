<%-- 
    Document   : index
    Created on : April, 2018
    Author     : Victor Ferreira Pereira
    E-mail     : vferreirap2@gmail.com
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" href="static/style.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SAWI Framework</title>
    </head>
    <body>
        <form action="indexServlet" method="get">

            <!-- Select the browser -->
            <select name="browser">
                <option value="CH">Chrome</option>
                <option value="FF">Firefox</option>
                <option value="IE">Internet Explorer</option>
            </select>

            <!-- Select the environment -->
            <select name="env">
                <%
                    // Can be added as much as you need
                    String environment[] = {"Environment 1","Environment 2","Environment 3"};
                    for (String e : environment) {
                        out.print("<option value='env'>");
                        out.println(e);
                        out.print("</option>");
                    }
                %>
            </select>

            <table name="scripts">
                <%
                    out.print("<tr>");
                    String title[] = {"Run", "Script Number", "Script Name", "Status", "Last execution status"};
                    for (String t : title) {
                        out.print("<th>");
                        out.println(t);
                        out.print("</th>");
                    }
                    out.print("</tr>");
                    
                    String tests[][] = {
                        {"01", "Turn on the Internet", "Running...", "Success"},
                        {"02", "Turn off the Internet", "Stopped", "Success"},
                        {"03", "Throw fireball", "Stopped", "Fail"},};
                    for (int i = 0; i < 3; i++) {
                        out.print("<tr>");
                        out.print("<td><input type='checkbox' name='chkBox" + i + "'></td>");
                        for (int j = 0; j < 4; j++) {
                            out.print("<td>" + tests[i][j] + "</td>");
                        }
                        out.print("</tr>");
                    }
                %>
            </table>
            <input type="submit" value="Run tests">
        </form>

        <form>
            <input type="submit" value="Generate report">
        </form>
        <p>
            <%
                String vale = (String) request.getAttribute("params");
                out.println(vale);
            %>
        </p>
    </body>
</html>
