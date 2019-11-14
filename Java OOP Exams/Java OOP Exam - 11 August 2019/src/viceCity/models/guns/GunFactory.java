package viceCity.models.guns;

import java.lang.reflect.InvocationTargetException;

public class GunFactory {

    private static final String path = "viceCity.models.guns.";

    private GunFactory() {
    }

    public static Gun makeGun(String name,String type){
        try {
            Class<?> aClass = Class.forName(path + type);
            return (Gun)aClass.getDeclaredConstructor(String.class).newInstance(name);

        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }
}
