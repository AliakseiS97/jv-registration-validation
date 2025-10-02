package core.basesyntax.service;

import core.basesyntax.dao.StorageDao;
import core.basesyntax.dao.StorageDaoImpl;
import core.basesyntax.model.User;

public class RegistrationServiceImpl implements RegistrationService {
    private final StorageDao storageDao = new StorageDaoImpl();

    @Override
    public User register(User user) {
        if (user.getLogin() == null || user.getLogin().isEmpty()) {
            throw new RegistrationException("Login can't be empty or null");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RegistrationException("Password can't be empty or null");
        }
        if (user.getAge() < 18) {
            throw new RegistrationException("Age must be at least 18");
        }
        if (user.getPassword().length() < 6) {
            throw new RegistrationException("Password must be at least 6 characters");
        }
        if (user.getLogin().length() < 6) {
            throw new RegistrationException("Login must be at least 6 characters");
        }
        if (storageDao.get(user.getLogin()) != null) {
            throw new RegistrationException("User already exists");
        }
        return storageDao.add(user);
    }
}
