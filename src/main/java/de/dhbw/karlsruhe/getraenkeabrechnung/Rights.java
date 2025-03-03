package de.dhbw.karlsruhe.getraenkeabrechnung;

public interface Rights {

    public boolean hasUserRights();
    public boolean hasBillingRights();
    public boolean hasAdminRights();
}
