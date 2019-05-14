package Model;

import javax.persistence.*;


/**
 * This is the entity for the user informations.
 * */
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

    /**
     * Constructor for the class.
     * @param username is the username.
     * @param passowrd is the password.
     * */
    public tbl_user(String username, String passowrd){
        this.username = username;
        this.password = passowrd;

    }

    /**
     * Empty constructor.
     * */
    public tbl_user(){}

    /**
     * Getter for UserID.
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
     * Getter for password.
     * @return the password.
     * */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for password.
     * @param password is the password.
     * */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return a row from the table in string format.
     * */
    @Override
    public String toString() {
        return "tbl_user{" +
                "userID=" + userID +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

}
