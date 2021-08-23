package com.my1rm.model.database;

import com.my1rm.model.ResponseMessage;
import com.my1rm.model.types.WeightUnit;
import com.my1rm.validator.ValidatorResponse;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.regex.Pattern;

@Entity
@Table(name = "USER_OPTION")
@NoArgsConstructor
@Getter
@Setter
public class UserOptions {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;

    @ManyToOne(targetEntity = Language.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    @NotNull
    private Language language;

    @Enumerated(EnumType.STRING)
    @Column(name = "weight_unit")
    @NotNull
    private WeightUnit weight_unit;

    public static ValidatorResponse isWeightUnitValid(String weightUnit) {
        if(!Arrays.asList(WeightUnit.values()).contains(weightUnit)) return new ValidatorResponse(false, ResponseMessage.UserResponseMessage.WEIGHT_UNIT_INCORRECT_DATA);

        return new ValidatorResponse(true, null);
    }
}
