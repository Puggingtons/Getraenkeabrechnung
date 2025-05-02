package de.dhbw.karlsruhe.getraenkeabrechnung.data.validatables;

import java.util.Optional;

public interface Validatable {
    boolean isValid(Optional<String> pattern);
}
