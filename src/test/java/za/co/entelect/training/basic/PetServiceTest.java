package za.co.entelect.training.basic;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class PetServiceTest {
    @Mock
    PetDb petDb;

    PetService petService;

    @Before
    public void setUp() throws Exception {
        petService = new PetService(petDb);
    }

    @Test
    public void shouldSaveOwnerToDb() throws Exception {
        Owner owner = createOwner();
        owner.setName("testName");
        owner.setAddress("testAddress");

        petService.registerOwner(owner);

        verify(petDb).saveOwner(owner);
    }

    @Test
    public void shouldReturnNewlySavedOwner() throws Exception {
        Owner providedOwner = createOwner();
        Owner expectedOwner = createOwner();

        when(petDb.saveOwner(providedOwner)).thenReturn(expectedOwner);
        Owner actualOwner = petService.registerOwner(providedOwner);

        assertThat(actualOwner, equalTo(expectedOwner));
    }

    @Test
    public void shouldThrowConstraintViolationExceptionWithMessageIfNameIsNull() throws Exception {
        Owner owner = createOwner();
        owner.setName(null);

        try {
            petService.registerOwner(owner);
            fail("Should have thrown a constraint violation exception by this stage");
        } catch (ConstraintViolationException e) {
            assertThat(e.getMessage(), equalTo("Name cannot be null"));
        } catch (Exception e) {
            fail("Should not have thrown this type of exception");
        }
    }

    @Test
    public void shouldThrowConstraintViolationExceptionWIthMessageIfNameIsEmpty() throws Exception {
        Owner owner = createOwner();
        owner.setName("");

        try {
            petService.registerOwner(owner);
            fail("Should have thrown a constraint violation exception by this stage");
        } catch (ConstraintViolationException e) {
            assertThat(e.getMessage(), equalTo("Name cannot be null"));
        } catch (Exception e) {
            fail("Should not have thrown this type of exception");
        }
    }

    @Test
    public void shouldThrowConstraintViolationExceptionWithMessageIfAddressIsNull() throws Exception {
        Owner owner = createOwner();
        owner.setAddress(null);

        try {
            petService.registerOwner(owner);
            fail("Should have thrown a constraint violation exception by this stage");
        } catch (ConstraintViolationException e) {
            assertThat(e.getMessage(), equalTo("Address cannot be null"));
        } catch (Exception e) {
            fail("Should not have thrown this type of exception");
        }
    }

    @Test
    public void shouldThrowConstraintViolationExceptionWIthMessageIfAddressIsEmpty() throws Exception {
        Owner owner = createOwner();
        owner.setAddress("");

        try {
            petService.registerOwner(owner);
            fail("Should have thrown a constraint violation exception by this stage");
        } catch (ConstraintViolationException e) {
            assertThat(e.getMessage(), equalTo("Address cannot be null"));
        } catch (Exception e) {
            fail("Should not have thrown this type of exception");
        }
    }

    @Test
    public void shouldSavePetToDb() throws Exception {
        Pet pet = new Pet();

        when(petDb.getOwner(100L)).thenReturn(new Owner()); // This line looks pointless right now, but it becomes necessary after you make the last test pass.
        petService.registerPetWithOwnerId(pet, 100L);

        verify(petDb).savePet(pet);
    }

    @Test
    public void shouldFetchOwnerFromDbAndAddItToPetBeforeSaving() throws Exception {
        Owner ownerFromDb = createOwner();
        ArgumentCaptor<Pet> petArgumentCaptor = ArgumentCaptor.forClass(Pet.class);

        when(petDb.getOwner(100L)).thenReturn(ownerFromDb);
        when(petDb.savePet(petArgumentCaptor.capture())).thenReturn(new Pet());

        petService.registerPetWithOwnerId(new Pet(), 100L);

        assertThat(petArgumentCaptor.getValue().getOwner(), equalTo(ownerFromDb));
    }

    @Test
    public void shouldThrowOwnerNotFoundExceptionIfOwnerNotInDb() throws Exception {
        // By virtue of not adding a when(pet.getOwner...), that call will return a null, so by default owner is not found.
        try {
            petService.registerPetWithOwnerId(new Pet(), 100L);
            fail("Should have thrown a OwnerNotFoundException by now");
        } catch (OwnerNotFoundException e) {
            assertThat(e.getMessage(), equalTo("Could not find owner with id=100"));
        } catch (Exception e) {
            fail("Shouldn't have thrown this kind of exception");
        }
    }

    private Owner createOwner() {
        Owner owner = new Owner();
        owner.setName("testName");
        owner.setAddress("testAddress");
        return owner;
    }
}

