package hello.controller;

import java.util.ArrayList;
import java.util.List;

import hello.model.User;
import hello.model.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  // ------------------------
  // PUBLIC METHODS
  // ------------------------
  
  /**
   * /create  --> Create a new user and save it in the database.
   * 
   * @param email User's email
   * @param name User's name
   * @return A string describing if the user is succesfully created or not.
   */
  @RequestMapping("/invite")
  @ResponseBody
  public String invite(String email, String name) {
    User user = null;
    try {
      user = new User(email, name);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created and will be invited soon! (  id = " + user.getId() + " email = " + 
    user.getEmail() + " Name = " + user.getName() + ")";
  }
  
  @RequestMapping("/QuickCreate")
  @ResponseBody
  public String QuickCreate(String email, String name, String password) {
    User user = null;
    try {
      user = new User(email, name, password);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error creating the user: " + ex.toString();
    }
    return "User succesfully created! (  id = " + user.getId() + " email = " + 
    user.getEmail() + " Name = " + user.getName() + user.getPassword() + ")";
  }
  
  

  /** 
   * /UserList --> send the user list
   * @return the JSON list of the user
   
  
  @RequestMapping("UserList")
  @ResponseBody
  public List<UserDao>  UserList() {      
      List<UserDao> userlist= new ArrayList<UserDao>();        
      userlist = UserDao.getAllUser();
      return userlist;
  }
  */
  
  
  
  /**
   * /delete  --> Delete the user having the passed id.
   * 
   * @param id The id of the user to delete
   * @return A string describing if the user is succesfully deleted or not.
   */
  
  @RequestMapping("/delete")
  @ResponseBody
  public String delete(long id) {
    try {
      User user = new User(id);
      userDao.delete(user);
    }
    catch (Exception ex) {
      return "Error deleting the user: " + ex.toString();
    }
    return "User succesfully deleted!";
  }
  
  /**
   * /get-by-email  --> Return the id for the user having the passed email.
   * 
   * @param email The email to search in the database.
   * @return The user id or a message error if the user is not found.
   */
  @RequestMapping("/get-by-email")
  @ResponseBody
  public String getByEmail(String email) {
    String userId;
    String sender;
    try {
      User user = userDao.findByEmail(email);
      userId = String.valueOf(user.getId());
      sender = "The user id is: " + userId;
    }
    catch (Exception ex) {
      return "User not found";
    }
    return String.format(sender, userId);
  }
 
  /**
  @RequestMapping("/alluser")
  @ResponseBody
  public List<User> userlist (){
	  
	return null;
	  
  }
  
  */
  
  @RequestMapping("/get-by-name")
  @ResponseBody
  public String getByName(String name) {
    String userId;
    String useremail;
    String username; 
   /** String userobj;*/
    try {
      User user = userDao.findByName(name);
      userId = String.valueOf(user.getId());
      useremail = String.valueOf(user.getEmail());
      username = String.valueOf(user.getName());
      /**userobj = String.valueOf(user.toString);*/
    }
    catch (Exception ex) {
      return "User not found";
    }
    return "User found.  Name = "+ username + ", "
    		+ " Id = "+ userId + ",  Email = " + useremail  ;
  }
  
  
  /** Authentication JAVA style
   * @param email
   * @param password
   * @return result of the authentication
   */
  
  @RequestMapping("/auth")
  @ResponseBody
  public String auth(String email, String password) {
    String userId;
    String username;
    try {
      User user = userDao.findByEmailAndPassword(email, password);
      userId = String.valueOf(user.getId());
      username = String.valueOf(user.getName());
    }
    catch (Exception ex) {
      return "User or password wrong check it and retry please";
    }
    return "Connection succesful; Welcome home Mr " + username + ", ID = " + userId ;
  }
  
  
  /**
   * /update  --> Update the email and the name for the user in the database 
   * having the passed id.
   * 
   * @param id The id for the user to update.
   * @param email The new email.
   * @param name The new name.
   * @return A string describing if the user is succesfully updated or not.
   */
  @RequestMapping("/update")
  @ResponseBody
  public String updateUser(long id, String email, String name) {
    try {
      User user = userDao.findOne(id);
      user.setEmail(email);
      user.setName(name);
      userDao.save(user);
    }
    catch (Exception ex) {
      return "Error updating the user: " + ex.toString();
    }
    return "User succesfully updated!";
  }

  // ------------------------
  // PRIVATE FIELDS
  // ------------------------

  @Autowired
  private UserDao userDao;
  
} // class UserController
