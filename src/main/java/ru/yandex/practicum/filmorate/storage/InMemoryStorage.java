package ru.yandex.practicum.filmorate.storage;

import ru.yandex.practicum.filmorate.config.Config;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.idObject;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.util.Collection;
import java.util.Map;

public abstract class InMemoryStorage<T extends idObject> implements Storage<T> {

    protected Map<Integer, T> map;

    protected int currentId = Config.ID_START_WITH;

    public boolean create(T obj) {
        if (obj.getId() == null) {
            obj.setId(currentId++);
        }
        if (map.containsKey(obj.getId())) {
            throw new KeyAlreadyExistsException();
        }
        map.put(obj.getId(), (T) obj);
        return true;
    }

    public T remove(int id) {
        return map.remove(id);
    }

    public T update(T obj) {
        if (!map.containsKey(obj.getId())) {
            throw new UserNotFoundException();
        }
        return map.put(obj.getId(), obj);
    }

    public Collection<T> getAll() {
        return map.values();
    }


    public T get(int id) {
        return map.get(id);
    }

    public int size() {
        return map.size();
    }

    public boolean containsId(int id) {
        return map.containsKey(id);
    }



}
