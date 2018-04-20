package ru.erogov.watty.agent;

import java.util.ArrayList;
import java.util.List;

public class WattyResourceHelper {
    public static List<WattyResource> getAgents() {
        List<WattyResource> agents = new ArrayList<>();
        agents.add(new DummyWattyResource());
        agents.add(new GummyWattyResource());

        MummyWattyResource mummyWattyResource = new MummyWattyResource();
        mummyWattyResource.setUser("skovorodkin");
        mummyWattyResource.setPassword("c3p0");

        agents.add(mummyWattyResource);

        return agents;
    }
}
