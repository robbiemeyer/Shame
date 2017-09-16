package io.arro.shame;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;

public class HomeActivity extends AppCompatActivity {

    ArrayList<Goal> goals = new ArrayList<Goal>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        goals.add(new Goal());
        Goal g = new Goal();
        g.endDate = new Date(123l);
        g.title = "WOOP";
        g.status = Goal.COMPLETED;
        goals.add(g);

        saveGoals(goals);
        ArrayList<Goal> otherGoals = loadGoals();
        for (Goal g1 : goals) {
            System.out.println(g1.toString());
        }
        System.out.println("SAIL");
        for (Goal g1 : otherGoals) {
            System.out.println(g1.toString());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onResume() {
        super.onResume();
        //Collections.sort(goals, new GoalComparator());
    }

    public ArrayList<Goal> loadGoals() {
        try {
            FileInputStream in = openFileInput("data.json");
            InputStreamReader inputStreamReader = new InputStreamReader(in);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }


            JSONArray jray = new JSONArray(sb.toString());
            ArrayList<Goal> g = new ArrayList<Goal>();
            for (int i = 0; i < jray.length(); i++) {
                g.add(new Goal((JSONObject) jray.get(i)));
            }
            return g;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        saveGoals(new ArrayList<Goal>());
        return new ArrayList<Goal>();
    }

    public void saveGoals(ArrayList<Goal> goals) {
        try {
            FileOutputStream fos = openFileOutput("data.json", Context.MODE_PRIVATE);

            JSONArray jray = new JSONArray();
            for (Goal i : goals)
                jray.put(i.save());

            fos.write(jray.toString().getBytes());
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
