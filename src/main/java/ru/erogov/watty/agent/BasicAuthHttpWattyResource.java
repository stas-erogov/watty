package ru.erogov.watty.agent;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import ru.erogov.watty.api.OfferList;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Feature;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class BasicAuthHttpWattyResource implements WattyResource<OfferList> {

    private String user;
    private String password;
    private String key;
    private String className;

    public BasicAuthHttpWattyResource() {
        className = this.getClass().getName();
    }

    public Client createClient() {
        ClientConfig config = new ClientConfig();

        if (user != null && password != null) {
            HttpAuthenticationFeature authenticationFeature = HttpAuthenticationFeature.basic(user, password);
            config.register(authenticationFeature);
        }

        config.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY);

        Logger logger = Logger.getLogger(className);
        Feature loggingFeature = new LoggingFeature(logger, Level.INFO, null, null);
        config.register(loggingFeature);

        config.register(JacksonFeature.class);

        return ClientBuilder.newClient(config);
    }

    @Override
    public OfferList apply(String request) {
        return null;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
