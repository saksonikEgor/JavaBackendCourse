package edu.hw7.task3.rwLock;

import edu.hw7.task3.model.Person;
import edu.hw7.task3.model.PersonDatabase;
import org.jetbrains.annotations.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class HashedPersonDatabaseRWLock implements PersonDatabase {
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private final Map<Integer, Person> ids;
    private final Map<String, Person> names;
    private final Map<String, Person> addresses;
    private final Map<String, Person> phoneNumbers;

    public HashedPersonDatabaseRWLock(
        Map<Integer, Person> ids,
        Map<String, Person> names,
        Map<String, Person> addresses,
        Map<String, Person> phoneNumbers
    ) {
        this.ids = ids;
        this.names = names;
        this.addresses = addresses;
        this.phoneNumbers = phoneNumbers;
    }

    public HashedPersonDatabaseRWLock() {
        this(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    @Override
    public void add(Person person) {
        if (person.name() == null || person.address() == null || person.phoneNumber() == null) {
            throw new NullPointerException("Persons fields cant be null");
        }

        try {
            lock.writeLock().lock();

            if (ids.containsKey(person.id())) {
                throw new IllegalArgumentException("Person with id " + person.id() + " is already added");
            }
            if (names.containsKey(person.name())) {
                throw new IllegalArgumentException("Person with name " + person.name() + " is already added");
            }
            if (addresses.containsKey(person.address())) {
                throw new IllegalArgumentException("Person with address " + person.address() + " is already added");
            }
            if (phoneNumbers.containsKey(person.phoneNumber())) {
                throw new IllegalArgumentException(
                    "Person with phoneNumber " + person.phoneNumber() + " is already added"
                );
            }

            ids.put(person.id(), person);
            names.put(person.name(), person);
            addresses.put(person.address(), person);
            phoneNumbers.put(person.phoneNumber(), person);
        } finally {
            lock.writeLock().unlock();
        }
    }

    @Override
    public void delete(int id) {
        try {
            lock.writeLock().lock();

            Person person = ids.remove(id);

            if (person == null) {
                return;
            }

            names.remove(person.name());
            addresses.remove(person.address());
            phoneNumbers.remove(person.phoneNumber());
        } finally {
            lock.writeLock().unlock();
        }

    }

    @Override
    public @Nullable Person findByName(String name) {
        try {
            lock.readLock().lock();

            return names.get(name);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByAddress(String address) {
        try {
            lock.readLock().lock();

            return addresses.get(address);
        } finally {
            lock.readLock().unlock();
        }
    }

    @Override
    public @Nullable Person findByPhone(String phone) {
        try {
            lock.readLock().lock();

            return phoneNumbers.get(phone);
        } finally {
            lock.readLock().unlock();
        }
    }
}
