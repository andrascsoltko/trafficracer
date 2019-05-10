package Model;

import javax.persistence.*;

@Entity
public class tbl_score {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    @Column(name = "userName")
    private String username;

    @Column(name = "Score")
    private int score;

    public tbl_score(String username, int score){
        this.username = username;
        this.score = score;
    }

    public tbl_score(){}

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "tbl_score{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", score=" + score +
                '}';
    }


}
