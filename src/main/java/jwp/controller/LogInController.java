package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.ModelAndView;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;


public class LogInController extends AbstractController {
    UserDao userDao = new UserDao();

    HttpSession session;
    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        String password = params.get("password");
        User logInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(logInUser)) {
            session.setAttribute("user", user);
            return jspView("redirect:/");
        }
        return jspView("redirect:/user/loginFailed");
    }
}
