package com.jfbrother.baseserver.jpa.generator;

import com.jfbrother.baseserver.utils.ReflectUtils;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * 自定义UUID生成器
 *
 * @author sevenlin
 */
public class CustomUUIDGenerator extends UUIDGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        Object id = null;
        try {
            id = ReflectUtils.getFiled(object, "id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (id != null) {
            return (Serializable) id;
        }
        return (Serializable) UUID.randomUUID().toString().replaceAll("-", "");
    }
}