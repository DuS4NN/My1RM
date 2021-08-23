package com.my1rm.validator;

import com.my1rm.model.database.*;

public enum ValidationItems {
    AttemptWeight{
        @Override
        public ValidatorResponse validate(Object weight) {
            return Attempt.isWeightValid((float) weight);
        }
    },
    AttemptRepetitions{
        @Override
        public ValidatorResponse validate(Object repetitions) {
            return Attempt.isRepetitionsValid((int) repetitions);
        }
    },
    ExerciseName{
        @Override
        public ValidatorResponse validate(Object name) {
            return Exercise.isNameValid((String) name);
        }
    },
    ExerciseGoal{
        @Override
        public ValidatorResponse validate(Object goal) {
            return Exercise.isGoalValid((float) goal);
        }
    },
    SeasonName{
        @Override
        public ValidatorResponse validate(Object name) {
            return Season.isNameValid((String) name);
        }
    },
    SeasonColor{
        @Override
        public ValidatorResponse validate(Object color) {
            return Season.isColorValid((String) color);
        }
    },
    UserEmail{
        @Override
        public ValidatorResponse validate(Object email) {
            return User.isEmailValid((String) email);
        }
    },
    UserPassword{
        @Override
        public ValidatorResponse validate(Object password) {
            return User.isPasswordValid((String) password);
        }
    },
    UserOptionsWeightUnit{
        @Override
        public ValidatorResponse validate(Object weightUnit) {
            return UserOptions.isWeightUnitValid((String) weightUnit);
        }
    };

   public abstract ValidatorResponse validate(Object value);
}
