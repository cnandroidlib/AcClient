package thereisnospon.acclient.base.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import thereisnospon.acclient.R;
import thereisnospon.acclient.modules.problem_list.HdojPresenterImpl;
import thereisnospon.acclient.modules.submmit_status.SubmmitStatusContact;

/**
 * Created by yzr on 16/8/2.
 */
public abstract class NormalSwipeFragment <T> extends BaseSwipeFragment<T> {


    public View normalViewAndInit(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.normal_list_layout,container,false);
        init(view, R.id.normal_list_swipe,R.id.normal_list_recycle);
        return view;
    }
}
