package jwp.controller.qna.api;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class AddAnswerController extends AbstractController {
    AnswerDao answerDao = new AnswerDao();
    QuestionDao questionDao = new QuestionDao();
    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        Answer answer = new Answer(Integer.parseInt(params.get("questionId")), params.get("writer"), params.get("contents"));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(answer.getQuestionId());
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        return jsonView().addObject("answer",savedAnswer);
    }
}
