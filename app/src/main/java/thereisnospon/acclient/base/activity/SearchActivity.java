package thereisnospon.acclient.base.activity;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import thereisnospon.acclient.R;

/**
 * Created by yzr on 16/6/6.
 */
public abstract class SearchActivity extends FragmentActivity implements  SearchView.OnQueryTextListener {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean ret=super.onCreateOptionsMenu(menu);
        final MenuItem searchItem=menu.findItem(R.id.ab_search);
        SearchView searchview=(SearchView)searchItem.getActionView();
        searchview.setOnQueryTextListener(this);
        return ret;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
