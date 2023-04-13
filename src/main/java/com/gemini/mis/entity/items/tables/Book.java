/*
 * This file is generated by jOOQ.
 */
package com.gemini.mis.entity.items.tables;


import com.gemini.mis.entity.items.Items;
import com.gemini.mis.entity.items.Keys;
import com.gemini.mis.entity.items.tables.records.BookRecord;

import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Identity;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Book extends TableImpl<BookRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>ITEMS.BOOK</code>
     */
    public static final Book BOOK = new Book();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BookRecord> getRecordType() {
        return BookRecord.class;
    }

    /**
     * The column <code>ITEMS.BOOK.ID</code>.
     */
    public final TableField<BookRecord, Integer> ID = createField(DSL.name("ID"), SQLDataType.INTEGER.nullable(false).identity(true), this, "");

    /**
     * The column <code>ITEMS.BOOK.AUTHOR_ID</code>.
     */
    public final TableField<BookRecord, Integer> AUTHOR_ID = createField(DSL.name("AUTHOR_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ITEMS.BOOK.TITLE</code>.
     */
    public final TableField<BookRecord, String> TITLE = createField(DSL.name("TITLE"), SQLDataType.VARCHAR(400).nullable(false), this, "");

    /**
     * The column <code>ITEMS.BOOK.PUBLISHED_IN</code>.
     */
    public final TableField<BookRecord, Integer> PUBLISHED_IN = createField(DSL.name("PUBLISHED_IN"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>ITEMS.BOOK.LANGUAGE_ID</code>.
     */
    public final TableField<BookRecord, Integer> LANGUAGE_ID = createField(DSL.name("LANGUAGE_ID"), SQLDataType.INTEGER.nullable(false), this, "");

    private Book(Name alias, Table<BookRecord> aliased) {
        this(alias, aliased, null);
    }

    private Book(Name alias, Table<BookRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>ITEMS.BOOK</code> table reference
     */
    public Book(String alias) {
        this(DSL.name(alias), BOOK);
    }

    /**
     * Create an aliased <code>ITEMS.BOOK</code> table reference
     */
    public Book(Name alias) {
        this(alias, BOOK);
    }

    /**
     * Create a <code>ITEMS.BOOK</code> table reference
     */
    public Book() {
        this(DSL.name("BOOK"), null);
    }

    public <O extends Record> Book(Table<O> child, ForeignKey<O, BookRecord> key) {
        super(child, key, BOOK);
    }

    @Override
    public Schema getSchema() {
        return Items.ITEMS;
    }

    @Override
    public Identity<BookRecord, Integer> getIdentity() {
        return (Identity<BookRecord, Integer>) super.getIdentity();
    }

    @Override
    public UniqueKey<BookRecord> getPrimaryKey() {
        return Keys.CONSTRAINT_1;
    }

    @Override
    public List<UniqueKey<BookRecord>> getKeys() {
        return Arrays.<UniqueKey<BookRecord>>asList(Keys.CONSTRAINT_1);
    }

    @Override
    public Book as(String alias) {
        return new Book(DSL.name(alias), this);
    }

    @Override
    public Book as(Name alias) {
        return new Book(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(String name) {
        return new Book(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Book rename(Name name) {
        return new Book(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<Integer, Integer, String, Integer, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}
