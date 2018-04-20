package ru.erogov.watty.agent;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.erogov.watty.api.Offer;
import ru.erogov.watty.api.OfferList;

import java.math.BigDecimal;

public class DummyWattyResource implements WattyResource<OfferList> {
    private static final Logger logger = LoggerFactory.getLogger(DummyWattyResource.class);

    @Override
    public OfferList apply(String request) {
        final String className = this.getClass().getName();
        logger.debug("started request :'" + request + "' in object " + className);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("finished request :'" + request + "' in object " + className);

        OfferList offerList = new OfferList();
        Offer offer = new Offer("DUMM123","Dummy", new BigDecimal(3.62));
        offerList.add(offer);
        return offerList;
    }

    public static String getCompleteOffer(String request, String className) {
        JSONParser parser = new JSONParser();
        logger.debug("JSON parser created: " + parser);
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(request);
            logger.debug("Parsed: " + jsonObject.toJSONString());
            jsonObject.put("class", className);
            logger.debug("Added with class name: " + jsonObject.toJSONString());
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return jsonObject.toJSONString();
    }

    public static String someJsonOffer(String request, String className) {
        JsonElement element = new JsonParser().parse(request);
        JsonObject object = element.getAsJsonObject();
        object.addProperty("class",className);
        return object.getAsString();
    }
}
