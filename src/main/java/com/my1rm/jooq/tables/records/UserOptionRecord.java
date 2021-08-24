/*
 * This file is generated by jOOQ.
 */
package com.my1rm.jooq.tables.records;


import com.my1rm.jooq.tables.UserOption;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.UpdatableRecordImpl;


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
public class UserOptionRecord extends UpdatableRecordImpl<UserOptionRecord> implements Record4<Long, String, Long, Long> {

    private static final long serialVersionUID = -1274945792;

    /**
     * Setter for <code>my1rm.USER_OPTION.ID</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>my1rm.USER_OPTION.ID</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>my1rm.USER_OPTION.WEIGHT_UNIT</code>.
     */
    public void setWeightUnit(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>my1rm.USER_OPTION.WEIGHT_UNIT</code>.
     */
    public String getWeightUnit() {
        return (String) get(1);
    }

    /**
     * Setter for <code>my1rm.USER_OPTION.LANGUAGE_ID</code>.
     */
    public void setLanguageId(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>my1rm.USER_OPTION.LANGUAGE_ID</code>.
     */
    public Long getLanguageId() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>my1rm.USER_OPTION.USER_ID</code>.
     */
    public void setUserId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>my1rm.USER_OPTION.USER_ID</code>.
     */
    public Long getUserId() {
        return (Long) get(3);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Long, String, Long, Long> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row4<Long, String, Long, Long> valuesRow() {
        return (Row4) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return UserOption.USER_OPTION.ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return UserOption.USER_OPTION.WEIGHT_UNIT;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field3() {
        return UserOption.USER_OPTION.LANGUAGE_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field4() {
        return UserOption.USER_OPTION.USER_ID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String component2() {
        return getWeightUnit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component3() {
        return getLanguageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long component4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getWeightUnit();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value3() {
        return getLanguageId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value4() {
        return getUserId();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOptionRecord value1(Long value) {
        setId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOptionRecord value2(String value) {
        setWeightUnit(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOptionRecord value3(Long value) {
        setLanguageId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOptionRecord value4(Long value) {
        setUserId(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserOptionRecord values(Long value1, String value2, Long value3, Long value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached UserOptionRecord
     */
    public UserOptionRecord() {
        super(UserOption.USER_OPTION);
    }

    /**
     * Create a detached, initialised UserOptionRecord
     */
    public UserOptionRecord(Long id, String weightUnit, Long languageId, Long userId) {
        super(UserOption.USER_OPTION);

        set(0, id);
        set(1, weightUnit);
        set(2, languageId);
        set(3, userId);
    }
}
