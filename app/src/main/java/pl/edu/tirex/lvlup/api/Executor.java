package pl.edu.tirex.lvlup.api;

@FunctionalInterface
public interface Executor<T, E extends Throwable>
{
    T execute() throws E;
}
