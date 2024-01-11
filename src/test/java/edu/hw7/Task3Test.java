package edu.hw7;

import edu.hw7.task3.model.Person;
import edu.hw7.task3.rwLock.HashedPersonDatabaseRWLock;
import edu.hw7.task3.sync.HashedPersonDatabaseSync;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class Task3Test {
    @Test
    @DisplayName("Последовательный поиск людей по аттрибутам используя synchronized")
    void synchronizedSearch() {
        Person person1 = new Person(1, "Will", "Will`s address", "Will`s phone");
        Person person2 = new Person(2, "Bob", "Bob`s address", "Bob`s phone");
        Person person3 = new Person(3, "Jane", "Jane`s address", "Jane`s phone");

        var database = new HashedPersonDatabaseSync();
        database.add(person1);
        database.add(person2);
        database.add(person3);

        assertEquals(person2, database.findByName(person2.name()));
        assertEquals(person1, database.findByAddress(person1.address()));
        assertEquals(person3, database.findByPhone(person3.phoneNumber()));

        database.delete(person2.id());

        assertNull(database.findByName(person2.name()));
        assertNull(database.findByAddress(person2.address()));
        assertNull(database.findByPhone(person2.phoneNumber()));
    }

    @Test
    @DisplayName("Имитация параллельного поиска людей по аттрибутам используя synchronized")
    void syncParallelSearch() {
        List<Person> people = List.of(
            new Person(1, "Will", "Will`s address", "Will`s phone"),
            new Person(2, "Bob", "Bob`s address", "Bob`s phone"),
            new Person(3, "Jane", "Jane`s address", "Jane`s phone"),
            new Person(4, "Tiny", "Tiny`s address", "Tiny`s phone"),
            new Person(5, "Shon", "Shon`s address", "Shon`s phone"),
            new Person(6, "Katy", "Katy`s address", "Katy`s phone"),
            new Person(7, "Ron", "Ron`s address", "Ron`s phone"),
            new Person(8, "Tim", "Tim`s address", "Tim`s phone")
        );

        var database = new HashedPersonDatabaseSync();

        List<Thread> adders = new ArrayList<>();
        List<Thread> removers = new ArrayList<>();
        List<Thread> readers = new ArrayList<>();
        AtomicInteger failCount = new AtomicInteger(0);

        people.forEach(p -> {
            adders.add(new Thread(() -> database.add(p)));
            removers.add(new Thread(() -> database.delete(p.id())));
            readers.add(new Thread(() -> {
                if (database.findByName(p.name()) != null) {
                    if (!(database.findByName(p.name()) == database.findByAddress(p.address())
                        && database.findByAddress(p.address()) == database.findByPhone(p.phoneNumber()))) {
                        failCount.getAndIncrement();
                    }
                }
            }));
        });

        adders.forEach(Thread::start);
        removers.forEach(Thread::start);
        readers.forEach(Thread::start);

        adders.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        removers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        readers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        assertEquals(0, failCount.get());
    }

    @Test
    @DisplayName("Последовательный поиск людей по аттрибутам используя reed/write lock")
    void rwLockSearch() {
        Person person1 = new Person(1, "Will", "Will`s address", "Will`s phone");
        Person person2 = new Person(2, "Bob", "Bob`s address", "Bob`s phone");
        Person person3 = new Person(3, "Jane", "Jane`s address", "Jane`s phone");

        var database = new HashedPersonDatabaseRWLock();
        database.add(person1);
        database.add(person2);
        database.add(person3);

        assertEquals(person2, database.findByName(person2.name()));
        assertEquals(person1, database.findByAddress(person1.address()));
        assertEquals(person3, database.findByPhone(person3.phoneNumber()));

        database.delete(person2.id());

        assertNull(database.findByName(person2.name()));
        assertNull(database.findByAddress(person2.address()));
        assertNull(database.findByPhone(person2.phoneNumber()));
    }

    @Test
    @DisplayName("Имитаия параллельного поиска людей по аттрибутам используя reed/write lock")
    void rwLockParallelSearch() {
        List<Person> people = List.of(
            new Person(1, "Will", "Will`s address", "Will`s phone"),
            new Person(2, "Bob", "Bob`s address", "Bob`s phone"),
            new Person(3, "Jane", "Jane`s address", "Jane`s phone"),
            new Person(4, "Tiny", "Tiny`s address", "Tiny`s phone"),
            new Person(5, "Shon", "Shon`s address", "Shon`s phone"),
            new Person(6, "Katy", "Katy`s address", "Katy`s phone"),
            new Person(7, "Ron", "Ron`s address", "Ron`s phone"),
            new Person(8, "Tim", "Tim`s address", "Tim`s phone")
        );

        var database = new HashedPersonDatabaseRWLock();

        List<Thread> adders = new ArrayList<>();
        List<Thread> removers = new ArrayList<>();
        List<Thread> readers = new ArrayList<>();
        AtomicInteger failCount = new AtomicInteger(0);

        people.forEach(p -> {
            adders.add(new Thread(() -> database.add(p)));
            removers.add(new Thread(() -> database.delete(p.id())));
            readers.add(new Thread(() -> {
                if (database.findByName(p.name()) != null) {
                    if (!(database.findByName(p.name()) == database.findByAddress(p.address())
                        && database.findByAddress(p.address()) == database.findByPhone(p.phoneNumber()))) {
                        failCount.getAndIncrement();
                    }
                }
            }));
        });

        adders.forEach(Thread::start);
        removers.forEach(Thread::start);
        readers.forEach(Thread::start);

        adders.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        removers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        readers.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        assertEquals(0, failCount.get());
    }
}
