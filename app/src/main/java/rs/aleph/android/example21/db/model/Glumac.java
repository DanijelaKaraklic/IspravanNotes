package rs.aleph.android.example21.db.model;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;


/**
 * Created by KaraklicDM on 22.11.2017.
 */
@DatabaseTable(tableName = Glumac.TABLE_NAME_ACTORS)
public class Glumac{
    public static final String TABLE_NAME_ACTORS = "glumac";

    public static final String FIELD_NAME_ID = "id_glumac";
    public static final String FIELD_NAME_NAME = "ime";
    public static final String FIELD_NAME_SURNAME = "prezime";
    public static final String FIELD_NAME_BIRTHDAY = "datumRodjenja";
    public static final String FIELD_NAME_BIOGRAPHY= "biografija";
    public static final String FIELD_NAME_RATING = "rating";
    public static final String FIELD_NAME_FILMS = "films";//Film.FIELD_NAME_GLUMAC


    @DatabaseField(columnName = FIELD_NAME_ID,generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;
    @DatabaseField(columnName = FIELD_NAME_SURNAME)
    private String mSurname;
    @DatabaseField(columnName = FIELD_NAME_BIRTHDAY)
    private Date mBirthday;
    @DatabaseField(columnName = FIELD_NAME_BIOGRAPHY)
    private String mBiography;
    @DatabaseField(columnName = FIELD_NAME_RATING)
    private float mRating;


    @ForeignCollectionField(columnName = Glumac.FIELD_NAME_FILMS,eager = true)
    private ForeignCollection<Film> films;

    public Glumac() {
    }



    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmSurname() {
        return mSurname;
    }

    public void setmSurname(String mSurname) {
        this.mSurname = mSurname;
    }

    public Date getmBirthday() {
        return mBirthday;
    }

    public void setmBirthday(Date mBirthday) {
        this.mBirthday = mBirthday;
    }

    public String getmBiography() {
        return mBiography;
    }

    public void setmBiography(String mBiography) {
        this.mBiography = mBiography;
    }

    public float getmRating() {
        return mRating;
    }

    public void setmRating(float mRating) {
        this.mRating = mRating;
    }

    public ForeignCollection<Film> getFilms() {
        return films;
    }

    public void setFilms(ForeignCollection<Film> films) {
        this.films = films;
    }


    @Override
    public String toString() {
        return mName;
    }



}