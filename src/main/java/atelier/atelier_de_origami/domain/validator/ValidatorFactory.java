package atelier.atelier_de_origami.domain.validator;

import java.io.InvalidObjectException;

public class ValidatorFactory implements Factory {
    private static ValidatorFactory instance = null;

    // Private constructor
    private ValidatorFactory() {
    }

    /**
     * Method for getting the instance of the factory
     *
     * @return the instance of the factory
     */
    public static ValidatorFactory getInstance() {
        if (instance == null) {
            instance = new ValidatorFactory();
        }
        return instance;
    }

    /**
     * Method for creating a validator
     *
     * @param strategy - the strategy for the validator
     * @return a new validator
     */
    @Override
    public Validator createValidator(ValidatorStrategy strategy) {
        switch (strategy) {
            case User -> {
                return new UserValidator();
            }
            case Student -> {
                return new StudentValidator();
            }
            case Teacher -> {
                return new TeacherValidator();
            }
            case Course -> {
                return new CourseValidator();
            }
            case Booking -> {
                return new BookingValidator();
            }
            default -> throw new Error("Invalid strategy");
        }
    }
}
