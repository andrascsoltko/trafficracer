package Model;

import javax.persistence.*;

/**
 * This is the entity for the high scores.
 */
@Entity
public class tbl_score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;


    /**
    * The username of the player.
    * */
    @Column(name = "userName")
    private String username;

    /**
     * The achieved score of the player.
     * */
    @Column(name = "Score")
    private int score;


    /**
     * Constructor for the class.
     * @param username is the username.
     * @param score is the score achieved.
     * */
    public tbl_score(String username, int score){
        this.username = username;
        this.score = score;
    }

    /**
     * Empty constructor.
     * */
    public tbl_score(){}

    /**
     * Getter for userID.
     * @return the userID.
     * */
    public int getUserID() {
        return userID;
    }

    /**
     * Setter for userID.
     * @param userID is the userID.
     * */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * Getter for username.
     * @return the username.
     * */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     * @param username  is the username.
     * */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for score.
     * @return the score.
     * */
    public int getScore() {
        return score;
    }

    /**
     * @param score achieved score.
     * Setter for score.
     * */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return a row from the table in string format.
     * */
    @Override
    public String toString() {
        return "tbl_score{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }


}
