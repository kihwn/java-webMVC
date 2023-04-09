package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Objects;

public class UpdateQuestionFormController extends AbstractController {
    QuestionDao questionDao = new QuestionDao();

    HttpSession session;
    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }
    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        if (!UserSessionUtils.isLogined(session)) {
            return jspView("redirect:/users/loginForm");
        }

        String questionId = params.get("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));

        if (!question.isSameUser(Objects.requireNonNull(UserSessionUtils.getUserFromSession(session)))) {
            throw new IllegalArgumentException();
        }
        return jspView("/qna/updateForm.jsp").addObject("question", question);
    }
}
