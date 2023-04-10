package jwp.mock;

import core.mvc.Controller;
import core.mvc.RequestMapping;
import core.mvc.view.ModelAndView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class RequestMappingTest {
    private RequestMapping requestMapping;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        requestMapping = new RequestMapping();
        response = new MockHttpServletResponse();
    }

    @Test
    void home() throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest("GET","/");
        Controller controller = requestMapping.getController(request);
        ModelAndView execute = controller.execute(createParamMap(request));
        execute.render(request,response);
        assertEquals("/home.jsp",response.getForwardedUrl());
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
