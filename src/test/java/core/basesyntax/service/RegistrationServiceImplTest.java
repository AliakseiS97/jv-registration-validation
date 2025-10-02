package core.basesyntax.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import core.basesyntax.model.User;
import org.junit.jupiter.api.Test;

class RegistrationServiceImplTest {
    @Test
    void register_loginTooShort_notOk() {
        User user = new User();
        user.setLogin("abc");
        user.setPassword("validPassword");
        user.setAge(30);

        RegistrationException actual = assertThrows(RegistrationException.class,
                () -> new RegistrationServiceImpl().register(user));
        assertEquals("Login must be at least 6 characters", actual.getMessage());
    }

    @Test
    void register_loginIsEmpty_notOk() {
        User user = new User();
        user.setLogin("");
        user.setPassword("alghkregohi");
        user.setAge(19);
        RegistrationException actual = assertThrows(
                RegistrationException.class,
                () -> new RegistrationServiceImpl().register(user));
        assertEquals("Login can't be empty or null", actual.getMessage());
    }

    @Test
    void register_loginIsNull_notOk() {
        User user = new User();
        user.setLogin(null);
        user.setPassword("akaufrygak");
        user.setAge(73);
        RegistrationException actual = assertThrows(
                RegistrationException.class,
                () -> new RegistrationServiceImpl().register(user));
        assertEquals("Login can't be empty or null", actual.getMessage());
    }

    @Test
    void register_passwordTooShort_notOk() {
        User user = new User();
        user.setLogin("DmitryIvanov");
        user.setPassword("8976");
        user.setAge(30);
        RegistrationException actual = assertThrows(RegistrationException.class,
                () -> new RegistrationServiceImpl().register(user));
        assertEquals("Password must be at least 6 characters", actual.getMessage());
    }

    @Test
    void register_passwordIsNull_notOk() {
        User user = new User();
        user.setLogin("GrigoriiLebedev");
        user.setPassword(null);
        user.setAge(70);
        RegistrationException actual = assertThrows(
                RegistrationException.class,
                () -> new RegistrationServiceImpl().register(user));
        assertEquals("Password can't be empty or null", actual.getMessage());
    }

    @Test
    void register_passwordIsEmpty_notOk() {
        User user = new User();
        user.setLogin("EvgeniiAzov");
        user.setPassword("");
        user.setAge(45);
        RegistrationException actual = assertThrows(
                RegistrationException.class,
                () -> new RegistrationServiceImpl().register(user));
        assertEquals("Password can't be empty or null", actual.getMessage());
    }

    @Test
    void register_ageTooYoung_notOk() {
        User user = new User();
        user.setLogin("SvetlanaPavlova");
        user.setPassword("123456789");
        user.setAge(3);
        RegistrationException actual = assertThrows(
                RegistrationException.class,
                () -> new RegistrationServiceImpl().register(user));
        assertEquals("Age must be at least 18", actual.getMessage());
    }

    @Test
    void register_storageContainUsername_notOk() {
        final RegistrationServiceImpl service = new RegistrationServiceImpl();
        User user = new User();
        user.setLogin("NikitaNikitin");
        user.setPassword("chnrf3f33ASf");
        user.setAge(55);
        service.register(user);
        User user2 = new User();
        user2.setLogin("NikitaNikitin");
        user2.setPassword("anotherPassword");
        user2.setAge(29);
        RegistrationException actual = assertThrows(RegistrationException.class,
                () -> service.register(user2));
        assertEquals("User already exists", actual.getMessage());
    }

    @Test
    void register_validUser_ok() {
        User user = new User();
        user.setLogin("AliakseiShyhala");
        user.setPassword("validPassword");
        user.setAge(30);
        User registered = new RegistrationServiceImpl().register(user);
        assertEquals(user.getLogin(), registered.getLogin());
        assertEquals(user.getPassword(), registered.getPassword());
        assertEquals(user.getAge(), registered.getAge());
    }
}
