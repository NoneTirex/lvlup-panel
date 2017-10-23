package pl.edu.tirex.lvlup.api;

@FunctionalInterface
public interface IntExecutor<T, E extends Throwable>
{
    T execute(int arg) throws E;
}

