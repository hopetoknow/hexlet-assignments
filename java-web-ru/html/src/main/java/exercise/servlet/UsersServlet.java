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

//    public static void main(String[] args) {
//        try {
//            List list = getUsers();
//            list.forEach(System.out::println);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//    }

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
                    </head>
                    <body>
                    <table>
                        <tr>
                            <th>id</th>
                            <th>fullName</th>
                        </tr>
                        <tr>
                            <td>
                """);

        for (Map<String, String> user : users) {
            String fullName = user.get("firstName") + " " + user.get("lastName");
            String id = user.get("id");
            html.append(id).append("</td>");
            html.append("""
                                <td>
                                    <a href="/users/
                    """);
            html.append(id).append("\">").append(fullName).append("</a>");
            html.append("""
                                </td>
                            </tr>
                    """);


        }

        html.append("""
                    </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println(html.toString());
        // END
    }

    private void showUser(HttpServletRequest request,
                         HttpServletResponse response,
                         String id)
                 throws IOException {

        // BEGIN
        List<Map<String, String>> users = getUsers();
        StringBuilder html = new StringBuilder();
        Map<String, String> user = users
                .stream()
                .filter(item -> item.get("id").equals(id))
                .findAny()
                .orElse(null);

        if (user == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String firstName = user.get("firstName");
        String lastName = user.get("lastName");
        String email = user.get("email");

        html.append("""
                <!DOCTYPE html>
                <html lang="ru">
                    <head>
                        <meta charset="UTF-8">
                        <title>Example application | User</title>
                    </head>
                    <body>
                    <table>
                        <tr>
                            <th>id</th>
                            <th>firstName</th>
                            <th>lastName</th>
                            <th>email</th>
                        </tr>
                        <tr>
                            <td>
                """);

        html.append(id).append("</td>");
        html.append("""
                            <td>
                """);
        html.append(firstName).append("</td");
        html.append("""
                            <td>
                """);
        html.append(lastName).append("</td");
        html.append("""
                            <td>
                """);
        html.append(email).append("</td");
        html.append("""
                        </tr>
                    </table>
                    </body>
                </html>
                """);

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
        out.println(html.toString());
        // END
    }
}
