package de.dhbw.karlsruhe.getraenkeabrechnung.rights;

import de.dhbw.karlsruhe.getraenkeabrechnung.data.users.User;

import java.util.HashSet;
import java.util.Set;

public class AdminRights
{
    private Set<Right> rights;

    public AdminRights()
    {
        rights = new HashSet<>();
        rights.add(Right.CAN_SELF_INVOICE);
        rights.add(Right.CAN_CREATE_NEW_USER);
        rights.add(Right.CAN_DELETE_USER);
        rights.add(Right.CAN_CREATE_DRINK);
        rights.add(Right.CAN_CREATE_CATEGORY);
        rights.add(Right.CAN_ADD_RIGHTS);
        rights.add(Right.CAN_VIEW_STORIES);
    }

    public void giveTo(User user)
    {
        user.addRights(rights);
    }
}
