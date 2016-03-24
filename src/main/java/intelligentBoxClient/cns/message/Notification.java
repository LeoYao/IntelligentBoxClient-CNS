package intelligentBoxClient.cns.message;

/**
 * Created by yaohx on 3/24/2016.
 */
public class Notification {
    private String _accountId;

    public Notification(String accountId)
    {
        _accountId = accountId;
    }

    public String getAccountId()
    {
        return _accountId;
    }

    public void setAccountId(String accountId)
    {
        _accountId = accountId;
    }
}
