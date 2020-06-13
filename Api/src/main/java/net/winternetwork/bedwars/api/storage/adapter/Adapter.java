package net.winternetwork.bedwars.api.storage.adapter;

public interface Adapter<T, C> {

    T from(C c);

    C to(T t);
}
