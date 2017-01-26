package za.co.entelect.training.basic;

public class PetService {

    private PetDb petDb;

    public PetService(PetDb petDb) {
        this.petDb = petDb;
    }

    /**
     * Saves an owner to the DB, and return the newly saved object.
     *
     * Throws exceptions if any fields are blank.
     */
    public Owner registerOwner(Owner owner) {
        return null;
    }

    /**
     * This one is a little trickier.
     *
     * The assumption is that an owner already exists in the DB.
     * The client doesn't want to provide an entire Owner object
     * Instead they will provide a Pet with a null owner, as well as an owner ID.
     * We have to fetch the owner using the ID, populate the pet with it, then save the pet.
     *
     * Throws exception if owner does not exist.
     */
    public void registerPetWithOwnerId(Pet pet, Long ownerId) {

    }
}
