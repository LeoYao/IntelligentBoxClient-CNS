package intelligentBoxClient.cns.message;

public class DropboxNotification {
    private ListFolder _list_folder;
    private Delta _delta;

    public ListFolder getList_folder() {
        return _list_folder;
    }

    public void setList_folder(ListFolder l) {
        _list_folder = l;
    }

    public Delta getDelta() {
        return _delta;
    }

    public void setDelta(Delta d) {
        _delta = d;
    }
}
