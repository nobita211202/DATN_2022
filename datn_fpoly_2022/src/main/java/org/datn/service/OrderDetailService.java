package org.datn.service;

import java.lang.reflect.Array;
import java.sql.SQLException;

public interface OrderDetailService {
    void orderByUserIdAndMultiCourse(Long userId, Long[] multiCourse) throws SQLException, ClassNotFoundException;
}
