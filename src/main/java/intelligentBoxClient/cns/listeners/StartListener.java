package intelligentBoxClient.cns.listeners;

import intelligentBoxClient.cns.bootstrapper.Bootstrapper;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

/**
 * Created by yaohx on 3/24/2016.
 */
public class StartListener implements ApplicationListener<ContextStartedEvent> {

    @Override
    public void onApplicationEvent(final ContextStartedEvent event) {
        Bootstrapper bootStrapper = event.getApplicationContext().getBean(Bootstrapper.class);
        bootStrapper.startup();
    }
}