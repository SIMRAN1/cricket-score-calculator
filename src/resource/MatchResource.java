package resource;

import model.CricketTeam;
import model.Over;
import model.Player;
import model.Score;
import service.MatchService;

import java.util.*;

/**
 * The type Match resource.
 */
public class MatchResource {

    private MatchService matchService;

    /**
     * Instantiates a new Match resource.
     */
    public MatchResource() {
        matchService = new MatchService();
    }

    /**
     * Print match score card
     */
    private void printMatchScoreCard() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter number of players for each team");
        int noOfPlayers = scanner.nextInt();
        System.out.println("Enter number of overs");
        int noOfOvers = scanner.nextInt();
        List<Player> battingOrderList = new ArrayList<>();
        List<CricketTeam> cricketTeamList = new ArrayList<>();

        List<String> overRuns = new ArrayList<>();
        for (int teamIndex = 0; teamIndex < 2; teamIndex++) {
            CricketTeam cricketTeam = initCricketTeam(noOfPlayers, noOfOvers, battingOrderList, teamIndex);
            battingOrderList.clear();
            System.out.println("Enter Batting Order List");
            for (int index = 1; index <= noOfPlayers; index++) {
                String playerName = scanner.next();
                Player player = new Player();
                player.setName(playerName);
                player.setScore(new Score());
                battingOrderList.add(player);
            }
            for (int overIndex = 0; overIndex < noOfOvers; overIndex++) {
                System.out.println("Enter over " + (overIndex + 1));
                Over over = new Over();
                over.setOverNumber(overIndex+1);
                cricketTeam.setOver(over);
                int wicketCounter = cricketTeam.getScore().getWickets();
                for (int index = 1; index <= 6; index++) {
                    String ballResult = scanner.next();
                    cricketTeam.setNoOfBallsPlayed(cricketTeam.getNoOfBallsPlayed()+1);
                    if(ballResult.equals("Wd") || ballResult.equals("Nb")) {
                        cricketTeam.setNoOfBallsPlayed(cricketTeam.getNoOfBallsPlayed()-1);
                        index--;
                    }
                    overRuns.add(ballResult);
                    if(ballResult.equals("W")) {
                        wicketCounter++;
                        if(wicketCounter+1 == cricketTeam.getNumberOfPlayers()) {
                            break;
                        }
                    }
                }
                HashMap<String, Score> playerScoreMap = matchService.calculateScore(overRuns, cricketTeam);
                overRuns.clear();
                if(cricketTeam.getAllOutIndicator()) {
                    break;
                }
                System.out.println("Result for over " + (overIndex + 1));
                for (Map.Entry<String, Score> playerScoreMapEntity : playerScoreMap.entrySet()) {
                    String playerName = playerScoreMapEntity.getKey();
                    Score scoreValue = playerScoreMapEntity.getValue();
                    System.out.println();
                    System.out.print(playerName + " ");
                    System.out.print(scoreValue.getBatterRuns() + " ");
                    System.out.print(scoreValue.getFours() + " ");
                    System.out.print(scoreValue.getSixes() + " ");
                    System.out.print(scoreValue.getNoOfBallsFaced() + " ");
                    System.out.println();
                    // ...
                }
            }
            System.out.println("Total Score " + cricketTeam.getScore().getTotalTeamScore() + " / " +  cricketTeam.getScore().getWickets());
            System.out.println("Overs " + matchService.getNowOfOversPlayed(cricketTeam));
            cricketTeamList.add(cricketTeam);
        }
        int differnceOfScore =  cricketTeamList.get(0).getScore().getTotalTeamScore()-cricketTeamList.get(1).getScore().getTotalTeamScore();
        if(differnceOfScore>0) {
            System.out.println("Team One Win by " + differnceOfScore + "runs");
        } else if (differnceOfScore<0) {
            System.out.println("Team Two Win by " + cricketTeamList.get(1).getNoOfPlayersLeft() + "wickets");
        }
    }

    /**
     * Initialize cricket team
     * @param noOfPlayers
     * @param noOfOvers
     * @param battingOrderList
     * @param teamIndex
     * @return
     */
    private CricketTeam initCricketTeam(int noOfPlayers, int noOfOvers, List<Player> battingOrderList, int teamIndex) {
        CricketTeam cricketTeam = new CricketTeam();
        cricketTeam.setScore(new Score());
        cricketTeam.setNumberOfOvers(noOfOvers);
        cricketTeam.setPlayerListInBattingOrder(battingOrderList);
        cricketTeam.setNumberOfPlayers(noOfPlayers);
        cricketTeam.setTeamName("Team " + teamIndex +1);
        cricketTeam.setNoOfBallsPlayed(0);
        return cricketTeam;
    }

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String args[]) {
        MatchResource matchResource = new MatchResource();
        matchResource.printMatchScoreCard();
    }
}
