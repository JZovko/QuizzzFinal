package ba.sum.fsre.ednevnik.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String title;
    @NotBlank
    private String description;
    @CreatedDate
    private LocalDateTime datum;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)//cascdetype da operacije na roditelju bude i prenjete na djecu
    private List<Pitanja> pitanja = new ArrayList<>();
}
