package rs.aleph.android.example21.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rs.aleph.android.example21.R;
import rs.aleph.android.example21.db.DatabaseHelper;
import rs.aleph.android.example21.db.model.Notes;
import rs.aleph.android.example21.dialogs.AboutDialog;

public class MainActivity extends AppCompatActivity{



    private AlertDialog dialog;
    //za rad sa bazom
    private DatabaseHelper databaseHelper;
    private SharedPreferences sharedPreferences;







    public static final String NOTES = "selectedItemId";
    public static final String TOAST = "pref_checkout_toast";
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy.");

    private boolean toast;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);




        // Enable ActionBar app icon to behave as action to toggle nav drawer
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            setSupportActionBar(toolbar);
        }


        List<Notes> listRs = new ArrayList<Notes>();
        try {
            listRs = getDatabaseHelper().getNotesDao().queryForAll();

            ListAdapter adapter1 = new ArrayAdapter<Notes>(MainActivity.this,R.layout.list_item,listRs);
            final ListView listView = (ListView)findViewById(R.id.notes);
            listView.setAdapter(adapter1);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(MainActivity.this,DetailActivity.class);
                    Notes n = (Notes) listView.getItemAtPosition(position);
                    int selectedItemId = n.getmId();
                    intent.putExtra(NOTES,selectedItemId);
                    startActivity(intent);
                }
            });


        } catch (SQLException e) {
            e.printStackTrace();
        }

     }

    private void refresh() {
        ListView listview = (ListView) findViewById(R.id.notes);

        if (listview != null) {
            ArrayAdapter<Notes> adapter = (ArrayAdapter<Notes>) listview.getAdapter();

            if (adapter != null) {
                try {
                    adapter.clear();
                    List<Notes> list = getDatabaseHelper().getNotesDao().queryForAll();

                    adapter.addAll(list);

                    adapter.notifyDataSetChanged();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private void showMessage(String message){
        toast = sharedPreferences.getBoolean(TOAST,false);
        if (toast){
            Toast.makeText(MainActivity.this,message,Toast.LENGTH_SHORT).show();
        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_item_master, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     *
     * Metoda koja je izmenjena da reflektuje rad sa Asinhronim zadacima
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.action_about:
                if (dialog == null){
                    dialog = new AboutDialog(MainActivity.this).prepareDialog();
                } else {
                    if (dialog.isShowing()) {
                        dialog.dismiss();
                    }
                }

                dialog.show();

                break;
            case R.id.action_add:
                final Dialog dialog = new Dialog(MainActivity.this);

                dialog.setContentView(R.layout.dialog_layout);

                dialog.setTitle("Add a new note");

                Button save = (Button) dialog.findViewById(R.id.save);

                save.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        final EditText editName = (EditText) dialog.findViewById(R.id.notes_name);
                        final EditText editDescription = (EditText) dialog.findViewById(R.id.notes_description);
                        final EditText editDate = (EditText) dialog.findViewById(R.id.notes_date);


                        final Notes notes = new Notes();

                        String name = editName.getText().toString();
                        String desc = editDescription.getText().toString();
                        String dat = editDate.getText().toString();

                        if (name.isEmpty()){
                            Toast.makeText(MainActivity.this, "Empty string",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (desc.isEmpty()){
                            Toast.makeText(MainActivity.this, "Empty string",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if (dat.isEmpty()){
                            Toast.makeText(MainActivity.this, "Empty string",Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Date date = null;
                        try {
                            date = sdf.parse(dat);

                        } catch (ParseException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "It's not a date.",Toast.LENGTH_SHORT).show();

                        }

                        notes.setmTitle(name);
                        notes.setmDescription(desc);
                        notes.setmDate(date);

                        try {
                            getDatabaseHelper().getNotesDao().create(notes);
                            showMessage(getString(R.string.note_saved));
                            refresh();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        dialog.dismiss();

                    }
                });

                Button cancel = (Button) dialog.findViewById(R.id.cancel);
                cancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {

                        showMessage(getString(R.string.note_not_saved));
                        dialog.dismiss();

                    }
                });

                dialog.show();



                break;
            case R.id.action_settings:
                Intent intent = new Intent(MainActivity.this,PrefererencesActivity.class);
                startActivity(intent);

                break;
        }

        return super.onOptionsItemSelected(item);
    }

   /* @Override
    public void setTitle(CharSequence title) {
        getSupportActionBar().setTitle(title);
    }*/










    @Override
    protected void onResume() {
        super.onResume();
        refresh();
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







