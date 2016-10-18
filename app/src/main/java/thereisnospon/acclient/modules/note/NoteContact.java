package thereisnospon.acclient.modules.note;

import java.util.List;

import thereisnospon.acclient.data.NoteItem;

/**
 * Created by yzr on 16/9/9.
 */
public interface NoteContact {

    interface View{
        void onRefreshNotes(List<NoteItem> noteItemList);
        void onMoreNotes(List<NoteItem>noteItems);
        void onFailure(String msg);
    }
    interface Presenter{
        void refreshNotes();
        void moreNotes();
    }
    interface Model{
        List<NoteItem>refreshNotes();
        List<NoteItem>moreNotes();
    }
}
