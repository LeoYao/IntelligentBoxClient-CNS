package intelligentBoxClient.cns.dao;

import intelligentBoxClient.cns.dao.objects.CallbackReg;

/**
 * Created by yaohx on 3/24/2016.
 */
public interface ICallbackRegRepository {

    CallbackReg get(String accountId);
    boolean register(String accountId, String callbackUrl);
    boolean unregister(String accountId, String callbackUrl);
}
