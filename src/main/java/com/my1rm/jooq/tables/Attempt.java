/*
 * This file is generated by jOOQ.
 */
package com.my1rm.jooq.tables;


import com.my1rm.jooq.Indexes;
import com.my1rm.jooq.Keys;
import com.my1rm.jooq.Public;
import com.my1rm.jooq.tables.records.AttemptRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.5"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Attempt extends TableImpl<AttemptRecord> {

    private static final long serialVersionUID = -751803415;

    /**
     * The reference instance of <code>PUBLIC.ATTEMPT</code>
     */
    public static final Attempt ATTEMPT = new Attempt();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<AttemptRecord> getRecordType() {
        return AttemptRecord.class;
    }

    /**
     * The column <code>PUBLIC.ATTEMPT.ID</code>.
     */
    public final TableField<AttemptRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.ATTEMPT.CREATED_AT</code>.
     */
    public final TableField<AttemptRecord, Timestamp> CREATED_AT = createField("CREATED_AT", org.jooq.impl.SQLDataType.TIMESTAMP.precision(6), this, "");

    /**
     * The column <code>PUBLIC.ATTEMPT.REPETITIONS</code>.
     */
    public final TableField<AttemptRecord, Short> REPETITIONS = createField("REPETITIONS", org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>PUBLIC.ATTEMPT.SUCCESS</code>.
     */
    public final TableField<AttemptRecord, Byte> SUCCESS = createField("SUCCESS", org.jooq.impl.SQLDataType.TINYINT, this, "");

    /**
     * The column <code>PUBLIC.ATTEMPT.WEIGHT</code>.
     */
    public final TableField<AttemptRecord, Short> WEIGHT = createField("WEIGHT", org.jooq.impl.SQLDataType.SMALLINT, this, "");

    /**
     * The column <code>PUBLIC.ATTEMPT.EXERCISE_ID</code>.
     */
    public final TableField<AttemptRecord, Long> EXERCISE_ID = createField("EXERCISE_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>PUBLIC.ATTEMPT.SEASON_ID</code>.
     */
    public final TableField<AttemptRecord, Long> SEASON_ID = createField("SEASON_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>PUBLIC.ATTEMPT.USER_ID</code>.
     */
    public final TableField<AttemptRecord, Long> USER_ID = createField("USER_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>PUBLIC.ATTEMPT</code> table reference
     */
    public Attempt() {
        this(DSL.name("ATTEMPT"), null);
    }

    /**
     * Create an aliased <code>PUBLIC.ATTEMPT</code> table reference
     */
    public Attempt(String alias) {
        this(DSL.name(alias), ATTEMPT);
    }

    /**
     * Create an aliased <code>PUBLIC.ATTEMPT</code> table reference
     */
    public Attempt(Name alias) {
        this(alias, ATTEMPT);
    }

    private Attempt(Name alias, Table<AttemptRecord> aliased) {
        this(alias, aliased, null);
    }

    private Attempt(Name alias, Table<AttemptRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> Attempt(Table<O> child, ForeignKey<O, AttemptRecord> key) {
        super(child, key, ATTEMPT);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FKCD2WCPCK3XOTL375KUYO4YB0U_INDEX_2, Indexes.FKIERY2PPOLDI66OL7525EB1ADB_INDEX_2, Indexes.FKNQLYSVCHVYJ71W2EX1AQGX444_INDEX_2, Indexes.PRIMARY_KEY_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<AttemptRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_2;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<AttemptRecord>> getKeys() {
        return Arrays.<UniqueKey<AttemptRecord>>asList(Keys.CONSTRAINT_2);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<AttemptRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<AttemptRecord, ?>>asList(Keys.FKCD2WCPCK3XOTL375KUYO4YB0U, Keys.FKNQLYSVCHVYJ71W2EX1AQGX444, Keys.FKIERY2PPOLDI66OL7525EB1ADB);
    }

    public Exercise exercise() {
        return new Exercise(this, Keys.FKCD2WCPCK3XOTL375KUYO4YB0U);
    }

    public Season season() {
        return new Season(this, Keys.FKNQLYSVCHVYJ71W2EX1AQGX444);
    }

    public User user() {
        return new User(this, Keys.FKIERY2PPOLDI66OL7525EB1ADB);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Attempt as(String alias) {
        return new Attempt(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Attempt as(Name alias) {
        return new Attempt(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Attempt rename(String name) {
        return new Attempt(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Attempt rename(Name name) {
        return new Attempt(name, null);
    }
}