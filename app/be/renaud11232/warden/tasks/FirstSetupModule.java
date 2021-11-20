package be.renaud11232.warden.tasks;

import com.google.inject.AbstractModule;

public class FirstSetupModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(FirstSetupTask.class).asEagerSingleton();
    }
}
