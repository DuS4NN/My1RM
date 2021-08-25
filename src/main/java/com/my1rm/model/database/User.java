package com.my1rm.model.database;

import com.my1rm.model.ResponseMessage;
import com.my1rm.validator.ValidatorResponse;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.regex.Pattern;

@Entity
@Table(name = "USER")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private long id;

    @Column(name = "email", columnDefinition = "VARCHAR(254)", unique = true)
    @NotNull
    private String email;

    @Column(name = "password", columnDefinition = "VARCHAR(100)")
    @NotNull
    private String password;

    @Column(name = "verified", columnDefinition = "TINYINT")
    @ColumnDefault("0")
    @NotNull
    private boolean verified;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private Date created_at;

    @OneToMany(targetEntity = Token.class, fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Token> token;

    @OneToMany(targetEntity = UserOptions.class, fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<UserOptions> userOption;

    @OneToMany(targetEntity = Season.class, fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Season> season;

    @OneToMany(targetEntity = Exercise.class, fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Exercise> exercise;

    @OneToMany(targetEntity = Attempt.class, fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Attempt> attempt;


    public static ValidatorResponse isPasswordValid(String password){
        if(password == null) return new ValidatorResponse(false, ResponseMessage.CommonResponseMessage.MISSING_DATA);
        if(password.length() < 4 || password.length() > 20) return new ValidatorResponse(false, ResponseMessage.UserResponseMessage.PASSWORD_INCORRECT_LENGTH);

        return new ValidatorResponse(true, null);
    }

    public static ValidatorResponse isEmailValid(String email) {
        if(email == null) return new ValidatorResponse(false, ResponseMessage.CommonResponseMessage.MISSING_DATA);
        if(email.length() > 254) return new ValidatorResponse(false, ResponseMessage.UserResponseMessage.EMAIL_INCORRECT_LENGTH);
        if(!Pattern.compile(".+@.+\\..+").matcher(email).matches()) return new ValidatorResponse(false, ResponseMessage.UserResponseMessage.EMAIL_INCORRECT_FORMAT);

        return new ValidatorResponse(true, null);
    }
}
