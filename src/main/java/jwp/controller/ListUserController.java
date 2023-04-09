package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class ListUserController extends AbstractController {
    UserDao userDao = new UserDao();

    HttpSession session;
    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        if (UserSessionUtils.isLogined(session)) {
            return jspView("/user/list.jsp").addObject("users", userDao.findAll());
        }
        return jspView("redirect:/user/loginForm");
    }
}
