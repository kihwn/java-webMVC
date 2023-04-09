package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.ModelAndView;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;


public class UpdateUserFormController extends AbstractController {

    UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        String userId = params.get("userId");
        User user = userDao.findByUserId(userId);
        if (user != null) {
            return jspView("/user/updateForm.jsp").addObject("user",user);
        }
        return jspView("redirect:/");
    }
}
