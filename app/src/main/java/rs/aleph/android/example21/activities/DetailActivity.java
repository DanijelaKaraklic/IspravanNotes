package rs.aleph.android.example21.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import rs.aleph.android.example21.R;
import rs.aleph.android.example21.db.DatabaseHelper;
import rs.aleph.android.example21.db.model.Notes;


public class DetailActivity extends AppCompatActivity {

  DatabaseHelper databaseHelper;







    boolean notification;
    private SharedPreferences sharedPreferences;
    private boolean toast;

    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

    private EditText editName;
    private EditText editDescription;
    private EditText editDate;
    private Notes notes;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_activity);
        //PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        // Enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        if (toolbar!= null) {
            setSupportActionBar(toolbar);
        }


        Intent intent = getIntent();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int identifikator = intent.getExtras().getInt(MainActivity.NOTES);

        try {
            notes = getDatabaseHelper().getNotesDao().queryForId(identifikator);
            editName = (EditText)findViewById(R.id.notes_name);
            editDescription = (EditText)findViewById(R.id.notes_description);
            editDate = (EditText)findViewById(R.id.notes_date);

            editName.setText(notes.getmTitle());
            editDescription.setText(notes.getmDescription());
            editDate.setText(sdf.format(notes.getmDate()));



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void showMessage(String message){
        toast = sharedPreferences.getBoolean(MainActivity.TOAST,false);
        if (toast){
            Toast.makeText(DetailActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_item_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {

            case R.id.action_delete:

                    try {
                        if (notes != null){
                        getDatabaseHelper().getNotesDao().delete(notes);
                            showMessage(getString(R.string.delete_note));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    finish();



                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //refresh();
    }

 @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }



    public DatabaseHelper getDatabaseHelper() {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(this, DatabaseHelper.class);
        }
        return databaseHelper;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (databaseHelper != null) {
            OpenHelperManager.releaseHelper();
            databaseHelper = null;
        }
    }
}
