package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}",username);
        log.info("age = {}",age);

        response.getWriter().write("ok");
    }

    @RequestMapping("/request-param-v2")
    @ResponseBody
    public String requestParamV2(@RequestParam("username") String username,
                                 @RequestParam("age") int age) throws IOException {
        log.info("username = {}",username);
        log.info("age = {}",age);

        return "ok";
    }
    @RequestMapping("/request-param-v3")
    @ResponseBody
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age) throws IOException {
        log.info("username = {}",username);
        log.info("age = {}",age);

        return "ok";
    }
    @RequestMapping("/request-param-v4")
    @ResponseBody
    public String requestParamV4(String username,
                                 int age) throws IOException {
        log.info("username = {}",username);
        log.info("age = {}",age);

        return "ok";
    }
    @RequestMapping("/request-param-required")
    @ResponseBody
    public String required(@RequestParam(required = false) String username,
                           @RequestParam(required = false) int age) throws IOException {
        log.info("username = {}",username);
        log.info("age = {}",age);

        return "ok";
    }
    @RequestMapping("/request-param-default")
    @ResponseBody
    public String defaultVal(@RequestParam(required = false) String username,
                           @RequestParam(required = false,defaultValue = "5") int age) throws IOException {
        log.info("username = {}",username);
        log.info("age = {}",age);

        return "ok";
    }
    @RequestMapping("/request-param-map")
    @ResponseBody
    public String requestMap(@RequestParam Map<String,Object> paramMap) throws IOException {
        log.info("username = {}",paramMap.get("username"));
        log.info("age = {}",paramMap.get("age"));

        return "ok";
    }

    /**
     * @ModelAttribute 사용
     * 참고: model.addAttribute(helloData) 코드도 함께 자동 적용됨, 뒤에 model을 설명할 때 자세히
    설명
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }

    /**
     * @ModelAttribute 생략 가능
     * String, int 같은 단순 타입 = @RequestParam
     * argument resolver 로 지정해둔 타입 외 = @ModelAttribute
     */
    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(),
                helloData.getAge());
        return "ok";
    }
}
