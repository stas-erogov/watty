package ru.erogov.watty.agent;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.logging.LoggingFeature;
import ru.erogov.watty.api.Offer;
import ru.erogov.watty.api.OfferList;
import ru.erogov.watty.api.PriceInfo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Feature;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ws.rs.core.HttpHeaders.AUTHORIZATION;

public class MummyWattyResource implements WattyResource<OfferList> {

    private String token;
    private String URL_TARGET = "http://localhost:9000";
    private String LOGIN_PATH = "public/login";
    private String SEARCH_PATH = "article";
    private static final String BEARER = "Bearer";
    private static final String USERNAME_QUERY_PARAM = "username";
    private static final String PASSWORD_QUERY_PARAM = "password";

    private String user;
    private String password;

    @Override
    public OfferList apply(String request) {
        OfferList<Offer> offerList = new OfferList<>();
        Client client = getClient();

        if (token == null) {
            Form form = new Form();
            form.param(USERNAME_QUERY_PARAM, user);
            form.param(PASSWORD_QUERY_PARAM, password);
            Response response = client
                    .target(URL_TARGET)
                    .path(LOGIN_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED));
            token = response.readEntity(String.class);
        }
        List<PriceInfo> answer = client
                .target(URL_TARGET)
                .path(SEARCH_PATH)
                .path(request)
                .request(MediaType.APPLICATION_JSON)
                .header(AUTHORIZATION, BEARER + " " + token)
                .get(new GenericType<List<PriceInfo>>() {
                });
        fillOffer(offerList, answer);

        System.out.println(offerList.toString());
        return offerList;
    }

    private void fillOffer(OfferList<Offer> offerList, List<PriceInfo> answer) {
        for (PriceInfo priceInfo : answer) {
            Offer offer = new Offer();
            offer.setVendorCode(priceInfo.getArticle().getArticleCode());
            offer.setName(priceInfo.getArticle().getDescription());
            offer.setPrice(priceInfo.getPrice());
            offerList.add(offer);
        }
    }

    private Client getClient() {
        ClientConfig config = new ClientConfig();
        config.property(LoggingFeature.LOGGING_FEATURE_VERBOSITY_CLIENT, LoggingFeature.Verbosity.PAYLOAD_ANY);

        Logger logger = Logger.getLogger(MummyWattyResource.class.getName());
        Feature loggingFeature = new LoggingFeature(logger, Level.INFO, null, null);
        config.register(loggingFeature);

        config.register(JacksonFeature.class);

        return ClientBuilder.newClient(config);
    }

    public static void main(String[] args) {
        MummyWattyResource client = new MummyWattyResource();
        client.setUser("skovorodkin");
        client.setPassword("c3p0");

        OfferList<Offer> offerList = client.apply("1231");
        for (Offer offer : offerList.getOffers()) {
            System.out.println(offer.toString());
        }
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
