package exercise.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.stream.Collectors;
import static exercise.Data.getCompanies;

public class CompaniesServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
                throws IOException, ServletException {

        // BEGIN
        List<String> companies = getCompanies();
        String query = request.getQueryString();
        String param = request.getParameter("search");
        PrintWriter out = response.getWriter();

        if (query == null || "".equals(param)) {
            out.println(String.join("\n", companies));
        } else {
            String filteredCompanies = companies.stream()
                    .filter(company -> company.contains(param))
                    .collect(Collectors.joining("\n"));

            if (filteredCompanies.length() > 0) {
                out.println(filteredCompanies);
            } else {
                out.println("Companies not found");
            }
        }
        // END
    }
}
