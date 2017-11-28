package com.simulatedminds.choreapplication.database;

/**
 * Created by arielkim on 2017-11-27.
 */

public class ChoreDbSchema {
    public static final class ChoreTable{
        //The name of the table
        public static final String NAME = "chores";

        public static final class Cols{
            //The name of the columns
            //Refer to them in this way: ChoreTable.Cols.TITLE
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DETAILS = "details";
            public static final String DATE = "date";
            public static final String COMPLETED = "completed";
        }
    }
}
