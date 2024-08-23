package ba.sum.fsre.ednevnik.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pitanja {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(min=3, max=120, message = "Polje ime mora biti izmđu 3 i 120 znakova.")
    private String Text;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Odgovori> odgovori = new ArrayList<>();

}
