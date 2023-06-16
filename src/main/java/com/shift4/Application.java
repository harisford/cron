package com.shift4;

import com.shift4.cron.Cron;
import com.shift4.provider.Docker;
import com.shift4.provider.Environment;
import com.shift4.provider.IDE;
import com.shift4.provider.LocalMachine;

import java.util.Map;
import java.util.Optional;

public class Application {
    private static final Cron cron = new Cron();
    private static final String APP_ENV_NAME = "APP";

    private static final Map<String, Environment> providers = Map.of(
            Environment.CLI, new LocalMachine(),
            Environment.DOCKER, new Docker(),
            Environment.IDE, new IDE()
    );

    public static void main(String[] arg){
        String app = Optional.ofNullable(System.getenv(APP_ENV_NAME)).orElse(Environment.CLI);
        Environment environment = providers.get(app);
        cron.print(arg, environment);
    }
}
