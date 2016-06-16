package thereisnospon.acclient.base.fragment;

import android.support.v4.app.Fragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import thereisnospon.acclient.event.Event;

/**
 * Created by yzr on 16/6/10.
 */
public abstract class BaseFragment extends Fragment{

    public void observerEvent(){
        EventBus.getDefault().register(this);
    }

    @Subscribe
    public void onEvent(Event event){

    }
}
