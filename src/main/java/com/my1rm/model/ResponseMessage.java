package com.my1rm.model;

public class ResponseMessage {

    public enum CommonResponseMessage {
        SUCCESS,
        ERROR,

        //Error
        LANGUAGE_NOT_FOUND,
        USER_NOT_FOUND,
        TOKEN_NOT_FOUND,
        EXERCISE_NOT_FOUND,
        SEASON_NOT_FOUND,
        MISSING_DATA,
    }

    public enum AttemptResponseMessage {
        //Error
        WEIGHT_INCORRECT_DATA,
        REPETITIONS_INCORRECT_DATA,

        //Success
    }

    public enum ExerciseResponseMessage {
        //Error
        NAME_INCORRECT_LENGTH,
        NAME_INCORRECT_FORMAT,
        GOAL_INCORRECT_DATA,
        NAME_ALREADY_EXISTS,
        MAXIMUM_NUMBER_OF_EXERCISES,

        //Success
        EXERCISE_CREATED,
        EXERCISE_REMOVED,
        EXERCISE_UPDATED,
    }

    public enum SeasonResponseMessage {
        //Error
        NAME_INCORRECT_LENGTH,
        NAME_INCORRECT_FORMAT,
        COLOR_INCORRECT_DATA,

        NAME_ALREADY_EXISTS,
        MAXIMUM_NUMBER_OF_SEASONS,

        //Success
        SEASON_CREATED,
        SEASON_UPDATED,
        SEASON_REMOVED,
    }

    public enum UserResponseMessage {
        //Error
        EMAIL_INCORRECT_LENGTH,
        EMAIL_INCORRECT_FORMAT,
        PASSWORD_INCORRECT_LENGTH,
        WEIGHT_UNIT_INCORRECT_DATA,
        EMAIL_ALREADY_EXISTS,

        ACCOUNT_DOES_NOT_EXIST,
        WRONG_CREDENTIALS,
        UNVERIFIED_ACCOUNT,
        WRONG_PASSWORD,
        EMAILS_ARE_EQUAL,

        //Success
        ACCOUNT_CREATED,
        ACCOUNT_VERIFIED,
        PASSWORD_RESET_REQUEST_CREATED,
        PASSWORD_RESTORED,
        ACCOUNT_IS_VERIFIED_OR_DOES_NOT_EXIST,
        VERIFICATION_EMAIL_RESEND,
        PASSWORD_CHANGED,
        EMAIL_CHANGED
    }
}


