package exercise.servlet;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.commons.lang3.ArrayUtils;

public class UsersServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null) {
            showUsers(request, response);
            return;
        }

        String[] pathParts = pathInfo.split("/");
        String id = ArrayUtils.get(pathParts, 1, "");

        showUser(request, response, id);
    }

    private List<Map<String, String>> getUsers() throws JsonProcessingException, IOException {
        // BEGIN
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("src/main/resources/users.json"), List.class);
        // END
    }

    private void showUsers(HttpServletRequest request,
                          HttpServletResponse response)
                throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder html = new StringBuilder();
        html.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Example application | Users</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                            rel="stylesheet"
                            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                            crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                            <tr>
                                <th>id</th>
                                <th>fullName</th>
                            </tr>
                """);

        for (Map<String, String> user : users) {
            String id = user.get("id");
            String fullName = user.get("firstName") + " " + user.get("lastName");

            html.append("<tr>");
            html.append("<td>").append(id).append("</td>");
            html.append("<td><a href=\"/users/").append(id).append("\">").append(fullName).append("</a>")
                    .append("</td>");
            html.append("</tr>");
        }

        html.append("""
                        </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(html);
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        Map<String, String> user = users
                .stream()
                .filter(item -> item.get("id").equals(id))
                .findAny()
                .orElse(null);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        StringBuilder html = new StringBuilder();
        String firstName = user.get("firstName");
        String lastName = user.get("lastName");
        String email = user.get("email");

        html.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Example application | User</title>
                        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
                            rel="stylesheet"
                            integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
                            crossorigin="anonymous">
                    </head>
                    <body>
                        <table>
                            <tr>
                                <th>id</th>
                                <th>firstName</th>
                                <th>lastName</th>
                                <th>email</th>
                            </tr>
                            <tr>""");

        html.append("<td>").append(id).append("</td>");
        html.append("<td>").append(firstName).append("</td>");
        html.append("<td>").append(lastName).append("</td>");
        html.append("<td>").append(email).append("</td>");
        html.append("""
                            </tr>
                        </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(html);
        // END
    }
}
