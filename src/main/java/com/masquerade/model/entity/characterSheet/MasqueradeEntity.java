package com.masquerade.model.entity.characterSheet;

import java.beans.Transient;
import java.lang.reflect.Field;

public class MasqueradeEntity {

    @Transient
    public boolean isEmpty()  {
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if(!field.getName().equals("__$lineHits$__")) {
                    if(field.get(this) != null) {
                        return false;
                    }
                }
            } catch (Exception e) {
                return true;
            }
        }
        return true;
    }
}
