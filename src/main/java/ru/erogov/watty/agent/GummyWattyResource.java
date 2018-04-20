package ru.erogov.watty.agent;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.erogov.watty.api.Offer;
import ru.erogov.watty.api.OfferList;

import java.math.BigDecimal;

public class GummyWattyResource implements WattyResource<OfferList> {
    private static final Logger logger = LoggerFactory.getLogger(GummyWattyResource.class);

    @Override
    public OfferList apply(String request) {
        logger.debug("started request :'" + request + "' in object " + this.toString());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
        logger.debug("finished request :'" + request + "' in object " + this.toString());
        OfferList offerList = new OfferList();
        Offer offer = new Offer("GUMM567","Gummy", new BigDecimal(1.40));
        offerList.add(offer);
        return offerList;
    }

    public static String getCompleteOffer(String request, Class clazz) {
        JSONParser parser = new JSONParser();
        logger.debug("JSON parser created: " + parser);
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(request);
            logger.debug("Parsed: " + jsonObject.toJSONString());
            jsonObject.put("class", clazz.getClass().getName());
            logger.debug("Added with class name: " + jsonObject.toJSONString());
        } catch (ParseException e) {
            logger.error(e.getMessage(), e);
        }
        return jsonObject.toJSONString();
    }
}
