package com.example.fastlabelcapture.database;

import android.provider.BaseColumns;



/**
 * This Object contains the name and the columns names (attributes) of the SQLite table that
 * contains the captures information.
 *
 */
public class FilesContract {

    public static abstract class ImageFileEntry implements BaseColumns {
        public static final String TABLE_NAME ="imagefile";

        public static final String USER = "user";
        public static final String FILENAME = "filename";
        public static final String PATH = "path";
        public static final String HEIGHT = "height";
        public static final String WIDTH = "width";
        public static final String HAVE_CROP = "haveCrop";
        public static final String POINT_X_1 = "pointX1";
        public static final String POINT_Y_1 = "pointY1";
        public static final String POINT_X_2 = "pointX2";
        public static final String POINT_Y_2 = "pointY2";
    }
}
