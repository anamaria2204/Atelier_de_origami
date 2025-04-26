package atelier.atelier_de_origami.domain.validator;

public interface Factory {
    Validator createValidator(ValidatorStrategy strategy);
}
