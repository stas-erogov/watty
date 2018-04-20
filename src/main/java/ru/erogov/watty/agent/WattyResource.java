package ru.erogov.watty.agent;

@FunctionalInterface
public interface WattyResource<T> {
    T apply(String request);
}
