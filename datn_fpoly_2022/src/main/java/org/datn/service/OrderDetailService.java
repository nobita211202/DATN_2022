package org.datn.service;

import java.lang.reflect.Array;

public interface OrderDetailService {
    void orderByUserIdAndMultiCourse(Long userId, Array multiCourse);
}
