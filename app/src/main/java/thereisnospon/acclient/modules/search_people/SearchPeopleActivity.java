package thereisnospon.acclient.modules.search_people;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.SearchActivity;

public class SearchPeopleActivity extends SearchActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_people);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        SearchPeopleFragment fragment=SearchPeopleFragment.newInstance(query);
        changeFragment(fragment);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }


    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_content,fragment)
                .commit();
    }

}
