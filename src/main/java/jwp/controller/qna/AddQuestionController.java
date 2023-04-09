package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import java.util.Map;

public class AddQuestionController extends AbstractController {
    QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        Question question = new Question(params.get("writer"), params.get("title"), params.get("contents"), 0);
        questionDao.insert(question);

        return jspView("redirect:/");
    }
}
