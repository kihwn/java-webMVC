package core.mvc;

import core.mvc.view.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

    private RequestMapping requestMapping;

    @Override
    public void init() {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        Controller controller = getController(req,resp);
        if (controller == null) return;

        try {
            Map<String, String> paramMap = createParamMap(req);
            ModelAndView view = controller.execute(paramMap);
            view.render(req, resp);
        } catch (Throwable e) {
            throw new ServletException(e.getMessage());
        }
    }

    private Controller getController(HttpServletRequest request, HttpServletResponse response) {
        Controller controller = requestMapping.getController(request);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return null;
        }
        setControllerFields(request, controller);
        return controller;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

    private void setControllerFields(HttpServletRequest req, Controller controller) {
        controller.setSession(req.getSession());
    }
}
