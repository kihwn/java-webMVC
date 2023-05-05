package jwp.dao;

import core.jdbc.JdbcTemplate;
import core.jdbc.KeyHolder;
import jwp.model.Question;

import java.security.Key;
import java.sql.SQLException;
import java.util.List;

public class QuestionDao {

    JdbcTemplate<Question> jdbcTemplate = new JdbcTemplate<>();
    /*
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createDate;
    private int countOfAnswer;
     */


    //List<Question>반환 메서드 구현
    public List<Question> findAll() throws SQLException {
        String sql = "SELECT * FROM QUESTIONS";

        //Question table의 모든 정보를 가져와서 객체로 반환
        return jdbcTemplate.query(sql,
                rs -> new Question(rs.getLong("questionId"), rs.getString("writer"), rs.getString("title"),
                        rs.getString("contents"),rs.getDate("createdDate"),rs.getInt("countOfAnswer")));
    }

    //정우님 코드 복붙하겠습니다 ...

    public Question insert(Question question) throws SQLException{
        KeyHolder keyHolder = new KeyHolder();


        String sql = "INSERT INTO QUESTIONS (WRITER, TITLE, CONTENTS, CREATEDDATE, COUNTOFANSWER) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(sql,pstmt -> {
            pstmt.setString(1, question.getWriter());
            pstmt.setString(2, question.getTitle());
            pstmt.setString(3, question.getContents());
            pstmt.setObject(4,question.getCreatedDate());
        }, keyHolder);
        return findByQuestionId(keyHolder.getId());

    }

    public Question findByQuestionId(int questionId) throws SQLException {
        return jdbcTemplate.queryForObject("SELECT questionId, writer, title,contents, createdDate,countOfAnswer FROM QUESTIONS WHERE questionID=?",
                pstmt -> pstmt.setObject(1, questionId),
                rs -> new Question(rs.getInt("questionId"),
                        rs.getString("writer"),
                        rs.getString("title"),
                        rs.getString("contents"),
                        rs.getDate("createdDate"),
                        rs.getInt("countOfAnswer")));
    }

}
