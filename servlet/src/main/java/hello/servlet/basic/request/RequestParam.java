package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "requestParamSv",urlPatterns = "/request-param")
public class RequestParam extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("request-param 호출");

        System.out.println("전체 파라미터 조회 - Start");

        request.getParameterNames().asIterator()
        .forEachRemaining(param -> System.out.println(param + " = " +request.getParameter(param)));

        System.out.println("전체 파라미터 조회 - End");
        System.out.println();

        System.out.println("단일 파라미터 조회 - Start");

        System.out.println(request.getParameter("username"));
        System.out.println(request.getParameter("age"));

        System.out.println("단일 파라미터 조회 - End");
        System.out.println();

        System.out.println("이름이 같은 복수 파라미터 조회");
        String[] usernames = request.getParameterValues("username");
        for (String username : usernames) {
            System.out.println(username);
        }
    }
}
