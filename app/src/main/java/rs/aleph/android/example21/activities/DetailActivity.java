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

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.text.SimpleDateFormat;

import rs.aleph.android.example21.R;
import rs.aleph.android.example21.db.DatabaseHelper;
import rs.aleph.android.example21.db.model.Notes;


public class DetailActivity extends AppCompatActivity {

  DatabaseHelper databaseHelper;






    boolean toast;
    boolean notification;
    private SharedPreferences sharedPreferences;

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


       /*
       Intent intent = getIntent();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int identifikator = intent.getExtras().getInt("identifikator");

        try {
            glumac= getDatabaseHelper().getGlumacDao().queryForId(identifikator);


            //ispisali smo na ekranu osnovne podatke o glumcu
            name= (EditText)findViewById(R.id.actor_name);
            name.setText(glumac.getmName());
            surname = (EditText)findViewById(R.id.actor_surname);
            surname.setText(glumac.getmSurname());
            rating = (RatingBar) findViewById(R.id.acrtor_rating);
            rating.setRating(glumac.getmRating());
            birthday = (EditText)findViewById(R.id.actor_birthday);
            birthday.setText(sdf.format(glumac.getmBirthday()));
            biography = (EditText)findViewById(R.id.actor_biography);
            biography.setText(glumac.getmBiography());

        } catch (SQLException e) {
            e.printStackTrace();
        }*/


       /* final ListView listView = (ListView)findViewById(R.id.lv_films);


        try {
            List<Film>  films = getDatabaseHelper().getFilmDao()
                    .queryBuilder()
                    .where()
                    .eq(Film.FIELD_NAME_GLUMAC,glumac.getmId())
                    .query();
            ListAdapter adapter = new ArrayAdapter<>(DetailActivity.this,R.layout.list_item,films);
            listView.setAdapter(adapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Film m = (Film) listView.getItemAtPosition(position);
                    Toast.makeText(DetailActivity.this, m.getmName()+" "+m.getmZanr()+" "+m.getmGodinaIzlaska(), Toast.LENGTH_SHORT).show();

                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }


  /*  private void refresh() {
        ListView listview = (ListView) findViewById(R.id.lv_films);
        if (listview != null){
            ArrayAdapter<Film> adapter = (ArrayAdapter<Film>) listview.getAdapter();
            if(adapter != null){

                try {
                    adapter.clear();

                    List<Film> films = getDatabaseHelper().getFilmDao()
                            .queryBuilder()
                            .where()
                            .eq(Film.FIELD_NAME_GLUMAC,glumac.getmId())
                            .query();


                    adapter.addAll(films);
                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();

                }
            }
        }
    }*/

   /* private void showStatusMesage(String message){
        NotificationManager mNotificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("Operacije nad bazom");
        mBuilder.setContentText(message);

        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.ic_action_add);

        mBuilder.setLargeIcon(bm);
        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(1, mBuilder.build());
    }*/

   /* private void showMessage(String message){
        //provera podesenja
        boolean toast = sharedPreferences.getBoolean("pref_checkout_toast", false);
        boolean status = sharedPreferences.getBoolean("pref_checkout_notification", false);

        if (toast){
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

        if (status){
            showStatusMesage(message);
        }
    }*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_item_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       /* sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        final boolean toast = sharedPreferences.getBoolean("@string/pref_checkout_toast",false);
        final boolean notification = sharedPreferences.getBoolean("@string/pref_checkout_notification",false);*/

        switch (item.getItemId()) {
            case R.id.action_edit:
               /* glumac.setmName(name.getText().toString());
                glumac.setmSurname(surname.getText().toString());
                try {
                    glumac.setmBirthday(sdf.parse(birthday.getText().toString()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                glumac.setmBiography(biography.getText().toString());
                glumac.setmRating(rating.getRating());

                try {
                    getDatabaseHelper().getGlumacDao().update(glumac);

                    showMessage("Actor detail updated");

                } catch (SQLException e) {
                    e.printStackTrace();
                }
*/
                break;
            case R.id.action_add:

              /*  final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialog_detail);

                Button add = (Button) dialog.findViewById(R.id.ok);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText name = (EditText) dialog.findViewById(R.id.film_name);
                        EditText genre = (EditText) dialog.findViewById(R.id.film_genre);
                        EditText year = (EditText) dialog.findViewById(R.id.film_year);

                        Film film = new Film();
                        film.setmName(name.getText().toString());
                        film.setmZanr(genre.getText().toString());
                        film.setmGodinaIzlaska(Integer.parseInt(year.getText().toString()));
                        film.setGlumac(glumac);



                        try {
                            getDatabaseHelper().getFilmDao().create(film);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //URADITI REFRESH
                        refresh();

                        showMessage("New movie added to actor");

                        dialog.dismiss();
                    }
                });

                dialog.show();*/

                break;
            case R.id.action_delete:

                    try {
                        if (notes != null){
                        getDatabaseHelper().getNotesDao().delete(notes);
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
