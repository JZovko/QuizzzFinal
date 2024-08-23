package ba.sum.fsre.ednevnik.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Odgovori {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String OdgovorText;

    private boolean isCorrect ;

    // Getter for isCorrect
    public boolean isCorrect() {
        return isCorrect;
    }

    // Setter for isCorrect
    public void setCorrect(boolean correct) {
        this.isCorrect = correct;
    }




}
