package rs.aleph.android.example21.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

/**
 * Created by KaraklicDM on 28.11.2017.
 */
@DatabaseTable(tableName = Notes.TABLE_NAME_NOTES)
public class Notes {
    public static final String TABLE_NAME_NOTES = "notes";

    public static final String FIELD_NAME_ID = "id";
    public static final String FIELD_NAME_TITLE = "title";
    public static final String FIELD_NAME_DESCRIPTION = "description";
    public static final String FIELD_NAME_DATE = "date";



    @DatabaseField(columnName = FIELD_NAME_ID,generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_TITLE)
    private String mTitle;

    @DatabaseField(columnName = FIELD_NAME_DESCRIPTION)
    private String mDescription;


    @DatabaseField(columnName = FIELD_NAME_DATE)
    private Date mDate;


    public Notes() {
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmTitle() {
        return mTitle;
    }

    public void setmTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }




    public Date getmDate() {
        return mDate;
    }

    public void setmDate(Date mDate) {
        this.mDate = mDate;
    }



    @Override
    public String toString() {
        return mTitle;
    }
}


