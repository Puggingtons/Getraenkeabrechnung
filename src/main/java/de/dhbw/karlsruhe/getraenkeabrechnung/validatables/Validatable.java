package de.dhbw.karlsruhe.getraenkeabrechnung.validatables;

import java.util.Optional;

public interface Validatable {
    boolean isValid(Optional<String> pattern);
}
