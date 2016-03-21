package intelligentBoxClient.cns.message;

/**
 * Created by yaohx on 3/21/2016.
 */
public class RegistrationRequest {
    private int _userId;
    private String _callbackUrl;

    public int getUserId() {return _userId;}
    public void setUserId(int u) {_userId = u;}

    public String getCallbackUrl() {return _callbackUrl;}
    public void setCallbackUrl(String c) {_callbackUrl = c;}
}
