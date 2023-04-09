package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import java.util.List;
import java.util.Map;

public class ShowController extends AbstractController {
    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDAO = new AnswerDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {

        String questionId = params.get("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        List<Answer> answers = answerDAO.findAllByQuestionId(Integer.parseInt(questionId));

        return jspView("/qna/show.jsp").addObject("question", question).addObject("answers",answers);
    }
}
