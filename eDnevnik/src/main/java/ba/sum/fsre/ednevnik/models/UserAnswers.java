package ba.sum.fsre.ednevnik.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    /*
    @ManyToOne
    @JoinColumn(name = "pitanja_id")
    private Pitanja pitanja;
    @ManyToOne
    @JoinColumn(name = "odgovori_id")
    private Odgovori odgovori;

     */
    private int TocnoOdgovoreno;
    private LocalDateTime OdgovorenoU;

}
