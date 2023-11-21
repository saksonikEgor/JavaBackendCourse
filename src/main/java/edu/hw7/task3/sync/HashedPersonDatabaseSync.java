package edu.hw7.task3.sync;

import edu.hw7.task3.model.Person;
import edu.hw7.task3.model.PersonDatabase;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class HashedPersonDatabaseSync implements PersonDatabase {
    private final Map<Integer, Person> ids;
    private final Map<String, Person> names;
    private final Map<String, Person> addresses;
    private final Map<String, Person> phoneNumbers;

    public HashedPersonDatabaseSync(
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

    public HashedPersonDatabaseSync() {
        this(new HashMap<>(), new HashMap<>(), new HashMap<>(), new HashMap<>());
    }

    @Override
    public synchronized void add(Person person) {
        if (person.name() == null || person.address() == null || person.phoneNumber() == null) {
            throw new NullPointerException("Persons fields cant be null");
        }
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
        phoneNumbers.put(person.address(), person);
    }

    @Override
    public synchronized void delete(int id) {
        Person person = ids.remove(id);

        if (person == null) {
            return;
        }

        names.remove(person.name());
        addresses.remove(person.address());
        phoneNumbers.remove(person.phoneNumber());
    }

    @Override
    public synchronized @Nullable Person findByName(String name) {
        return names.get(name);
    }

    @Override
    public synchronized @Nullable Person findByAddress(String address) {
        return addresses.get(address);
    }

    @Override
    public synchronized @Nullable Person findByPhone(String phone) {
        return phoneNumbers.get(phone);
    }
}
