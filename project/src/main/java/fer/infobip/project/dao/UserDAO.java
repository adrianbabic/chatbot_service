package fer.infobip.project.dao;

import fer.infobip.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    User findByPhoneNumber(String phoneNumber);
}
