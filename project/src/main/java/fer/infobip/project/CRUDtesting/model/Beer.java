package fer.infobip.project.CRUDtesting.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Entity
@Table(name = "BEER")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Beer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BEER_ID")
    int beerId;
    @Column(name = "BEER_NAME")
    String beerName;
    @Column(name = "BREWERY_NAME")
    String breweryName;


}
