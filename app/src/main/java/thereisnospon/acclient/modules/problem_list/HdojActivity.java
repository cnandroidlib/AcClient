package thereisnospon.acclient.modules.problem_list;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import butterknife.BindView;
import butterknife.ButterKnife;
import thereisnospon.acclient.R;
import thereisnospon.acclient.base.activity.SearchActivity;
import thereisnospon.acclient.modules.problem_list.search_list.SearchProblemFragment;

/**
 * Created by yzr on 16/6/5.
 */
public class HdojActivity extends SearchActivity
        implements View.OnClickListener{

    public static final String TAG="HdojActivity";

    BottomSheetBehavior behavior;

    @BindView(R.id.problem_fab)FloatingActionButton faButton;
    @BindView(R.id.problem_pager_np)NumberPicker numberPicker;
    @BindView(R.id.problem_pager_btn)Button  numButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_problem_list);
        initDrawer();
        changeFragment(new HdojProblemFragment());
        ButterKnife.bind(this);
        initView();
    }

    void initView(){
        LinearLayout linearLayout=(LinearLayout)findViewById(R.id.problem_bottomsheet);
        behavior=BottomSheetBehavior.from(linearLayout);
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        linearLayout.setOnClickListener(this);
        faButton.setOnClickListener(this);
        behavior.setBottomSheetCallback(bcall);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(48);
        numButton.setOnClickListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Fragment fragment= SearchProblemFragment.newInstance(query);
        changeFragment(fragment);
        return false;
    }

    void onLinearLayout(){
        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }
    void onFab(){
        behavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }

    void changePage(){
        int page=numberPicker.getValue();
        Fragment fragment=HdojProblemFragment.newInstance(page);
        changeFragment(fragment);
    }

    BottomSheetBehavior.BottomSheetCallback bcall=
            new BottomSheetBehavior.BottomSheetCallback() {
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.problem_bottomsheet:
                onLinearLayout();
                break;
            case R.id.problem_fab:
                onFab();
                break;
            case R.id.problem_pager_btn:
                changePage();
            default:
                break;
        }
    }
}
