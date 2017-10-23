package pl.edu.tirex.lvlup.api;

@FunctionalInterface
public interface Consumer<T>
{
    void accept(T argument);
}
