package service;

import dao.DAOuserSQL;
import entity.CurrentState;
import entity.Message;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceMessaging {
    DAOuserSQL dao;

    public ServiceMessaging() throws SQLException {
        dao = new DAOuserSQL();
    }

    public void add(int whom, String content) throws SQLException {
        dao.addMessage(CurrentState.getCurrentUser().getId(), whom, content);
    }

    public List<Message> getMessagesToMe(int who) throws SQLException {
        ResultSet messages = dao.getMessages(who, CurrentState.getCurrentUser().getId());
        return makeMessage(messages);
    }

    public List<Message> getMessagesFromMe(int whom) throws SQLException {
        ResultSet messages = dao.getMessages(CurrentState.getCurrentUser().getId(), whom);
        return makeMessage(messages);
    }

    public List<Message> getAll(int whom) throws SQLException {
        List<Message> messagesFromMe = getMessagesFromMe(whom);
        List<Message> messagesToMe = getMessagesToMe(whom);
        List<Message> all = new ArrayList<>(messagesFromMe);
        all.addAll(messagesToMe);
        return all.stream()
                .sorted((m1, m2) -> m1.getId() - m2.getId()).collect(Collectors.toList());
    }

    private List<Message> makeMessage(ResultSet messages) throws SQLException {
        List<Message> msgs = new ArrayList<>();
        while (messages.next()) {
            msgs.add(new Message(
                    messages.getInt("id"),
                    messages.getInt("who"),
                    messages.getInt("whom"),
                    messages.getString("content"),
                    messages.getTimestamp("date")
            ));
        }
        return msgs;
    }


}
