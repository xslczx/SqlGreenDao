package com.xslczx.sqlgreendao.db.greendao.converter;

import org.greenrobot.greendao.converter.PropertyConverter;

public class NoteTypeConverter
        implements PropertyConverter<TypeEnum, String> {
    @Override
    public TypeEnum convertToEntityProperty(String databaseValue) {
        return TypeEnum.valueOf(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(TypeEnum entityProperty) {
        return entityProperty.name();
    }
}
