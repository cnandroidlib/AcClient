package thereisnospon.acclient.modules.rank;

import java.util.List;

import thereisnospon.acclient.data.RankItem;

/**
 * Created by yzr on 16/8/27.
 */
public interface RankContact {


    interface View{
        void onRefreshRankSuccess(List<RankItem> list);
        void onMoreRanks(List<RankItem>list);
        void onRankFailure(String msg);
    }

    interface Model{
        List<RankItem>loadRankItems();
        List<RankItem>moreRankItems();
    }

    interface Presenter{
        void loadRankItems();
        void moreRankItems();
    }
}
