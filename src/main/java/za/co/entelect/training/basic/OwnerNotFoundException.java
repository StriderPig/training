package za.co.entelect.training.basic;

public class OwnerNotFoundException extends RuntimeException {
    public OwnerNotFoundException(Long ownerId) {
        super(String.format("Could not find owner with id=%d", ownerId));
    }
}
