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


/**
 * Convenience access to all tables in PUBLIC
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Tables {

    /**
     * The table <code>PUBLIC.ATTEMPT</code>.
     */
    public static final Attempt ATTEMPT = com.my1rm.jooq.tables.Attempt.ATTEMPT;

    /**
     * The table <code>PUBLIC.EXERCISE</code>.
     */
    public static final Exercise EXERCISE = com.my1rm.jooq.tables.Exercise.EXERCISE;

    /**
     * The table <code>PUBLIC.LANGUAGE</code>.
     */
    public static final Language LANGUAGE = com.my1rm.jooq.tables.Language.LANGUAGE;

    /**
     * The table <code>PUBLIC.SEASON</code>.
     */
    public static final Season SEASON = com.my1rm.jooq.tables.Season.SEASON;

    /**
     * The table <code>PUBLIC.TOKEN</code>.
     */
    public static final Token TOKEN = com.my1rm.jooq.tables.Token.TOKEN;

    /**
     * The table <code>PUBLIC.USER</code>.
     */
    public static final User USER = com.my1rm.jooq.tables.User.USER;

    /**
     * The table <code>PUBLIC.USER_OPTION</code>.
     */
    public static final UserOption USER_OPTION = com.my1rm.jooq.tables.UserOption.USER_OPTION;
}
