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
import java.util.regex.Pattern;

@Entity
@Table(name = "EXERCISE")
@NoArgsConstructor
@Getter
@Setter
public class Exercise {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @Column(name = "name", columnDefinition = "VARCHAR(30)")
    @NotNull
    private String name;

    @Column(name = "goal", columnDefinition = "FLOAT")
    private float goal;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

    @OneToMany(targetEntity = Attempt.class, fetch = FetchType.LAZY, mappedBy = "exercise", cascade = CascadeType.ALL)
    private Set<Attempt> attempt;

    public static ValidatorResponse isNameValid(String name){
        if(name.length() < 3 || name.length() < 16) return new ValidatorResponse(false, ResponseMessage.ExerciseResponseMessage.NAME_INCORRECT_LENGTH);
        if(!Pattern.compile("[A-Z,a-z,\\-,_,.,0-9]*").matcher(name).matches()) return new ValidatorResponse(false, ResponseMessage.ExerciseResponseMessage.NAME_INCORRECT_FORMAT);

        return new ValidatorResponse(true, null);
    }

    public static ValidatorResponse isGoalValid(float goal){
        if(goal <= 0) return new ValidatorResponse(false, ResponseMessage.ExerciseResponseMessage.GOAL_INCORRECT_DATA);

        return new ValidatorResponse(true, null);
    }
}
