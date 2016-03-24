package intelligentBoxClient.cns.dao;

import intelligentBoxClient.cns.dao.objects.CallbackReg;

/**
 * Created by yaohx on 3/24/2016.
 */
public interface ICallbackRegRepository {

    CallbackReg get(String accountId);
    boolean upsert(CallbackReg item);
    boolean upsert(CallbackReg item, boolean overwritten);
    boolean delete(CallbackReg item);
    boolean delete(CallbackReg item, boolean force);
}
