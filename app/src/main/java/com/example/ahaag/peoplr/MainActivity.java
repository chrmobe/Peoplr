package com.example.ahaag.peoplr;

//lolololol butts
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.FragmentTransaction;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;



//MOAR BUTTS
public class MainActivity extends Activity implements AdapterView.OnItemClickListener{

    final String drawerTitle= "Navigation";
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle drawerToggle;
    String[] fragmentNames;
    ListView drawerList;
    int fragPos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // Set the drawer toggle as the DrawerListener
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,R.string.drawer_open,R.string.drawer_close) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle(drawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);

        //Setting up the values of the Sidebar menu (Home, My Profile, Matches, Settings)
        fragmentNames=getResources().getStringArray(R.array.fragment_names);
        drawerList = (ListView) findViewById(R.id.left_drawer);

        //Sets the adapter of the list view for the side Drawer
        drawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, fragmentNames));

        //Listener for the drawer objects
        drawerList.setOnItemClickListener(this);

        if(savedInstanceState==null){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            MainFragment mainFragment=new MainFragment();
            transaction.add(R.id.fragmentContainer,mainFragment).commit();
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    //onItemClick to handle placement of title on drawer
    @Override
    public void onItemClick(AdapterView parent, View view, int position, long id) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        fragPos=position; //TODO IS THIS CORRECT???
        switch(position){
            case 0:
                //switching the fragment
                MainFragment mainFragment=new MainFragment();
                transaction.replace(R.id.fragmentContainer,mainFragment).commit();
                break;
            //add other cases to replace fragment with correct fragment
        }
        getActionBar().setTitle(fragmentNames[position]);
        drawerLayout.closeDrawer(drawerList);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(drawerList)) {
            //changing the title back to the fragment currently on
            getActionBar().setTitle(fragmentNames[fragPos]);
            drawerLayout.closeDrawer(drawerList);
        } else {
            super.onBackPressed();
        }
    }
}
