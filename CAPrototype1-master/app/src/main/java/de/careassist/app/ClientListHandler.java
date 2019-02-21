package de.careassist.app;

import java.util.ArrayList;

/**
 * Created by Max on 28.06.17.
 * This class is handling the current list of clients from the route of today
 */

public class ClientListHandler {
    private static final ClientListHandler ourInstance = new ClientListHandler();
    private static ArrayList<Client> clientList;

    private ClientListHandler() {
    }

    public static ArrayList<Client> getClientList() {
        return clientList;
    }

    static void setClientList(ArrayList<Client> clientList) {
        ClientListHandler.clientList = clientList;
    }

    static ClientListHandler getInstance() {
        return ourInstance;
    }

}
