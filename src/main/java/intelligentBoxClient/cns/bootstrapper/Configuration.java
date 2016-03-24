package intelligentBoxClient.cns.bootstrapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by yaohx on 3/24/2016.
 */
@Component
public class Configuration {
    @Value("${retryMax}")
    private int _retryMax;

    public int getRetryMax(){
        return _retryMax;
    }
}
