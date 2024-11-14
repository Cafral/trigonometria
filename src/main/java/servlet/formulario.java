package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/trigonometria")

public class formulario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html");
        //Obtenemos nuestro ángulo
        String grados2 = req.getParameter("grados");

        Map<String, String> errores = new HashMap();

        if (grados2 == null && grados2.isEmpty()) {
            errores.put("grados", "Se requiere un número");
        } else {
            try {
                Double grados = Double.parseDouble(grados2);
                if (grados < 0 || grados > 360) {
                    errores.put("grados", "Se requiere un numero entre 0 y 360");
                } else {
                    try (PrintWriter out = resp.getWriter()) {
                        //Creo la pantilla html
                        out.print("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<meta charset=\'UTF-8\'>");
                        out.println("<title>Razones trigonométricas</title>");
                        out.println("</head>");
                        out.println("<style>");
                        out.println("body { background-color: skyblue; font-family: Arial; text-align: center; }");
                        out.println("h1 { color: #689821; font-size: 24px; }");
                        out.println("table { width: 50%; margin: 20px auto; background-color: #000000; }");
                        out.println("th, td { padding: 5px; border: 1px solid #066dce; text-align: center; color: #ffffff; font-weight: bold; }");
                        out.println("th { background-color: #1d8ed1; color: #ffffff; }");
                        out.println("</style>");
                        out.println("<body>");
                        out.println("<h1>Tabla de razones trigonométricas</h1>");
                        out.println("<table border='1'>");
                        out.println("<tr>");
                        out.println("<th>Ángulo (grados)</th>");
                        out.println("<th>Seno</th>");
                        out.println("<th>Coseno</th>");
                        out.println("</tr>");
                        for (int i = 0; i <= grados; i += 15) {
                            //Convertimos el ángulo de grados a radianes
                            Double radianes = Math.toRadians(i);

                            //Calculo del seno y coseno
                            Double seno = Math.sin(radianes);
                            Double coseno = Math.cos(radianes);
                            //Imprimimos los resultados en formato de tabla
                            out.println("<tr>");
                            out.println("<td>" + i + "</td>");
                            out.printf("<td>%.3f</td>", seno);
                            out.printf("<td>%.3f</td>", coseno);
                            out.println("</tr>");
                        }
                        out.println("</table>");
                        out.println("</body>");
                        out.println("</html>");
                    }
                    return;
                }
            } catch (NumberFormatException e) {
                errores.put("grados", "Debe ingresar solo números");
            }

        }
        req.setAttribute("errores", errores);
        getServletContext().getRequestDispatcher("/index.jsp").forward(req, resp);
    }


}
