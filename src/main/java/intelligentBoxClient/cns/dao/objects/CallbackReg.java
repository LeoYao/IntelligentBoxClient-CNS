package intelligentBoxClient.cns.dao.objects;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBVersionAttribute;

/**
 * Created by yaohx on 3/22/2016.
 */
@DynamoDBTable(tableName="CallbackReg")
public class CallbackReg {
    private String _accountId;
    private String _callbackUrl;
    private Long _version;

    public CallbackReg() {
    }

    public CallbackReg(String accountId, String callbackUrl) {
        _accountId = accountId;
        _callbackUrl = callbackUrl;
    }

    @DynamoDBHashKey(attributeName = "account_id")
    public String getAccountId() {
        return _accountId;
    }

    public void setAccountId(String accountId) {
        _accountId = accountId;
    }

    @DynamoDBAttribute(attributeName = "callback_url")
    public String getCallbackUrl() {
        return _callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        _callbackUrl = callbackUrl;
    }

    @DynamoDBVersionAttribute(attributeName = "version")
    public Long getVersion() {
        return _version;
    }

    public void setVersion(Long version) {
        _version = version;
    }
}
