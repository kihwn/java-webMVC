package jwp.controller;

import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionFormController implements Controller {
    //ctrl + O : Override

    //1단계 CREATE 미션
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if(user == null) {
            return "/user/login";
        }

        return "/qna/form.jsp";



    }
}
