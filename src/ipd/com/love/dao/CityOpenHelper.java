package ipd.com.love.dao;

import java.io.File;

import android.database.sqlite.SQLiteDatabase;
import ipd.com.love.global.GlobalParams;

public class CityOpenHelper {
	public static final String DATABASE_PATH = GlobalParams.ROOT_PATH + "/static.dll";
	private SQLiteDatabase db;

	public SQLiteDatabase openSQLite() {
		if (db != null) {
			return db;
		} else {
			try {
				File databaseFilename = new File(DATABASE_PATH);
				db = SQLiteDatabase.openOrCreateDatabase(databaseFilename, null);
				
				return db;
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}
}
