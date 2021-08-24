package com.my1rm.model.database;

import com.my1rm.model.ResponseMessage;
import com.my1rm.validator.ValidatorResponse;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "ATTEMPT")
@NoArgsConstructor
@Getter
@Setter
public class Attempt {

    @Id @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(targetEntity = Exercise.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "exercise_id")
    @NotNull
    private Exercise exercise;

    @ManyToOne(targetEntity = Season.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "season_id")
    @NotNull
    private Season season;

    @Column(name = "weight", columnDefinition = "FLOAT")
    @NotNull
    private float weight;

    @Column(name = "success", columnDefinition = "TINYINT")
    @NotNull
    private boolean success;

    @Column(name = "repetitions", columnDefinition = "SMALLINT")
    @NotNull
    private short repetitions;

    @Column(name = "date", columnDefinition = "TIMESTAMP")
    @NotNull
    private Date date;

    public static ValidatorResponse isWeightValid(Float weight){
        if(weight <= 0) return new ValidatorResponse(false, ResponseMessage.AttemptResponseMessage.WEIGHT_INCORRECT_DATA);

        return new ValidatorResponse(true, null);
    }

    public static ValidatorResponse isRepetitionsValid(int repetitions){
        if(repetitions < 1 || repetitions > 10) return new ValidatorResponse(false, ResponseMessage.AttemptResponseMessage.REPETITIONS_INCORRECT_DATA);

        return new ValidatorResponse(true, null);
    }
}
