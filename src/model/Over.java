package model;

/**
 * The type Over.
 */
public class Over {
    private int overNumber;
    private  Player striker;
    private  Player nonStriker;
    private  Balls balls;
    private  Player bowler;
    private  Integer ballsInOver;

    /**
     * Gets over number.
     *
     * @return the over number
     */
    public int getOverNumber() {
        return overNumber;
    }

    /**
     * Sets over number.
     *
     * @param overNumber the over number
     */
    public void setOverNumber(int overNumber) {
        this.overNumber = overNumber;
    }

    /**
     * Gets striker.
     *
     * @return the striker
     */
    public Player getStriker() {
        return striker;
    }

    /**
     * Sets striker.
     *
     * @param striker the striker
     */
    public void setStriker(Player striker) {
        this.striker = striker;
    }

    /**
     * Gets non striker.
     *
     * @return the non striker
     */
    public Player getNonStriker() {
        return nonStriker;
    }

    /**
     * Sets non striker.
     *
     * @param nonStriker the non striker
     */
    public void setNonStriker(Player nonStriker) {
        this.nonStriker = nonStriker;
    }

    /**
     * Gets balls.
     *
     * @return the balls
     */
    public Balls getBalls() {
        return balls;
    }

    /**
     * Sets balls.
     *
     * @param balls the balls
     */
    public void setBalls(Balls balls) {
        this.balls = balls;
    }

    /**
     * Gets bowler.
     *
     * @return the bowler
     */
    public Player getBowler() {
        return bowler;
    }

    /**
     * Sets bowler.
     *
     * @param bowler the bowler
     */
    public void setBowler(Player bowler) {
        this.bowler = bowler;
    }

    /**
     * Gets balls in over.
     *
     * @return the balls in over
     */
    public Integer getBallsInOver() {
        return ballsInOver;
    }

    /**
     * Sets balls in over.
     *
     * @param ballsInOver the balls in over
     */
    public void setBallsInOver(Integer ballsInOver) {
        this.ballsInOver = ballsInOver;
    }
}
