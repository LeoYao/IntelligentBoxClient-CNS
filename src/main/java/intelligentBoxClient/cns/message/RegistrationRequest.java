package intelligentBoxClient.cns.message;

/**
 * Created by yaohx on 3/21/2016.
 */
public class RegistrationRequest {
    private String _accountId;
    private String _callbackUrl;

    public String getAccountId() {return _accountId;}
    public void setAccountId(String accountId) {_accountId = accountId;}

    public String getCallbackUrl() {return _callbackUrl;}
    public void setCallbackUrl(String callbackUrl) {_callbackUrl = callbackUrl;}

    @Override
    public String toString()
    {
        return "[accountId: " + _accountId + ", callbackUrl: " + _callbackUrl + "]";
    }
}
