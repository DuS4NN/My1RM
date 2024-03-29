/*
 * This file is generated by jOOQ.
 */
package com.my1rm.jooq.tables;


import com.my1rm.jooq.Indexes;
import com.my1rm.jooq.Keys;
import com.my1rm.jooq.My1rm;
import com.my1rm.jooq.tables.records.UserOptionRecord;

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
public class UserOption extends TableImpl<UserOptionRecord> {

    private static final long serialVersionUID = 1430236491;

    /**
     * The reference instance of <code>my1rm.USER_OPTION</code>
     */
    public static final UserOption USER_OPTION = new UserOption();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<UserOptionRecord> getRecordType() {
        return UserOptionRecord.class;
    }

    /**
     * The column <code>my1rm.USER_OPTION.ID</code>.
     */
    public final TableField<UserOptionRecord, Long> ID = createField("ID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>my1rm.USER_OPTION.WEIGHT_UNIT</code>.
     */
    public final TableField<UserOptionRecord, String> WEIGHT_UNIT = createField("WEIGHT_UNIT", org.jooq.impl.SQLDataType.VARCHAR(255), this, "");

    /**
     * The column <code>my1rm.USER_OPTION.LANGUAGE_ID</code>.
     */
    public final TableField<UserOptionRecord, Long> LANGUAGE_ID = createField("LANGUAGE_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * The column <code>my1rm.USER_OPTION.USER_ID</code>.
     */
    public final TableField<UserOptionRecord, Long> USER_ID = createField("USER_ID", org.jooq.impl.SQLDataType.BIGINT, this, "");

    /**
     * Create a <code>my1rm.USER_OPTION</code> table reference
     */
    public UserOption() {
        this(DSL.name("USER_OPTION"), null);
    }

    /**
     * Create an aliased <code>my1rm.USER_OPTION</code> table reference
     */
    public UserOption(String alias) {
        this(DSL.name(alias), USER_OPTION);
    }

    /**
     * Create an aliased <code>my1rm.USER_OPTION</code> table reference
     */
    public UserOption(Name alias) {
        this(alias, USER_OPTION);
    }

    private UserOption(Name alias, Table<UserOptionRecord> aliased) {
        this(alias, aliased, null);
    }

    private UserOption(Name alias, Table<UserOptionRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> UserOption(Table<O> child, ForeignKey<O, UserOptionRecord> key) {
        super(child, key, USER_OPTION);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return My1rm.MY1RM;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FK2P5OTBGWV6TGF70068RSW2JNH_INDEX_D, Indexes.FK300INJW6I3MCW7FVRWO19P7Q5_INDEX_D, Indexes.PRIMARY_KEY_D);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<UserOptionRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_D;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<UserOptionRecord>> getKeys() {
        return Arrays.<UniqueKey<UserOptionRecord>>asList(Keys.CONSTRAINT_D);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<ForeignKey<UserOptionRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<UserOptionRecord, ?>>asList(Keys.FK300INJW6I3MCW7FVRWO19P7Q5, Keys.FK2P5OTBGWV6TGF70068RSW2JNH);
    }

    public Language language() {
        return new Language(this, Keys.FK300INJW6I3MCW7FVRWO19P7Q5);
    }

    public User user() {
        return new User(this, Keys.FK2P5OTBGWV6TGF70068RSW2JNH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOption as(String alias) {
        return new UserOption(DSL.name(alias), this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOption as(Name alias) {
        return new UserOption(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public UserOption rename(String name) {
        return new UserOption(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public UserOption rename(Name name) {
        return new UserOption(name, null);
    }
}
