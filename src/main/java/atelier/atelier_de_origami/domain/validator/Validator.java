package atelier.atelier_de_origami.domain.validator;

public interface Validator<T> {
    void validate(T entity) throws Exception;
}