package model;

/**
 * The type Player.
 */
public class Player {
    private String playerId;
    private String name;
    private Score score;

    /**
     * Gets player id.
     *
     * @return the player id
     */
    public String getPlayerId() {
        return playerId;
    }

    /**
     * Sets player id.
     *
     * @param playerId the player id
     */
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
