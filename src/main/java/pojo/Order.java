package pojo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate creationDate;
    @OneToMany(mappedBy ="order")
    @Cascade(org.hibernate.annotations.CascadeType.PERSIST)
    private List<Product> productList;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
