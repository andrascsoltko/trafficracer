package Model;

import javax.persistence.*;

@Entity
public class tbl_user {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int userID;

    /**
     * The username of the player.
     * */
    @Column(name = "userName")
    private String username;

    /**
     * The password of the user.
     * */
    @Column(name = "userPassword")
    private String password;

    public tbl_user(String username, String passowrd){
        this.username = username;
        this.password = passowrd;

    }

    public tbl_user(){}

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "tbl_user{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
