package service;

import model.CricketTeam;
import model.Player;
import model.Score;
import java.util.HashMap;
import java.util.List;

/**
 * The type Match service.
 */
public class MatchService {

    /**
     * Calculate score of each team.
     *
     * @param OverRuns    the over runs
     * @param cricketTeam the cricket team
     * @return the hash map
     */
    public  HashMap<String, Score> calculateScore(List<String> OverRuns, CricketTeam cricketTeam) {

         Player striker = new Player();
         striker.setName(cricketTeam.getPlayerListInBattingOrder().get(cricketTeam.getScore().getWickets()).getName());
         Player nonStriker = new Player();
         nonStriker.setName(cricketTeam.getPlayerListInBattingOrder().get(cricketTeam.getScore().getWickets()+1).getName());
         cricketTeam.setNoOfPlayersLeft(cricketTeam.getNoOfPlayersLeft()+2);
        Score strikerPlayerScore;
        Score nonStrikerPlayerScore;
         if(cricketTeam.getOver().getOverNumber()==0) {
              strikerPlayerScore = new Score();
              nonStrikerPlayerScore = new Score();
         } else {
             strikerPlayerScore = cricketTeam.getPlayerListInBattingOrder().get(cricketTeam.getScore().getWickets()).getScore();
             nonStrikerPlayerScore = cricketTeam.getPlayerListInBattingOrder().get(cricketTeam.getScore().getWickets()+1).getScore();
         }
        cricketTeam.getOver().setOverNumber(cricketTeam.getOver().getOverNumber()+1);
        Boolean nonStrikerIndicator = false;
        HashMap<String,Score> strikerScoreMap = new HashMap<>();
        int wicketCounter=2;
        for(String ballScore: OverRuns) {
            if(ballScore.equals("W")) {

                cricketTeam.getScore().setWickets(cricketTeam.getScore().getWickets()+1);
                if(!nonStrikerIndicator) {
                    strikerScoreMap.put(striker.getName(),strikerPlayerScore);
                    striker = new Player();
                    striker.setName(cricketTeam.getPlayerListInBattingOrder().get(wicketCounter).getName());
                    strikerPlayerScore = new Score();
                } else {
                    strikerScoreMap.put(nonStriker.getName(),nonStrikerPlayerScore);
                    nonStriker = new Player();
                    nonStriker.setName(cricketTeam.getPlayerListInBattingOrder().get(wicketCounter).getName());
                    nonStrikerPlayerScore = new Score();
                }
                wicketCounter++;
                cricketTeam.setNoOfPlayersLeft(cricketTeam.getNoOfPlayersLeft()+1);
            } else if (ballScore.equals("Wd")) {
                handleWideDeliveries(cricketTeam, strikerPlayerScore, nonStrikerPlayerScore, nonStrikerIndicator);
            } else if (ballScore.equals("Nb")) {
                cricketTeam.getScore().setNoBalls(cricketTeam.getScore().getNoBalls());
            } else if(!nonStrikerIndicator) {
                nonStrikerIndicator = handleStrikerPlayer(cricketTeam, strikerPlayerScore, nonStrikerIndicator, ballScore);
            } else {
                nonStrikerIndicator =  handleNonStrikerPlayer(cricketTeam, nonStrikerPlayerScore, nonStrikerIndicator, ballScore);
            }

        }
        setScoreMap(cricketTeam, striker, nonStriker, strikerPlayerScore, nonStrikerPlayerScore, strikerScoreMap);
        return strikerScoreMap;
    }

    /**
     * handle wide ball deleiveries
     * @param cricketTeam
     * @param strikerPlayerScore
     * @param nonStrikerPlayerScore
     * @param nonStrikerIndicator
     */
    private void handleWideDeliveries(CricketTeam cricketTeam, Score strikerPlayerScore, Score nonStrikerPlayerScore, Boolean nonStrikerIndicator) {
        cricketTeam.getScore().setTotalTeamScore(cricketTeam.getScore().getTotalTeamScore()+1);
        cricketTeam.getScore().setWideDeliveries(cricketTeam.getScore().getWideDeliveries()+1);
        if(!nonStrikerIndicator) {
            strikerPlayerScore.setNoOfBallsFaced(strikerPlayerScore.getNoOfBallsFaced()+1);
        } else {
            nonStrikerPlayerScore.setNoOfBallsFaced(nonStrikerPlayerScore.getNoOfBallsFaced()+1);
        }
    }

    /**
     * set cricket score map
     * @param cricketTeam
     * @param striker
     * @param nonStriker
     * @param strikerPlayerScore
     * @param nonStrikerPlayerScore
     * @param strikerScoreMap
     */
    private void setScoreMap(CricketTeam cricketTeam, Player striker, Player nonStriker, Score strikerPlayerScore, Score nonStrikerPlayerScore, HashMap<String, Score> strikerScoreMap) {
        strikerScoreMap.put(striker.getName(), strikerPlayerScore);
        strikerScoreMap.put(nonStriker.getName(), nonStrikerPlayerScore);
        int totalNumberOfPlayes = cricketTeam.getNumberOfPlayers();
        for(int index=0; index<totalNumberOfPlayes;index++) {
            if(!strikerScoreMap.containsKey(cricketTeam.getPlayerListInBattingOrder().get(index).getName())) {
                if(cricketTeam.getPlayerListInBattingOrder().get(index).getScore() != null) {
                    strikerScoreMap.put(cricketTeam.getPlayerListInBattingOrder().get(index).getName(), cricketTeam.getPlayerListInBattingOrder().get(index).getScore());
                } else {
                    strikerScoreMap.put(cricketTeam.getPlayerListInBattingOrder().get(index).getName(), new Score());
                }
            }
        }
    }

    /**
     * handle striker player
     * @param cricketTeam
     * @param nonStrikerPlayerScore
     * @param nonStrikerIndicator
     * @param ballScore
     * @return
     */
    private Boolean  handleNonStrikerPlayer(CricketTeam cricketTeam, Score nonStrikerPlayerScore, Boolean nonStrikerIndicator, String ballScore) {
        int ballScoreValue;
        ballScoreValue = Integer.parseInt(ballScore);
        cricketTeam.getScore().setTotalTeamScore(cricketTeam.getScore().getTotalTeamScore()+ballScoreValue);
        nonStrikerPlayerScore.setNoOfBallsFaced(nonStrikerPlayerScore.getNoOfBallsFaced()+1);
        if (ballScoreValue % 2 != 0) {
            nonStrikerIndicator = false;
            nonStrikerPlayerScore.setBatterRuns(nonStrikerPlayerScore.getBatterRuns() + ballScoreValue);
        } else  {
            nonStrikerPlayerScore.setBatterRuns(nonStrikerPlayerScore.getBatterRuns() + ballScoreValue);
            if (ballScoreValue % 4 == 0) {
                nonStrikerPlayerScore.setFours(nonStrikerPlayerScore.getFours() + 1);
            } else if (ballScoreValue % 6 == 0) {
                nonStrikerPlayerScore.setSixes(nonStrikerPlayerScore.getSixes() + 1);
            }
        }
        return nonStrikerIndicator;
    }

    /**
     * handle the non striker player
     * @param cricketTeam
     * @param strikerPlayerScore
     * @param nonStrikerIndicator
     * @param ballScore
     * @return
     */
    private Boolean handleStrikerPlayer(CricketTeam cricketTeam, Score strikerPlayerScore, Boolean nonStrikerIndicator, String ballScore) {
        int ballScoreValue;
        ballScoreValue = Integer.parseInt(ballScore);
        strikerPlayerScore.setNoOfBallsFaced(strikerPlayerScore.getNoOfBallsFaced()+1);
        cricketTeam.getScore().setTotalTeamScore(cricketTeam.getScore().getTotalTeamScore()+ballScoreValue);
        if (ballScoreValue % 2 != 0) {
            nonStrikerIndicator = true;
            strikerPlayerScore.setBatterRuns(strikerPlayerScore.getBatterRuns() + ballScoreValue);
        } else {
            strikerPlayerScore.setBatterRuns(strikerPlayerScore.getBatterRuns() + ballScoreValue);
            if (ballScoreValue % 4 == 0) {
                strikerPlayerScore.setFours(strikerPlayerScore.getFours() + 1);
            } else if (ballScoreValue % 6 == 0) {
                strikerPlayerScore.setSixes(strikerPlayerScore.getSixes() + 1);
            }
        }
        return nonStrikerIndicator;
    }

    /**
     * Gets now of overs played.
     *
     * @param cricketTeam the cricket team
     * @return the now of overs played
     */
    public  float getNowOfOversPlayed(CricketTeam cricketTeam) {
        return (float) (cricketTeam.getNoOfBallsPlayed())/10;
    }
}
