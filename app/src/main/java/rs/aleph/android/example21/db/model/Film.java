package rs.aleph.android.example21.db.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by KaraklicDM on 22.11.2017.
 */





@DatabaseTable(tableName = Film.TABLE_NAME_FILMS)
public class Film {

    public static final String TABLE_NAME_FILMS = "films";

    public static final String FIELD_NAME_ID = "id_film";
    public static final String FIELD_NAME_NAME = "nazivFilma";
    public static final String FIELD_NAME_ZANR = "zanr";
    public static final String FIELD_NAME_GODINA = "godinaIzlaska";
    public static final String FIELD_NAME_GLUMAC = "glumac";


    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int mId;

    @DatabaseField(columnName = FIELD_NAME_NAME)
    private String mName;

    @DatabaseField(columnName = FIELD_NAME_ZANR)
    private String mZanr;

    @DatabaseField(columnName = FIELD_NAME_GODINA)
    private int mGodinaIzlaska;

    @DatabaseField(columnName = FIELD_NAME_GLUMAC, foreign = true, foreignAutoRefresh = true)
    private Glumac glumac;


    //ORMLite zahteva prazan konstuktur u klasama koje opisuju tabele u bazi!


    public Film() {
    }


    /**
     * Getters & Setters
     **/

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

    public String getmZanr() {
        return mZanr;
    }

    public void setmZanr(String mZanr) {
        this.mZanr = mZanr;
    }

    public int getmGodinaIzlaska() {
        return mGodinaIzlaska;
    }

    public void setmGodinaIzlaska(int mGodinaIzlaska) {
        this.mGodinaIzlaska = mGodinaIzlaska;
    }

    public Glumac getGlumac() {
        return glumac;
    }

    public void setGlumac(Glumac glumac) {
        this.glumac = glumac;
    }




    @Override
    public String toString() {
        return mName;
    }
}