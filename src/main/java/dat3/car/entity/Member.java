package dat3.car.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class Member {
    @Id
    private String username;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String city;
    private String zip;
    private boolean approved;
    private int ranking;
    public Member(String user, String password, String email,
                  String firstName, String lastName, String street, String city, String zip) {
        this.username = user;
        this.password = password;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }


    @CreationTimestamp
    LocalDateTime created;

    @UpdateTimestamp
    LocalDateTime updated;

    @ElementCollection
    List<String> favoriteCarColours = new ArrayList<>();

    @ElementCollection
    @MapKeyColumn(name = "description")
    @Column(name = "phone_number")
    Map<String,String> phones = new HashMap<>();

}
