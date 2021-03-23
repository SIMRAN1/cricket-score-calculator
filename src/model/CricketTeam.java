package model;

import java.util.List;

/**
 * The type Cricket team.
 */
public class CricketTeam {

    private Integer numberOfPlayers;
    private Integer numberOfOvers;
    private List<Player> playerListInBattingOrder;
    private  Over over;
    private Score score;
    private String teamName;
    private int noOfPlayersLeft;
    private int noOfBallsPlayed;
    private boolean allOutIndicator;

    private List<CricketTeam> cricketTeams;

    /**
     * Gets number of players.
     *
     * @return the number of players
     */
    public Integer getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Sets number of players.
     *
     * @param numberOfPlayers the number of players
     */
    public void setNumberOfPlayers(Integer numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Sets number of overs.
     *
     * @param numberOfOvers the number of overs
     */
    public void setNumberOfOvers(Integer numberOfOvers) {
        this.numberOfOvers = numberOfOvers;
    }

    /**
     * Gets number of overs.
     *
     * @return the number of overs
     */
    public int getNumberOfOvers() {
        return numberOfOvers;
    }

    /**
     * Sets number of overs.
     *
     * @param numberOfOvers the number of overs
     */
    public void setNumberOfOvers(int numberOfOvers) {
        this.numberOfOvers = numberOfOvers;
    }

    /**
     * Gets player list in batting order.
     *
     * @return the player list in batting order
     */
    public List<Player> getPlayerListInBattingOrder() {
        return playerListInBattingOrder;
    }

    /**
     * Sets player list in batting order.
     *
     * @param playerListInBattingOrder the player list in batting order
     */
    public void setPlayerListInBattingOrder(List<Player> playerListInBattingOrder) {
        this.playerListInBattingOrder = playerListInBattingOrder;
    }

    /**
     * Gets over.
     *
     * @return the over
     */
    public Over getOver() {
        return over;
    }

    /**
     * Sets over.
     *
     * @param over the over
     */
    public void setOver(Over over) {
        this.over = over;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public Score getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(Score score) {
        this.score = score;
    }

    /**
     * Gets team name.
     *
     * @return the team name
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * Sets team name.
     *
     * @param teamName the team name
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    /**
     * Gets no of players left.
     *
     * @return the no of players left
     */
    public int getNoOfPlayersLeft() {
        return noOfPlayersLeft;
    }

    /**
     * Sets no of players left.
     *
     * @param noOfPlayersLeft the no of players left
     */
    public void setNoOfPlayersLeft(int noOfPlayersLeft) {
        this.noOfPlayersLeft = noOfPlayersLeft;
    }

    public boolean getAllOutIndicator() {
        return allOutIndicator;
    }

    public void setAllOutIndicator(boolean allOutIndicator) {
        this.allOutIndicator = allOutIndicator;
    }

    public List<CricketTeam> getCricketTeams() {
        return cricketTeams;
    }

    public void setCricketTeams(List<CricketTeam> cricketTeams) {
        this.cricketTeams = cricketTeams;
    }

    public int getNoOfBallsPlayed() {
        return noOfBallsPlayed;
    }

    public void setNoOfBallsPlayed(int noOfBallsPlayed) {
        this.noOfBallsPlayed = noOfBallsPlayed;
    }
}
