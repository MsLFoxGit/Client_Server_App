package Netology.ClientServerApp;

import java.util.*;

public class ServerAnswers {
    private Map<String,String[]> serverAnswersMap;
    private String userName;

    public ServerAnswers(String userName){
        this.userName = userName;
        Map <String, String[]> defaultAnswers = new HashMap<>();
        defaultAnswers.put( "yes", new String[]{
                String.format("Welcome to the kids area, %s! Let's play!", userName),"q"});
        defaultAnswers.put("no"  , new String[] {
                String.format("Welcome to the adult zone, %s! Have a good rest, or a good working day!", userName),"q"});
        defaultAnswers.put("quit", new String[] {
            String.format("Goodbye, %s!", userName), "q" });
        this.serverAnswersMap = defaultAnswers;

    }
    public String getServerAnswer(String inputAnswer) throws NullPointerException {
        return serverAnswersMap.get(inputAnswer.trim().toLowerCase())[0];
    }
    public String getServerCommand(String inputAnswer) throws NullPointerException {
        return serverAnswersMap.get(inputAnswer.trim().toLowerCase())[1];
    }
}
