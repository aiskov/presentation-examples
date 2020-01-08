package mixin;

public interface LocationAware {
    String[] getLocation();

    default boolean isIn(String... location) {
//        for (getLocation())
        return false;
    }
}
