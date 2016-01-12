package es.hol.danirb.brastlewarkcensus.views;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.fonts.MaterialIcons;

import java.util.ArrayList;
import java.util.List;

import es.hol.danirb.brastlewarkcensus.R;
import es.hol.danirb.brastlewarkcensus.models.Gnome;
import es.hol.danirb.brastlewarkcensus.network.GetCesus;
import es.hol.danirb.brastlewarkcensus.views.adapters.GnomeRecyclerAdapter;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rv;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    private List<Gnome> gnomesTemp = new ArrayList<>();
    private List<Gnome> gnomes;
    private Context context;
    private GetCesus g;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        rv = (RecyclerView) findViewById(R.id.rv_gnomelist);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        context = this;
        g = new GetCesus(this, rv);
        g.execute();
        gnomes = new ArrayList<>();

//
    }


    public void handleMenuSearch() {
        ActionBar action = getSupportActionBar();
        if (isSearchOpened) {
            action.setDisplayShowCustomEnabled(false);
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);
            mSearchAction.setIcon(new IconDrawable(this, FontAwesomeIcons.fa_search)
                    .colorRes(R.color.colorPrimaryDark)
                    .actionBarSize());
            isSearchOpened = false;
        } else {
            action.setDisplayShowCustomEnabled(true);
            action.setCustomView(R.layout.searchbar);
            action.setDisplayShowTitleEnabled(false);
            edtSeach = (EditText) action.getCustomView().findViewById(R.id.edtSearch);
            edtSeach.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.d("BUSCANDO", s.toString());
                    gnomesTemp.clear();
                    for (Gnome gnome : g.getBr().getGnomeList()) {
                        if (gnome.getName().toLowerCase().contains(s)) {
                            Log.d("BUSCANDO", "Articulo:" + gnome.getName());
                            gnomesTemp.add(gnome);
                        }
                    }
                    rv.setAdapter(new GnomeRecyclerAdapter(gnomesTemp, context));
                    rv.getAdapter().notifyDataSetChanged();
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.d("BUSCANDO", "DESPUES" + s.toString());
                }
            });

            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    return actionId == EditorInfo.IME_ACTION_SEARCH;
                }
            });
            edtSeach.requestFocus();
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);
            mSearchAction.setIcon(
                    new IconDrawable(this, FontAwesomeIcons.fa_times)
                            .colorRes(R.color.colorPrimaryDark)
                            .actionBarSize());

            isSearchOpened = true;
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        mSearchAction.setIcon(
                new IconDrawable(this, MaterialIcons.md_search)
                        .colorRes(R.color.colorPrimaryDark)
                        .actionBarSize());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        handleMenuSearch();

        return super.onOptionsItemSelected(item);
    }

}
