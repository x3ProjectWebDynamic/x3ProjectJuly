package hello.model;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional

public interface UserDao extends CrudRepository<User, Long> {

  /**
   * Return the user having the passed email or null if no user is found.
   * 
   * @param email the user email.
   */
  public User findByEmail(String email);


} // class UserDao