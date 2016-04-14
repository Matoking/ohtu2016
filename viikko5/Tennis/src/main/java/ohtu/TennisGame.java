package ohtu;

import java.util.HashMap;
import java.util.Map;

public class TennisGame {
    
    private int m_score1 = 0;
    private int m_score2 = 0;
    private String player1Name;
    private String player2Name;
    
    private Map<String, Integer> points;
    
    public static int POINT_THRESHOLD = 4;

    public TennisGame(String player1Name, String player2Name) {
        points = new HashMap<String, Integer>();
        points.put(player1Name, 0);
        points.put(player2Name, 0);
    }

    public void wonPoint(String playerName) {
        int currentScore = points.get(playerName);
        points.put(playerName, currentScore+1);
    }
    
    private String getScoreForEqualScores(int score) {
        switch (score) {
            case 0:
                return "Love-All";
            case 1:
                return "Fifteen-All";
            case 2:
                return "Thirty-All";
            case 3:
                return "Forty-All";
            default:
                return "Deuce";
        }
    }
    
    private String getScoreForScoresPastThreshold(int firstScore, int secondScore) {
        int minusResult = firstScore - secondScore;
        
        if (minusResult == 1) return "Advantage player1";
        else if (minusResult == -1) return "Advantage player2";
        else if (minusResult >= 2) return "Win for player1";
        else return "Win for player2";
    }
    
    private String getScoreAlias(int score) {
        switch(score)
        {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }
        
        return "";
    }
    
    private String getScoreForScoresBelowThreshold(int firstScore, int secondScore) {
        String score = "";
        int tempScore = 0;
        for (int i=1; i<3; i++) {
            if (i == 1) { 
                tempScore = firstScore;
            } else {
                score += "-";
                tempScore = secondScore;
            }
            
            score += getScoreAlias(tempScore);
        }
        
        return score;
    }

    public String getScore() {
        int firstScore = (int)points.values().toArray()[0];
        int secondScore = (int)points.values().toArray()[1];
        
        if (firstScore == secondScore) {
            return getScoreForEqualScores(firstScore);
        } else if (firstScore >= POINT_THRESHOLD || secondScore >= POINT_THRESHOLD) {
            return getScoreForScoresPastThreshold(firstScore, secondScore);
        } else {
            return getScoreForScoresBelowThreshold(firstScore, secondScore);
        }
    }
}