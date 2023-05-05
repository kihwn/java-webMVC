package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        List<Question> q = questionDao.findAll();
        /*
        'setAttribute'는 데이터 JSP(웹어플리케이션 서버 - 동적 파일을 정적 파일로 바꿔주는 역할)에 보내주는 역할.
         */
        req.setAttribute("questions",q);


        return "/home.jsp";
    }

}