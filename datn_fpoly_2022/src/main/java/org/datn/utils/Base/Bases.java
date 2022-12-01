package org.datn.utils.Base;

import org.datn.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.Date;

@Service
public class Bases<T> {
    public static final String UPDATE="update";
    public static final String CREATE="create";

    @Autowired
    ModelMapper mapper;


    /**
     * @param entity Entity <b>Chuyền vào Entity để set mấy cái thuộc tính modifier,created... của Entity</b>
     * @param method int <p>Cái này là method UPDATE hoặc CREATE <b></b>Ví dụ UPDATE thì truyền BASE.UPDATE vào </p>
     * @return Entity
     */
    public T getBase(T entity, String method){
        try{
            ModifyBase modify= new ModifyBase();
            Date time=new  Date();
            if(method.equals(UPDATE)) {
                mapper.map(entity,modify);
                modify.setModifier("hoang");
                modify.setModified(time);
                mapper.map(modify, entity);
            }
            if(method.equals(CREATE)) {// để tạm thời vì chưa có login ADMIN
                modify.setCreator("hoang");
                modify.setCreated(time);
                mapper.map(modify, entity);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return entity;
    }

}
