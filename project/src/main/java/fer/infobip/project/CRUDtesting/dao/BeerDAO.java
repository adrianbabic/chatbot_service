package fer.infobip.project.CRUDtesting.dao;

import fer.infobip.project.CRUDtesting.model.Beer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeerDAO extends JpaRepository<Beer, Integer> {

}
