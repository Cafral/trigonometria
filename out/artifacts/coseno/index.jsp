<%@page contentType="text/html" pageEncoding="UTF-8" %>

<%-- Importamos la biblioteca --%>
<%@page import="java.util.Map" %>

<%-- Implementamos un scriplet --%>
<%
    Map<String, String> errores = (Map<String, String>) request.getAttribute("errores");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Razones trigonometricas</title>
</head>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #cbf3f3;
        text-align: center;
    }

    h1 {
        color: #1232b2;
    }

    div {
        padding: 20px;
        margin: 20px;
        display: inline-block;
    }

    form {
        display: flex;
        flex-direction: column;
        align-items: center;
    }

    label {
        margin: 10px 0;
        font-size: 16px;
    }

    #grados {
        padding: 20px;
    }

    #calcular {
        padding: 50px;
        font-size: 25px;

    }

    #calcular:hover {
        background-color: #d9a1a1;
    }

</style>
<body>

<h1>Razones trigonometricas (SENO - COSENO)</h1>

<div>
    <form action="/coseno/trigonometria" method="POST">
        <div>
            <label for="grados">Ángulo máximo: </label>
            <input type="number" name="grados" id="grados" placeholder="Ingrese el limite del ángulo a calcular">
        </div>
        <div>
            <input type="submit" value="CALCULAR" id="calcular">
        </div>
        <%
            if (errores != null && errores.containsKey("grados")) {
                out.println("<div style = 'color: red'>" + errores.get("grados") + "</div>");
            }
        %>
    </form>
</div>
</body>
</html>
