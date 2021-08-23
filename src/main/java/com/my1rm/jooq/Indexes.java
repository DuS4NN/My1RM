/*
 * This file is generated by jOOQ.
 */
package com.my1rm.jooq;


import com.my1rm.jooq.tables.Attempt;
import com.my1rm.jooq.tables.Exercise;
import com.my1rm.jooq.tables.Language;
import com.my1rm.jooq.tables.Season;
import com.my1rm.jooq.tables.Token;
import com.my1rm.jooq.tables.User;
import com.my1rm.jooq.tables.UserOption;

import javax.annotation.Generated;

import org.jooq.Index;
import org.jooq.OrderField;
import org.jooq.impl.Internal;


/**
 * A class modelling indexes of tables of the <code>PUBLIC</code> schema.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Indexes {

    // -------------------------------------------------------------------------
    // INDEX definitions
    // -------------------------------------------------------------------------

    public static final Index FKCD2WCPCK3XOTL375KUYO4YB0U_INDEX_2 = Indexes0.FKCD2WCPCK3XOTL375KUYO4YB0U_INDEX_2;
    public static final Index FKIERY2PPOLDI66OL7525EB1ADB_INDEX_2 = Indexes0.FKIERY2PPOLDI66OL7525EB1ADB_INDEX_2;
    public static final Index FKNQLYSVCHVYJ71W2EX1AQGX444_INDEX_2 = Indexes0.FKNQLYSVCHVYJ71W2EX1AQGX444_INDEX_2;
    public static final Index PRIMARY_KEY_2 = Indexes0.PRIMARY_KEY_2;
    public static final Index FKB0MWPNFMBFU2SO83PB9WAF0AO_INDEX_A = Indexes0.FKB0MWPNFMBFU2SO83PB9WAF0AO_INDEX_A;
    public static final Index PRIMARY_KEY_A = Indexes0.PRIMARY_KEY_A;
    public static final Index PRIMARY_KEY_C = Indexes0.PRIMARY_KEY_C;
    public static final Index FKTCL52SDGLRMC1JPTFMNCINK09_INDEX_9 = Indexes0.FKTCL52SDGLRMC1JPTFMNCINK09_INDEX_9;
    public static final Index PRIMARY_KEY_9 = Indexes0.PRIMARY_KEY_9;
    public static final Index FKCR55UO5IEX6L1HS2XQP94G2SS_INDEX_4 = Indexes0.FKCR55UO5IEX6L1HS2XQP94G2SS_INDEX_4;
    public static final Index PRIMARY_KEY_4 = Indexes0.PRIMARY_KEY_4;
    public static final Index PRIMARY_KEY_27 = Indexes0.PRIMARY_KEY_27;
    public static final Index UK_OSO07PUDW19E66BS4YP8HWPUX_INDEX_2 = Indexes0.UK_OSO07PUDW19E66BS4YP8HWPUX_INDEX_2;
    public static final Index FK2P5OTBGWV6TGF70068RSW2JNH_INDEX_D = Indexes0.FK2P5OTBGWV6TGF70068RSW2JNH_INDEX_D;
    public static final Index FK300INJW6I3MCW7FVRWO19P7Q5_INDEX_D = Indexes0.FK300INJW6I3MCW7FVRWO19P7Q5_INDEX_D;
    public static final Index PRIMARY_KEY_D = Indexes0.PRIMARY_KEY_D;

    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Indexes0 {
        public static Index FKCD2WCPCK3XOTL375KUYO4YB0U_INDEX_2 = Internal.createIndex("FKCD2WCPCK3XOTL375KUYO4YB0U_INDEX_2", Attempt.ATTEMPT, new OrderField[] { Attempt.ATTEMPT.EXERCISE_ID }, false);
        public static Index FKIERY2PPOLDI66OL7525EB1ADB_INDEX_2 = Internal.createIndex("FKIERY2PPOLDI66OL7525EB1ADB_INDEX_2", Attempt.ATTEMPT, new OrderField[] { Attempt.ATTEMPT.USER_ID }, false);
        public static Index FKNQLYSVCHVYJ71W2EX1AQGX444_INDEX_2 = Internal.createIndex("FKNQLYSVCHVYJ71W2EX1AQGX444_INDEX_2", Attempt.ATTEMPT, new OrderField[] { Attempt.ATTEMPT.SEASON_ID }, false);
        public static Index PRIMARY_KEY_2 = Internal.createIndex("PRIMARY_KEY_2", Attempt.ATTEMPT, new OrderField[] { Attempt.ATTEMPT.ID }, true);
        public static Index FKB0MWPNFMBFU2SO83PB9WAF0AO_INDEX_A = Internal.createIndex("FKB0MWPNFMBFU2SO83PB9WAF0AO_INDEX_A", Exercise.EXERCISE, new OrderField[] { Exercise.EXERCISE.USER_ID }, false);
        public static Index PRIMARY_KEY_A = Internal.createIndex("PRIMARY_KEY_A", Exercise.EXERCISE, new OrderField[] { Exercise.EXERCISE.ID }, true);
        public static Index PRIMARY_KEY_C = Internal.createIndex("PRIMARY_KEY_C", Language.LANGUAGE, new OrderField[] { Language.LANGUAGE.ID }, true);
        public static Index FKTCL52SDGLRMC1JPTFMNCINK09_INDEX_9 = Internal.createIndex("FKTCL52SDGLRMC1JPTFMNCINK09_INDEX_9", Season.SEASON, new OrderField[] { Season.SEASON.USER_ID }, false);
        public static Index PRIMARY_KEY_9 = Internal.createIndex("PRIMARY_KEY_9", Season.SEASON, new OrderField[] { Season.SEASON.ID }, true);
        public static Index FKCR55UO5IEX6L1HS2XQP94G2SS_INDEX_4 = Internal.createIndex("FKCR55UO5IEX6L1HS2XQP94G2SS_INDEX_4", Token.TOKEN, new OrderField[] { Token.TOKEN.USER_ID }, false);
        public static Index PRIMARY_KEY_4 = Internal.createIndex("PRIMARY_KEY_4", Token.TOKEN, new OrderField[] { Token.TOKEN.ID }, true);
        public static Index PRIMARY_KEY_27 = Internal.createIndex("PRIMARY_KEY_27", User.USER, new OrderField[] { User.USER.ID }, true);
        public static Index UK_OSO07PUDW19E66BS4YP8HWPUX_INDEX_2 = Internal.createIndex("UK_OSO07PUDW19E66BS4YP8HWPUX_INDEX_2", User.USER, new OrderField[] { User.USER.EMAIL }, true);
        public static Index FK2P5OTBGWV6TGF70068RSW2JNH_INDEX_D = Internal.createIndex("FK2P5OTBGWV6TGF70068RSW2JNH_INDEX_D", UserOption.USER_OPTION, new OrderField[] { UserOption.USER_OPTION.USER_ID }, false);
        public static Index FK300INJW6I3MCW7FVRWO19P7Q5_INDEX_D = Internal.createIndex("FK300INJW6I3MCW7FVRWO19P7Q5_INDEX_D", UserOption.USER_OPTION, new OrderField[] { UserOption.USER_OPTION.LANGUAGE_ID }, false);
        public static Index PRIMARY_KEY_D = Internal.createIndex("PRIMARY_KEY_D", UserOption.USER_OPTION, new OrderField[] { UserOption.USER_OPTION.ID }, true);
    }
}
